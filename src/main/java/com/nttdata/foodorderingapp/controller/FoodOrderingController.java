package com.nttdata.foodorderingapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.foodorderingapp.dao.FoodDAO;
import com.nttdata.foodorderingapp.dao.FoodDAOImpl;
import com.nttdata.foodorderingapp.model.Dish;
import com.nttdata.foodorderingapp.model.Order;
import com.nttdata.foodorderingapp.model.OrderAndDishes;
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
	public List<Dish> getAllDishesOnMenu() {
		return foodDAO.getAllDishesOnMenu();
	}
	
	@PostMapping("/uploadMenu")
	public void uploadMenu(@RequestBody List<Dish> dishList) {
		int[] addToDishesResult;
		for(Dish dish : dishList) {
			addToDishesResult = foodDAO.addDishToDishes(dish);
			dish.setDishId(addToDishesResult[1]);
			foodDAO.addDishToMenu(dish);
		}
	}
	
	@PostMapping("/placeOrder")
	public void placeOrder(@RequestBody OrderAndDishes oad) {
		int[] addOrderToOrdersResult;
		int[] addDishToOrderDetailsResult;
		
		addOrderToOrdersResult = foodDAO.addOrderToOrders(oad.getOrder());
		for(Dish dish : oad.getDishesInOrder()) {
			addDishToOrderDetailsResult = foodDAO.addDishToOrderDetails(dish, addOrderToOrdersResult[1]);
		}
	}
}
