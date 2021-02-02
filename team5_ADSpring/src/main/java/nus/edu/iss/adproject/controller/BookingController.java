package nus.edu.iss.adproject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import nus.edu.iss.adproject.NonEntityModel.AttractionBooking;
import nus.edu.iss.adproject.NonEntityModel.BookingWrapper;
import nus.edu.iss.adproject.NonEntityModel.HotelBooking;
import nus.edu.iss.adproject.NonEntityModel.ProductType;
import nus.edu.iss.adproject.model.BookingDetails;
import nus.edu.iss.adproject.model.User;
import nus.edu.iss.adproject.service.BookingService;
import nus.edu.iss.adproject.service.SessionService;

@Controller
@RequestMapping("/booking")
public class BookingController {

	@Autowired
	private BookingService bookService;
	
	@Autowired
	private SessionService session_svc;
	
	@Autowired
	private RestTemplate restTemplate;
	
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
	
	@PostMapping("/makeBook")
	public String makeBooking() {
////	testing for calling API with post request===> success
//	  final String uri = "http://localhost:8081/api/hotel/room/date";
//    RestTemplate restTemplate = new RestTemplate();
//    DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//    DateTypeQuery query = new DateTypeQuery(LocalDate.parse("25/01/2021", df),"SINGLE");
//    System.out.println(query);
//    DailyRoomTypeDetail result = restTemplate.postForObject( uri, query, DailyRoomTypeDetail.class);
//    model.addAttribute("test", result);
		return null;
	}
}
