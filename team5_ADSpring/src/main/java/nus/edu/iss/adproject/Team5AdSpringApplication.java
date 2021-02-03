package nus.edu.iss.adproject;

import java.time.Duration;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import nus.edu.iss.adproject.NonEntityModel.ProductType;
import nus.edu.iss.adproject.model.Attraction;
import nus.edu.iss.adproject.model.Booking;
import nus.edu.iss.adproject.model.BookingDetails;
import nus.edu.iss.adproject.model.Cart;
import nus.edu.iss.adproject.model.Hotel;
import nus.edu.iss.adproject.model.Product;
import nus.edu.iss.adproject.model.RoleType;
import nus.edu.iss.adproject.model.RoomType;
import nus.edu.iss.adproject.model.TravelPackage;
import nus.edu.iss.adproject.model.User;
import nus.edu.iss.adproject.repository.AttractionRepository;
import nus.edu.iss.adproject.repository.BookingDetailsRepo;
import nus.edu.iss.adproject.repository.BookingRepo;
import nus.edu.iss.adproject.repository.CartRepository;
import nus.edu.iss.adproject.repository.HotelRepository;
import nus.edu.iss.adproject.repository.ProductRepo;
import nus.edu.iss.adproject.repository.RoomTypeRepo;
import nus.edu.iss.adproject.repository.TravelPackageRepository;
import nus.edu.iss.adproject.repository.UserRepository;



@SpringBootApplication
public class Team5AdSpringApplication {

	@Autowired
	private TravelPackageRepository tpRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BookingRepo bookRepo;
	
	@Autowired
	private BookingDetailsRepo bookDetRepo;
	
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private AttractionRepository attractionRepo;
	
	@Autowired
	private HotelRepository htRepo;
	
	@Autowired
	private RoomTypeRepo roomTypeRepo;
	
	@Autowired
	private CartRepository cartRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(Team5AdSpringApplication.class, args);
	}
	
	//https://howtodoinjava.com/spring-boot2/resttemplate/spring-restful-client-resttemplate-example/
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	 
	    return builder
	            .setConnectTimeout(Duration.ofMillis(3000))
	            .setReadTimeout(Duration.ofMillis(3000))
	            .build();
	}

	//Only run during the first time for data insertion
	@Bean
	CommandLineRunner runner() {
		return args -> { 
			TravelPackage tp1 = new TravelPackage(2,3,5);
			tpRepo.save(tp1);
			TravelPackage tp2 = new TravelPackage(5,3,7);
			tpRepo.save(tp2);
			TravelPackage tp3 = new TravelPackage(7,3,10);
			tpRepo.save(tp3);
			
			//creating User
			User customer1 = new User("customer1","customer1",RoleType.CUSTOMER,"customer1@gmail.com");
			User customer2 = new User("customer2","customer2",RoleType.CUSTOMER,"customer2@gmail.com");
			User platform1 = new User("platform1","platform1",RoleType.PLATFORMMANAGER,"platform1@gmail.com");
			userRepo.save(customer1);
			userRepo.save(customer2);
			userRepo.save(platform1);
			
			Booking booking1 = new Booking(customer1, LocalDate.of(2021, 1, 15), 5);
			Booking booking2 = new Booking(customer1, LocalDate.of(2021, 1, 18), 5);
			Booking booking3 = new Booking(customer1, LocalDate.of(2021, 2, 15), 5);
			Booking booking4 = new Booking(customer2, LocalDate.of(2021, 1, 15), 5);
			bookRepo.save(booking1);
			bookRepo.save(booking2);
			bookRepo.save(booking3);
			bookRepo.save(booking4);
			
			Product product1 = new Product(ProductType.ATTRACTION);
			productRepo.save(product1);
			Attraction attraction1 = new Attraction("zoo", 75, "Singapore Jalan 1", 4, "Zoo with animals", "Singapore", product1, "http://localhost:8081/api/attraction/");
			attractionRepo.save(attraction1);
			
			Product room1 = new Product(ProductType.HOTEL);
			Product room2 = new Product(ProductType.HOTEL);
			productRepo.save(room1);
			productRepo.save(room2);
			Hotel hotel1 = new Hotel("Marina Bay Sands Hotel", "Singapore",1.282302,103.858528, 400.0, 8, "SG","aa", "best", "we try to be home", "http://localhost:8081/api/hotel/");
			htRepo.save(hotel1);
			RoomType singleRoom = new RoomType(room1, hotel1, "SINGLE", "Single bed room", "http:...");
			RoomType doubleRoom = new RoomType(room2, hotel1, "DOUBLE", "Double bed room", "http:...");
			roomTypeRepo.save(singleRoom);
			roomTypeRepo.save(doubleRoom);
//		    htRepo.save(new Hotel("Swissôtel The Stamford ","Singapore", 1.293354,103.853561, 260.0, 8, "SG","aa", "best", "we try to be home"));
//		    htRepo.save(new Hotel("Hotel Miramar ","Singapore", 1.288710,103.837372, 260.0, 8, "SG","aa", "best", "we try to be home"));
			
			Cart cart1 = new Cart(product1, 3, LocalDate.of(2021, 1, 15), customer1);
			Cart cart2 = new Cart(room1, 2, LocalDate.of(2021, 1, 15), LocalDate.of(2021, 1, 17),2, "No lunch", customer1);
			Cart cart3 = new Cart(room2, 2, LocalDate.of(2021, 1, 15), LocalDate.of(2021, 1, 20),4, "4 lunch", customer1);
			Cart cart4 = new Cart(room1, 2, LocalDate.of(2021, 1, 15), LocalDate.of(2021, 1, 16),2, "2 lunch",customer1);
			cartRepo.save(cart1);
			cartRepo.save(cart2);
			cartRepo.save(cart3);
			cartRepo.save(cart4);
			
			BookingDetails bookDetail1 = new BookingDetails(booking1, singleRoom.getProduct(), "1", 4, 300);
			BookingDetails bookDetail2 = new BookingDetails(booking1, singleRoom.getProduct(), "2", 3, 250);
			BookingDetails bookDetail3 = new BookingDetails(booking1, doubleRoom.getProduct(), "3", 5, 400);
			BookingDetails bookDetail4 = new BookingDetails(booking1, attraction1.getProduct(), "1", 5, 100);
			bookDetRepo.save(bookDetail1);
			bookDetRepo.save(bookDetail2);
			bookDetRepo.save(bookDetail3);
			bookDetRepo.save(bookDetail4);
		};
	}
}
