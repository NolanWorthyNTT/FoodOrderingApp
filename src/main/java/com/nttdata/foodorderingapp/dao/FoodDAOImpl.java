package com.nttdata.foodorderingapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.jdbc.core.JdbcTemplate;

import com.nttdata.foodorderingapp.model.Dish;
import com.nttdata.foodorderingapp.model.Order;

public class FoodDAOImpl implements FoodDAO {
	//@Value("${spring.datasource.url}")
	private String dbUrl = "jdbc:mysql://localhost:3306/food_ordering_db";
	
	//@Value("${spring.datasource.username}")
	private String dbUser = "root";
	
	//@Value("${spring.datasource.password}")
	private String dbPass = "L7b#!x%3^KHUZJVG";

	//@Autowired
	//JdbcTemplate jdbcTemplate;
	
	public Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
		} catch (SQLException e) {
			System.out.println("Couldn't connect to DB: " + e.getMessage());
		}
		return connection;
	}
	
	
	// returns "user" if user role, "admin" if admin role, "no user" if user doesn't exist
	@Override
	public String login(String user, String pass) {
		String result = "no user";
		try (Connection conn = getConnection();
				PreparedStatement pStmt = conn.prepareStatement("SELECT IsAdmin FROM Users WHERE Username = ? AND Pass = ?")) {
			pStmt.setString(1, user);
			pStmt.setString(2, pass);
			
			ResultSet rs = pStmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getBoolean("IsAdmin")) {
					result = "admin";
				} else {
					result = "user";
				}
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
				result.add(new Dish(rs.getInt("DishId"), rs.getString("DishName"), rs.getInt("QtyAvailable"), rs.getString("PricePer"), rs.getString("ImageUrl"), rs.getString("Ingredients")));
			}
		} catch (SQLException e) {
			System.out.println("Error logging in: " + e.getMessage());
		}
		return result;
	}

	@Override
	public void addDishToMenu(Dish dish) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addDishToDishes(Dish dish) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteDishFromMenu(Dish dish) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int addOrderToOrders(Order order) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void addDishesToOrderDetails(List<Order> orders) {
		// TODO Auto-generated method stub
		
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
