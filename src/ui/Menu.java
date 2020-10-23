package ui;
import model.Client;
import model.Delivery;
import model.DocumentType;
import model.Manager;
import model.Product;
import model.Restaurant;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import exceptions.DiferentRestaurantException;
import exceptions.EmptyClientListException;
import exceptions.EmptyProductListException;
import exceptions.EmptyRestaurantListException;
import exceptions.InvalidClientException;
import exceptions.InvalidNitException;
import exceptions.InvalidOptionException;

public class Menu {
	private final static int EXIT = 11;
	private static String ASTERISKS = "*****************";	
	private Scanner in; 
	private Manager manager;

	public Menu() {
		manager = new Manager();
		in = new Scanner(System.in);
	}
	
	private void pressAnyKeyToContinue() {
		System.out.println(ASTERISKS);
		System.out.println("Press any key to continue.");
		in.nextLine();
	}

	public void startProgram() {
		boolean exit = false;
		String option = "";
		try {
			manager.loadManager();
		} catch (ClassNotFoundException classNotFoundException) {
			System.err.println("Problem loading the saved data.\nIf its the first time running the program, IGNORE this message.");

		}catch (IOException ioException) {
			System.err.println("Problem loading the saved data.\nIf its the first time running the program, IGNORE this message.");
		}
		
		do {
			try {
				showMenu();
				option  = in.nextLine();
				exit = runOptions(Integer.parseInt(option));

			} catch (NumberFormatException numberFormatException) {
				System.err.println(ASTERISKS);
				System.err.println("the entered option - "+ option +" - is not a valid selection.\n Please only enter the number next to the option." );
				pressAnyKeyToContinue();
			}
			try {
				manager.saveManager();
			} catch (IOException ioException) {
				System.err.println("Problem saving the data.");
			}
			

		} while (!exit);
	}

	private void showMenu() {
		System.out.println(ASTERISKS);
		System.out.println("*PRINCIPAL MENU*");
		System.out.println(ASTERISKS);
		System.out.println("1.Register Restaurant.");
		System.out.println("2. Register Product.");
		System.out.println("3. Register Client.");
		System.out.println("4. Register delivery.");
		System.out.println("5. Edit Info:(Restaurants, Clients, products).");
		System.out.println("6. Change delivery status.");
		System.out.println("7. Export delivery report.");
		System.out.println("8. import data");
		System.out.println("9. Search client");
		System.out.println("10. Show restaurants");
		System.out.println("11. Exit.");
	}

