package ui;
import java.util.*;

public class Menu {
	private final static int EXIT = 2;
	private static String ASTERISKS = "*****************";	
	private Scanner in; 


	public Menu() {
		in = new Scanner(System.in);
	}

	public void startProgram() {
		boolean exit = false;
		String option = "";

		do {
			try {
				showMenu();
				option  = in.nextLine();
				exit = runOptions(option);

			} catch (NumberFormatException numberFormatException) {
				System.out.println(ASTERISKS);
				System.out.println("the entered option - "+ option +" - is not a valid selection.\n Please only enter the number next to the option." );
			}


		} while (!exit);
	}

	private void showMenu() {
		System.out.println(ASTERISKS);
		System.out.println("*PRINCIPAL MENU*");
		System.out.println(ASTERISKS);
		System.out.println("1.Register Restaurant");
		System.out.println("2. Exit");
	}

	private boolean runOptions(String option) throws NumberFormatException{
		boolean exit = false;

		switch (Integer.parseInt(option)) {

		case 1:

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

}
