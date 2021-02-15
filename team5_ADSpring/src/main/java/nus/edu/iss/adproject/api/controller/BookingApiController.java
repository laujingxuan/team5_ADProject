package nus.edu.iss.adproject.api.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
import nus.edu.iss.adproject.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/api/booking")
public class BookingApiController {
	@Autowired
	private BookingService bookService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/list")
	public ResponseEntity<List<Booking>> showBookings(@Param("username") String username) {
		System.out.println(username);
		User user = userService.findByUsername(username);
		System.out.println(bookService.retrieveUserBooking(user));
		return new ResponseEntity<List<Booking>>(bookService.retrieveUserBooking(user),HttpStatus.OK);
	}
	
	//need to ask about the booking part 
}
