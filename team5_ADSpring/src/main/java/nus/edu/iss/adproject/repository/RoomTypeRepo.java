package nus.edu.iss.adproject.repository;

import java.util.ArrayList;
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


//	@Query("Select r From RoomType r where r.hotel_id=:Hid")
//	public List<RoomType> findbyHotelId(@Param("Hid") int hid);



	public void save(@Valid Product product);
	
//	distinct r.roomType as RoomType, r.description as Description
	
	@Query("Select distinct r.roomType as RoomType From RoomType r")
	public ArrayList<Object> findDistinctRoomTypes();


}
