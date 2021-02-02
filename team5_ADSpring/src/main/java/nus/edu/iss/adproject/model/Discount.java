package nus.edu.iss.adproject.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Discount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private Product product;
	
	private int discount_rate;
	@DateTimeFormat (pattern="yyyy-MM-dd")
	private Date from_date;
	@DateTimeFormat (pattern="yyyy-MM-dd")
	private Date to_date;	
	
	@ManyToOne
	private Hotel hotel;
	
	@ManyToOne
	private Attraction attraction;
	
	
	public Discount() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Discount(long id, Product product, int discount_rate, Date from_date, Date to_date) {
		super();
		this.id = id;
		this.product = product;
		this.discount_rate = discount_rate;
		this.from_date = from_date;
		this.to_date = to_date;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
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
	public Date getFrom_date() {
		return from_date;
	}
	public void setFrom_date(Date from_date) {
		this.from_date = from_date;
	}
	public Date getTo_date() {
		return to_date;
	}
	public void setTo_date(Date to_date) {
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
		return "Discount [id=" + id + ", product_id=" + product + ", discount_rate=" + discount_rate + ", from_date="
				+ from_date + ", to_date=" + to_date + "]";
	}
	
	
}
