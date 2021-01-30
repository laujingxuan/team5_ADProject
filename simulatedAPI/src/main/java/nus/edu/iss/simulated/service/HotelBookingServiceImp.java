package nus.edu.iss.simulated.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.edu.iss.simulated.model.HotelBooking;
import nus.edu.iss.simulated.repository.HotelBookingRepo;

@Service
@Transactional
public class HotelBookingServiceImp implements HotelBookingService {

	@Autowired
	private HotelBookingRepo hotelBookRepo;
	
	@Override
	public HotelBooking findBookingById(long Id) {
		return hotelBookRepo.findById(Id).get();
	}

	@Override
	public HotelBooking createBooking(HotelBooking hotelBooking) {
		return hotelBookRepo.save(hotelBooking);
	}

}
