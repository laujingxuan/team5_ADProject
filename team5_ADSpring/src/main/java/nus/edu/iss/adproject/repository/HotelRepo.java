package nus.edu.iss.adproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nus.edu.iss.adproject.model.Hotel;

public interface HotelRepo extends JpaRepository<Hotel, Long> {

}
