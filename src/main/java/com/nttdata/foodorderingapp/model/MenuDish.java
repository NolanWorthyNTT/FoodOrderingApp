package com.nttdata.foodorderingapp.model;

public class MenuDish {
	DishFromDishes dish;
	private int qty;
	
	public MenuDish(int dishId, String dishName, float pricePer, String imageUrl, String ingredients, int qty) {
		super();
		dish = new DishFromDishes(dishId, dishName, pricePer, imageUrl, ingredients);
		this.qty = qty;
	}
	
	public MenuDish(DishFromDishes dish, int qty) {
		super();
		this.dish = dish;
		this.qty = qty;
	}
	
	public MenuDish () {};
	
	public DishFromDishes getDish() {
		return dish;
	}

	public void setDish(DishFromDishes dish) {
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
