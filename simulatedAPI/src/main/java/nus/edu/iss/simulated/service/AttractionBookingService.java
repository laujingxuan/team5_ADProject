package nus.edu.iss.simulated.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.edu.iss.simulated.model.AttractionBooking;
import nus.edu.iss.simulated.repository.AttractionBookingRepo;


public interface  AttractionBookingService  {
	
	
	public List<AttractionBooking> findAllBooking();
	public AttractionBooking findBookingById(long id);
	public AttractionBooking createBooking(AttractionBooking attractionBooking);
	

	
	
	
	
}
