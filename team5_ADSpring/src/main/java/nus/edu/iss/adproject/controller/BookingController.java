package nus.edu.iss.adproject.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import nus.edu.iss.adproject.NonEntityModel.AttractionBooking;
import nus.edu.iss.adproject.NonEntityModel.BookingWrapper;
import nus.edu.iss.adproject.NonEntityModel.DailyRoomDetailWrapper;
import nus.edu.iss.adproject.NonEntityModel.DailyRoomTypeDetail;
import nus.edu.iss.adproject.NonEntityModel.HotelBooking;
import nus.edu.iss.adproject.NonEntityModel.MultipleDateQuery;
import nus.edu.iss.adproject.NonEntityModel.ProductType;
import nus.edu.iss.adproject.model.Booking;
import nus.edu.iss.adproject.model.BookingDetails;
import nus.edu.iss.adproject.model.Cart;
import nus.edu.iss.adproject.model.User;
import nus.edu.iss.adproject.repository.BookingDetailsRepo;
import nus.edu.iss.adproject.service.BookingService;
import nus.edu.iss.adproject.service.CartService;
import nus.edu.iss.adproject.service.SessionService;
import nus.edu.iss.adproject.service.TravelPackageService;

@Controller
@RequestMapping("/booking")
public class BookingController {

	@Autowired
	private BookingService bookService;
	
	@Autowired
	private SessionService session_svc;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private TravelPackageService travelPacService;
	
	@GetMapping("/list")
	public String showBookings(Model model, HttpSession session) {
		if (session_svc.isNotLoggedIn(session)) return "redirect:/user/login";
		User user = (User) session.getAttribute("user");
		model.addAttribute("BookingsList", bookService.retrieveUserBooking(user));
		return "bookingList";
	}
	
	@GetMapping("/{id}")
	public String showBookingDetails(@PathVariable("id") Long id, HttpSession session, Model model) {
		if (session_svc.isNotLoggedIn(session)) return "redirect:/user/login";
		List<BookingDetails> detailsList = bookService.retrieveDetailsByBookingId(id);
		Map<BookingDetails,BookingWrapper> bookingMap = new HashMap<>();
		for (BookingDetails b: detailsList) {
			String uri;
			Map<String, String> params = new HashMap<String, String>();
			params.put("id", b.getAPIBookingId());
			RestTemplate restTemplate = new RestTemplate();
			BookingWrapper booking;
			if (b.getProduct().getType() == ProductType.ATTRACTION) {
				uri = b.getProduct().getAttraction().getAPI_URL() + "{id}";
				booking = restTemplate.getForObject(uri, AttractionBooking.class, params);
			}else {
				uri = b.getProduct().getRoomType().getHotel().getAPI_URL()+ "booking/{id}";	
				booking = restTemplate.getForObject(uri, HotelBooking.class, params);
			}
			System.out.println("book" + booking);
			bookingMap.put(b, booking);
		}
		model.addAttribute("bookingDetails", bookingMap);
		return "bookingDetails";
	}
	
	@GetMapping("/makeBook")
	public String makeBooking(HttpSession session) {
		if (session_svc.isNotLoggedIn(session)) return "redirect:/user/login";
		User user = (User) session.getAttribute("user");
		List<Cart> carts = cartService.retrieveByUserId(user.getId());
		System.out.println(1);
		Booking newBooking = createBooking(carts,user);
		System.out.println(newBooking);
		for (Cart cart: carts) {
			if (cart.getProduct().getType()==ProductType.HOTEL) {
				double beforeDiscountPrice = getTotalNightPrice(cart);
				double afterDiscountPrice = beforeDiscountPrice * (100-newBooking.getTravelPackageDiscount()) / 100;
				System.out.println(2);
				//hotel side will be storing before discount price while we store after discount price;
				HotelBooking hotelBook = new HotelBooking(cart.getProduct().getRoomType().getRoomType(),cart.getQuantity(),cart.getNumGuests(),cart.getRemarks(), beforeDiscountPrice, newBooking.getBookingDate(), cart.getStartDate(), cart.getEndDate());
				System.out.println(3);
				String uri = cart.getProduct().getRoomType().getHotel().getAPI_URL() + "booking";
				RestTemplate restTemplate = new RestTemplate();
				HotelBooking returnBooking = restTemplate.postForObject( uri, hotelBook, HotelBooking.class);
				BookingDetails newDetail = new BookingDetails(newBooking, cart.getProduct(), Long.toString(returnBooking.getId()), cart.getNumGuests(), afterDiscountPrice);
				bookService.saveBookingDetails(newDetail);
				System.out.println(4);
			}else {
				//code for cart is attraction
			}
			
		}
		
////	testing for calling API with post request===> success
//		model.addAttribute("test", result);
		return "testing";
	}
	
	public double getTotalNightPrice(Cart cart) {
		String uri = cart.getProduct().getRoomType().getHotel().getAPI_URL()+ "room/period";	
	    RestTemplate restTemplate = new RestTemplate();
//	    DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//	    DateTypeQuery query = new DateTypeQuery(LocalDate.parse("25/01/2021", df),"SINGLE");
	    MultipleDateQuery query = new MultipleDateQuery(cart.getStartDate(), cart.getEndDate(), cart.getProduct().getRoomType().getRoomType());
	    System.out.println(query);
	    DailyRoomDetailWrapper result = restTemplate.postForObject( uri, query, DailyRoomDetailWrapper.class);
	    System.out.println(result);
	    double total = 0;
    	for (DailyRoomTypeDetail daily: result.getDailyList()) {
	    	total += daily.getDailyPrice();
	    }
		return total;
	}
	
	public Booking createBooking(List<Cart> carts, User user) {
		Booking newBooking;
		//Checking number of attractions and nights
		int numNights = 0;
		int numAttractions = 0;
		for (Cart cart: carts) {
			if (cart.getProduct().getType()==ProductType.HOTEL) {
				long days = cart.getStartDate().until(cart.getEndDate(), ChronoUnit.DAYS);
				System.out.println(days);
				numNights += (int)days;
			}else {
				numAttractions += 1;
			}
		}
		int tp = travelPacService.getDiscount(numNights, numAttractions);
		newBooking = new Booking(user, LocalDate.now(), tp);
		bookService.saveBooking(newBooking);
		return newBooking;
	}
	
}
