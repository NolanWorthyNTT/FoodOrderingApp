package com.nttdata.foodorderingapp.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.foodorderingapp.dao.FoodDAO;
import com.nttdata.foodorderingapp.dao.FoodDAOImpl;

@RestController
public class FoodOrderingController {
	
	FoodDAO foodDAO = new FoodDAOImpl();
	
	@PostMapping("/login")
	public String login(@RequestBody Map<String, String> json) {
		return foodDAO.login(json.get("username"), json.get("password"));
	}
}
