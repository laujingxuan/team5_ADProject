package nus.edu.iss.adproject.nonEntityModel;

public class CartWrapper {

	private String cartId;
	private String quantity;
	private String remarks;
	private String numGuest;
	
	public CartWrapper(String cartId, String quantity, String remarks, String numGuest) {
		super();
		this.cartId = cartId;
		this.quantity = quantity;
		this.remarks = remarks;
		this.numGuest = numGuest;
	}
	
	public String getNumGuest() {
		return numGuest;
	}

	public void setNumGuest(String numGuest) {
		this.numGuest = numGuest;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "CartWrapper [cartId=" + cartId + ", quantity=" + quantity + ", remarks=" + remarks + ", numGuest="
				+ numGuest + "]";
	}

}
