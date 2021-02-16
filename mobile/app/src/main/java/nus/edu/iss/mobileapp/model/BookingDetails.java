package nus.edu.iss.mobileapp.model;

import java.io.Serializable;

public class BookingDetails implements Serializable {
    private long id;

    private Product product;

    private String APIBookingId;

    private int numOfGuest;

    private double price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getAPIBookingId() {
        return APIBookingId;
    }

    public void setAPIBookingId(String APIBookingId) {
        this.APIBookingId = APIBookingId;
    }

    public int getNumOfGuest() {
        return numOfGuest;
    }

    public void setNumOfGuest(int numOfGuest) {
        this.numOfGuest = numOfGuest;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BookingDetails{" +
                "id=" + id +
                ", product=" + product +
                ", APIBookingId='" + APIBookingId + '\'' +
                ", numOfGuest=" + numOfGuest +
                ", price=" + price +
                '}';
    }
}
