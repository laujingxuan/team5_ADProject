package nus.edu.iss.adproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class BookingDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private Booking booking; 
	
	@ManyToOne
	private Product product; 
	
	private String hotelBookingId;
	
	private String attractionBookingId;
	
	private int numOfGuest;
	
	private double price;
	
	public BookingDetails() {
		super();
	}

	public BookingDetails(Booking booking, Product product, String hotelBookingId, String attractionBookingId,
			int numOfGuest, double price) {
		super();
		this.booking = booking;
		this.product = product;
		this.hotelBookingId = hotelBookingId;
		this.attractionBookingId = attractionBookingId;
		this.numOfGuest = numOfGuest;
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getHotelBookingId() {
		return hotelBookingId;
	}

	public void setHotelBookingId(String hotelBookingId) {
		this.hotelBookingId = hotelBookingId;
	}

	public String getAttractionBookingId() {
		return attractionBookingId;
	}

	public void setAttractionBookingId(String attractionBookingId) {
		this.attractionBookingId = attractionBookingId;
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
		return "BookingDetails [id=" + id + ", booking=" + booking + ", product=" + product + ", hotelBookingId="
				+ hotelBookingId + ", attractionBookingId=" + attractionBookingId + ", numOfGuest=" + numOfGuest
				+ ", price=" + price + "]";
	}
	
}
