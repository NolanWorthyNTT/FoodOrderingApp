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
import com.nttdata.foodorderingapp.model.Order;
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
			System.out.println("Couldn't connect to DB: " + e.getMessage());
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
		} catch (SQLException e) {
			System.out.println("Error logging in: " + e.getMessage());
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
		} catch (SQLException e) {
			System.out.println("Error logging in: " + e.getMessage());
		}
		return result;
	}

	@Override
	public int[] addDishToDishes(Dish dish) {
		int result = -1;
		int generatedKeys = -1;
		try (Connection conn = getConnection();
				PreparedStatement pStmt = conn.prepareStatement("INSERT INTO Dishes VALUES (NULL, ?, ?, ?, ?)")) {
			pStmt.setString(1, dish.getDishName());
			pStmt.setFloat(2, dish.getPricePer());
			pStmt.setString(3, dish.getImageUrl());
			pStmt.setString(4, dish.getIngredients());
			
			result = pStmt.executeUpdate();
			ResultSet rs = pStmt.getGeneratedKeys();
			
			if(rs.next()) {
				generatedKeys = (int)rs.getLong(1);
			}
		} catch (SQLException e) {
			System.out.println("Error logging in: " + e.getMessage());
		}
		return new int[] {result, generatedKeys};
	}

	@Override
	public void deleteDishFromMenu(Dish dish) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void purchaseDish(Dish dish) {
		// decrement quantity
	}

	@Override
	public int[] addOrderToOrders(Order order) {
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
		} catch (SQLException e) {
			System.out.println("Error logging in: " + e.getMessage());
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
			System.out.println(dish.getQty());
			
			result = pStmt.executeUpdate();
			ResultSet rs = pStmt.getGeneratedKeys();
			
			if(rs.next()) {
				generatedKeys = (int)rs.getLong(1);
			}
		} catch (SQLException e) {
			System.out.println("Error logging in: " + e.getMessage());
		}
		return new int[] {result, generatedKeys};
	}

	@Override
	public List<Order> getOrdersWithUser(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> searchOrders(int userId, String partialOrderId) {
		// TODO Auto-generated method stub
		return null;
	}

}
