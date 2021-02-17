package nus.edu.iss.adproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class ProductReview {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Product product;
	
	@NotNull
	private double rating;
	
	@NotEmpty
	private String message;
	
	private String photo;
	
	@Lob
	private byte[] pic;
	
	
	public ProductReview() { }
	public ProductReview(User user, Product product, double rating, String message, String photo) {
		this.user = user;
		this.product = product;
		this.rating = rating;
		this.message = message;
		this.photo = photo;
	}
	public ProductReview(User user, Product product, double rating, String message, String photo,
			byte[] pic) {
		super();
		this.user = user;
		this.product = product;
		this.rating = rating;
		this.message = message;
		this.photo = photo;
		this.pic = pic;
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
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public byte[] getPic() {
		return pic;
	}
	public void setPic(byte[] pic) {
		this.pic = pic;
	}
	
	
	@Transient
	public String getPhotoImagePath() {
		if(photo == null)
			return null;
		
		return "/user-photos/" + user.getId() + "/" + photo;
	}
	

}
