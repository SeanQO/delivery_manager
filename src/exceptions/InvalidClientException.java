package exceptions;

public class InvalidClientException extends Exception{

	private static final long serialVersionUID = 1;
	
	private String idNumber;
	
	public InvalidClientException(String idNumber) {
		super("----------------\nThe entered id number: " + idNumber + " is already registered.");
		this.idNumber = idNumber;
	}
	
	public String getIdNumber() {
		return idNumber;
	}
	
}
