package com.nttdata.foodorderingapp.dao;

import java.util.List;

import com.nttdata.foodorderingapp.model.Dish;
import com.nttdata.foodorderingapp.model.Order;
import com.nttdata.foodorderingapp.model.User;

public interface FoodDAO {
	public User login(String user, String pass);
	public List<Dish> getAllDishesOnMenu();
	public int addDishToMenu(Dish dish);
	public int[] addDishToDishes(Dish dish);
	public void deleteDishFromMenu(Dish dish);
	public void purchaseDish(Dish dish);
	public int[] addOrderToOrders(Order order);
	public int[] addDishToOrderDetails(Dish dish, int orderId);
	public List<Order> getOrdersWithUser(int userId);
	public List<Order> searchOrders(int userId, String partialOrderId);
}
