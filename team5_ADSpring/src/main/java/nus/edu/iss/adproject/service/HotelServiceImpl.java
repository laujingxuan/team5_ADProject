package nus.edu.iss.adproject.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.edu.iss.adproject.model.Hotel;
import nus.edu.iss.adproject.repository.HotelRepo;

@Service
@Transactional
public class HotelServiceImpl implements HotelService{
	@Autowired
	private HotelRepo hotel_repo;
	
	@Override
	public void save(Hotel x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Hotel findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Hotel> findAll() {
		return hotel_repo.findAll();
	}

	@Override
	public void delete(Hotel x) {
		// TODO Auto-generated method stub
		
	}

}
