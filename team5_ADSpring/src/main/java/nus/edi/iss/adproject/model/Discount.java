package nus.edi.iss.adproject.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Discount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long product_id;
	private int discount_rate;
	private Date from_date;
	private Date to_date;	
	
	public Discount(long product_id, int discount_rate, Date from_date, Date to_date) {
		super();
		this.product_id = product_id;
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
	public long getProduct_id() {
		return product_id;
	}
	public void setProduct_id(long product_id) {
		this.product_id = product_id;
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
	@Override
	public String toString() {
		return "Discount [id=" + id + ", product_id=" + product_id + ", discount_rate=" + discount_rate + ", from_date="
				+ from_date + ", to_date=" + to_date + "]";
	}
	
	
}
