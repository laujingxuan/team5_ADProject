package nus.edu.iss.adproject.service;

import java.util.List;

import nus.edu.iss.adproject.model.Booking;
import nus.edu.iss.adproject.model.BookingDetails;
import nus.edu.iss.adproject.model.User;

public interface BookingService {

	public List<Booking> retrieveUserBooking(User user);
	
	public List<BookingDetails> retrieveDetailsByBookingId(Long id);
	
	public Booking saveBooking(Booking booking);

	public BookingDetails saveBookingDetails(BookingDetails bookD);
	
	public List<Object> findGuestByMonth();
}
