package nus.edu.iss.mobileapp.model;

import java.io.Serializable;
import java.util.List;

public class Hotel implements Serializable {

    public long id;

    private String name;
    private String location;
    private double lat;
    private double longi;
    private double rating;
    private int numberOfRestaurants;
    private String country_City;
    private String emenities;
    private String description;
    private String API_URL;
    private String imageURL;
//    private List< RoomType> roomType;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public double getLat() {
        return lat;
    }

    public double getLongi() {
        return longi;
    }

    public double getRating() {
        return rating;
    }

    public int getNumberOfRestaurants() {
        return numberOfRestaurants;
    }

    public String getCountry_City() {
        return country_City;
    }

    public String getEmenities() {
        return emenities;
    }

    public String getDescription() {
        return description;
    }

    public String getAPI_URL() {
        return API_URL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLongi(double longi) {
        this.longi = longi;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setNumberOfRestaurants(int numberOfRestaurants) {
        this.numberOfRestaurants = numberOfRestaurants;
    }

    public void setCountry_City(String country_City) {
        this.country_City = country_City;
    }

    public void setEmenities(String emenities) {
        this.emenities = emenities;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAPI_URL(String API_URL) {
        this.API_URL = API_URL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", lat=" + lat +
                ", longi=" + longi +
                ", rating=" + rating +
                ", numberOfRestaurants=" + numberOfRestaurants +
                ", country_City='" + country_City + '\'' +
                ", emenities='" + emenities + '\'' +
                ", description='" + description + '\'' +
                ", API_URL='" + API_URL + '\'' +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}
