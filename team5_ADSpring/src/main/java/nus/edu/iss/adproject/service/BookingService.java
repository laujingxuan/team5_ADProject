package nus.edu.iss.adproject.service;

import java.util.List;

import nus.edu.iss.adproject.model.Booking;
import nus.edu.iss.adproject.model.BookingDetails;
import nus.edu.iss.adproject.model.User;
import nus.edu.iss.adproject.nonEntityModel.DashboardQuery;

public interface BookingService {

	public List<Booking> retrieveUserBooking(User user);
	
	public List<BookingDetails> retrieveDetailsByBookingId(Long id);
	
	public Booking saveBooking(Booking booking);

	public BookingDetails saveBookingDetails(BookingDetails bookD);


	public List<Booking> findLatestBookingsByUser(User user);

	public List<Booking> findPastOneMonthBookings();

	
	public List<Object> findGuestByMonth();
	
	public List<Object> findMonthlyRevenueByHotel(Long userId);
	
	public List<Object> findMonthlyBookingRateByHotel(Long userId);
	
	public List<Object> findMonthlyRevenueForAllHotels();
	
	public List<Object> findMonthlyRevenueForAllAttractions();
	
	public List<Object> findMonthlyGuestByHotelId(Long hotel_id);
	
	public List<Object> findMonthlyRevenueByHotelId(Long hotel_id);
	
	public List<Object> findMonthlyBookingRateByHotelId(Long hotel_id);

}
