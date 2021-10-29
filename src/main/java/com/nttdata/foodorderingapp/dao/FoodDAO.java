package com.nttdata.foodorderingapp.dao;

import java.util.List;

import com.nttdata.foodorderingapp.model.Dish;
import com.nttdata.foodorderingapp.model.DishDetails;
import com.nttdata.foodorderingapp.model.DishFromDishes;
import com.nttdata.foodorderingapp.model.OrderFromTable;
import com.nttdata.foodorderingapp.model.OrderToInsert;
import com.nttdata.foodorderingapp.model.User;

public interface FoodDAO {
	public User login(String user, String pass);
	public List<Dish> getAllDishesOnMenu();
	public List<DishFromDishes> getAllDishes();
	public int addDishToMenu(Dish dish);
	public int[] addDishToDishes(Dish dish);
	public int clearMenu();
	public int[] addOrderToOrders(OrderToInsert order);
	public int[] addDishToOrderDetails(Dish dish, int orderId);
	public int reduceQtyOfDishAvailable(Dish dish);
	public List<OrderFromTable> getOrdersWithUser(int userId);
	public List<DishDetails> getDetailsWithOrder(int orderId);
}
