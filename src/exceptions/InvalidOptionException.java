package exceptions;

public class InvalidOptionException extends Exception{
	private static final long serialVersionUID = 1;
	
	 public InvalidOptionException(int optionFloor, int optionCealing) {
		super("----------------\n the option selected is invalid."
				+ "\n please select an option in between " +  optionFloor + " and " + optionCealing );
	}
}
