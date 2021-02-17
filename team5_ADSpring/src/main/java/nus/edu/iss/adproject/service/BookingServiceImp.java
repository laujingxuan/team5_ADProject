package nus.edu.iss.adproject.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
	
	public BookingDetails retrieveDetailsByDetailId(Long id) {
		return bookRepoDet.findDetailsByDetailId(id);
		
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
	public List findGuestByMonth(Long hotel_id) {
		return bookRepoDet.findGuestByMonth(hotel_id);
	}

	@Override
	public List<Object> findMonthlyRevenueByHotel(Long userId,Long hotel_id) {
		return bookRepoDet.findMonthlyRevenueByHotel(userId, hotel_id);
	}

	@Override
	public List<Object> findMonthlyBookingRateByHotel(Long userId,Long hotel_id) {
		return bookRepoDet.findMonthlyBookingRateByHotel(userId, hotel_id);
	}

	@Override
	public List<Object> findMonthlyRevenueForAllHotels(Integer month, Integer year) {
		return bookRepoDet.findMonthlyRevenueForAllHotels(month, year);
	}

	@Override
	public List<Object> findMonthlyRevenueForAllAttractions(Integer month, Integer year) {
		return bookRepoDet.findMonthlyRevenueForAllAttractions(month, year);
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
