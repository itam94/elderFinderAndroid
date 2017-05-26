package com.telematyka.objects;
import java.util.ArrayList;
import java.util.List;


public class Slave {
	private String masterLogin;
	private String nameSurname;
	private String dateofBirth;
	private String id;
	private List<Location> locations;
	private int locationSize;


	public Slave(){
		locations = new ArrayList<Location>();
	}
	public String getId() {
		return this.id;
	}

	public List<Location> getLocation() {
		return this.locations;
	}

	public void setNameSurname(String nameSurname) {
		this.nameSurname = nameSurname;
	}

	public void setMasterLogin(String masterLogin) {
		this.masterLogin = masterLogin;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNameSurname() {
		return this.nameSurname;
	}

	public String getMasterLogin() {
		return this.masterLogin;
	}

	public String getDateofBirth() {
		return this.dateofBirth;
	}

	public void setDateofBirth(String dateofBirth) {
		this.dateofBirth = dateofBirth;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	public void addLocation(Location l) {
		this.locations.add(l);
	}
	public Location getLocation(int i) {
		return this.locations.get(i);
	}
	public int getLocationsSize() {
		this.locationSize = this.locations.size();
		return this.locations.size();
	}
}
