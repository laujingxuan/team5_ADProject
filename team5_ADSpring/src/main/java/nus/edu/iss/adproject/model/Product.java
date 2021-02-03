package nus.edu.iss.adproject.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import nus.edu.iss.adproject.nonEntityModel.ProductType;


@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private ProductType type;
	
	@OneToMany(mappedBy = "product")
	private List<ProductReview> productReview;
	
	@OneToOne(mappedBy = "product")
	private Attraction attraction;
	
	@OneToOne(mappedBy = "product")
	private RoomType roomType;

	public Product() {
		super();
	}

	public Product(ProductType type) {
		super();
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
