package nus.edu.iss.adproject;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import nus.edu.iss.adproject.model.Hotel;
import nus.edu.iss.adproject.repository.HotelRepository;

@SpringBootTest
public class HotetTEST {

	@Autowired
	private HotelRepository htRepo;
	@Test
	void htcreate()
	{
		
		htRepo.save(new Hotel("Marina Bay Sands Hotel ", 1.282302,103.858528 , 400.0, 8, "SG","aa", "best", "we try to be home"));
		htRepo.save(new Hotel("Swissôtel The Stamford ", 1.293354,103.853561 , 260.0, 8, "SG","aa", "best", "we try to be home"));
		htRepo.save(new Hotel("Hotel Miramar ", 1.288710,103.837372 , 260.0, 8, "SG","aa", "best", "we try to be home"));
//		ArrayList<Hotel> plist = new ArrayList<Hotel>();		
//		plist = (ArrayList<Hotel>) htRepo.findAll();
//		
//		System.out.println("Print all products");
//		for (Iterator<Hotel> iterator = plist.iterator(); iterator.hasNext();) {
//			Hotel product =  iterator.next();
//			System.out.println(product.toString());
//		}
	}
	
}