	private boolean runOptions(int option) throws NumberFormatException{
		boolean exit = false;
		
		switch (option) {

		case 1:
				try {
					runOptionOne();
				} catch (InvalidNitException invalidNitException) {
					System.err.println(invalidNitException.getMessage());
					System.err.println("-The entered Nit is already registered under an other restaurant");
					pressAnyKeyToContinue();
				}
			
			break;
			
		case 2:
				
				try {
					runOptionTwo();
				} catch (InvalidNitException invalidNitException) {
					System.err.println(invalidNitException.getMessage());
					System.err.println("-The entered Nit is not registered under any saved restaurant.\n-please add a new restaurant or select a valid Nit");
					pressAnyKeyToContinue();
				}catch (NumberFormatException numberFormatException) {
					System.err.println(ASTERISKS);
					System.err.println("-The Number entered has an invalid declaration.");
					System.err.println("-Separate the decimal parts of a number using '.' ex: 3.14");
					pressAnyKeyToContinue();
				} catch (EmptyRestaurantListException emptyRestaurantListException) {
					System.err.println(emptyRestaurantListException.getMessage());
					pressAnyKeyToContinue();
				}
			
			break;
			
		case 3:
			try {
				runOptionTree();
				
			} catch (NumberFormatException numberFormatException) {
				System.err.println(ASTERISKS);
				System.err.println("the Number entered is not a valid selection.\n Please only enter the number next to the option. ");
				pressAnyKeyToContinue();
				
			}catch (InvalidClientException invalidClientException) {
				System.err.println(invalidClientException.getMessage());
				pressAnyKeyToContinue();
			}
			
			break;
			
		case 4:
			try {
				runOptionFour();
				
			}catch (EmptyProductListException emptyProductListException) {
				System.err.println(emptyProductListException.getMessage());
				pressAnyKeyToContinue();
			}catch (EmptyRestaurantListException emptyRestaurantListException) {
				System.err.println(emptyRestaurantListException.getMessage());
				pressAnyKeyToContinue();
			}catch (InvalidClientException invalidClientException) {
				System.err.println("----------------\nThe entered id number: " + invalidClientException.getIdNumber() + " is not registered.");
				pressAnyKeyToContinue();
			}catch (InvalidNitException invalidNitException) {
				System.err.println(invalidNitException.getMessage());
				System.err.println("-The entered Nit is not registered under any saved restaurant.\n-please add a new restaurant or select a valid Nit");
				pressAnyKeyToContinue();
			}catch (EmptyClientListException emptyClientListException) {
				System.err.println(emptyClientListException.getMessage());
				pressAnyKeyToContinue();
			}catch (NumberFormatException numberFormatException) {
				System.err.println("the entered option is not a valid selection.\n Please only enter the number next to the option.");
				pressAnyKeyToContinue();
			}
			
			
			break;
		case 5:
			boolean localExit = false;
			do {
				
				localExit = menuOptionFive();
				
			} while (localExit);
			break;
			
		case 6:
			runOptionSix();
			break;
			
		case 7:
			System.out.println(ASTERISKS);
			System.out.println("enter file name to export to. ex: data/deliveryReport.csv");
			String path = in.nextLine();
			System.out.println("enter separator. ex  - ");
			String separator = in.nextLine();
			try {
				manager.exportDeliveryReport(path, separator);
				System.out.println("Files succesfully saved.");
			} catch (FileNotFoundException fileNotFoundException) {
				System.err.println("couldnt save the report, please try again.");
			}
			
			break;
			
		case 8:
			
			runOptionEigth();
			
			break;
		
		case 9:
				runOptionNine();
			break;
		case 10:
			runOptionTen();
		break;
		case EXIT:
			System.out.println(ASTERISKS);
			System.out.println("*Program finished*");
			System.out.println(ASTERISKS);
			exit = true;
			break;
		
		default:
			throw new  NumberFormatException();

		}

		return exit;
	}
	
	private void runOptionOne() throws InvalidNitException{
		System.out.println(ASTERISKS);
		System.out.println("Add a new restaurant (the entered info can be changed later on):");
		System.out.println("-Enter the restaurants name:");
		String name = in.nextLine();
		
		System.out.println("-Enter the restaurants nit:");
		String nit = in.nextLine();
		if (manager.usedNit(nit)) {
			throw new InvalidNitException(nit);
		}
		
		System.out.println("-Enter the restaurants admin. name:");
		String adminName = in.nextLine();
		
		Restaurant restaurant = new Restaurant(name, nit, adminName);
		manager.addRestaurant(restaurant);
		
	}
	
	private void runOptionTwo() throws InvalidNitException, NumberFormatException, EmptyRestaurantListException{
		if (manager.getRestaurants().size() == 0) {
			System.out.println("");
			throw new EmptyRestaurantListException();
		}
		
		System.out.println(ASTERISKS);
		System.out.println("Add a new product (the entered info can be changed later on):");
		System.out.println("-Enter the product name:");
		String name = in.nextLine();
		System.out.println("-Enter the product description:");
		String description = in.nextLine();
		System.out.println("-Enter the product price: (use period to separate the decimal part if needed ex: 3.14)");
		double price = Double.parseDouble( in.nextLine() );
		System.out.println("-Enter the restaurant nit wich owns the product:");
		System.out.println(ASTERISKS);
		System.out.println("-current nit list: ");
		for (Restaurant restaurant: manager.getRestaurants()) {
			System.out.println(restaurant.getName() + ": " + restaurant.getNit());
			
		}
		System.out.println(ASTERISKS);
		
		System.out.println("Enter nit:");
		String resNit = in.nextLine();
		
		if (!manager.usedNit(resNit)) {
			throw new InvalidNitException(resNit);
		}
		
		Product product = new Product(name, description, price, resNit);
		
		manager.addProduct(product);
		
		System.out.println("Product generated code: " + product.getCode());

	}
	
