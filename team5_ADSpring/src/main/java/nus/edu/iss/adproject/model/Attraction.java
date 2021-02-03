package nus.edu.iss.adproject.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	private String API_URL;
	
	@ManyToOne
	private User user;
	
	@OneToOne
	private Product product;
	
	 @OneToMany(mappedBy = "attraction")
	 private List<Discount> discount;
	
	

	public Attraction() { }	
	
	public Attraction(String name, double price, String location, double rating, String description,
			String country_city, Product product, String API_URL) {
		super();

		this.name = name;
		this.price = price;
		this.location = location;
		this.rating = rating;
		this.description = description;
		this.country_city = country_city;
		this.product = product;
		this.API_URL = API_URL;
	}

	public String getAPI_URL() {
		return API_URL;
	}

	public void setAPI_URL(String aPI_URL) {
		API_URL = aPI_URL;
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
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public List<Discount> getDiscount() {
		return discount;
	}
	public void setDiscount(List<Discount> discount) {
		this.discount = discount;
	}
	public void setId(long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
