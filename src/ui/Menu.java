package ui;
import model.Client;
import model.Delivery;
import model.DocumentType;
import model.Manager;
import model.Product;
import model.Restaurant;

import java.util.ArrayList;
import java.util.Scanner;
import exceptions.EmptyClientListException;
import exceptions.EmptyProductListException;
import exceptions.EmptyRestaurantListException;
import exceptions.InvalidClientException;
import exceptions.InvalidNitException;

public class Menu {
	private final static int EXIT = 5;
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
		System.out.println("5. Exit.");
	}

	private boolean runOptions(int option) throws NumberFormatException{
		boolean exit = false;
		//boolean optionExit = false;
		
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
			
		case 342:
			System.out.println(ASTERISKS + ASTERISKS);
			System.out.println(manager.getRestaurants().toString());
			System.out.println(manager.getProducts().toString());
			System.out.println(manager.getClients().toString());
			System.out.println(ASTERISKS + ASTERISKS);
			pressAnyKeyToContinue();
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
		
		manager.addProduct(product,resNit);
		
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
		manager.addClients(client);

	}
	
	private void runOptionFour() throws EmptyProductListException, EmptyRestaurantListException, InvalidClientException, InvalidNitException, EmptyClientListException {
		
		if(manager.getClients().size() == 0) {
			throw new EmptyClientListException();
			
		}else if (manager.getRestaurants().size() == 0) {
			throw new EmptyRestaurantListException();
			
		}else if(manager.getProducts().size() == 0){		
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
		
		System.out.println("-Enter the restaurant nit from where is made the order:");
		System.out.println(ASTERISKS);
		System.out.println("-current nit list: ");
		System.out.println("-only showing restaurants with at least one product.");
		for (Restaurant restaurant: manager.getRestaurants()) {
			if (restaurant.getProducts().size() != 0) {
				System.out.println(restaurant.getName() + ": " + restaurant.getNit());
			}
			
		}
		System.out.println(ASTERISKS);
		
		System.out.println("Enter nit:");
		String resNit = in.nextLine();
		if (!manager.usedNit(resNit)) {
			throw new InvalidNitException(resNit);
		}
		
		boolean selectAnOther = false;
		ArrayList<Product> products = new ArrayList<>();
		ArrayList<Integer> quantities =  new ArrayList<>();
		
		do {
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
			System.out.println("1. No");
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
		
		
		
		Delivery delivery = new Delivery(clientId, resNit, products, quantities);
		manager.getDeliveries().add(delivery);

		
	}
	
	private Product selectPorudct(String resNit) throws NumberFormatException{
		Restaurant restaurant = manager.getRestaurant(resNit);
		System.out.println(ASTERISKS);
		System.out.println("-to add a product select the number next to the desired product.");
		for (int i = 0; i < restaurant.getProducts().size(); i++){
				System.out.println("-" + (i+1) + ". " + restaurant.getProducts().get(i).getName() + ": " + restaurant.getProducts().get(i).getPrice() );
		}
		
		int option = Integer.parseInt(in.nextLine());
		if (option < 1 || option > manager.getProducts().size()) {
			throw  new NumberFormatException();
		}
		
		Product product  = manager.getProducts().get(option-1);
		
		
		return product;
		
		
		
	}
	
	
	

}
