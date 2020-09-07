package model;

public class Restaurant {
	private String name;
	private String nit;
	private String adminName;

	public Restaurant(String name, String nit, String adminName) {

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

}
