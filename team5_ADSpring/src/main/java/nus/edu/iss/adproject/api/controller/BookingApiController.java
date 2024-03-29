package nus.edu.iss.adproject.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import nus.edu.iss.adproject.model.Booking;
import nus.edu.iss.adproject.model.BookingDetails;
import nus.edu.iss.adproject.model.User;
import nus.edu.iss.adproject.nonEntityModel.AttractionBooking;
import nus.edu.iss.adproject.nonEntityModel.BookingWrapper;
import nus.edu.iss.adproject.nonEntityModel.HotelBooking;
import nus.edu.iss.adproject.nonEntityModel.ProductType;
import nus.edu.iss.adproject.service.BookingService;
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
		User user = userService.findByUsername(username);
		return new ResponseEntity<List<Booking>>(bookService.retrieveUserBooking(user),HttpStatus.OK);
	}
	
	@GetMapping("/attraction/bookingdetails")
	public ResponseEntity<AttractionBooking> getAttractionBooking(@Param("detailId") Long detailId) {
		BookingDetails bookD = bookService.retrieveDetailsByDetailId(detailId);
		String uri = bookD.getProduct().getAttraction().getAPI_URL()+ "booking/{id}";
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", bookD.getAPIBookingId());
		RestTemplate restTemplate = new RestTemplate();
		AttractionBooking booking = restTemplate.getForObject(uri, AttractionBooking.class, params);
		return new ResponseEntity<AttractionBooking>(booking,HttpStatus.OK);
	}
	
	@GetMapping("/hotel/bookingdetails")
	public ResponseEntity<HotelBooking> getHotelBooking(@Param("detailId") Long detailId) {
		BookingDetails bookD = bookService.retrieveDetailsByDetailId(detailId);
		String uri = bookD.getProduct().getRoomType().getHotel().getAPI_URL()+ "booking/{id}";	
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", bookD.getAPIBookingId());
		RestTemplate restTemplate = new RestTemplate();
		HotelBooking booking = restTemplate.getForObject(uri, HotelBooking.class, params);
		return new ResponseEntity<HotelBooking>(booking,HttpStatus.OK);
	}
}