	private void runOptionTree() throws NumberFormatException, InvalidClientException{
		System.out.println(ASTERISKS);
		System.out.println("Add a new Client (the entered info can be changed later on):");
		System.out.println("-Enter the clients idNumber:");
		String idNumber = in.nextLine();
		
		if (manager.registeredId(idNumber)) {
			throw new InvalidClientException(idNumber);
		};
		
		System.out.println("-Select the clients document Type:");
		System.out.println("1.TI\n 2.CC\n 3.PP\n 4.CE ");
		int documentTypeInt = Integer.parseInt(in.nextLine());
		DocumentType documentType;
		switch (documentTypeInt) {
		case 1:
			documentType = DocumentType.valueOf("TI");
			break;
		case 2:
			documentType = DocumentType.valueOf("CC");
			break;
		case 3:
			documentType = DocumentType.valueOf("PP");
			break;
		case 4:
			documentType = DocumentType.valueOf("CE");
			break;

		default:
			throw new NumberFormatException();
			
		}
		
		System.out.println("-Enter the clients name:");
		String name = in.nextLine();
		System.out.println("-Enter the clients lastname:");
		String lastName = in.nextLine();
		System.out.println("-Enter the clients phoneNumber:");
		String phoneNumber = in.nextLine();		
		System.out.println("-Enter the clients address:");
		String address = in.nextLine();
		
		Client client  = new Client(documentType, idNumber, name, lastName, phoneNumber, address);
		System.out.println("Adding client ...");
		manager.addClients(client);
		System.out.println("Client succesfully added");
	}
	
	private void runOptionFour() throws EmptyProductListException, EmptyRestaurantListException, InvalidClientException, InvalidNitException, EmptyClientListException {
		
		if(manager.getClients().size() == 0) {
			throw new EmptyClientListException();
			
		}else if (manager.getRestaurants().size() == 0) {
			throw new EmptyRestaurantListException();
			
		}else if (manager.getProducts().size() == 0) {
			throw new EmptyProductListException();
		}
		
		System.out.println(ASTERISKS);
		System.out.println("Add a new delivery order (the entered info can be changed later on):");
		System.out.println("-Enter the client idNumber:");
		
		System.out.println(ASTERISKS + "\nRegistred id list:");
		for (Client client : manager.getClients()) {
			System.out.println(client.getName() + ": " + client.getIdNumber());
		}
		System.out.println(ASTERISKS);
		
		System.out.println("-Enter the id number:");
		String clientId = in.nextLine();
		if (!manager.registeredId(clientId)) {
			throw new InvalidClientException(clientId);
		}
				
		ArrayList<Product> products = new ArrayList<>();
		ArrayList<Integer> quantities =  new ArrayList<>();
		
		boolean selectAnOther = false;
		String resNit = "";
		do {
			if (products.size() != 0 ) {
				resNit = products.get(0).getRestaurantNit();
			}
			Product product = selectPorudct(resNit);
			System.out.println("- Enter the quantitie of this product to add to the delivery:");
			int quantity = Integer.parseInt(in.nextLine());
			if (quantity < 0) {
				throw new NumberFormatException();
			}
			
			products.add(product);
			quantities.add(quantity);
			
			System.out.println("- Add nore products:");
			System.out.println("1. Yes");
			System.out.println("2. No");
			int option = Integer.parseInt(in.nextLine());
			switch (option) {
			case 1:
				selectAnOther = true;
				break;
			case 2:
				selectAnOther = false;
				break;
			default:
				throw new NumberFormatException();
			}
			
			
			
		} while (selectAnOther);
		
		
		
		Delivery delivery = new Delivery(clientId, products.get(0).getRestaurantNit() , products, quantities);
		manager.getDeliveries().add(delivery);

		
	}
	
