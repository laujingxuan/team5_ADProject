package nus.edi.iss.adproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Attraction {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String name;
	private double price;
	private String location;
	private String rating;
	private String description;
	private String country_city;
	
	
	public Attraction() { }
	public Attraction(long id, String name, double price, String location, String rating, String description,
			String country_city) {
		this.name = name;
		this.price = price;
		this.location = location;
		this.rating = rating;
		this.description = description;
		this.country_city = country_city;
	}
	
	
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCountry_city() {
		return country_city;
	}
	public void setCountry_city(String country_city) {
		this.country_city = country_city;
	}
	
	
	
}
