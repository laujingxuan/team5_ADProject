package nus.edu.iss.adproject.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Coupon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int barcode;
	private Date expiration_date;
	private int value;
	private int status;
	public Coupon(int barcode, Date expiration_date, int value, int status) {
		super();
		this.barcode = barcode;
		this.expiration_date = expiration_date;
		this.value = value;
		this.status = status;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getBarcode() {
		return barcode;
	}
	public void setBarcode(int barcode) {
		this.barcode = barcode;
	}
	public Date getExpiration_date() {
		return expiration_date;
	}
	public void setExpiration_date(Date expiration_date) {
		this.expiration_date = expiration_date;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Cupon [id=" + id + ", barcode=" + barcode + ", expiration_date=" + expiration_date + ", value=" + value
				+ ", status=" + status + "]";
	}
	
	
}
