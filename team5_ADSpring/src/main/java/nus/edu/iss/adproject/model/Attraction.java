package nus.edu.iss.adproject.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"user","product","discount"})
@Entity
public class Attraction {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty
	private String name;
	
	@NotNull
	private double price;
	@NotEmpty
	private String location;
	@NotNull
	private double lat;
	@NotNull
    private double longi;
	private double rating;
	@NotEmpty
	private String description;
	@NotEmpty
	private String country_city;
	@NotEmpty
	private String API_URL;
	@NotEmpty
	private String imageURL;
	
	@ManyToOne
	private User user;
	
	@OneToOne (cascade = CascadeType.REMOVE)
	private Product product;

	@OneToMany(mappedBy = "attraction")
	private List<Discount> discount;
	
	public Attraction() { }	
	public Attraction(String name, double price, String location, double rating, String description,
			String country_city, String aPI_URL) {
		super();
		discount = new ArrayList<Discount>();
		this.name = name;
		this.price = price;
		this.location = location;
		this.rating = rating;
		this.description = description;
		this.country_city = country_city;
		API_URL = aPI_URL;
	}
	
	public Attraction(String name, double price, String location, double rating, String description,
			String country_city, String aPI_URL, User user, Product product, String imageURL, double lat, double longi) {
		super();
		discount = new ArrayList<Discount>();
		this.name = name;
		this.price = price;
		this.location = location;
		this.rating = rating;
		this.description = description;
		this.country_city = country_city;
		API_URL = aPI_URL;
		this.user = user;
		this.product = product;
		this.imageURL = imageURL;
		this.lat = lat;
		this.longi = longi;
	}
	
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLongi() {
		return longi;
	}
	public void setLongi(double longi) {
		this.longi = longi;
	}
	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
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
	
	public List<Discount> getDiscount() {
		return discount;
	}
	public void setDiscount(List<Discount> discount) {
		this.discount = discount;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}

