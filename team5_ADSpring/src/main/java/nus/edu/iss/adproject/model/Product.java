package nus.edu.iss.adproject.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import nus.edu.iss.adproject.nonEntityModel.ProductType;

@JsonIgnoreProperties({"productReview","carts","bookingDetails","roomType"})
@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private ProductType type;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "product")
	private List<ProductReview> productReview;
	
	@OneToOne(cascade = CascadeType.REMOVE, mappedBy = "product")
	private Attraction attraction;
	
	@OneToOne(cascade = CascadeType.REMOVE, mappedBy = "product")
	private RoomType roomType;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "product")
	private List<Cart> carts;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "product")
	private List<BookingDetails> bookingDetails;

	public Product() {
		super();
	}

	public Product(ProductType type) {
		super();
		this.productReview = new ArrayList<ProductReview>();
		this.type = type;
	}

	public List<ProductReview> getProductReview() {
		return productReview;
	}

	public Attraction getAttraction() {
		return attraction;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public Long getId() {
		return id;
	}

	public ProductType getType() {
		return type;
	}

	public void setType(ProductType type) {

		this.type = type;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", type=" + type + "]";
	}
	
}
