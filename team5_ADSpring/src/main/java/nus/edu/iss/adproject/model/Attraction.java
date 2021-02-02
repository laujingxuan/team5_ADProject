package nus.edu.iss.adproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Attraction {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String name;
	private double price;
	private String location;
	private double rating;
	private String description;
	private String country_city;
	
	@OneToOne
	private Product product;
	
	
	public Attraction() { }	
	
	public Attraction(String name, double price, String location, double rating, String description,
			String country_city, Product product) {
		super();
		this.name = name;
		this.price = price;
		this.location = location;
		this.rating = rating;
		this.description = description;
		this.country_city = country_city;
		this.product = product;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setId(long id) {
		this.id = id;
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
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
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
