package com.nttdata.foodorderingapp.model;

import java.util.List;

public class OrderAndDishes {
	private Order order;
	private List<Dish> dishesInOrder;
	
	public OrderAndDishes(Order order, List<Dish> dishesInOrder) {
		super();
		this.order = order;
		this.dishesInOrder = dishesInOrder;
	}
	
	public Order getOrder() {
		return order;
	}
	
	public void setOrder(Order order) {
		this.order = order;
	}
	
	public List<Dish> getDishesInOrder() {
		return dishesInOrder;
	}
	
	public void setDishesInOrder(List<Dish> dishesInOrder) {
		this.dishesInOrder = dishesInOrder;
	}
	
	
}
