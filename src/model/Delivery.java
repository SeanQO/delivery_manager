package model;

import java.sql.Date;

public class Delivery {
	private int deliveryCode;
	private Date dateAndTime;
	private int clientId;
	private String restaurantNit;
	private Product[] products;
	private int[] quantities;
	
	public Delivery(int clientId, String restaurantNit, Product[] products, int[] quantities) {
		super();
		this.clientId = clientId;
		this.restaurantNit = restaurantNit;
		this.products = products;
		this.quantities = quantities;
	}

	public void setProducts(Product[] products) {
		this.products = products;
	}

	public void setQuantities(int[] quantities) {
		this.quantities = quantities;
	}

	public int getDeliveryCode() {
		return deliveryCode;
	}

	public Date getDateAndTime() {
		return dateAndTime;
	}

	public int getClientId() {
		return clientId;
	}

	public String getRestaurantNit() {
		return restaurantNit;
	}

	public Product[] getProducts() {
		return products;
	}

	public int[] getQuantities() {
		return quantities;
	}
	
	
	
}
