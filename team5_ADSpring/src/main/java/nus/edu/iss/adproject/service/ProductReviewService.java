package nus.edu.iss.adproject.service;

import java.util.List;

import javax.validation.Valid;

import nus.edu.iss.adproject.model.ProductReview;

public interface ProductReviewService {
	
	void save(@Valid ProductReview review);
	public List<ProductReview> findReviewByProductId(Long id);
	
}
