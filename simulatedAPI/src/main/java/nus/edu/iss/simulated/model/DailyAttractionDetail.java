package nus.edu.iss.simulated.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DailyAttractionDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	private String name;
	
	private LocalDate date;
	
	private int quantityLeft;

	public DailyAttractionDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DailyAttractionDetail(String name, LocalDate date, int quantityLeft) {
		super();
		this.name = name;
		this.date = date;
		this.quantityLeft = quantityLeft;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getQuantityLeft() {
		return quantityLeft;
	}

	public void setQuantityLeft(int quantityLeft) {
		this.quantityLeft = quantityLeft;
	}
	
	
}
