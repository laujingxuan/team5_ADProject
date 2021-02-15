package nus.edu.iss.adproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import nus.edu.iss.adproject.model.BookingDetails;
import nus.edu.iss.adproject.nonEntityModel.DashboardQuery;

public interface BookingDetailsRepo  extends JpaRepository<BookingDetails, Long>{

	@Query("SELECT b FROM BookingDetails b WHERE b.booking.id = :bookingId")
	public List<BookingDetails> findDetailsById(@Param("bookingId")Long bookingId);
	
	
	/*
	 * @Query("SELECT b FROM BookingDetails b") public List<BookingDetails>
	 * findGuestByMonth();
	 */
	@Query("SELECT b FROM BookingDetails b WHERE b.id = :detailId")
	public BookingDetails findDetailsByDetailId(@Param("detailId")Long detailId);
	
	
	  @Query("SELECT b.booking.bookingDate, sum(b.numOfGuest) as total_guest FROM BookingDetails b where b.product.roomType.hotel.id = :hotel_id Group By month(b.booking.bookingDate), year(b.booking.bookingDate) order by month(b.booking.bookingDate)") 
	  public List<Object> findGuestByMonth(Long hotel_id);
	  
	  
	  @Query("SELECT bd.booking.bookingDate, sum(bd.price) as revenue "
	  		+ "FROM BookingDetails bd where bd.product.roomType.hotel.user.id = :userId and bd.product.roomType.hotel.id = :hotel_id Group By month(bd.booking.bookingDate), year(bd.booking.bookingDate) order by month(bd.booking.bookingDate)")
	  public List<Object> findMonthlyRevenueByHotel(@Param("userId")Long userId, @Param("hotel_id")Long hotel_id);
	  
	  
	  @Query("SELECT bd.booking.bookingDate, count(bd.booking.id) as booking_rate "
		  		+ "FROM BookingDetails bd where bd.product.roomType.hotel.user.id = :userId and bd.product.roomType.hotel.id = :hotel_id Group By month(bd.booking.bookingDate), year(bd.booking.bookingDate) order by month(bd.booking.bookingDate)")
		  public List<Object> findMonthlyBookingRateByHotel(@Param("userId")Long userId, @Param("hotel_id")Long hotel_id);
	  
	  @Query("SELECT b.booking.bookingDate, sum(b.numOfGuest) as total_guest FROM BookingDetails b where b.product.roomType.hotel.id = :hotel_id Group By month(b.booking.bookingDate), year(b.booking.bookingDate) order by month(b.booking.bookingDate)") 
	  public List<Object> findMonthlyGuestByHotelId(@Param("hotel_id")Long hotel_id);
	  
	  
	  @Query("SELECT bd.booking.bookingDate, sum(bd.price) as revenue "
	  		+ "FROM BookingDetails bd where bd.product.roomType.hotel.id = :hotel_id Group By month(bd.booking.bookingDate), year(bd.booking.bookingDate) order by month(bd.booking.bookingDate)")
	  public List<Object> findMonthlyRevenueByHotelId(@Param("hotel_id")Long hotel_id);
	  
	  
	  @Query("SELECT bd.booking.bookingDate, count(bd.booking.id) as booking_rate "
		  		+ "FROM BookingDetails bd where bd.product.roomType.hotel.id = :hotel_id Group By month(bd.booking.bookingDate), year(bd.booking.bookingDate) order by month(bd.booking.bookingDate)")
		  public List<Object> findMonthlyBookingRateByHotelId(@Param("hotel_id")Long hotel_id);
	 
	  
	  @Query("SELECT b.product.roomType.hotel.id as hotel_id, sum(b.price) as monthly_revenue, sum(b.numOfGuest) as monthly_guest FROM BookingDetails b where month(b.booking.bookingDate)=1 and year(b.booking.bookingDate)=2021 Group By month(b.booking.bookingDate), year(b.booking.bookingDate) order by month(b.booking.bookingDate)") 
	  public List<Object> findMonthlyRevenueForAllHotels();
	  
	  @Query("SELECT b.product.attraction.id, sum(b.price) as monthly_revenue, sum(b.numOfGuest) as monthly_guest FROM BookingDetails b where month(b.booking.bookingDate)=1 and year(b.booking.bookingDate)=2021 Group By month(b.booking.bookingDate), year(b.booking.bookingDate) order by month(b.booking.bookingDate)") 
	  public List<Object> findMonthlyRevenueForAllAttractions();
}