	private Product selectPorudct(String resNit) {
		boolean error = false;
		int option = 0;
		Product finalProduct = new Product(); 
		do {
			
			System.out.println(ASTERISKS);
			System.out.println("you can only order from one restaurant.");
			System.out.println("if a product is unavailable, its because its from a diferent restaurant.");
			int productIndex = 0;
			for (Product product : manager.getProducts()) {
				productIndex++;
				if (!resNit.equals("") && !product.getRestaurantNit().equals(resNit)) {
					System.out.print("unavailable- ");
				}
				System.out.println(productIndex + ". " + product.getName() + ": " + product.getPrice() + ". Restaurant: " + manager.getRestaurant(product.getRestaurantNit()).getName() );
			}
			
			try {
				option = Integer.parseInt(in.nextLine());
				if (option < 1 || option > productIndex) {
					throw new InvalidOptionException(1, productIndex);
				}
				
				if (resNit.equals("") ) {
					finalProduct = manager.getProducts().get(option-1);
				}else {
					if (manager.getProducts().get(option-1).getRestaurantNit().equals(resNit)) {
						finalProduct = manager.getProducts().get(option-1);
					}else {
						throw new DiferentRestaurantException();
					}
				}
				error = false;
			} catch (InvalidOptionException invalidOptionException) {
				System.err.println(invalidOptionException.getMessage());
				pressAnyKeyToContinue();
				error = true;
			}catch (NumberFormatException numberFormatException) {
				System.err.println("the entered option was invalid. please only enter the number next to the option");
				pressAnyKeyToContinue();
				error = true;
			}catch (DiferentRestaurantException diferentRestaurantException) {
				System.err.println(diferentRestaurantException.getMessage());
				pressAnyKeyToContinue();
				error = true;
			}
			
		} while (error);
		
		
		
		return finalProduct;
						
	}
	
	private boolean menuOptionFive() {
		boolean exit = false;
		boolean error = false;
		int option = 0;
		do {
			System.out.println(ASTERISKS);
			System.out.println("Edit info menu:");
			System.out.println("1. Edit restaurant info.");
			System.out.println("2. Edit Products info." );
			System.out.println("3. Edit Clients info.");
			System.out.println("4. principal menu");
			try {
				option = Integer.parseInt( in.nextLine());
				if (option < 1 || option >  4) {
					throw new InvalidOptionException( 1, 4);
				}
			} catch (InvalidOptionException invalidOptionException) {
				System.err.println(invalidOptionException.getMessage());
				error = true;
				pressAnyKeyToContinue();
			}catch (NumberFormatException numberFormatException) {
				System.err.println("the entered option was invalid. please only enter the number next to the option");
				error = true;
				pressAnyKeyToContinue();
			}
			
		} while (error);
		
		
		
		switch (option) {
		
		case 1:
			editRestaurantInfo();
			break;
		case 2:
			editProductInfo();
			break;			
		case 3:
			editClientInfo();
			break;
			
		case 5:
			exit = true;
			break;
			
		default:
			break;
		}
		
		return exit;
	}
	
