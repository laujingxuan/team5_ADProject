package nus.edu.iss.adproject.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id ;
	
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy = "booking")
	private List<BookingDetails> bookingDetails;
	
	private int amountPaid;
	
	private Date bookingDate;
	
	private int travelPackageDiscount;
	
	

	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Booking(long id, User user, int amountPaid, Date bookingDate, int travelPackageDiscount) {
		super();
		this.id = id;
		this.user = user;
		this.amountPaid = amountPaid;
		this.bookingDate = bookingDate;
		this.travelPackageDiscount = travelPackageDiscount;
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

	public int getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(int amountPaid) {
		this.amountPaid = amountPaid;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public int getTravelPackageDiscount() {
		return travelPackageDiscount;
	}

	public void setTravelPackageDiscount(int travelPackageDiscount) {
		this.travelPackageDiscount = travelPackageDiscount;
	}
	
	

}
