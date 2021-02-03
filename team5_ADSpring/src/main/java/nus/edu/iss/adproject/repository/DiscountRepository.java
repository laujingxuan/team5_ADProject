package nus.edu.iss.adproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import nus.edu.iss.adproject.model.Discount;

public interface DiscountRepository extends JpaRepository<Discount, Long> {
	
	  @Query("Select d from Discount d where d.hotel.user.id = :userId")
	  List<Discount> findHotelByUserId(@Param("userId")Long UserId);
	  
	  @Query("Select d from Discount d where d.attraction.user.id = :userId")
	  List<Discount> findAttractionByUserId(@Param("userId")Long UserId);
	 
}