	private void editRestaurantInfo() {
		boolean error = false;
		int resIndex = 0;
		
		do {
			
			System.out.println(ASTERISKS);
			System.out.println("Select a restaurant to edit:");
			for (int i = 0; i < manager.getRestaurants().size() ; i++) {
				System.out.println(  i + ".  "+ manager.getRestaurants().get(i).getName() + ". ");
				
			}
			
			try {
				resIndex = Integer.parseInt( in.nextLine());
				if (resIndex < 1 || resIndex > manager.getRestaurants().size() ) {
					throw new InvalidOptionException( 1, manager.getRestaurants().size());
				}
			} catch (InvalidOptionException invalidOptionException) {
				System.err.println(invalidOptionException.getMessage());
				error = true;
			}catch (NumberFormatException numberFormatException) {
				System.err.println("the entered option was invalid. please only enter the number next to the option");
				error = true;
			}
			
		} while (error);
		
		
		error = false;
		int option = 0;
		do {
			
			System.out.println(ASTERISKS);
			System.out.println("Select what to edit:");
			System.out.println("1. Restaurant name.");
			System.out.println("2. Restaurant Nit.");
			System.out.println("3. Restaurant admin name.");
			
			try {
				option = Integer.parseInt( in.nextLine());
				if (option < 1 || option > 3 ) {
					throw new InvalidOptionException( 1, 3);
				}
			} catch (InvalidOptionException invalidOptionException) {
				System.err.println(invalidOptionException.getMessage());
				pressAnyKeyToContinue();
				error = true;
			} catch (NumberFormatException numberFormatException) {
				System.err.println("the entered option was invalid. please only enter the number next to the option");
				pressAnyKeyToContinue();
				error = true;
			}
			
			
		} while (error);
		
		
		
		switch (option) {
		case 1:
			System.out.println(ASTERISKS);
			System.out.println("Enter the new restaurants name: ");
			manager.getRestaurants().get(resIndex).setName(in.nextLine());
			break;
		
		case 2:
			System.out.println(ASTERISKS);
			System.out.println("Enter the new restaurants Nit: ");
			manager.getRestaurants().get(resIndex).setNit(in.nextLine());
			break;
		
		case 3:
			System.out.println(ASTERISKS);
			System.out.println("Enter the new restaurants admin name: ");
			manager.getRestaurants().get(resIndex).setAdminName(in.nextLine());
			break;
		}
		
		
	}
	
	private void editProductInfo() {
		boolean error = false;
		int productIndex = 0;
		
		do {
			int cont = 0;
			System.out.println(ASTERISKS);
			System.out.println("Select a product to edit:");
			for (Product product: manager.getProducts()) {
				cont++;
				System.out.println(cont + ". " + product.getName() + ".");
			}
			
			try {
				productIndex = Integer.parseInt( in.nextLine());
				if (productIndex < 1 || productIndex >  cont ) {
					throw new InvalidOptionException( 1, cont);
				}
			} catch (InvalidOptionException invalidOptionException) {
				System.err.println(invalidOptionException.getMessage());
				error = true;
				pressAnyKeyToContinue();
			}catch (NumberFormatException numberFormatException) {
				System.err.println("the entered option was invalid. please only enter the number next to the option");
				error = true;
				pressAnyKeyToContinue();
			}
			
			
			
		} while (error);
		
		
		error = false;
		int option = 0;
		do {
			
			System.out.println(ASTERISKS);
			System.out.println("Select what to edit:");
			System.out.println("1. product name.");
			System.out.println("2. product description.");
			System.out.println("3. product price.");
			
			try {
				option = Integer.parseInt( in.nextLine());
				if (option < 1 || option > 3 ) {
					throw new InvalidOptionException( 1, 3);
				}
			} catch (InvalidOptionException invalidOptionException) {
				System.err.println(invalidOptionException.getMessage());
				pressAnyKeyToContinue();
				error = true;
			} catch (NumberFormatException numberFormatException) {
				System.err.println("the entered option was invalid. please only enter the number next to the option");
				pressAnyKeyToContinue();
				error = true;
			}
			
			
		} while (error);
		
		
		
		switch (option) {
		case 1:
			System.out.println(ASTERISKS);
			System.out.println("Enter the new product name: ");
			manager.getProducts().get(productIndex).setName(in.nextLine());
			break;
		
		case 2:
			System.out.println(ASTERISKS);
			System.out.println("Enter the new product description: ");
			manager.getProducts().get(productIndex).setDescription(in.nextLine());
			break;
		
		case 3:
			error = false;
			do {
				try {
					System.out.println(ASTERISKS);
					System.out.println("Enter the new product price: ");
					manager.getProducts().get(productIndex).setPrice(Double.parseDouble(in.nextLine()));
				} catch (NumberFormatException numberFormatException) {
					System.err.println("the entered price is invalid. enter the price in numbers and if decimal, separate the decimal part with a '.'");
					pressAnyKeyToContinue();
					error = true;
				}
				
			} while (error);
			
			break;
		}
		
	}

