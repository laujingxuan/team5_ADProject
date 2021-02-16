package nus.edu.iss.mobileapp.nonEntityModel;

import java.time.LocalDate;
import java.util.List;

public class Booking {
    private long id ;

    private List<BookingDetails> bookingDetails;

    private String bookingDate;

    private int travelPackageDiscount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<BookingDetails> getBookingDetails() {
        return bookingDetails;
    }

    public void setBookingDetails(List<BookingDetails> bookingDetails) {
        this.bookingDetails = bookingDetails;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getTravelPackageDiscount() {
        return travelPackageDiscount;
    }

    public void setTravelPackageDiscount(int travelPackageDiscount) {
        this.travelPackageDiscount = travelPackageDiscount;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", bookingDetails=" + bookingDetails +
                ", bookingDate=" + bookingDate +
                ", travelPackageDiscount=" + travelPackageDiscount +
                '}';
    }
}
