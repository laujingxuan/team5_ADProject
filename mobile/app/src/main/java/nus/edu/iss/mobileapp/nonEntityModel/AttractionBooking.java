package nus.edu.iss.mobileapp.nonEntityModel;

import java.time.LocalDate;

public class AttractionBooking implements BookingWrapper{
    private long id;

    private String attractionName;

    private int quantity;

    private String date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAttractionName() {
        return attractionName;
    }

    public void setAttractionName(String attractionName) {
        this.attractionName = attractionName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "AttractionBooking{" +
                "id=" + id +
                ", attractionName='" + attractionName + '\'' +
                ", quantity=" + quantity +
                ", date='" + date + '\'' +
                '}';
    }
}
