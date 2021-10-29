package com.nttdata.foodorderingapp.model;

public class DishFromDishes {
	private int dishId;
	private String dishName;
	private float pricePer;
	private String imageUrl;
	private String ingredients;
	
	public DishFromDishes(int dishId, String dishName, float pricePer, String imageUrl, String ingredients) {
		super();
		this.dishId = dishId;
		this.dishName = dishName;
		this.pricePer = pricePer;
		this.imageUrl = imageUrl;
		this.ingredients = ingredients;
	}

	public int getDishId() {
		return dishId;
	}
	
	public void setDishId(int dishId) {
		this.dishId = dishId;
	}
	
	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public float getPricePer() {
		return pricePer;
	}

	public void setPricePer(float pricePer) {
		this.pricePer = pricePer;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}
}
