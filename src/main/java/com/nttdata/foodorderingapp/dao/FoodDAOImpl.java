package com.nttdata.foodorderingapp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nttdata.foodorderingapp.model.Dish;
import com.nttdata.foodorderingapp.model.DishDetails;
import com.nttdata.foodorderingapp.model.MenuItem;
import com.nttdata.foodorderingapp.model.OrderFromTable;
import com.nttdata.foodorderingapp.model.OrderToInsert;
import com.nttdata.foodorderingapp.model.User;

public class FoodDAOImpl implements FoodDAO {
	private String dbUrl = "jdbc:mysql://localhost:3306/food_ordering_db";
	private String dbUser = "root";
	private String dbPass = "L7b#!x%3^KHUZJVG";
	
	public Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
		} catch (SQLException e) {
			System.out.println("Error getting connection: " + e.getMessage());
		}
		return connection;
	}
	
	
	// userId member of return object is -1 if no match
	@Override
	public User login(String user, String pass) {
		User result = new User(-1, "", "", false);
		try (Connection conn = getConnection();
				PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM Users WHERE Username = ? AND Pass = ?")) {
			pStmt.setString(1, user);
			pStmt.setString(2, pass);
			
			ResultSet rs = pStmt.executeQuery();
			
			if(rs.next()) {
				result = new User(rs.getInt("UserId"), rs.getString("Username"), rs.getString("Pass"), rs.getBoolean("IsAdmin"));
			}
			
			System.out.println("SELECT * FROM Users WHERE Username = " + user + " AND Pass = " + pass);
		} catch (SQLException e) {
			System.out.println("Error logging in: " + e.getMessage());
		}
		return result;
	}

	@Override
	public List<MenuItem> getAllDishesOnMenu() {
		List<MenuItem> result = new ArrayList<MenuItem>();
		try (Connection conn = getConnection();
				Statement stmt = conn.createStatement()) {

			ResultSet rs = stmt.executeQuery("SELECT * FROM Menu");
			
			while(rs.next()) {
				result.add(new MenuItem(new Dish(rs.getInt("DishId"), rs.getString("DishName"), rs.getFloat("PricePer"), rs.getString("ImageUrl"), rs.getString("Ingredients")), rs.getInt("QtyAvailable")));
			}
			
			System.out.println("SELECT * FROM Menu");
		} catch (SQLException e) {
			System.out.println("Error getting menu: " + e.getMessage());
		}
		return result;
	}
	
	@Override
	public List<Dish> getAllDishes() {
		List<Dish> result = new ArrayList<Dish>();
		try (Connection conn = getConnection();
				Statement stmt = conn.createStatement()) {

			ResultSet rs = stmt.executeQuery("SELECT * FROM Dishes");
			
			while(rs.next()) {
				result.add(new Dish(rs.getInt("DishId"), rs.getString("DishName"), rs.getFloat("PricePer"), rs.getString("ImageUrl"), rs.getString("Ingredients")));
			}
			
			System.out.println("SELECT * FROM Dishes");
		} catch (SQLException e) {
			System.out.println("Error getting dishes: " + e.getMessage());
		}
		return result;
	}

	@Override
	public int addDishToMenu(MenuItem menuItem) {
		int result = -1;
		try (Connection conn = getConnection();
				PreparedStatement pStmt = conn.prepareStatement("INSERT INTO Menu VALUES (?, ?, ?, ?, ?, ?)")) {
			pStmt.setInt(1, menuItem.getDish().getDishId());
			pStmt.setString(2, menuItem.getDish().getDishName());
			pStmt.setInt(3, menuItem.getQty());
			pStmt.setFloat(4, menuItem.getDish().getPricePer());
			pStmt.setString(5, menuItem.getDish().getImageUrl());
			pStmt.setString(6, menuItem.getDish().getIngredients());
			
			result = pStmt.executeUpdate();
			
			System.out.println("INSERT INTO Menu VALUES ("
									+ menuItem.getDish().getDishId() + ", "
									+ menuItem.getDish().getDishName() + ", "
									+ menuItem.getQty() + ", "
									+ menuItem.getDish().getPricePer() + ", "
									+ menuItem.getDish().getImageUrl() + ", "
									+ menuItem.getDish().getIngredients() + ")");
		} catch (SQLException e) {
			System.out.println("Error creating dish in Menu: " + e.getMessage());
		}
		return result;
	}

	@Override
	public int[] addDishToDishes(Dish dish) {
		int result = -1;
		int generatedKeys = -1;
		try (Connection conn = getConnection();
				PreparedStatement pStmt = conn.prepareStatement("INSERT INTO Dishes VALUES (NULL, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
			pStmt.setString(1, dish.getDishName());
			pStmt.setFloat(2, dish.getPricePer());
			pStmt.setString(3, dish.getImageUrl());
			pStmt.setString(4, dish.getIngredients());
			
			result = pStmt.executeUpdate();
			ResultSet rs = pStmt.getGeneratedKeys();
			
			if(rs.next()) {
				generatedKeys = (int)rs.getLong(1);
			}
			
			System.out.println("INSERT INTO Dishes VALUES ("
					+ dish.getDishName() + ", "
					+ dish.getPricePer() + ", "
					+ dish.getImageUrl() + ", "
					+ dish.getIngredients() + ")");
		} catch (SQLException e) {
			System.out.println("Error creating dish in Dishes: " + e.getMessage());
		}
		return new int[] {result, generatedKeys};
	}

	@Override
	public int clearMenu() {
		int result = -1;
		try (Connection conn = getConnection();
				Statement stmt = conn.createStatement()) {

			result = stmt.executeUpdate("DELETE FROM Menu");
			
			System.out.println("DELETE FROM Menu");
		} catch (SQLException e) {
			System.out.println("Error clearing menu: " + e.getMessage());
		}
		return result;
	}

	@Override
	public int[] addOrderToOrders(OrderToInsert order) {
		int result = -1;
		int generatedKeys = -1;
		try (Connection conn = getConnection();
				PreparedStatement pStmt = conn.prepareStatement("INSERT INTO Orders (DateOfOrder, Total, UserId) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
			pStmt.setDate(1, Date.valueOf(order.getDateOfOrder()));
			pStmt.setFloat(2, order.getTotal());
			pStmt.setInt(3, order.getUserId());
			
			result = pStmt.executeUpdate();
			ResultSet rs = pStmt.getGeneratedKeys();
			
			if(rs.next()) {
				generatedKeys = (int)rs.getLong(1);
			}
			
			System.out.println("INSERT INTO Orders (DateOfOrder, Total, UserId) VALUES ("
									+ Date.valueOf(order.getDateOfOrder()) + ", "
									+ order.getTotal() + ", "
									+ order.getUserId() + ")");
		} catch (SQLException e) {
			System.out.println("Error creating order in Orders: " + e.getMessage());
		}
		return new int[] {result, generatedKeys};
	}

	@Override
	public int[] addDishToOrderDetails(MenuItem menuItem, int orderId) {
		int result = -1;
		int generatedKeys = -1;
		try (Connection conn = getConnection();
				PreparedStatement pStmt = conn.prepareStatement("INSERT INTO OrderDetails (OrderId, DishId, Qty) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
			pStmt.setInt(1, orderId);
			pStmt.setInt(2, menuItem.getDish().getDishId());
			pStmt.setInt(3, menuItem.getQty());
			
			result = pStmt.executeUpdate();
			ResultSet rs = pStmt.getGeneratedKeys();
			
			if(rs.next()) {
				generatedKeys = (int)rs.getLong(1);
			}
			
			System.out.println("INSERT INTO OrderDetails (OrderId, DishId, Qty) VALUES ("
					+ orderId + ", "
					+ menuItem.getDish().getDishId() + ", "
					+ menuItem.getQty() + ")");
		} catch (SQLException e) {
			System.out.println("Error creating dish in OrderDetails: " + e.getMessage());
		}
		return new int[] {result, generatedKeys};
	}
	
	@Override
	public int reduceQtyOfDishAvailable(MenuItem menuItem) {
		int result = -1;
		try (Connection conn = getConnection();
				PreparedStatement pStmt = conn.prepareStatement("UPDATE Menu SET QtyAvailable = QtyAvailable - ? WHERE DishId = ?", Statement.RETURN_GENERATED_KEYS)) {
			pStmt.setInt(1, menuItem.getQty());
			pStmt.setInt(2, menuItem.getDish().getDishId());
			
			result = pStmt.executeUpdate();
			
			System.out.println("UPDATE Menu SET QtyAvailable = QtyAvailable - " + menuItem.getQty() + " WHERE DishId = " + menuItem.getDish().getDishId());
		} catch (SQLException e) {
			System.out.println("Error updating dish quantity in Menu: " + e.getMessage());
		}
		return result;
	}

	@Override
	public List<OrderFromTable> getOrdersWithUser(int userId) {
		List<OrderFromTable> result = new ArrayList<OrderFromTable>();
		try (Connection conn = getConnection();
				Statement stmt = conn.createStatement()) {

			ResultSet rs = stmt.executeQuery("SELECT * FROM Orders WHERE UserId = " + userId + " ORDER BY OrderId DESC");
			
			while(rs.next()) {
				result.add(new OrderFromTable(rs.getInt("OrderId"), rs.getDate("DateOfOrder").toLocalDate(), rs.getFloat("Total"), rs.getInt("UserId")));
			}
			
			System.out.println("SELECT * FROM Orders WHERE UserId = " + userId + " ORDER BY OrderId DESC");
		} catch (SQLException e) {
			System.out.println("Error getting orders by user: " + e.getMessage());
		}
		return result;
	}
	
	@Override
	public List<DishDetails> getDetailsWithOrder(int orderId) {
		List<DishDetails> result = new ArrayList<DishDetails>();
		try (Connection conn = getConnection();
				Statement stmt = conn.createStatement()) {

			ResultSet rs = stmt.executeQuery("SELECT * FROM OrderDetails JOIN Dishes ON OrderDetails.DishId = Dishes.DishId WHERE OrderId = " + orderId);
			
			while(rs.next()) {
				result.add(new DishDetails(rs.getInt("DishId"), rs.getString("DishName"), rs.getInt("Qty"), rs.getInt("PricePer"), rs.getString("ImageUrl"), rs.getString("Ingredients")));
			}
		} catch (SQLException e) {
			System.out.println("Error getting order details by order: " + e.getMessage());
		}
		return result;
	}
}
