package model;

import java.io.Serializable;

public class Restaurant implements Serializable{
	
	private static final long serialVersionUID = 1;
	
	private String name;
	private String nit;
	private String adminName;
	
	public Restaurant() {
		
	}
	
	public Restaurant(String name, String nit, String adminName) {
		this.name = name;
		this.nit = nit;
		this.adminName = adminName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNit(String nit) {
		this.nit = nit;
		
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getName() {
		return name;
	}

	public String getNit() {
		return nit;
	}

	public String getAdminName() {
		return adminName;
	}

	@Override
	public String toString() {
		return "Restaurant [name=" + name + ", nit=" + nit + ", adminName=" + adminName + "]";
	}

	
	
}
