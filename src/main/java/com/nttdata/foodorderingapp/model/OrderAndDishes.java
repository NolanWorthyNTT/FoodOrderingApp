package com.nttdata.foodorderingapp.model;

import java.util.List;

public class OrderAndDishes {
	private OrderToInsert order;
	private List<Dish> dishesInOrder;
	
	public OrderAndDishes(OrderToInsert order, List<Dish> dishesInOrder) {
		super();
		this.order = order;
		this.dishesInOrder = dishesInOrder;
	}
	
	public OrderToInsert getOrderToInsert() {
		return order;
	}
	
	public void setOrderToInsert(OrderToInsert order) {
		this.order = order;
	}
	
	public List<Dish> getDishesInOrder() {
		return dishesInOrder;
	}
	
	public void setDishesInOrder(List<Dish> dishesInOrder) {
		this.dishesInOrder = dishesInOrder;
	}
	
	
}
