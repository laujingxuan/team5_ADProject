package nus.edi.iss.adproject.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cart {
	
	@Id
	private Long id;
	private Long productId;
	private int quantity;
	private String userId;
	
	public Cart(Long productId, int quantity, String userId) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", productId=" + productId + ", quantity=" + quantity + ", userId=" + userId + "]";
	}
	
	

}
