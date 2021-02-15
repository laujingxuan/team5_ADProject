package nus.edu.iss.adproject.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Discount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Min(value = 1)
	@Digits(integer = 3, fraction = 0, message = "Enter integer value")
	private int discount_rate;
	//@NotEmpty
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate from_date;
	//@NotEmpty
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate to_date;	
	
	@ManyToOne
	private Hotel hotel;
	
	@ManyToOne
	private Attraction attraction;
	
	
	public Discount() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Discount(long id, int discount_rate, LocalDate from_date, LocalDate to_date) {
		super();
		this.id = id;
		this.discount_rate = discount_rate;
		this.from_date = from_date;
		this.to_date = to_date;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public int getDiscount_rate() {
		return discount_rate;
	}
	public void setDiscount_rate(int discount_rate) {
		this.discount_rate = discount_rate;
	}
	public LocalDate getFrom_date() {
		return from_date;
	}
	public void setFrom_date(LocalDate from_date) {
		this.from_date = from_date;
	}
	public LocalDate getTo_date() {
		return to_date;
	}
	public void setTo_date(LocalDate to_date) {
		this.to_date = to_date;
	}	
	
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}	
	
	public Attraction getAttraction() {
		return attraction;
	}
	public void setAttraction(Attraction attraction) {
		this.attraction = attraction;
	}
	@Override
	public String toString() {
		return "Discount [id=" + id + ", discount_rate=" + discount_rate + ", from_date=" + from_date + ", to_date="
				+ to_date + ", hotel=" + hotel + ", attraction=" + attraction + "]";
	}

	
}
