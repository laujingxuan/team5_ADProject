package nus.edu.iss.simulated.service;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.edu.iss.simulated.model.AttractionBooking;
import nus.edu.iss.simulated.repository.AttractionBookingRepo;

@Service
@Transactional
public class AttractionBookingServiceImpl  implements AttractionBookingService  {

	@Autowired
	private AttractionBookingRepo ABrepo;
	
	

	@Override
	public AttractionBooking findBookingById(long id) {
		return  ABrepo.findById(id).get();
	}

	@Override
	public AttractionBooking createBooking(AttractionBooking attractionBooking) {
		
		return ABrepo.save(attractionBooking);
		
	}

	@Override
	public List<AttractionBooking> findAllBooking() {
		// TODO Auto-generated method stub
		return ABrepo.findAll();
	}
}
