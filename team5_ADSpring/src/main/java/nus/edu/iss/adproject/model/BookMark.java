package nus.edu.iss.adproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class BookMark {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Product product;
	
	
	public BookMark() {
		super();
		// TODO Auto-generated constructor stub
	}




	public long getId() {
		return id;
	}




	public void setId(long id) {
		this.id = id;
	}




	public BookMark(long id, User user, Product product) {
		super();
		this.id = id;
		this.user = user;
		this.product = product;
	}




	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	
	
}
