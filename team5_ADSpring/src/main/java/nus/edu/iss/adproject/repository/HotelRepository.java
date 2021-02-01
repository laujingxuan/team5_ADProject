package nus.edu.iss.adproject.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import nus.edu.iss.adproject.model.Hotel;
import nus.edu.iss.adproject.model.Product;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
	public Hotel findById(long a);

}
