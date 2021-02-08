package nus.edu.iss.adproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import nus.edu.iss.adproject.model.ProductReview;

public interface ProductReviewRepo extends JpaRepository<ProductReview, Long> {

	@Query("Select pr From ProductReview pr where pr.product.id = :cid")
	public List<ProductReview> findReviewByProductId(@Param("cid") Long id);
}
