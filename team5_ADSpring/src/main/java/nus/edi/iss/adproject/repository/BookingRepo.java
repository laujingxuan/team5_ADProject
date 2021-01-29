package nus.edi.iss.adproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nus.edi.iss.adproject.model.Booking;

public interface BookingRepo extends JpaRepository<Booking, Long> {

}
