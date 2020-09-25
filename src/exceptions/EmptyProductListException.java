package exceptions;

public class EmptyProductListException extends Exception{
	private static final long serialVersionUID = 1;
	
	public EmptyProductListException() {
		
		super("----------------\nThere are no saved products, please add at least one product.");
		
	}
}
