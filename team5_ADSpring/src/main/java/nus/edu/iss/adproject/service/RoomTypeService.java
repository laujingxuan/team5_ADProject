package nus.edu.iss.adproject.service;

import java.util.ArrayList;
import java.util.List;

import nus.edu.iss.adproject.model.RoomType;

public interface RoomTypeService {

	List<RoomType> findRoomTypesByHotelId(Long id);

	RoomType findById(Long id);
  
  	List<RoomType> findAll();

//	List<RoomType> findbyHotelId(int hid);

	public RoomType save(RoomType x);

	void delete(RoomType rtype);
	
	ArrayList<Object> findDistinctRoomTypes();

}
