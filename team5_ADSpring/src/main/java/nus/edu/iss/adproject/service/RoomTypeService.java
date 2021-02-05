package nus.edu.iss.adproject.service;

import java.util.List;

import nus.edu.iss.adproject.model.RoomType;

public interface RoomTypeService {

	List<RoomType> findRoomTypesByHotelId(Long id);

	RoomType findById(Long id);
  
  	List<RoomType> findAll();

	List<RoomType> findbyName(String roomT);

	void save(RoomType x);

}
