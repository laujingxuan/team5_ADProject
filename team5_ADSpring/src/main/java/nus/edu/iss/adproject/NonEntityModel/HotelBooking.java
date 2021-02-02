package nus.edu.iss.adproject.NonEntityModel;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class HotelBooking {
    private long id;
	
	private String roomType;
	private int numRooms;
	private int numGuests;
	private String remarks;
	private double price;
	private LocalDate bookingDate;
	private LocalDate startDate;
	private LocalDate endDate;
	
	public HotelBooking() {
		super();
	}
	
	public HotelBooking(String roomType, int numRooms, int numGuests, String remarks, double price, LocalDate bookingDate,
			LocalDate startDate, LocalDate endDate) {
		super();
		this.roomType = roomType;
		this.numRooms = numRooms;
		this.numGuests = numGuests;
		this.remarks = remarks;
		this.price = price;
		this.bookingDate = bookingDate;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public int getNumRooms() {
		return numRooms;
	}

	public void setNumRooms(int numRooms) {
		this.numRooms = numRooms;
	}

	public int getNumGuests() {
		return numGuests;
	}

	public void setNumGuests(int numGuests) {
		this.numGuests = numGuests;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
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

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "HotelBooking [id=" + id + ", roomType=" + roomType + ", numRooms=" + numRooms + ", numGuests="
				+ numGuests + ", remarks=" + remarks + ", price=" + price + ", bookingDate=" + bookingDate
				+ ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
}
