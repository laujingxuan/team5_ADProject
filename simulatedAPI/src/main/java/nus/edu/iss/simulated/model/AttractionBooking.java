package nus.edu.iss.simulated.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AttractionBooking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String attractionName;
	
	private int quantity;
	
	private LocalDate date;

	public AttractionBooking() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AttractionBooking(String attractionName, int quantity, LocalDate date) {
		super();
		this.attractionName = attractionName;
		this.quantity = quantity;
		this.date = date;
	}

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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	} 
	
	

}
