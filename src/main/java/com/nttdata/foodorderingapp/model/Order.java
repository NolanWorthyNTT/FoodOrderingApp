package com.nttdata.foodorderingapp.model;

import java.time.LocalDate;

public class Order {
	private LocalDate dateOfOrder;
	private float total;
	private int userId;
	
	public Order(LocalDate dateOfOrder, float total, int userId) {
		super();
		this.dateOfOrder = dateOfOrder;
		this.total = total;
		this.userId = userId;
	}

	public LocalDate getDateOfOrder() {
		return dateOfOrder;
	}

	public void setDateOfOrder(LocalDate dateOfOrder) {
		this.dateOfOrder = dateOfOrder;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
