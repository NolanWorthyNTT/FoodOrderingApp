package com.nttdata.foodorderingapp.dao;

import java.util.List;

import com.nttdata.foodorderingapp.model.DishDetails;
import com.nttdata.foodorderingapp.model.DishFromDishes;
import com.nttdata.foodorderingapp.model.MenuDish;
import com.nttdata.foodorderingapp.model.OrderFromTable;
import com.nttdata.foodorderingapp.model.OrderToInsert;
import com.nttdata.foodorderingapp.model.User;

public interface FoodDAO {
	public User login(String user, String pass);
	public List<MenuDish> getAllDishesOnMenu();
	public List<DishFromDishes> getAllDishes();
	public int addDishToMenu(MenuDish dish);
	public int[] addDishToDishes(MenuDish dish);
	public int clearMenu();
	public int[] addOrderToOrders(OrderToInsert order);
	public int[] addDishToOrderDetails(MenuDish dish, int orderId);
	public int reduceQtyOfDishAvailable(MenuDish dish);
	public List<OrderFromTable> getOrdersWithUser(int userId);
	public List<DishDetails> getDetailsWithOrder(int orderId);
}
