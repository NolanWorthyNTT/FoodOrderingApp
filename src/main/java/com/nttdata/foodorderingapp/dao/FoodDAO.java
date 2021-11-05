package com.nttdata.foodorderingapp.dao;

import java.util.List;

import com.nttdata.foodorderingapp.model.Dish;
import com.nttdata.foodorderingapp.model.DishDetails;
import com.nttdata.foodorderingapp.model.MenuItem;
import com.nttdata.foodorderingapp.model.OrderFromTable;
import com.nttdata.foodorderingapp.model.OrderToInsert;
import com.nttdata.foodorderingapp.model.User;

public interface FoodDAO {
	public User login(String user, String pass);
	public List<MenuItem> getAllDishesOnMenu();
	public List<Dish> getAllDishes();
	public int addDishToMenu(MenuItem menuItem);
	public int[] addDishToDishes(Dish dish);
	public int clearMenu();
	public int[] addOrderToOrders(OrderToInsert order);
	public int[] addDishToOrderDetails(MenuItem menuItem, int orderId);
	public int reduceQtyOfDishAvailable(MenuItem menuItem);
	public List<OrderFromTable> getOrdersWithUser(int userId);
	public List<DishDetails> getDetailsWithOrder(int orderId);
}
