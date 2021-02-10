package nus.edu.iss.adproject.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
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
	
	private String APIBookingId;
	
	private int numOfGuest;
	
	private double price;
	
	private LocalDate startDate;
	
	private LocalDate endDate;
	
	private int quantity;
	
	private String remarks;
	
	public BookingDetails() {
		super();
	}
	
	

	public BookingDetails(Booking booking, Product product, String aPIBookingId, int numOfGuest,
			double price) {
		super();
		this.booking = booking;
		this.product = product;
		APIBookingId = aPIBookingId;
		this.numOfGuest = numOfGuest;
		this.price = price;
	}
	
	

	public BookingDetails(Booking booking, Product product, String aPIBookingId, int numOfGuest, double price,
			LocalDate startDate, LocalDate endDate) {
		super();
		this.booking = booking;
		this.product = product;
		APIBookingId = aPIBookingId;
		this.numOfGuest = numOfGuest;
		this.price = price;
		this.startDate = startDate;
		this.endDate = endDate;
	}



	public BookingDetails(Booking booking, Product product, String aPIBookingId, int numOfGuest, double price,
			LocalDate startDate, LocalDate endDate, int quantity, String remarks) {
		super();
		this.booking = booking;
		this.product = product;
		APIBookingId = aPIBookingId;
		this.numOfGuest = numOfGuest;
		this.price = price;
		this.startDate = startDate;
		this.endDate = endDate;
		this.quantity = quantity;
		this.remarks = remarks;
	}



	/*
	 * public BookingDetails(Booking booking, Product product, String APIBookingId,
	 * int numOfGuest, double price) { super(); this.booking = booking; this.product
	 * = product; this.APIBookingId = APIBookingId; this.numOfGuest = numOfGuest;
	 * this.price = price; }
	 */
	
	
	

	public long getId() {
		return id;
	}

	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	public String getRemarks() {
		return remarks;
	}



	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}



	public LocalDate getStartDate() {
		return startDate;
	}



	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}



	public LocalDate getEndDate() {
		return endDate;
	}



	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
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


	public String getAPIBookingId() {
		return APIBookingId;
	}

	public void setAPIBookingId(String aPIBookingId) {
		APIBookingId = aPIBookingId;
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
		return "BookingDetails [id=" + id + ", booking=" + booking + ", product=" + product + ", APIBookingId="
				+ APIBookingId + ", numOfGuest=" + numOfGuest + ", price=" + price + "]";
	}


	
}
