package nus.edi.iss.adproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BookingDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private Booking booking; 
	
	private Product product; 
	
	private hotelBooking hotelBooking;
	
	private AttractionBooking attractionBooking;
	
	private int NumOfGuest;
	
	private int Price;
	
	

	public BookingDetails(long id, int numOfGuest, int price) {
		super();
		this.id = id;
		NumOfGuest = numOfGuest;
		Price = price;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNumOfGuest() {
		return NumOfGuest;
	}

	public void setNumOfGuest(int numOfGuest) {
		NumOfGuest = numOfGuest;
	}

	public int getPrice() {
		return Price;
	}

	public void setPrice(int price) {
		Price = price;
	}
	
	
	

}
