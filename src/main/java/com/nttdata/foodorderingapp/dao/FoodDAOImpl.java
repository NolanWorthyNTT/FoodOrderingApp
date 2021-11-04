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
import com.nttdata.foodorderingapp.model.DishFromDishes;
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
	public List<Dish> getAllDishesOnMenu() {
		List<Dish> result = new ArrayList<Dish>();
		try (Connection conn = getConnection();
				Statement stmt = conn.createStatement()) {

			ResultSet rs = stmt.executeQuery("SELECT * FROM Menu");
			
			while(rs.next()) {
				result.add(new Dish(rs.getInt("DishId"), rs.getString("DishName"), rs.getInt("QtyAvailable"), rs.getFloat("PricePer"), rs.getString("ImageUrl"), rs.getString("Ingredients")));
			}
			
			System.out.println("SELECT * FROM Menu");
		} catch (SQLException e) {
			System.out.println("Error getting menu: " + e.getMessage());
		}
		return result;
	}
	
	@Override
	public List<DishFromDishes> getAllDishes() {
		List<DishFromDishes> result = new ArrayList<DishFromDishes>();
		try (Connection conn = getConnection();
				Statement stmt = conn.createStatement()) {

			ResultSet rs = stmt.executeQuery("SELECT * FROM Dishes");
			
			while(rs.next()) {
				result.add(new DishFromDishes(rs.getInt("DishId"), rs.getString("DishName"), rs.getFloat("PricePer"), rs.getString("ImageUrl"), rs.getString("Ingredients")));
			}
			
			System.out.println("SELECT * FROM Dishes");
		} catch (SQLException e) {
			System.out.println("Error getting dishes: " + e.getMessage());
		}
		return result;
	}

	@Override
	public int addDishToMenu(Dish dish) {
		int result = -1;
		try (Connection conn = getConnection();
				PreparedStatement pStmt = conn.prepareStatement("INSERT INTO Menu VALUES (?, ?, ?, ?, ?, ?)")) {
			pStmt.setInt(1, dish.getDishId());
			pStmt.setString(2, dish.getDishName());
			pStmt.setInt(3, dish.getQty());
			pStmt.setFloat(4, dish.getPricePer());
			pStmt.setString(5, dish.getImageUrl());
			pStmt.setString(6, dish.getIngredients());
			
			result = pStmt.executeUpdate();
			
			System.out.println("INSERT INTO Menu VALUES ("
									+ dish.getDishId() + ", "
									+ dish.getDishName() + ", "
									+ dish.getQty() + ", "
									+ dish.getPricePer() + ", "
									+ dish.getImageUrl() + ", "
									+ dish.getIngredients() + ")");
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
	public int[] addDishToOrderDetails(Dish dish, int orderId) {
		int result = -1;
		int generatedKeys = -1;
		try (Connection conn = getConnection();
				PreparedStatement pStmt = conn.prepareStatement("INSERT INTO OrderDetails (OrderId, DishId, Qty) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
			pStmt.setInt(1, orderId);
			pStmt.setInt(2, dish.getDishId());
			pStmt.setInt(3, dish.getQty());
			
			result = pStmt.executeUpdate();
			ResultSet rs = pStmt.getGeneratedKeys();
			
			if(rs.next()) {
				generatedKeys = (int)rs.getLong(1);
			}
			
			System.out.println("INSERT INTO OrderDetails (OrderId, DishId, Qty) VALUES ("
					+ orderId + ", "
					+ dish.getDishId() + ", "
					+ dish.getQty() + ")");
		} catch (SQLException e) {
			System.out.println("Error creating dish in OrderDetails: " + e.getMessage());
		}
		return new int[] {result, generatedKeys};
	}
	
	@Override
	public int reduceQtyOfDishAvailable(Dish dish) {
		int result = -1;
		try (Connection conn = getConnection();
				PreparedStatement pStmt = conn.prepareStatement("UPDATE Menu SET QtyAvailable = QtyAvailable - ? WHERE DishId = ?", Statement.RETURN_GENERATED_KEYS)) {
			pStmt.setInt(1, dish.getQty());
			pStmt.setInt(2, dish.getDishId());
			
			result = pStmt.executeUpdate();
			
			System.out.println("UPDATE Menu SET QtyAvailable = QtyAvailable - " + dish.getQty() + " WHERE DishId = " + dish.getDishId());
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
