package nus.edu.iss.adproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import nus.edu.iss.adproject.model.Hotel;
import nus.edu.iss.adproject.repository.HotelRepository;


public interface HotelService {

	
	List<Hotel> findAll();

	public Optional<Hotel> findById(Long id);

}
