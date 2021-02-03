
package nus.edu.iss.adproject.service;

import nus.edu.iss.adproject.model.Hotel;

public interface HotelService extends IService<Hotel>{
	
	Hotel findHotelByProductId(Long id);
	
}
