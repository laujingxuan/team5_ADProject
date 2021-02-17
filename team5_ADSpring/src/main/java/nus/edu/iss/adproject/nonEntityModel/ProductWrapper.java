package nus.edu.iss.adproject.nonEntityModel;

import java.util.ArrayList;
import java.util.List;

import nus.edu.iss.adproject.model.Product;

public class ProductWrapper {
	List<Product> productlist;

	
	
	
	public ProductWrapper(List<Product> productlist) {
		super();
		this.productlist = productlist;
	}

	public ProductWrapper() {
		super();
		// TODO Auto-generated constructor stub
		productlist = new ArrayList<Product>();
	}

	public List<Product> getProductlist() {
		return productlist;
	}

	public void setProductlist(List<Product> productlist) {
		this.productlist = productlist;
	}
	
	
	
}


