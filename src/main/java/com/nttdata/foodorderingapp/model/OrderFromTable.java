package com.nttdata.foodorderingapp.model;

import java.time.LocalDate;

public class OrderFromTable {
	private int orderId;
	private LocalDate dateOfOrder;
	private float total;
	private int userId;

	public OrderFromTable(int orderId, LocalDate dateOfOrder, float total, int userId) {
		super();
		this.orderId = orderId;
		this.dateOfOrder = dateOfOrder;
		this.total = total;
		this.userId = userId;
	}

	public int getOrderId() {
		return orderId;
	}


	public void setOrderId(int orderId) {
		this.orderId = orderId;
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
