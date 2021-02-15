package nus.edu.iss.adproject;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import nus.edu.iss.adproject.model.Attraction;
import nus.edu.iss.adproject.model.Booking;
import nus.edu.iss.adproject.model.BookingDetails;
import nus.edu.iss.adproject.model.Cart;
import nus.edu.iss.adproject.model.Discount;
import nus.edu.iss.adproject.model.Hotel;
import nus.edu.iss.adproject.model.Product;
import nus.edu.iss.adproject.model.ProductReview;
import nus.edu.iss.adproject.model.RoomType;
import nus.edu.iss.adproject.model.TravelPackage;
import nus.edu.iss.adproject.model.User;
import nus.edu.iss.adproject.nonEntityModel.ProductType;
import nus.edu.iss.adproject.nonEntityModel.RoleType;
import nus.edu.iss.adproject.repository.AttractionRepository;
import nus.edu.iss.adproject.repository.BookingDetailsRepo;
import nus.edu.iss.adproject.repository.BookingRepo;
import nus.edu.iss.adproject.repository.CartRepository;
import nus.edu.iss.adproject.repository.HotelRepository;
import nus.edu.iss.adproject.repository.ProductRepo;
import nus.edu.iss.adproject.repository.ProductReviewRepo;
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
	
	@Autowired
	private ProductReviewRepo prrepo;
	
	public static void main(String[] args) {
		SpringApplication.run(Team5AdSpringApplication.class, args);
	}
	
//	//https://howtodoinjava.com/spring-boot2/resttemplate/spring-restful-client-resttemplate-example/
//	@Bean
//	public RestTemplate restTemplate(RestTemplateBuilder builder) {
//	 
//	    return builder
//	            .setConnectTimeout(Duration.ofMillis(3000))
//	            .setReadTimeout(Duration.ofMillis(3000))
//	            .build();
//	}
	
