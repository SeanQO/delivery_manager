package model;

public class Client {
	private String type;
	private String idNumber;
	private String fullName;
	private String phoneNumber;
	private String adress;
	
	public Client(String type, String idNumber, String fullName, String phoneNumber, String adress) {
		this.type = type;
		this.idNumber = idNumber;
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.adress = adress;
	}
	
	public String getType() {
		return type;
	}
	
	public String getIdNumber() {
		return idNumber;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public String getAdress() {
		return adress;
	}
	
	
}
