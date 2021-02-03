package nus.edu.iss.adproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import nus.edu.iss.adproject.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long>{

	 @Query("Select p From Product p where p.attraction.name LIKE %?1%"
		+ " OR p.attraction.price LIKE %?1%"
	 	+ " OR p.attraction.location LIKE %?1%"
	 	+ " OR p.attraction.rating LIKE %?1%"
	 	+ " OR p.attraction.description LIKE %?1%"
	 	+ " OR p.attraction.country_city LIKE %?1%")
	 public List<Product> search1(String keyword);
	 
	 
	 @Query("Select p From Product p where p.roomType.hotel.name LIKE %?1%"
       + " OR p.roomType.hotel.location LIKE %?1%"
       + " OR p.roomType.hotel.lat LIKE %?1%"  
       + " OR p.roomType.hotel.longi LIKE %?1%"  
       + " OR p.roomType.hotel.rate LIKE %?1%"  
       + " OR p.roomType.hotel.numberOfRestaurants LIKE %?1%"  
       + " OR p.roomType.hotel.country_City LIKE %?1%"  
       + " OR p.roomType.hotel.emenities LIKE %?1%"
       + " OR p.roomType.hotel.quality LIKE %?1%"  
       + " OR p.roomType.hotel.description LIKE %?1%")
//  + " OR p.roomType.price LIKE %?1%"
//  + " OR p.roomType.roomType LIKE %?1%" 
//  + " OR p.roomType.description LIKE %?1%" 
//  + " OR p.roomType.hotel.name LIKE %?1%"   )
	 public List<Product> search2(String keyword);
	 
	 
}
