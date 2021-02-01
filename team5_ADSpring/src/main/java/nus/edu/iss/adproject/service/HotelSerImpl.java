package nus.edu.iss.adproject.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.edu.iss.adproject.model.Hotel;
import nus.edu.iss.adproject.repository.HotelRepository;

@Transactional
@Service("HotelService")
public class HotelSerImpl implements HotelService {

	@Autowired
	 HotelRepository hotelrepo;
	
	@Override 
	public List<Hotel> findAll(){
		return hotelrepo.findAll();
	}
	@Override 
	public Optional<Hotel> OptionalFindById(Long id){
		return hotelrepo.findById(id);
	}
}
