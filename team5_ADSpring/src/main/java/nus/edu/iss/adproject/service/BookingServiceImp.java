package nus.edu.iss.adproject.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
	
	@Override
	public List<Booking> findLatestBookingsByUser(User user){
		return bookRepo.findFiveLatestBookingsByUserId(user.getId(), PageRequest.of(0, 5));
	}
	
	@Override
	public List<Booking> findPastOneMonthBookings(){
		LocalDate todayDate = LocalDate.now();
		LocalDate oneMonthBefore = todayDate.minusMonths(1);
		return bookRepo.findByBookingDateBetween(oneMonthBefore, todayDate);
	}
	
}