	private void editClientInfo() {
		boolean error = false;
		int clientIndex = 0;
		
		do {
			int cont = 0;
			System.out.println(ASTERISKS);
			System.out.println("Select a client to edit:");
			for (Client client: manager.getClients()) {
				cont++;
				System.out.println(cont + ". " + client.getName() + "." + client.getLastname() + "." + "id: "+ client.getIdNumber());
			}
			
			try {
				clientIndex = Integer.parseInt( in.nextLine());
				if (clientIndex < 1 || clientIndex >  cont ) {
					throw new InvalidOptionException( 1, cont);
				}
			} catch (InvalidOptionException invalidOptionException) {
				System.err.println(invalidOptionException.getMessage());
				error = true;
				pressAnyKeyToContinue();
			}catch (NumberFormatException numberFormatException) {
				System.err.println("the entered option was invalid. please only enter the number next to the option");
				error = true;
				pressAnyKeyToContinue();
			}
			
			
			
		} while (error);	
		
		error = false;
		int option = 0;
		do {
			
			System.out.println(ASTERISKS);
			System.out.println("Select what to edit:");
			System.out.println("1. Client id Number.");
			System.out.println("2. Client name.");
			System.out.println("3. Client lastname.");
			System.out.println("4. Client phone number.");
			System.out.println("5. Client address.");
			
			
			try {
				option = Integer.parseInt( in.nextLine());
				if (option < 1 || option > 6 ) {
					throw new InvalidOptionException( 1, 6);
				}
			} catch (InvalidOptionException invalidOptionException) {
				System.err.println(invalidOptionException.getMessage());
				pressAnyKeyToContinue();
				error = true;
			} catch (NumberFormatException numberFormatException) {
				System.err.println("the entered option was invalid. please only enter the number next to the option");
				pressAnyKeyToContinue();
				error = true;
			}
			
			
		} while (error);
		
		switch (option) {
		case 1:
			System.out.println(ASTERISKS);
			System.out.println("Enter the new Client id number: ");
			manager.getClients().get(clientIndex).setIdNumber(in.nextLine());
			break;
		
		case 2:
			System.out.println(ASTERISKS);
			System.out.println("Enter the new Client name: ");
			manager.getClients().get(clientIndex).setName(in.nextLine());
			break;
		
		case 3:
			System.out.println(ASTERISKS);
			System.out.println("Enter the new Client lastname: ");
			manager.getClients().get(clientIndex).setLastname(in.nextLine());
			break;
			
		case 4:
			System.out.println(ASTERISKS);
			System.out.println("Enter the new Client phone number: ");
			manager.getClients().get(clientIndex).setPhoneNumber(in.nextLine());
			break;
			
		case 5:
			System.out.println(ASTERISKS);
			System.out.println("Enter the new Client address: ");
			manager.getClients().get(clientIndex).setAdress(in.nextLine());
			break;

		}
		
		
	}
	
