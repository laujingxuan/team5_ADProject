//package nus.edu.iss.adproject;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import nus.edu.iss.adproject.model.Hotel;
//import nus.edu.iss.adproject.repository.HotelRepository;
//
//@SpringBootTest
//public class HotelTest {
//	@Autowired
//	HotelRepository htRepo;
//	
//	@Test
//	public void saveHotel() {
//		htRepo.save(new Hotel("Marina Bay Sands Hotel", "Singapore",1.282302,103.858528, 400.0, 8, "SG","aa", "best", "we try to be home"));
//        htRepo.save(new Hotel("Swissôtel The Stamford ","Singapore", 1.293354,103.853561, 260.0, 8, "SG","aa", "best", "we try to be home"));
//        htRepo.save(new Hotel("Hotel Miramar ","Singapore", 1.288710,103.837372, 260.0, 8, "SG","aa", "best", "we try to be home"));
//	}
//}
package nus.edu.iss.adproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import nus.edu.iss.adproject.model.Hotel;
import nus.edu.iss.adproject.repository.HotelRepository;

@SpringBootTest
public class HotelTest {
	/*
	 * @Autowired HotelRepository htRepo;
	 * 
	 * @Test public void saveHotel() {
	 * 
	 * htRepo.save(new Hotel("Marina Bay Sands Hotel ", "1.282302,103.858528",
	 * 400.0, 8, "SG", "aa", "best", "we try to be home")); htRepo.save(new
	 * Hotel("Swissôtel The Stamford ", "1.293354,103.853561", 260.0, 8, "SG", "aa",
	 * "best", "we try to be home")); htRepo.save( new Hotel("Hotel Miramar ",
	 * "1.288710,103.837372", 260.0, 8, "SG", "aa", "best", "we try to be home"));
	 * 
	 * }
	 */
}
