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
import nus.edu.iss.adproject.NonEntityModel.DailyAttractionDetail;
import nus.edu.iss.adproject.NonEntityModel.DailyRoomDetailWrapper;
import nus.edu.iss.adproject.NonEntityModel.DailyRoomTypeDetail;
import nus.edu.iss.adproject.NonEntityModel.DateTypeQuery;
import nus.edu.iss.adproject.NonEntityModel.HotelBooking;
import nus.edu.iss.adproject.NonEntityModel.MultipleDateQuery;
import nus.edu.iss.adproject.NonEntityModel.ProductType;
import nus.edu.iss.adproject.model.Booking;
import nus.edu.iss.adproject.model.BookingDetails;
import nus.edu.iss.adproject.model.Cart;
import nus.edu.iss.adproject.model.User;
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
				uri = b.getProduct().getAttraction().getAPI_URL() + "booking/{id}";
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
	
	//need to change to post mapping
	@GetMapping("/makeBook")
	public String makeBooking(HttpSession session, Model model) {
		if (session_svc.isNotLoggedIn(session)) return "redirect:/user/login";
		User user = (User) session.getAttribute("user");
		List<Cart> carts = cartService.retrieveByUserId(user.getId());
		Booking newBooking = createBooking(carts,user);
		System.out.println(newBooking);
		for (Cart cart: carts) {
			if (cart.getProduct().getType()==ProductType.HOTEL) {
				//check the room availabilities and total prices for room
				String roomPrice = checkVacanciesAndTotalNightPrice(cart);
				if (roomPrice == null) {
					//not enough rooms
					model.addAttribute("error", cart.getProduct().getRoomType().getHotel() + " has insufficient" + cart.getProduct().getRoomType().getRoomType() + ". Sorry");
					return "error";
				}
				//get the total price by multiplying number of nights and number of rooms
				double beforeDiscountPrice = Double.parseDouble(roomPrice);
				double afterDiscountPrice = beforeDiscountPrice * (100-newBooking.getTravelPackageDiscount()) / 100;
				//hotel side will be storing before discount price while we store after discount price;
				HotelBooking hotelBook = new HotelBooking(cart.getProduct().getRoomType().getRoomType(),cart.getQuantity(),cart.getNumGuests(),cart.getRemarks(), beforeDiscountPrice, newBooking.getBookingDate(), cart.getStartDate(), cart.getEndDate());
				String uri = cart.getProduct().getRoomType().getHotel().getAPI_URL() + "booking";
				RestTemplate restTemplate = new RestTemplate();
				HotelBooking returnBooking = restTemplate.postForObject( uri, hotelBook, HotelBooking.class);
				BookingDetails newDetail = new BookingDetails(newBooking, cart.getProduct(), Long.toString(returnBooking.getId()), cart.getNumGuests(), afterDiscountPrice);
				bookService.saveBookingDetails(newDetail);
			}else {
				if (checkAndUpdateAttractionAPIQuantityLeft(cart)==false) {
					//Not enough tickets
					model.addAttribute("error", cart.getProduct().getAttraction().getName() + " has insufficient tickets. Sorry");
					return "error";
				};
				//creating booking details in our database and hotel booking in the api
				double beforeDiscountPrice = cart.getProduct().getAttraction().getPrice() * cart.getQuantity();
				double afterDiscountPrice = beforeDiscountPrice * (100-newBooking.getTravelPackageDiscount()) / 100;
				AttractionBooking attractBook = new AttractionBooking(cart.getProduct().getAttraction().getName(),cart.getQuantity(),cart.getStartDate());
				String uri = cart.getProduct().getAttraction().getAPI_URL()+ "booking";
				RestTemplate restTemplate = new RestTemplate();
				AttractionBooking returnBooking = restTemplate.postForObject(uri, attractBook, AttractionBooking.class);
				BookingDetails newDetail = new BookingDetails(newBooking, cart.getProduct(), Long.toString(returnBooking.getId()), cart.getQuantity(), afterDiscountPrice);
				bookService.saveBookingDetails(newDetail);
				
			}
			//remove from cart since alr added into booking
			cartService.deleteCart(cart);
		}
		return "redirect:/booking/list";
	}
	
	public boolean checkAndUpdateAttractionAPIQuantityLeft(Cart cart) {
		String uri = cart.getProduct().getAttraction().getAPI_URL()+ "booking/date";
		DateTypeQuery query = new DateTypeQuery(cart.getStartDate());
		RestTemplate restTemplate = new RestTemplate();
		DailyAttractionDetail daily = restTemplate.postForObject(uri, query, DailyAttractionDetail.class);
		if (daily.getQuantityLeft()<cart.getQuantity()) {
			//not enough tickets
			return false;
		}
		daily.setQuantityLeft(daily.getQuantityLeft()-cart.getQuantity());
		//update the ticket quantity in the attraction API
		String url = cart.getProduct().getAttraction().getAPI_URL()+ "booking/update";
		Boolean output = restTemplate.postForObject(url, daily, Boolean.class);
		return output;
	}	
	
	public String checkVacanciesAndTotalNightPrice(Cart cart) {
		String uri = cart.getProduct().getRoomType().getHotel().getAPI_URL()+ "room/period";	
	    RestTemplate restTemplate = new RestTemplate();
//	    DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//	    DateTypeQuery query = new DateTypeQuery(LocalDate.parse("25/01/2021", df),"SINGLE");
	    MultipleDateQuery query = new MultipleDateQuery(cart.getStartDate(), cart.getEndDate(), cart.getProduct().getRoomType().getRoomType());
	    DailyRoomDetailWrapper result = restTemplate.postForObject( uri, query, DailyRoomDetailWrapper.class);
	    double total = 0;
    	for (DailyRoomTypeDetail daily: result.getDailyList()) {
    		//check if there is sufficient rooms
    		if (daily.getNumVacancies() < cart.getQuantity()) {
    			return null;
    		}
    		daily.setNumVacancies(daily.getNumVacancies() - cart.getQuantity());
	    	total += daily.getDailyPrice()*cart.getQuantity();
	    }
    	
    	//Update the HotelAPI on the updated quantity of rooms
    	String url = cart.getProduct().getRoomType().getHotel().getAPI_URL()+ "room/update";
    	Boolean check = restTemplate.postForObject(url, result, Boolean.class);
    	return Double.toString(total);
	}
	
	public Booking createBooking(List<Cart> carts, User user) {
		Booking newBooking;
		//Checking number of attractions and nights to determine the discount package
		int numNights = 0;
		int numAttractions = 0;
		for (Cart cart: carts) {
			if (cart.getProduct().getType()==ProductType.HOTEL) {
				//number of days for each room
				long days = cart.getStartDate().until(cart.getEndDate(), ChronoUnit.DAYS);
				//number of rooms * number of days
				numNights += cart.getQuantity() * (int)days;
			}else {
				//number of tickets for 1 attraction
				numAttractions += cart.getQuantity();
			}
		}
		int tp = travelPacService.getDiscount(numNights, numAttractions);
		newBooking = new Booking(user, LocalDate.now(), tp);
		bookService.saveBooking(newBooking);
		return newBooking;
	}
	
}
