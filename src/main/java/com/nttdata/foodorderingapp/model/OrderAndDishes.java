package com.nttdata.foodorderingapp.model;

import java.util.List;

public class OrderAndDishes {
	private OrderToInsert order;
	private List<MenuDish> dishesInOrder;
	
	public OrderAndDishes(OrderToInsert order, List<MenuDish> dishesInOrder) {
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
	
	public List<MenuDish> getDishesInOrder() {
		return dishesInOrder;
	}
	
	public void setDishesInOrder(List<MenuDish> dishesInOrder) {
		this.dishesInOrder = dishesInOrder;
	}
	
	
}
