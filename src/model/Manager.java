package model;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Manager implements Serializable{
	
	private static final long serialVersionUID = 1;
	
	private final String MANAGER_FILE_NAME = "data/manager.mng";
	
	private ArrayList<Restaurant> restaurants;
	private ArrayList<Client> clients;
	private ArrayList<Product> products;
	private ArrayList<Delivery> deliveries;
	
	public Manager() {
		restaurants = new ArrayList<>();
		clients = new ArrayList<>();
		products = new ArrayList<>();
		deliveries = new ArrayList<>();
	}
	
	public void saveManager() throws IOException{
		ObjectOutputStream oStream = new ObjectOutputStream(new FileOutputStream(MANAGER_FILE_NAME));
		oStream.writeObject(restaurants);
		oStream.writeObject(clients);
		oStream.writeObject(products);
		oStream.writeObject(deliveries);
		oStream.close();
	}
	
	@SuppressWarnings("unchecked")
	public void loadManager() throws IOException, ClassNotFoundException {
		ObjectInputStream oInStream = new ObjectInputStream(new FileInputStream(MANAGER_FILE_NAME));
		restaurants = (ArrayList <Restaurant>) oInStream.readObject();
		clients = (ArrayList <Client>) oInStream.readObject();
		products = (ArrayList <Product>) oInStream.readObject();
		deliveries = (ArrayList <Delivery>) oInStream.readObject();
		oInStream.close();
	}
	
	public ArrayList<Restaurant> getRestaurants() {
		return restaurants;
	}
	
	public Restaurant getRestaurant (String resNit) {
		Restaurant restaurant = new Restaurant();
		for (int i = 0; i < restaurants.size(); i++) {
			if (restaurants.get(i).getNit().equals(resNit)) {
				restaurant = restaurants.get(i);
			}
		}
		
		return restaurant;
	}
	
	public void addRestaurant(Restaurant restaurant) {
		restaurants.add( restaurant );
		
	}

	public void addProduct(Product product) {
		products.add(product);
		
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
	
	public ArrayList<Product> getProducts(){
		return products;
	}
	
	public void AddProduct(Product product) {
		products.add(product);
	}


	public ArrayList<Delivery> getDeliveries() {
		return deliveries;
	}
	
	public void addDelivery(Delivery delivery) {
		deliveries.add(delivery);
	}
	
	
}
