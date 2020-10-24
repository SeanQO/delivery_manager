	package model;

import java.io.Serializable;

public class Client implements Serializable, Comparable<Client> {
	
	private static final long serialVersionUID = 1;
	
	private DocumentType documentType;
	private String idNumber;
	private String name;
	private String lastName;
	private String phoneNumber;
	private String address;

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
	

	@Override
	public int compareTo(Client otherClient) {
		int comp;
		char lastNameOne = Character.toUpperCase( lastName.charAt(0) );
		char lasNameTwo =  Character.toUpperCase( otherClient.getLastname().charAt(0) );
		comp = lastNameOne - lasNameTwo;
		if (comp == 0) {
			char nameOne = Character.toUpperCase( name.charAt(0) );
			char nameTwo = Character.toUpperCase( otherClient.getName().charAt(0) );
			comp = nameOne - nameTwo;
		}
		
		return comp;
	}
	
	public int compareToByPhoneNumber(Client otherClient) {
		long comp = Long.parseLong( phoneNumber ) - Long.parseLong( otherClient.getPhoneNumber() );
		int comp2 = 0;
		if (comp > 0) {
			comp2 = 1;
		}else if(comp == 0) {
			comp2 = 0;
		}else {
			comp2 = -1;
		}
		return comp2;
	}
	
	
}
