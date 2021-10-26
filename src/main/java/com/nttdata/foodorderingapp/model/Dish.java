package com.nttdata.foodorderingapp.model;

public class Dish {
	private String dishName;
	private int qtyAvailable;
	private String pricePer;
	private String imageUrl;
	private String ingredients;
	
	public Dish(String dishName, int qtyAvailable, String pricePer, String imageUrl, String ingredients) {
		super();
		this.dishName = dishName;
		this.qtyAvailable = qtyAvailable;
		this.pricePer = pricePer;
		this.imageUrl = imageUrl;
		this.ingredients = ingredients;
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

	public String getPricePer() {
		return pricePer;
	}

	public void setPricePer(String pricePer) {
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
