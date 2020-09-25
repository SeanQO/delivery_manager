package exceptions;

public class EmptyRestaurantListException extends Exception{
	private static final long serialVersionUID = 1;
	
	public EmptyRestaurantListException() {
		
		super("----------------\nThere are no saved restaurants , please add at least one restaurant.");
		
	}
	
}
