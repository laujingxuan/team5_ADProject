package nus.edu.iss.adproject.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private Product product;
	
	private int quantity;
	private LocalDate startDate;
	private LocalDate endDate;
	private int numGuests;
	private String remarks;
	
	@ManyToOne
	private User user;

	public Cart() {
		super();
	}
	
	public Cart(Product product, int quantity,User user) {
		super();
		this.product = product;
		this.quantity = quantity;
		this.user = user;
	}
	
	public Cart(Product product, int quantity, LocalDate startDate,	User user) {
		super();
		this.product = product;
		this.quantity = quantity;
		this.startDate = startDate;
		this.user = user;
	}
	
	public Cart(Product product, int quantity, LocalDate startDate, LocalDate endDate, int numGuests, String remarks,
			User user) {
		super();
		this.product = product;
		this.quantity = quantity;
		this.startDate = startDate;
		this.endDate = endDate;
		this.numGuests = numGuests;
		this.remarks = remarks;
		this.user = user;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", product=" + product + ", quantity=" + quantity + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", numGuests=" + numGuests + ", remarks=" + remarks + ", user=" + user + "]";
	}

}
