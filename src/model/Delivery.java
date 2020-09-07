package model;

import java.sql.Date;


public class Delivery {
	private final static int  REQUESTED = 0 ; 
	private final static int  IN_PROCESS = 1 ; 
	private final static int  SENT = 2 ; 
	private final static int  DELIVERED = 3 ; 
	private String orderState;
	private int deliveryCode;
	private Date dateAndTime;
	private int clientId;
	private String restaurantNit;
	private Product[] products;
	private int[] quantities;
	
	public Delivery(int clientId, String restaurantNit, Product[] products, int[] quantities) {
		this.clientId = clientId;
		this.restaurantNit = restaurantNit;
		this.products = products;
		this.quantities = quantities;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(int orderStateNumber) {
		switch (orderStateNumber) {
		case REQUESTED:
			orderState = "Requested";
			break;
			
		case IN_PROCESS:
			orderState = "In process";
			break;

		case SENT:
			orderState = "Sent";
			break;
			
		case DELIVERED:
			orderState = "Delivered";
			break;
		}
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
