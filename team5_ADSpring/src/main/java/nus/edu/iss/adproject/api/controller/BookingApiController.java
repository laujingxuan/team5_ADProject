package nus.edu.iss.adproject.api.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nus.edu.iss.adproject.model.Booking;
import nus.edu.iss.adproject.model.User;
import nus.edu.iss.adproject.service.BookingService;
import nus.edu.iss.adproject.service.SessionService;

@RestController
@CrossOrigin
@RequestMapping("/api/booking")
public class BookingApiController {
	@Autowired
	private SessionService session_svc;
	@Autowired
	private BookingService bookService;
	
	@GetMapping("/list")
	public ResponseEntity<Booking> showBookings(Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		model.addAttribute("BookingsList", bookService.retrieveUserBooking(user));
		return new ResponseEntity<Booking>((Booking) bookService.retrieveUserBooking(user),HttpStatus.OK);
	}
	
	//need to ask about the booking part 
}
