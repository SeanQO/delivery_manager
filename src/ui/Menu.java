package ui;
import java.util.*;

public class Menu {
	private Scanner in; 
	
	public Menu() {
		in = new Scanner(System.in);
	}
	
	public void startProgram() {
		boolean exit = false;
		
		do {
			showMenu();
			int option  = Integer.parseInt(in.nextLine());
			runOptions(option);
			
		} while (!exit);
	}
	
	private void showMenu() {
		
	}
	
	private boolean runOptions(int option) {
		boolean exit = false;
		
		return exit;
	}

}
