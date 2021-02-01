package nus.edu.iss.adproject.model;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Hotel {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
	
	@OneToMany(mappedBy = "hotel")
	private List< RoomType> roomType;
	
	
    private String name;
    private String location;
    private double rate;
    private int numberOfRestaurants;
    private String country_City;
    private String emenities;
    private String quality;
    private String description;
    
    @OneToMany(mappedBy = "hotel")
    private List<Discount> discount;
    
    
    public Hotel() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Hotel(String name, String location, double rate, int numberOfRestaurants, String country_City,
			String emenities, String quality, String description) {
		this.name = name;
		this.location = location;
		this.rate = rate;
		this.numberOfRestaurants = numberOfRestaurants;
		this.country_City = country_City;
		this.emenities = emenities;
		this.quality = quality;
		this.description = description;
	}

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<RoomType> getRoomType() {
		return roomType;
	}

	public void setRoomType(List<RoomType> roomType) {
		this.roomType = roomType;
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

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
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

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
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


	@Override
	public String toString() {
		return "Hotel [name=" + name + ", location=" + location + ", rate=" + rate + ", numberOfRestaurants="
				+ numberOfRestaurants + ", country_City=" + country_City + ", emenities=" + emenities + ", quality="
				+ quality + ", description=" + description + "]";
	}
	
	
	
}
