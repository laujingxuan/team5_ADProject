package nus.edu.iss.adproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nus.edu.iss.adproject.model.ProductReview;

public interface ProductReviewRepo extends JpaRepository<ProductReview, Long> {

}
