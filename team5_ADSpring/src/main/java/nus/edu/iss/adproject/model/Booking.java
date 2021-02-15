package nus.edu.iss.adproject.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id ;
	
	@ManyToOne
	@JsonIgnore
	private User user;
	
	@OneToMany(mappedBy = "booking")
	private List<BookingDetails> bookingDetails;
	
	private LocalDate bookingDate;
	
	private int travelPackageDiscount;

	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Booking(User user, LocalDate bookingDate, int travelPackageDiscount) {
		super();
		this.user = user;
		this.bookingDate = bookingDate;
		this.travelPackageDiscount = travelPackageDiscount;
		bookingDetails = new ArrayList<BookingDetails>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public int getTravelPackageDiscount() {
		return travelPackageDiscount;
	}

	public void setTravelPackageDiscount(int travelPackageDiscount) {
		this.travelPackageDiscount = travelPackageDiscount;
	}
	
	public List<BookingDetails> getBookingDetails() {
		return bookingDetails;
	}

	public void setBookingDetails(List<BookingDetails> bookingDetails) {
		this.bookingDetails = bookingDetails;
	}

	public int getNumTransactions() {
		return bookingDetails.size();
	}
	
	public double getTotalPrice() {
		double output = 0;
		
		for (BookingDetails b: bookingDetails) {
			output += b.getPrice();
		}
		return output;
	}

	@Override
	public String toString() {

		return "Booking [id=" + id + ", bookingDate="
				+ bookingDate + ", travelPackageDiscount=" + travelPackageDiscount + "]";

	}

	

	
}
