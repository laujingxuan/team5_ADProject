package nus.edu.iss.mobileapp.nonEntityModel;

public class Attraction {

    private long id;

    private String name;

    private double price;
    private String location;
    private double lat;
    private double longi;
    private double rating;
    private String description;
    private String country_city;
    private String API_URL;
    private String imageURL;
    private Product product;

    public long getId() {
        return id;
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

    public String getAPI_URL() {
        return API_URL;
    }

    public void setAPI_URL(String API_URL) {
        this.API_URL = API_URL;
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

    @Override
    public String toString() {
        return "Attraction{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", location='" + location + '\'' +
                ", lat=" + lat +
                ", longi=" + longi +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                ", country_city='" + country_city + '\'' +
                ", API_URL='" + API_URL + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", product=" + product +
                '}';
    }
}
