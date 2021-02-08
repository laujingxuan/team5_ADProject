package nus.edu.iss.adproject.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.edu.iss.adproject.model.ProductReview;
import nus.edu.iss.adproject.repository.ProductReviewRepo;

@Service
public class ProductReviewServiceImpl implements ProductReviewService{
	
	@Autowired
	ProductReviewRepo prrepo;
	
	
	@Transactional
	public void save (ProductReview review) {
		prrepo.save(review);
	}
	
	
	@Transactional
	public List<ProductReview> findReviewByProductId(Long id) {
		return prrepo.findReviewByProductId(id);
	}
	
}
