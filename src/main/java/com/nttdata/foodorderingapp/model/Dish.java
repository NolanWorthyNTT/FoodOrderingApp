package com.nttdata.foodorderingapp.model;

public class Dish {
	private int dishId;
	private String dishName;
	private int qtyAvailable;
	private float pricePer;
	private String imageUrl;
	private String ingredients;
	
	public Dish(int dishId, String dishName, int qtyAvailable, float pricePer, String imageUrl, String ingredients) {
		super();
		this.dishId = dishId;
		this.dishName = dishName;
		this.qtyAvailable = qtyAvailable;
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
	
	public int getQtyAvailable() {
		return qtyAvailable;
	}
	
	public void setQtyAvailable(int qtyAvailable) {
		this.qtyAvailable = qtyAvailable;
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
