package com.nttdata.foodorderingapp.model;

public class DishDetails {
	private int dishId;
	private String dishName;
	private int qty;
	private int pricePer;
	private String imageUrl;
	private String ingredients;
	
	public DishDetails(int dishId, String dishName, int qty, int pricePer, String imageUrl, String ingredients) {
		super();
		this.dishId = dishId;
		this.dishName = dishName;
		this.qty = qty;
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

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getPricePer() {
		return pricePer;
	}

	public void setPricePer(int pricePer) {
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
