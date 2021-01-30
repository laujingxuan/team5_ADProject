package nus.edu.iss.simulated.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nus.edu.iss.simulated.model.DailyRoomTypeDetail;
import nus.edu.iss.simulated.model.HotelBooking;
import nus.edu.iss.simulated.nonEntityModel.DateTypeQuery;
import nus.edu.iss.simulated.service.DailyRoomTypeDetailService;
import nus.edu.iss.simulated.service.HotelBookingService;

@RestController
@CrossOrigin
@RequestMapping("/api/hotel")
public class HotelController {

	@Autowired
	private HotelBookingService hotelBookSer;
	
	@Autowired
	private DailyRoomTypeDetailService dailyRoomSer;
	
	//findBookingById
	@GetMapping("/booking/{id}")
	public ResponseEntity<HotelBooking> findBookingById (@PathVariable("id") Long id) {
		return new ResponseEntity<HotelBooking>(hotelBookSer.findBookingById(id), HttpStatus.OK);
	}
	
	//findRoomDetailsByType&Date
	@GetMapping("/room")
	public ResponseEntity<DailyRoomTypeDetail>findRoomDetailsByTypeDate(@RequestBody DateTypeQuery input){
		System.out.println(input.getDate());
		System.out.println(input.getRoomType());
		return new ResponseEntity<DailyRoomTypeDetail>(dailyRoomSer.findRoomDetailByDateAndType(input.getDate(), input.getRoomType()), HttpStatus.OK);
	}
	
	//findRoomDetailsByType&Month
	
	//predictBookingCancellationRate
	
	//createNewHotelBooking
	@PostMapping("/booking")
	public ResponseEntity<HotelBooking> newBooking (@RequestBody HotelBooking hotelBooking) {
		return new ResponseEntity<HotelBooking>(hotelBookSer.createBooking(hotelBooking), HttpStatus.OK);
	}
	
	
	
	
}
