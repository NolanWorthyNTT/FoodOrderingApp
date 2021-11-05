package com.nttdata.foodorderingapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.foodorderingapp.dao.FoodDAO;
import com.nttdata.foodorderingapp.dao.FoodDAOImpl;
import com.nttdata.foodorderingapp.model.Dish;
import com.nttdata.foodorderingapp.model.DishDetails;
import com.nttdata.foodorderingapp.model.MenuItem;
import com.nttdata.foodorderingapp.model.OrderAndDishes;
import com.nttdata.foodorderingapp.model.OrderAndDishesFromTable;
import com.nttdata.foodorderingapp.model.OrderFromTable;
import com.nttdata.foodorderingapp.model.User;

@CrossOrigin
@RestController
public class FoodOrderingController {
	
	FoodDAO foodDAO = new FoodDAOImpl();
	
	@PostMapping("/login")
	public User login(@RequestBody Map<String, String> json) {
		return foodDAO.login(json.get("username"), json.get("password"));
	}

	@GetMapping("/menu")
	public List<MenuItem> getAllDishesOnMenu() {
		return foodDAO.getAllDishesOnMenu();
	}
	
	@PostMapping("/uploadMenu")
	public void uploadMenu(@RequestBody List<MenuItem> dishList) {
		
		int[] addToDishesResult;
		foodDAO.clearMenu();
		
		for(MenuItem menuItem : dishList) {
			// dishId of -1 signifies a new dish - one not in Dishes table
			if(menuItem.getDish().getDishId() == -1) {
				addToDishesResult = foodDAO.addDishToDishes(menuItem.getDish());
				menuItem.getDish().setDishId(addToDishesResult[1]);
			}
			
			foodDAO.addDishToMenu(menuItem);
		}
	}
	
	@PostMapping("/placeOrder")
	public void placeOrder(@RequestBody OrderAndDishes oad) {
		int[] addOrderToOrdersResult;
		int[] addDishToOrderDetailsResult;
		int reduceQtyOfDishAvailableResult;
		
		addOrderToOrdersResult = foodDAO.addOrderToOrders(oad.getOrderToInsert());
		for(MenuItem menuItem : oad.getDishesInOrder()) {
			addDishToOrderDetailsResult = foodDAO.addDishToOrderDetails(menuItem, addOrderToOrdersResult[1]);
			reduceQtyOfDishAvailableResult = foodDAO.reduceQtyOfDishAvailable(menuItem);
		}
		
		
	}
	
	@GetMapping("/orders")
	public List<OrderAndDishesFromTable> getOrders(@RequestParam int id) {
		List<OrderAndDishesFromTable> result = new ArrayList<OrderAndDishesFromTable>();
		List<OrderFromTable> orders = foodDAO.getOrdersWithUser(id);
		
		for(OrderFromTable order : orders) {
			List<DishDetails> dishes = foodDAO.getDetailsWithOrder(order.getOrderId());
			result.add(new OrderAndDishesFromTable(order, dishes));
		}
		return result;
	}
	
	@GetMapping("/dishes")
	public List<Dish> getAllDishes() {
		return foodDAO.getAllDishes();
	}
}
