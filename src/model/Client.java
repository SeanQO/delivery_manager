package model;

import java.io.Serializable;

public class Client implements Serializable{
	
	private static final long serialVersionUID = 1;
	
	private DocumentType documentType;
	private String idNumber;
	private String name;
	private String lastName;
	private String phoneNumber;
	private String address;
	
	public Client() {
		
	}
	
	public Client(DocumentType documentType, String idNumber, String name, String lastName,  String phoneNumber, String address) {
		this.documentType = documentType;
		this.idNumber = idNumber;
		this.name = name;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}
	
	public void setType(DocumentType documentType) {
		this.documentType = documentType;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLastname(String lastName) {
		this.lastName = lastName;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setAdress(String address) {
		this.address = address;
	}

	public DocumentType getType() {
		return documentType;
	}
	
	public String getIdNumber() {
		return idNumber;
	}
	
	public String getName() {
		return name;
	}
	
	public String getLastname() {
		return lastName;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public String getAdress() {
		return address;
	}

	@Override
	public String toString() {
		return "Client [documentType=" + documentType + ", idNumber=" + idNumber + ", name=" + name + ", lastName="
				+ lastName + ", phoneNumber=" + phoneNumber + ", address=" + address + "]";
	}
	
	
}
