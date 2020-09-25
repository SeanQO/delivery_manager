package model;

import java.util.ArrayList;
public class Restaurant {
	private String name;
	private String nit;
	private String adminName;
	private ArrayList<Product> products;

	public Restaurant(String name, String nit, String adminName) {
		this.name = name;
		this.nit = nit;
		this.adminName = adminName;
		products = new ArrayList<>();
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getName() {
		return name;
	}

	public String getNit() {
		return nit;
	}

	public String getAdminName() {
		return adminName;
	}
	
	public ArrayList<Product> getProducts(){
		return products;
	}
	
	public void AddProduct(Product product) {
		products.add(product);
	}

	@Override
	public String toString() {
		return "Restaurant [name=" + name + ", nit=" + nit + ", adminName=" + adminName + "]";
	}

	
	
}
