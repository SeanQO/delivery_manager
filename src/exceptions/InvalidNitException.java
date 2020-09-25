package exceptions;

public class InvalidNitException extends Exception {

	private static final long serialVersionUID = 1;
	
	private String nit;
	
	public InvalidNitException(String nit) { 
		super("----------------\nThe entered NIT: " + nit + " is invalid");
		this.nit = nit;
	}
	
	public String getNit() {
		return nit;
	}
	
}
