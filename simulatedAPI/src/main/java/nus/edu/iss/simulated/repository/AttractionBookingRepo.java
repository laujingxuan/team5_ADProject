package nus.edu.iss.simulated.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nus.edu.iss.simulated.model.AttractionBooking;

public interface AttractionBookingRepo extends JpaRepository<AttractionBooking, Long> {

}
