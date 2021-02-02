package nus.edu.iss.adproject.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import nus.edu.iss.adproject.NonEntityModel.DailyRoomTypeDetail;
import nus.edu.iss.adproject.NonEntityModel.DateTypeQuery;
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
		User user = (User) session.getAttribute("user");
////		testing for calling API with post request===> success
//		final String uri = "http://localhost:8081/api/hotel/room/date";
//	    RestTemplate restTemplate = new RestTemplate();
//	    DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//	    DateTypeQuery query = new DateTypeQuery(LocalDate.parse("25/01/2021", df),"SINGLE");
//	    System.out.println(query);
//	    DailyRoomTypeDetail result = restTemplate.postForObject( uri, query, DailyRoomTypeDetail.class);
//	    model.addAttribute("test", result);
		return "testing";
	}
}
