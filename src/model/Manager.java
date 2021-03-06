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
import java.util.Comparator;

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
		
		sortDeliveries();
		
		PrintWriter pWriter = new PrintWriter(fileName);
		
		pWriter.println("[DelyveryCode]" + separator + "[delivery date and time]"  + separator + "[Delivery State]" + separator + 
						"[Restaurant Name]" + separator + "[Restaurant Nit]" + separator +
						"[Client Name]" + separator + "[Client id]"  + separator + "[Client address]" + separator + 
						"[Product Number]" + separator + "[Product Name]" + separator + "[Product price]" + separator + "[Product code]" );
		
		
		for (Delivery delivery : deliveries) {
			
			//delivery
			pWriter.print(delivery.getDeliveryCode() + separator);
			pWriter.print(delivery.getDate().toString() + separator);
			pWriter.print(delivery.getOrderState() + separator);
			//restaurant
			Restaurant restaurant = getRestaurant( delivery.getRestaurantNit() );
			pWriter.print(restaurant.getName() + separator);
			pWriter.print(restaurant.getNit() + separator);
			//client
			Client client = getClient( delivery.getClientId() );
			pWriter.print(client.getName() + separator);
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
	
	public void sortDeliveries() {
		Comparator<Delivery> deliveryComparator = new Comparator<Delivery>() {
			public int compare(Delivery deliveryOne, Delivery deliveryTwo) {
				int comp = 0;
				comp =  - Integer.parseInt(deliveryOne.getRestaurantNit()) - Integer.parseInt( deliveryTwo.getRestaurantNit());
				if(comp == 0) {
					long document = Long.parseLong(deliveryOne.getClientId()) - Long.parseLong( deliveryTwo.getClientId() );
					comp = (document > 0) ? 1 : ((document < 0) ? -1 : 0 );
					if (comp == 0) {
						comp = - deliveryOne.getDate().compareTo(deliveryTwo.getDate());
					}
				}
				
				return comp;
			}
		}; 
		
		Collections.sort(deliveries, deliveryComparator);
		
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
		Collections.sort(clients);
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
		sortRestaurants();
		return restaurants;
	}
	
	public void sortRestaurants() {
	    Restaurant temp = null;
	    for(int i = 0; i < restaurants.size() ; i++){
	      for(int j = 0; j < (restaurants.size() - 1 - i) ; j++){
	        if(restaurants.get(j).compareTo(restaurants.get(j+1)) > 0){
	          temp = restaurants.get(j);
	          restaurants.set(j, restaurants.get(j+1));
	          restaurants.set(j+1, temp);
	        }

	      }
	    }
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
		if (clients.size() == 0 ) {
			clients.add(client);
		}else if(clients.size() == 1){
			clients.add(clients.get(clients.size()-1));
			if (client.compareTo(clients.get(0)) >= 0) {
				clients.set(1, client);
			}else {
				clients.set(0, client);
			}
		}else if(client.compareTo(clients.get(clients.size()-1))  >= 0){
			clients.add(client);
		}else {
			//duplicate last element
			clients.add(clients.get(clients.size()-1));
			boolean exit = false;
			for (int i = clients.size()-2; i > 0 && !exit; i--) {
				if (client.compareTo(clients.get(i-1)) > 0 ) {
					exit = true;
					clients.set(i, client);
				}else {
					clients.set(i, clients.get(i-1));
					if (i == 1) {
						clients.set(0, client);
					}
				}
				
			}
		}
			
		
	}
	
	public void sortClientsByPhoneNumber() {
		int index = 0;
	    for(int i = 0; i < clients.size() - 1 ; i++){

	      index = i;
	      for(int j = i + 1 ; j < clients.size(); j++){
	        if (clients.get(j).compareToByPhoneNumber(clients.get(index)) < 0){
	          index = j;
	        }      
	      }
	      Client min = clients.get(index);
	      clients.set(index, clients.get(i));
	      clients.set(i, min);
	    }
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
		Client client = null;
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
	
	public Client searchClient(String name) {
		int left = 0;
		int rigth = clients.size() - 1;
		while (left <= rigth) {
			int mid = (rigth + left) / 2;
			if (name.compareToIgnoreCase(clients.get(mid).getName()) < 0) {
				rigth = mid	-1;
			}else if (name.compareToIgnoreCase(clients.get(mid).getName()) > 0) {
				left = mid + 1;
			}else {
				return clients.get(mid);
			}
		}
		
		
		return null;
	}
	
	
}
