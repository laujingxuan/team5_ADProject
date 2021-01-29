package nus.edu.iss.adproject.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Product {
	
	@Id
	private Long id;
	private Long type;
	
	@OneToMany(mappedBy = "product" )
	private List<Discount> discount;
	
	@OneToMany(mappedBy = "product")
	private List<ProductReview> productReview;
	
	@OneToMany(mappedBy = "product")
	private List<Attraction> attraction;
	
	@OneToOne(mappedBy = "product")
	private RoomType roomType;
	
	
	public Product(Long type) {
		super();
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", type=" + type + "]";
	}
	
	
	
}
