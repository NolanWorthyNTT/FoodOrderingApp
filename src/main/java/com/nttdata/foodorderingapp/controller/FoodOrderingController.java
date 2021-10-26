package com.nttdata.foodorderingapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.foodorderingapp.dao.FoodDAO;
import com.nttdata.foodorderingapp.dao.FoodDAOImpl;
import com.nttdata.foodorderingapp.model.Dish;

@RestController
public class FoodOrderingController {
	
	FoodDAO foodDAO = new FoodDAOImpl();
	
	@PostMapping("/login")
	public String login(@RequestBody Map<String, String> json) {
		return foodDAO.login(json.get("username"), json.get("password"));
	}
	
	@GetMapping("/menu")
	public List<Dish> getAllDishesOnMenu() {
		return foodDAO.getAllDishesOnMenu();
	}
}
