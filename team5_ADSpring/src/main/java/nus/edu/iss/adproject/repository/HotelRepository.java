package nus.edu.iss.adproject.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import nus.edu.iss.adproject.model.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
	public Hotel findById(long a);

	@Query("Select h From Hotel h Join h.roomType r where r.product.id = :hid")
	public Hotel findHotelByProductId(@Param("hid") Long id);
	
}
