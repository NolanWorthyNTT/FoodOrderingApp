package com.nttdata.foodorderingapp.model;

public class MenuItem {
	Dish dish;
	private int qty;
	
	public MenuItem(int dishId, String dishName, float pricePer, String imageUrl, String ingredients, int qty) {
		super();
		dish = new Dish(dishId, dishName, pricePer, imageUrl, ingredients);
		this.qty = qty;
	}
	
	public MenuItem(Dish dish, int qty) {
		super();
		this.dish = dish;
		this.qty = qty;
	}
	
	public MenuItem () {};
	
	public Dish getDish() {
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}
	
	public int getQty() {
		return qty;
	}
	
	public void setQty(int qty) {
		this.qty = qty;
	}
	
	@Override
	public String toString() {
		return "dishID " + dish.getDishId();
	}
}
