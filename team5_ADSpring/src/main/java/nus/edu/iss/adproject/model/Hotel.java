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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Hotel {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    public long id;	
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "hotel")
	private List< RoomType> roomType;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "hotel")
    @JsonIgnore
    private List<Discount> discount;
    @ManyToOne
    @JsonIgnore
    private User user;
	
    @NotEmpty
	private String name;
    @NotEmpty
    private String location;
    @NotNull
    private double lat;
    @NotNull
    private double longi;
    private double rating;
    private int numberOfRestaurants;
    @NotEmpty
    private String country_City;
    private String emenities;
    private String description;
    @NotEmpty
    private String API_URL;
    @NotEmpty
    private String imageURL;

	public Hotel() {
		super();
		// TODO Auto-generated constructor stub
	}
		
	public Hotel(String name, String location, double lat, double longi, double rating,
			int numberOfRestaurants, String country_City, String emenities, String description,
			String aPI_URL, User user) {
		super();
		this.roomType = new ArrayList<RoomType>();
		this.discount = new ArrayList<Discount>();
		this.name = name;
		this.location = location;
		this.lat = lat;
		this.longi = longi;
		this.rating = rating;
		this.numberOfRestaurants = numberOfRestaurants;
		this.country_City = country_City;
		this.emenities = emenities;
		this.description = description;
		API_URL = aPI_URL;
		this.user = user;
	}
	
	public Hotel(String name, String location, double lat, double longi, double rating,
			int numberOfRestaurants, String country_City, String emenities, String quality, String description,
			String aPI_URL, User user, String imageURL) {
		super();
		this.roomType = new ArrayList<RoomType>();
		this.discount = new ArrayList<Discount>();
		this.name = name;
		this.location = location;
		this.lat = lat;
		this.longi = longi;
		this.rating = rating;
		this.numberOfRestaurants = numberOfRestaurants;
		this.country_City = country_City;
		this.emenities = emenities;
		this.description = description;
		API_URL = aPI_URL;
		this.user = user;
		this.imageURL = imageURL;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
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

	public List<RoomType> getRoomType() {
		return roomType;
	}
	public void setRoomType(List<RoomType> roomType) {
		this.roomType = roomType;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
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
	public int getNumberOfRestaurants() {
		return numberOfRestaurants;
	}
	public void setNumberOfRestaurants(int numberOfRestaurants) {
		this.numberOfRestaurants = numberOfRestaurants;
	}
	public String getCountry_City() {
		return country_City;
	}
	public void setCountry_City(String country_City) {
		this.country_City = country_City;
	}
	public String getEmenities() {
		return emenities;
	}
	public void setEmenities(String emenities) {
		this.emenities = emenities;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Discount> getDiscount() {
		return discount;
	}
	public void setDiscount(List<Discount> discount) {
		this.discount = discount;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Hotel [id=" + id + ", roomType=" + roomType + ", name=" + name + ", location=" + location + ", lat="
				+ lat + ", longi=" + longi + ", rating=" + rating + ", numberOfRestaurants=" + numberOfRestaurants
				+ ", country_City=" + country_City + ", emenities=" + emenities
				+ ", description=" + description + ", API_URL=" + API_URL + "]";
	}	
	

}
//@OneToMany(mappedBy = "hotel")
	//private List< RoomType> roomType;
//	Marina Bay Sands Hotel, Singapore (1.282302, 103.858528)
//	Swissôtel The Stamford, Singapore (1.293354, 103.853561)
//	Hotel Miramar, Singapore (1.288710, 103.837372
