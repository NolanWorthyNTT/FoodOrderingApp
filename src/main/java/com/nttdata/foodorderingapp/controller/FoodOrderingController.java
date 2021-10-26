package com.nttdata.foodorderingapp.controller;

import java.util.Collections;
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

@CrossOrigin
@RestController
public class FoodOrderingController {
	
	FoodDAO foodDAO = new FoodDAOImpl();
	
	@PostMapping("/login")
	public Map<String, String> login(@RequestBody Map<String, String> json) {
		String response = foodDAO.login(json.get("username"), json.get("password"));
		return Collections.singletonMap("response", response);
	}

	@GetMapping("/menu")
	public List<Dish> getAllDishesOnMenu() {
		return foodDAO.getAllDishesOnMenu();
	}
}
