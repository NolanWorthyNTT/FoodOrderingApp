package com.nttdata.foodorderingapp.dao;

import java.util.List;

import com.nttdata.foodorderingapp.model.Dish;
import com.nttdata.foodorderingapp.model.Order;

public interface FoodDAO {
	public String login(String user, String pass);
	public List<Dish> getAllDishes();
	public void addDishToMenu(Dish dish);
	public void addDishToDishes(Dish dish);
	public void deleteDishFromMenu(Dish dish);
	public int addOrderToOrders(Order order);
	public void addDishesToOrderDetails(List<Order> orders);
	public List<Order> getOrdersWithUser(int userId);
	public List<Order> searchOrders(int userId, String partialOrderId);
}