//	@Configuration
//	public class DefaultViewConfig implements WebMvcConfigurer {
//	    @Override
//	    public void addViewControllers(ViewControllerRegistry registry) {
//	        registry.addViewController("/").setViewName("/productslist.html");
//	        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
//	    }
//	}
	

	//Only run during the first time for data insertion
	@Bean
	CommandLineRunner runner() {
		return args -> { 

			
			//Creating travel package
			TravelPackage tp1 = new TravelPackage(2,3,5);
			tpRepo.save(tp1);
			TravelPackage tp2 = new TravelPackage(5,3,7);
			tpRepo.save(tp2);
			TravelPackage tp3 = new TravelPackage(7,3,10);
			tpRepo.save(tp3);
			
			
			//creating User
			User customer1 = new User("customer1","customer1",RoleType.CUSTOMER,"tanfeng95@hotmail.com");
			User customer2 = new User("customer2","customer2",RoleType.CUSTOMER,"customer2@gmail.com");
			User platform1 = new User("platform1","platform1",RoleType.PLATFORMMANAGER,"platform1@gmail.com");
			User hotelM1 = new User("hotel1","hotel1",RoleType.HOTELMANAGER,"hotel1@gmail.com");
			User hotelM2 = new User("hotel2","hotel2",RoleType.HOTELMANAGER,"hotel2@gmail.com");
			User attractionM1 = new User("attraction1","attraction1",RoleType.ATTRACTIONMANAGER,"attraction1@gmail.com");
			User attractionM2 = new User("attraction2","attraction2",RoleType.ATTRACTIONMANAGER,"attraction2@gmail.com");
			User customer3 = new User("customer3","customer3",RoleType.CUSTOMER,"lusicong22@gmail.com");
			userRepo.save(customer1);
			userRepo.save(customer2);
			userRepo.save(platform1);
			userRepo.save(hotelM1);
			userRepo.save(hotelM2);
			userRepo.save(attractionM1);
			userRepo.save(attractionM2);
			userRepo.save(customer3);
			
			
			//Creating Booking
			Booking booking1 = new Booking(customer1, LocalDate.of(2021, 2, 7), 5);
			Booking booking2 = new Booking(customer1, LocalDate.of(2021, 2, 1), 5);
			Booking booking3 = new Booking(customer1, LocalDate.of(2021, 1, 30), 5);
			Booking booking5 = new Booking(customer1, LocalDate.of(2020, 1, 15), 5);
			Booking booking6 = new Booking(customer1, LocalDate.of(2020, 1, 18), 5);
			Booking booking7 = new Booking(customer1, LocalDate.of(2020, 2, 15), 5);
			Booking booking4 = new Booking(customer2, LocalDate.of(2021, 1, 15), 5);
			bookRepo.save(booking1);
			bookRepo.save(booking2);
			bookRepo.save(booking3);
			bookRepo.save(booking4);
			bookRepo.save(booking5);
			bookRepo.save(booking6);
			bookRepo.save(booking7);
			
			
			//Creating Attraction
			Product product1 = new Product(ProductType.ATTRACTION);
			Product product10 = new Product(ProductType.ATTRACTION);
			Product product11 = new Product(ProductType.ATTRACTION);
			productRepo.save(product1);
			productRepo.save(product10);
			productRepo.save(product11);
			Attraction attraction1 = new Attraction("Singapore Zoo", 75, "80 Mandai Lake Rd, 729826", 4, "Zoo with animals", "Singapore", "http://localhost:8081/api/attraction/", attractionM1, product1, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRPRuo0c4vT4LkTUTsMHQKdPad7unhMWYL68A&usqp=CAU",1.4043,103.7930);
			Attraction attraction2 = new Attraction("Universal Studios Singapore", 75, "8 Sentosa Gateway, 098269", 4, "Universal Studios Singapore is a theme park located within Resorts World Sentosa on Sentosa Island, Singapore", "Singapore", "NA", attractionM1, product10, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ0iNHp4S_BJIGknelmLR-x2obdFa9rb7Gh4w&usqp=CAU", 1.2540, 103.8238);
			Attraction attraction3 = new Attraction("S.E.A. Aquarium", 75, "8 Sentosa Gateway, Sentosa Island, 098269", 4, "Large aquarium & resort featuring 800 species of marine life in a variety of habitats.", "Singapore", "NA", attractionM1, product11, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTdlJxOrew9nEOurRCadeDMVZUcCMzLbX4svw&usqp=CAU", 1.2582, 103.8204);
			attractionRepo.save(attraction1);
			attractionRepo.save(attraction2);
			attractionRepo.save(attraction3);
			
			
			
			//Creating Hotel
			//ONLY HOTEL 1 HAS API SETUP
			Product room1 = new Product(ProductType.HOTEL);
			Product room2 = new Product(ProductType.HOTEL);
			Product room3 = new Product(ProductType.HOTEL);
			Product room4 = new Product(ProductType.HOTEL);
			Product room5 = new Product(ProductType.HOTEL);
			productRepo.save(room1);
			productRepo.save(room2);
			productRepo.save(room3);
			productRepo.save(room4);
			productRepo.save(room5);
			Hotel hotel1 = new Hotel("Marina Bay Sands Hotel", "Singapore",1.282302,103.858528, 5, 8, "Singapore","aa", "best", "we try to be home", "http://localhost:8081/api/hotel/", hotelM1 ,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSlHWHuNv8X8UXn9KrVrVu4P5zZMn9GWEVotg&usqp=CAU");
			Hotel hotel2 = new Hotel("Swissôtel The Stamford",  "Singapore ",1.293354,103.853561 , 4, 8,"Singapore","aa", "best", "we try to be home","NA", hotelM1,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ61juiK980qp_59nzk8BZSKCD1LBwLMkrgYg&usqp=CAU");
			Hotel hotel3 = new Hotel("Hotel Miramar", "Singapore",1.288710,103.837372 , 4, 6, "Singapore","aa", "best", "we try to be home","NA", hotelM1, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJQasGWLv85VE--hj4JjvcYvlH6AVu1qpi4A&usqp=CAU");
			htRepo.save(hotel1);
			htRepo.save(hotel2);
			htRepo.save(hotel3);
			
			
			
			//Creating roomType
			RoomType DoubleRoom1 = new RoomType(room3, hotel2, "DOUBLE", "Double bed room", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRfhcmJ1X_UaXdgLx7g71aztPspAMs0onNcBw&usqp=CAU");
			RoomType singleRoom = new RoomType(room1, hotel1, "SINGLE", "Single bed room","https://media-cdn.tripadvisor.com/media/photo-s/0e/e5/f5/d2/small-single-room-at.jpg");
			RoomType singleRoom2 = new RoomType(room4, hotel2, "SINGLE", "Single bed room","https://www.oyster.com/wp-content/uploads/sites/35/2019/05/single-room-v12936474-1440-1024x683.jpg");
			RoomType singleRoom3 = new RoomType(room5, hotel3, "SINGLE", "Single bed room","https://setupmyhotel.com/images/Room-Type-Single-Room.jpg");
			RoomType doubleRoom = new RoomType(room2, hotel1, "DOUBLE", "Double bed room", "https://cdn.traveltripper.io/site-assets/512_855_12327/media/2018-02-27-080021/large_ex-double-1.jpg");
//			RoomType doubleRoom1 = new RoomType(room2, hotel2, "DOUBLE", "Double bed room", "https://www.hotel-solemare.com/photo/diapo1/13/_solemare_hotel_25720180717164604_550xautox75.jpg");
//			RoomType doubleRoom2 = new RoomType(room2, hotel3, "DOUBLE", "Double bed room", "https://www.pavillon-monceau.com/files/hotel/pavillon-monceau/chambres/chambre_standard_.jpg");
			roomTypeRepo.save(singleRoom);
			roomTypeRepo.save(DoubleRoom1);
			roomTypeRepo.save(singleRoom2);
			roomTypeRepo.save(singleRoom3);
			roomTypeRepo.save(doubleRoom);
//			roomTypeRepo.save(doubleRoom1);
//			roomTypeRepo.save(doubleRoom2);
////		    htRepo.save(new Hotel("Swissôtel The Stamford ","Singapore", 1.293354,103.853561, 260.0, 8, "SG","aa", "best", "we try to be home"));
//		    htRepo.save(new Hotel("Hotel Miramar ","Singapore", 1.288710,103.837372, 260.0, 8, "SG","aa", "best", "we try to be home"));
			
			//Creating booking details
			Cart cart1 = new Cart(product1, 3, LocalDate.of(2021, 1, 15), customer1);
			Cart cart2 = new Cart(room1, 2, LocalDate.of(2021, 1, 15), LocalDate.of(2021, 1, 17),2, "No lunch", customer1);
			Cart cart3 = new Cart(room2, 2, LocalDate.of(2021, 12, 15), LocalDate.of(2021, 12, 20),4, "4 lunch", customer1);
			Cart cart4 = new Cart(room1, 4, LocalDate.of(2021, 1, 19), LocalDate.of(2021, 1, 23),2, "2 lunch",customer1);
			cartRepo.save(cart1);
			cartRepo.save(cart2);
			cartRepo.save(cart3);
			cartRepo.save(cart4);
			
			
			//Creating booking details
			BookingDetails bookDetail1 = new BookingDetails(booking1, singleRoom.getProduct(), "1", 4, 300);
			BookingDetails bookDetail2 = new BookingDetails(booking1, singleRoom.getProduct(), "2", 3, 250);
			BookingDetails bookDetail3 = new BookingDetails(booking1, doubleRoom.getProduct(), "3", 5, 400);
			BookingDetails bookDetail4 = new BookingDetails(booking1, attraction1.getProduct(), "2", 2, 100);
			bookDetRepo.save(bookDetail1);
			bookDetRepo.save(bookDetail2);
			bookDetRepo.save(bookDetail3);
			bookDetRepo.save(bookDetail4);	


			BookingDetails bookDetail5 = new BookingDetails(booking2, attraction2.getProduct(), "1", 4, 300);
			BookingDetails bookDetail6 = new BookingDetails(booking2, attraction3.getProduct(), "2", 3, 250);
			BookingDetails bookDetail7 = new BookingDetails(booking3, DoubleRoom1.getProduct(), "3", 5, 400);
			BookingDetails bookDetail8 = new BookingDetails(booking4, singleRoom2.getProduct(), "1", 5, 100);
			bookDetRepo.save(bookDetail5);
			bookDetRepo.save(bookDetail6);
			bookDetRepo.save(bookDetail7);
			bookDetRepo.save(bookDetail8);
  
  
//			ProductReview review1 = new ProductReview(customer1, room1, 4.6, "I think it is comfortable", "pic1");
//			prrepo.save(review1);
//			ProductReview review2 = new ProductReview(customer2, room1, 4.8, "I perfectly like what I ordered", "pic1");
//			prrepo.save(review2);
//			ProductReview review3 = new ProductReview(customer1, product1, 4.7, "Really enjoy the time in the zoo", "pic1");
//			prrepo.save(review3);
//			ProductReview review4 = new ProductReview(customer1, room2, 4.8, "Want to back for several times", "pic1");
//			prrepo.save(review4);
//			ProductReview review5 = new ProductReview(customer2, room2, 4.2, "Just so so", "pic1");
//			prrepo.save(review5);
//			ProductReview review6 = new ProductReview(customer1, room3, 4.8, "Have a good time in the room", "pic1");
//			prrepo.save(review6);
//			ProductReview review7 = new ProductReview(customer2, product1, 4.9, "We had fun!", "pic1");
//			prrepo.save(review7);
//			ProductReview review8 = new ProductReview(customer2, room3, 4.1, "Don't want to reorder anymore", "pic1");
//			prrepo.save(review8);
//			ProductReview review9 = new ProductReview(customer1, room4, 4.8, "Just order without hesitate, it worth!", "pic1");
//			prrepo.save(review9);
//			ProductReview review10 = new ProductReview(customer2, room4, 4.7, "Will live here again the next time to Singapore", "pic1");
//			prrepo.save(review10);
//			ProductReview review11 = new ProductReview(customer1, room5, 4.2, "Better to find another hotel...", "pic1");
//			prrepo.save(review11);
//			ProductReview review12 = new ProductReview(customer2, room5, 4.3, "It is ok, but not best", "pic1");
//			prrepo.save(review12);
//			


		};
	}
}

	
	
