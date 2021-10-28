package com.nttdata.foodorderingapp.model;

import java.util.List;

public class OrderAndDishesFromTable {
	private OrderFromTable order;
	private List<DishDetails> dishes;
	
	public OrderAndDishesFromTable(OrderFromTable order, List<DishDetails> dishes) {
		super();
		this.order = order;
		this.dishes = dishes;
	}

	public OrderFromTable getOrder() {
		return order;
	}

	public void setOrder(OrderFromTable order) {
		this.order = order;
	}

	public List<DishDetails> getDishes() {
		return dishes;
	}

	public void setDishes(List<DishDetails> dishes) {
		this.dishes = dishes;
	}
	
	
}
