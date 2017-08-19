package models;

import java.util.ArrayList;
import java.util.List;

public class Entry implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	private String agency;
	
	private String headline;
	
	private String date;
	
	private String city;
	
	private String content;
	
	private String address;
	
	public Entry(){};
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
