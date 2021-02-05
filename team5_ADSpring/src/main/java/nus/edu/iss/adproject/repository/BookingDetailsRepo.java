package nus.edu.iss.adproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import nus.edu.iss.adproject.model.BookingDetails;

public interface BookingDetailsRepo  extends JpaRepository<BookingDetails, Long>{

	@Query("SELECT b FROM BookingDetails b WHERE b.booking.id = :bookingId")
	public List<BookingDetails> findDetailsById(@Param("bookingId")Long bookingId);
	
	
	/*
	 * @Query("SELECT b FROM BookingDetails b") public List<BookingDetails>
	 * findGuestByMonth();
	 */
	
	
	  @Query("SELECT b.booking.bookingDate, sum(b.numOfGuest) as total_guest FROM BookingDetails b Group By month(b.booking.bookingDate), year(b.booking.bookingDate) order by month(b.booking.bookingDate)") 
	  public List<Object> findGuestByMonth();
	  
	  
	  @Query("SELECT bd.booking.bookingDate, sum(bd.price) as revenue "
	  		+ "FROM BookingDetails bd where bd.product.roomType.hotel.user.id = :userId Group By month(bd.booking.bookingDate), year(bd.booking.bookingDate) order by month(bd.booking.bookingDate)")
	  public List<Object> findMonthlyRevenueByHotel(@Param("userId")Long userId);
	  
	  
	  @Query("SELECT bd.booking.bookingDate, count(bd.booking.id) as booking_rate "
		  		+ "FROM BookingDetails bd where bd.product.roomType.hotel.user.id = :userId Group By month(bd.booking.bookingDate), year(bd.booking.bookingDate) order by month(bd.booking.bookingDate)")
		  public List<Object> findMonthlyBookingRateByHotel(@Param("userId")Long userId);
	 
}
