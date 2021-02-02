package nus.edu.iss.adproject.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.edu.iss.adproject.model.Booking;
import nus.edu.iss.adproject.model.User;
import nus.edu.iss.adproject.repository.BookingRepo;

@Service
@Transactional
public class BookingServiceImp implements BookingService {

	@Autowired
	private BookingRepo bookRepo;

	@Override
	public List<Booking> retrieveUserBooking(User user) {
		return bookRepo.findBookingsByUserId(user.getId());
	}
	
}
