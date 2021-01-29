package nus.edi.iss.adproject.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {
	
	@Id
	private Long id;
	private Long type;
	
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
