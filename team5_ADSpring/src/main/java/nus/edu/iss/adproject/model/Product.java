package nus.edu.iss.adproject.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String type;
	
	@OneToMany(mappedBy = "product" )
	private List<Discount> discount;
	
	@OneToMany(mappedBy = "product")
	private List<ProductReview> productReview;
	
	@OneToMany(mappedBy = "product")
	private List<Attraction> attraction;
	
	@OneToOne(mappedBy = "product")
	private RoomType roomType;
	
	
	public Product(String type) {
		super();
		this.type = type;
	}
	
	

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", type=" + type + "]";
	}
	
	
	
}
