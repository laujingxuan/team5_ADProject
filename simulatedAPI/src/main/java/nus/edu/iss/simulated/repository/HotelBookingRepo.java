package nus.edu.iss.simulated.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nus.edu.iss.simulated.model.HotelBooking;

public interface HotelBookingRepo extends JpaRepository<HotelBooking, Long> {

}
