package nus.edu.iss.adproject.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import nus.edu.iss.adproject.model.Booking;

public interface BookingRepo extends JpaRepository<Booking, Long> {

	@Query("SELECT b FROM Booking b WHERE b.user.id = :userId")
	public List<Booking> findBookingsByUserId(@Param("userId")Long userId);
	
	@Query("SELECT b FROM Booking b WHERE b.user.id = :userId ORDER BY b.bookingDate DESC")
	public List<Booking> findFiveLatestBookingsByUserId(@Param("userId") Long userId, Pageable pageable);
	
	public List<Booking> findByBookingDateBetween(LocalDate startD, LocalDate endD);
}
