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
	public String getdescription() {
		return description;
	}
	public void setdescription(String description) {
		this.description = description;
	}
    
    



	
	
}
