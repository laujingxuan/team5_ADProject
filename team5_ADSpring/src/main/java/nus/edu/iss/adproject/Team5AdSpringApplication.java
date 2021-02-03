package nus.edu.iss.adproject;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import nus.edu.iss.adproject.model.Attraction;
import nus.edu.iss.adproject.model.Hotel;
import nus.edu.iss.adproject.model.Product;
import nus.edu.iss.adproject.model.RoomType;
import nus.edu.iss.adproject.model.TravelPackage;
import nus.edu.iss.adproject.repository.AttractionRepository;
import nus.edu.iss.adproject.repository.HotelRepository;
import nus.edu.iss.adproject.repository.ProductRepo;
import nus.edu.iss.adproject.repository.RoomTypeRepo;
import nus.edu.iss.adproject.repository.TravelPackageRepository;



@SpringBootApplication
public class Team5AdSpringApplication {

	@Autowired
	private TravelPackageRepository tpRepo;
	
	@Autowired
	private HotelRepository hrepo;
	
	@Autowired
	private ProductRepo prepo;
	
	@Autowired
	private AttractionRepository arepo;
	
	@Autowired
	private RoomTypeRepo rrepo;
	
	public static void main(String[] args) {
		SpringApplication.run(Team5AdSpringApplication.class, args);
	}

//    //Only run during the first time for data insertion
//	@Bean
//	CommandLineRunner runner() {
//		return args -> { 
//			TravelPackage tp1 = new TravelPackage(2,3,5);
//			tpRepo.save(tp1);
//			TravelPackage tp2 = new TravelPackage(5,3,7);
//			tpRepo.save(tp2);
//			TravelPackage tp3 = new TravelPackage(7,3,10);
//			tpRepo.save(tp3);
//			
//			Product p1 = new Product("Attraction");
//			prepo.save(p1);
//			Product p2 = new Product("Hotel");
//			prepo.save(p2);
//			Product p3 = new Product("Hotel");
//			prepo.save(p3);
//			Product p4 = new Product("Attraction");
//			prepo.save(p4);
//			Product p5 = new Product("Hotel");
//			prepo.save(p5);
//			Product p6 = new Product("Hotel");
//			prepo.save(p6);
//	
//			
//			Hotel h1 = new Hotel("King Crown", "Clementi", 4.0, 4.0, 4.5, 5, "Singapore", "nothing", "well", "good place");
//			hrepo.save(h1);
//	    	Hotel h2 = new Hotel("Happy Life", "Alexandra", 4.0, 4.0, 4.5, 5, "Singapore","nothing", "well", "unique place");
//			hrepo.save(h2);
//			
//			
//			RoomType r1 = new RoomType(p2, h1, 188, "Single Room", "Relaxing", "http://image1");
//			rrepo.save(r1);
//			RoomType r2 = new RoomType(p3, h1, 372, "Double Room", "Enjoying", "http://image2");
//			rrepo.save(r2);
//			List<RoomType> rtl1 = new ArrayList<>();
//			rtl1.add(r1);
//			rtl1.add(r2);
//			
//			RoomType r3 = new RoomType(p5, h2, 298, "Double Room", "Relaxing", "http://image3");
//			rrepo.save(r3);
//			RoomType r4 = new RoomType(p6, h2, 355, "Family Room", "Harmonious", "http://image4");
//			rrepo.save(r4);
//			List<RoomType> rtl2 = new ArrayList<>();
//			rtl2.add(r3);
//			rtl2.add(r4);
//			
//			
//			Attraction a1 = new Attraction("Birds Zoo",36.0,"Jurong",4.6,"Can see multiple category birds","Singapore", p1);
//			arepo.save(a1);
//			Attraction a2 = new Attraction("SEA Acquarium",38.0,"Sentosa",4.8,"Explore worlds under water","Singapore", p4);
//			arepo.save(a2);
//			
//		};
//	}
}
