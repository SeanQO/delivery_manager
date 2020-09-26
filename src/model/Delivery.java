package model;

import java.util.Date;
import java.io.Serializable;
import java.util.ArrayList;
public class Delivery implements Serializable{
	
	private static final long serialVersionUID = 1;
	
	private OrderState orderState;
	private String deliveryCode;
	private Date date;
	private String clientId; 
	private String restaurantNit;
	private ArrayList<Product> products;
	private ArrayList<Integer> quantities;
	
	public Delivery(String clientId, String restaurantNit, ArrayList<Product> products, ArrayList<Integer> quantities) {
		this.clientId = clientId;
		this.restaurantNit = restaurantNit;
		this.products = products;
		this.quantities = quantities;
		date = new Date(); 
		deliveryCode = String.valueOf("D-" + date.getTime());
	}

	public String getOrderState() {
		return orderState.toString() ;
	}

	public void setOrderState(int orderStateNum) {
		switch (orderStateNum) {
		case 0:
			orderState = OrderState.valueOf( "REQUESTED" );
			break;
			
		case 1:
			orderState = OrderState.valueOf( "IN_PROCESS" );
			break;

		case 2:
			orderState = OrderState.valueOf( "SENT" );
			break;
			
		case 3:
			orderState = OrderState.valueOf( "DELIVERED" );
			break;
		}
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

	public String getDeliveryCode() {
		return deliveryCode;
	}

	public String getDateAndTime() {
		return date.toString();
	}

	public String getClientId() {
		return clientId;
	}

	public String getRestaurantNit() {
		return restaurantNit;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	@Override
	public String toString() {
		return "Delivery [orderState=" + orderState + ", deliveryCode=" + deliveryCode + ", dateAndTime=" + date
				+ ", clientId=" + clientId + ", restaurantNit=" + restaurantNit + ", products=" + products
				+ ", quantities=" + quantities + "]";
	}

	
	
}
