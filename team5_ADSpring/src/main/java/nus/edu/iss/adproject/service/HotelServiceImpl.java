
package nus.edu.iss.adproject.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.edu.iss.adproject.model.Attraction;
import nus.edu.iss.adproject.model.Hotel;
import nus.edu.iss.adproject.repository.HotelRepository;

@Service
@Transactional
public class HotelServiceImpl implements HotelService{
	@Autowired
	private HotelRepository hotelrepo;
	
	@Override
	public void save(Hotel x) {
		// TODO Auto-generated method stub
	}

	@Override
	public Hotel findById(Long id) {
		return hotelrepo.findById(id).get();
	}

	@Override
	public void delete(Hotel x) {
		// TODO Auto-generated method stub
		
	}
	
	@Override 
	public List<Hotel> findAll(){
		return hotelrepo.findAll();
	}
	
	@Override
	public Hotel findHotelByProductId(Long id) {
		return hotelrepo.findHotelByProductId(id);
	}

	@Override
	public List<Hotel> findByUserId(Long userId) {
		return hotelrepo.findByUserId(userId);
	}

	
}

