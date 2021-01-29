package nus.edu.iss.adproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nus.edu.iss.adproject.model.Booking;

public interface BookingRepo extends JpaRepository<Booking, Long> {

}