	private void runOptionSix(){
		boolean error = false;
		int deliveryIndex = 0;
		
		do {
			int cont = 0;
			System.out.println(ASTERISKS);
			System.out.println("Select a delivery to change status:");
			for (Delivery delivery: manager.getDeliveries()) {
				System.out.println(cont + ". "  + String.valueOf( delivery.getDeliveryCode() )+ "." + delivery.getOrderState());
			}
			
			try {
				deliveryIndex = Integer.parseInt( in.nextLine());
				if (deliveryIndex < 1 || deliveryIndex >  cont ) {
					throw new InvalidOptionException( 1, cont);
				}
			} catch (InvalidOptionException invalidOptionException) {
				System.err.println(invalidOptionException.getMessage());
				error = true;
				pressAnyKeyToContinue();
			}catch (NumberFormatException numberFormatException) {
				System.err.println("the entered option was invalid. please only enter the number next to the option");
				error = true;
				pressAnyKeyToContinue();
			}
			
			
			
		} while (error);
		
		int orderStateNum = manager.getDeliveries().get(deliveryIndex).getOrderState().ordinal() + 1;
		if (orderStateNum <= 3) {
			manager.getDeliveries().get(deliveryIndex).setOrderState(orderStateNum);
		}
		
		System.out.println("the new status is: " + String.valueOf( manager.getDeliveries().get(deliveryIndex).getOrderState() ));
		
	}
	
	private void runOptionEigth() {
		boolean error = false;
		int option = 0;
		do {
			
			System.out.println(ASTERISKS);
			System.out.println("1. import Clients data");
			System.out.println("2. import Restaurants data");
			System.out.println("3. import products data");
			
			try {
				option = Integer.parseInt( in.nextLine());
				if (option < 1 || option > 6 ) {
					throw new InvalidOptionException( 1, 6);
				}
			} catch (InvalidOptionException invalidOptionException) {
				System.err.println(invalidOptionException.getMessage());
				pressAnyKeyToContinue();
				error = true;
			} catch (NumberFormatException numberFormatException) {
				System.err.println("the entered option was invalid. please only enter the number next to the option");
				pressAnyKeyToContinue();
				error = true;
			}
			
			
		} while (error);
		
		switch (option) {
		case 1:
			System.out.println("enter path. ex: data/CLIENT_DATA.csv");
			String fileName = in.nextLine();
			try {
				manager.importClientsData(fileName);
			} catch (IOException ioException) {
				System.err.println("couldnt import the report, please try again.");
			}
			break;
		
		case 2:
			System.out.println("enter path. ex: data/RESTAURANT_DATA.csv");
			 fileName = in.nextLine();
			try {
				manager.importRestaurantsData(fileName);
			} catch (IOException ioException) {
				System.err.println("couldnt import the report, please try again.");
			}
			break;
		
		case 3:
			System.out.println("enter path. ex: data/PRODUCT_DATA.csv");
			 fileName = in.nextLine();
			try {
				manager.importProductsata(fileName);
			} catch (IOException ioException) {
				System.err.println("couldnt import the report, please try again.");
			}
			break;

		}
		
		 
	}
	
	private void runOptionNine() throws NumberFormatException{

		Comparator<Client> clientNameComparator = new Comparator<Client>(){
			public int compare(Client clientOne, Client clientTwo) {
				int comp = 0;
				comp = clientOne.getName().compareToIgnoreCase( clientTwo.getName() );
				return comp;
			}
		};
		Collections.sort(manager.getClients(), clientNameComparator);
		System.out.println("Enter the clients name:");
		String name = in.nextLine();
		System.out.println("Searching for client...");
		long start = System.currentTimeMillis();
		Client client = manager.searchClient(name);
		long finish = System.currentTimeMillis();
		if (client != null) {
			System.out.println("-" + client.getName() + " " + client.getLastname() + " - id:" + client.getIdNumber() + "." );
			System.out.println("Time to find: " + (finish - start) + "miliseconds");
		}else {
			System.out.println("Culdnt find a client with the given name: " + name);
		}
		
		Collections.sort(manager.getClients());
	}

	private void runOptionTen() {
		System.out.println(ASTERISKS);
		System.out.println("Restaurant sorted list:\n\n");
		for (Restaurant restaurant : manager.getRestaurants()) {
			System.out.println("- " + restaurant.getName() + " Nit: " + restaurant.getNit());
		}
		pressAnyKeyToContinue();
	}
	
}
