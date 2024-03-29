
package nus.edu.iss.adproject.service;

import java.util.List;

import nus.edu.iss.adproject.model.Hotel;

public interface HotelService extends IService<Hotel>{

	
	Hotel findHotelByProductId(Long id);
	public Hotel findByHotelIdAndUserId(Long hotelId, Long userId);
	public List<Hotel> findByUserId(Long userId);

}
