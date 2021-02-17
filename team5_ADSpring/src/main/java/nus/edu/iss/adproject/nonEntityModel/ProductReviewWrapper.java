package nus.edu.iss.adproject.nonEntityModel;

import java.util.ArrayList;
import java.util.List;

import nus.edu.iss.adproject.model.ProductReview;

public class ProductReviewWrapper {
	List<ProductReview> productReviewList;

	public ProductReviewWrapper() {
		super();
		// TODO Auto-generated constructor stub
		productReviewList = new ArrayList<ProductReview>();
	}

	public ProductReviewWrapper(List<ProductReview> productReviewList) {
		super();
		this.productReviewList = productReviewList;
	}

	public List<ProductReview> getProductReviewList() {
		return productReviewList;
	}

	public void setProductReviewList(List<ProductReview> productReviewList) {
		this.productReviewList = productReviewList;
	}
	
	
}
