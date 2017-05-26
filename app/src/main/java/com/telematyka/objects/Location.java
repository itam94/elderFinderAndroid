package com.telematyka.objects;
import java.sql.Date;

public class Location {
	private double longitude;
	private double latitude;
	private String date;
	
	public double getLongitude(){
		return this.longitude;
	}
	
	public double getLatitude(){
		return this.latitude;
	}
	
	public void setLongitude(double longitude){
		this.longitude = longitude;
	}
	public String getDate(){
		return this.date;
	}
	
	public void setDate(String date){
		this.date = date;
	}
	public void setLatitude(double latitude){
		this.latitude = latitude;
	}
}
