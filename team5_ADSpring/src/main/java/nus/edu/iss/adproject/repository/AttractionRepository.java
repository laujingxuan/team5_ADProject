package nus.edu.iss.adproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import nus.edu.iss.adproject.model.Attraction;

public interface AttractionRepository extends JpaRepository<Attraction, Long> {

	
	@Query("Select a From Attraction a where a.product.id = :aid")
	public Attraction findAttractionByProductId(@Param("aid") Long id);
	

	@Query("Select a from Attraction a where a.user.id = :userId")
	List<Attraction> findByUserId(@Param("userId")Long UserId);

}
