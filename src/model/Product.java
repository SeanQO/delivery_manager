package model;
import java.util.Date;

public class Product {
	private String code;
	private String name;
	private String description;
	private double price;
	private String restaurantNit;
	
	public Product( String name, String description, double price, String restaurantNit) {
		Date date = new Date();
		code = String.valueOf("P-" + date.getTime());
		this.name = name;
		this.description = description;
		this.price = price;
		this.restaurantNit = restaurantNit;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public double getPrice() {
		return price;
	}

	public String getRestaurantNit() {
		return restaurantNit;
	}

	@Override
	public String toString() {
		return "Product [code=" + code + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", restaurantNit=" + restaurantNit + "]";
	}
	
	
	

}
