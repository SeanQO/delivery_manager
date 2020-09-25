package model;
import java.util.ArrayList;

public class Manager {
	private ArrayList<Restaurant> restaurants;
	private ArrayList<Product> products;
	private ArrayList<Client> clients;
	private ArrayList<Delivery> deliveries;
	
	public Manager() {
		restaurants = new ArrayList<>();
		products = new ArrayList<>();
		clients = new ArrayList<>();
	}
	
	public ArrayList<Restaurant> getRestaurants() {
		return restaurants;
	}
	
	public Restaurant getRestaurant (String resNit) {
		Object restaurant = new Object();
		for (int i = 0; i < restaurants.size(); i++) {
			if (restaurants.get(i).getNit() == resNit) {
				restaurant = restaurants.get(i);
			}
		}
		
		return (Restaurant)restaurant;
	}
	
	public void addRestaurant(Restaurant restaurant) {
		restaurants.add( restaurant );
		
	}
	
	public ArrayList<Product> getProducts() {
		return products;
	}
	
	public void addProduct(Product product, String resNit) {
		products.add(product);
		Restaurant restaurant = getRestaurant(resNit);
		restaurant.AddProduct(product);
		
	}
	
	public boolean usedNit(String nit) {
		boolean usedNit = false;

		for (int i = 0; i < restaurants.size() && !usedNit ; i++){
			if ( restaurants.get(i).getNit().equals(nit) ) {
				usedNit = true;
			}
		}
		
		return usedNit;
	}
	
	public ArrayList<Client> getClients(){
		return clients;
	}
	
	public void addClients(Client client) {
		clients.add(client);
	}
	
	public boolean registeredId(String id) {
		boolean registeredId = false;
		
		for (int i = 0; i < clients.size() && !registeredId; i++){
			if ( clients.get(i).getIdNumber().equals(id) ) {
				registeredId = true;
			}
		}
		
		return registeredId;		
	}

	public ArrayList<Delivery> getDeliveries() {
		return deliveries;
	}
	
	public void addDelivery(Delivery delivery) {
		deliveries.add(delivery);
	}
	
	
}
