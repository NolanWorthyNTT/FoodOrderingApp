package com.nttdata.foodorderingapp.model;

import java.util.List;

public class OrderAndDishes {
	private OrderToInsert order;
	private List<MenuItem> dishesInOrder;
	
	public OrderAndDishes(OrderToInsert order, List<MenuItem> dishesInOrder) {
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
	
	public List<MenuItem> getDishesInOrder() {
		return dishesInOrder;
	}
	
	public void setDishesInOrder(List<MenuItem> dishesInOrder) {
		this.dishesInOrder = dishesInOrder;
	}
	
	@Override
	public String toString() {
		return "ORDER: " + order + ", DISHES: " + dishesInOrder;
	}
}
