package nus.edu.iss.adproject.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.edu.iss.adproject.model.Booking;
import nus.edu.iss.adproject.model.BookingDetails;
import nus.edu.iss.adproject.model.User;
import nus.edu.iss.adproject.nonEntityModel.DashboardQuery;
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
	public List findGuestByMonth() {
		return bookRepoDet.findGuestByMonth();
	}

	@Override
	public List<Object> findMonthlyRevenueByHotel(Long userId) {
		return bookRepoDet.findMonthlyRevenueByHotel(userId);
	}

	@Override
	public List<Object> findMonthlyBookingRateByHotel(Long userId) {
		return bookRepoDet.findMonthlyBookingRateByHotel(userId);
	}

	@Override
	public List<Object> findMonthlyRevenueForAllHotels() {
		return bookRepoDet.findMonthlyRevenueForAllHotels();
	}

	@Override
	public List<Object> findMonthlyRevenueForAllAttractions() {
		return bookRepoDet.findMonthlyRevenueForAllAttractions();
	}

	@Override
	public List<Object> findMonthlyGuestByHotelId(Long hotel_id) {
		return bookRepoDet.findMonthlyGuestByHotelId(hotel_id);
	}

	@Override
	public List<Object> findMonthlyRevenueByHotelId(Long hotel_id) {
		return bookRepoDet.findMonthlyRevenueByHotelId(hotel_id);
	}

	@Override
	public List<Object> findMonthlyBookingRateByHotelId(Long hotel_id) {
		return bookRepoDet.findMonthlyBookingRateByHotelId(hotel_id);
	}

	
}
