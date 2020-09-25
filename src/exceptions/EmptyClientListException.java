package exceptions;

public class EmptyClientListException extends Exception {
	private static final long serialVersionUID = 1;
	
	 public EmptyClientListException() {
		super("----------------\nThere are no registered clients, please add at least one client.");
	}
		
		
		
}
