
package nus.edu.iss.adproject.service;

import nus.edu.iss.adproject.model.Hotel;

public interface HotelService extends IService<Hotel>{

	List<Hotel> findAll();

	public Optional<Hotel> OptionalFindById(Long id);

}
