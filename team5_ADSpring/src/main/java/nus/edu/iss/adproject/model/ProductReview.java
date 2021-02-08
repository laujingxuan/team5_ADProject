package nus.edu.iss.adproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class ProductReview {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Product product;
	
	private double rating;
	
	private String message;
	
	private String photoImagePath;
	
	private String photo;
	
	
	public ProductReview() { }

	public ProductReview(User user, Product product, double rating, String message, String photo) {
		this.user = user;
		this.product = product;
		this.rating = rating;
		this.message = message;
		this.photo = photo;
	}

	
	public long getId() {
		return id;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
	public void setPhotoImagePath(String photoImagePath) {
		this.photoImagePath = photoImagePath;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	
	@Transient
	public String getPhotoImagePath() {
		if(photo == null)
			return null;
		
		return "/user-photos/" + id + "/" + photo;
	}
	

}
