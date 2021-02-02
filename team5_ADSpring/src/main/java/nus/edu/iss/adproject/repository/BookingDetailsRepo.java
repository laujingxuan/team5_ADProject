package nus.edu.iss.adproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import nus.edu.iss.adproject.model.BookingDetails;

public interface BookingDetailsRepo  extends JpaRepository<BookingDetails, Long>{

	@Query("SELECT b FROM BookingDetails b WHERE b.booking.id = :bookingId")
	public List<BookingDetails> findDetailsById(@Param("bookingId")Long bookingId);
}
