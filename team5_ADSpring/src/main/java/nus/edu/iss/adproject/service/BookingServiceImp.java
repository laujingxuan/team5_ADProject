package nus.edu.iss.adproject.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.edu.iss.adproject.model.Booking;
import nus.edu.iss.adproject.model.BookingDetails;
import nus.edu.iss.adproject.model.User;
import nus.edu.iss.adproject.repository.BookingDetailsRepo;
import nus.edu.iss.adproject.repository.BookingRepo;

@Service
@Transactional
public class BookingServiceImp implements BookingService {

	@Autowired
	private BookingRepo bookRepo;
	
	@Autowired
	private BookingDetailsRepo bookRepoDet;

	@Override
	public List<Booking> retrieveUserBooking(User user) {
		return bookRepo.findBookingsByUserId(user.getId());
	}

	@Override
	public List<BookingDetails> retrieveDetailsByBookingId(Long id) {
		return bookRepoDet.findDetailsById(id);
	}

	@Override
	public Booking saveBooking(Booking booking) {
		return bookRepo.save(booking);
	}
	
	@Override
	public BookingDetails saveBookingDetails(BookingDetails bookD) {
		return bookRepoDet.save(bookD);
	}
	
}
