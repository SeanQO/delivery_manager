package model;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

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
	
	public void exportDeliveryReport(String fileName, String separator) throws FileNotFoundException {
		PrintWriter pWriter = new PrintWriter(fileName);
		
		pWriter.println("[DelyveryCode]" + separator + "[delivery date and time]"  + separator + "[Delivery State]" + separator + 
						"[Restaurant Name]" + separator + "[Restaurant Nit]" + separator +
						"[Client Name]" + separator + "[Client id]"  + separator + "[Client address]" + separator + 
						"[Product Number]" + separator + "[Product Name]" + separator + "[Product price]" + separator + "[Product code]" );
		
		
		for (Delivery delivery : deliveries) {
			
			//delivery
			pWriter.print(delivery.getDeliveryCode() + separator);
			pWriter.print(delivery.getDateAndTime() + separator);
			pWriter.print(delivery.getOrderState() + separator);
			//restaurant
			Restaurant restaurant = getRestaurant( delivery.getRestaurantNit() );
			pWriter.print(restaurant.getName() + separator);
			pWriter.print(restaurant.getNit() + separator);
			//client
			Client client = getClient( delivery.getClientId() );
			pWriter.print(client.getIdNumber() + separator);
			pWriter.print(client.getIdNumber() + separator);
			pWriter.print(client.getAdress() + separator);
			//Product
			int cent = 0;
			for (Product product: delivery.getProducts()) {
				cent++;
				pWriter.print(cent + separator);
				pWriter.print(product.getName() + separator);
				pWriter.print(product.getPrice() + separator);
				pWriter.print(product.getCode() + "\n");
				
			}
			
		}
		pWriter.close();
	}
	
	public void importClientsData(String fileName) throws IOException{
		BufferedReader bReader = new BufferedReader(new FileReader(fileName));
		String line = bReader.readLine();
		int cent = 1;
		while (line != null ) {
			if (cent != 1) {
				String[] parts = line.split(",");
				DocumentType documentType = DocumentType.valueOf( parts[0] );
				String idNumber = parts[1];
				String name = parts[2];
				String lastName = parts[3];
				String phoneNumber = parts[4];
				String address = parts[5];
				Client client = new Client(documentType, idNumber, name, lastName, phoneNumber, address);
				clients.add(client);
			}
			cent ++;
			
			line = bReader.readLine();
		}
		
		saveManager();
		bReader.close();
	
	}
	
	public void importRestaurantsData(String fileName) throws IOException {
		BufferedReader bReader = new BufferedReader(new FileReader(fileName));
		String line = bReader.readLine();
		int cent = 1;
		while (line != null ) {
			if (cent != 1) {
				String[] parts = line.split(",");
				String name = parts[0];
				String adminName = parts[1];
				String nit = parts[2];
				Restaurant restaurant = new Restaurant(name, nit, adminName);
				restaurants.add(restaurant);
			}
			cent ++;
			
			line = bReader.readLine();
		}
		
		saveManager();
		bReader.close();
	}
	
	public void importProductsata(String fileName) throws IOException {
		BufferedReader bReader = new BufferedReader(new FileReader(fileName));
		String line = bReader.readLine();
		int cent = 1;
		while (line != null ) {
			if (cent != 1) {
				String[] parts = line.split(",");
				String name = parts[0];
				String description = parts[1];
				Double price = Double.parseDouble(parts[2]);
				String restaurantNit = parts[3];
				Product product = new Product(name, description, price, restaurantNit);
				products.add(product);
			}
			cent ++;
			
			line = bReader.readLine();
		}
		
		saveManager();
		bReader.close();
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
		Collections.sort(clients);
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
	
	public Client getClient(String ClientId) {
		Client client = new Client();
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).getIdNumber().equals(ClientId)) {
				client = clients.get(i);
			}
		}
		
		return client;
		
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
