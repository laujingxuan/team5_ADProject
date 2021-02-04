package nus.edu.iss.adproject.repository;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import nus.edu.iss.adproject.model.Product;
import nus.edu.iss.adproject.model.RoomType;

public interface RoomTypeRepo extends JpaRepository<RoomType, Long> {
	

	@Query("Select r From RoomType r where r.hotel.id = :rid")
	public List<RoomType> findRoomTypesByHotelId(@Param("rid") Long id);
	
	

	public List<RoomType> findAll();


	@Query("Select r From RoomType r where r.roomType = :rooT")
	public List<RoomType> findbyName(@Param("rooT")String roomT);



	public void save(@Valid Product product);


}
