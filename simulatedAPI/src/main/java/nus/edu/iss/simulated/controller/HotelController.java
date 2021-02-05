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
import nus.edu.iss.simulated.nonEntityModel.DailyRoomDetailWrapper;
import nus.edu.iss.simulated.nonEntityModel.DateTypeQuery;
import nus.edu.iss.simulated.nonEntityModel.MonthTypeQuery;
import nus.edu.iss.simulated.nonEntityModel.MultipleDateQuery;
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
		System.out.println(id);
		return new ResponseEntity<HotelBooking>(hotelBookSer.findBookingById(id), HttpStatus.OK);
	}
	
	//createNewHotelBooking
	@PostMapping("/booking")
	public ResponseEntity<HotelBooking> newBooking (@RequestBody HotelBooking hotelBooking) {
		return new ResponseEntity<HotelBooking>(hotelBookSer.createBooking(hotelBooking), HttpStatus.OK);
	}
	
//	{
//	    "date" : "2021-01-30",
//	    "roomType" : "single"
//
//	}
	
	@PostMapping("/room/date")
	public ResponseEntity<DailyRoomTypeDetail>findRoomDetailsByTypeDate(@RequestBody DateTypeQuery input){
		return new ResponseEntity<DailyRoomTypeDetail>(dailyRoomSer.findRoomDetailByDateAndType(input.getDate(), input.getRoomType()), HttpStatus.OK);
	}

	
	@PostMapping("/room/month")
	public ResponseEntity<DailyRoomDetailWrapper> findRoomDetailsByTypeMonth(@RequestBody MonthTypeQuery input){
		System.out.println("Hi");
		return new ResponseEntity<DailyRoomDetailWrapper>
		
		(new DailyRoomDetailWrapper (dailyRoomSer.findRoomDetailsByMonthAndType(input.getMonth(), input.getRoomType())), HttpStatus.OK);
	}
	
	@PostMapping("/room/period")
	public ResponseEntity<DailyRoomDetailWrapper>findRoomDetailsByTypePeriod(@RequestBody MultipleDateQuery input){
		
		return new ResponseEntity<DailyRoomDetailWrapper>(new DailyRoomDetailWrapper(dailyRoomSer.findRoomDetailsByPeriodAndType(input.getStartDate(), input.getEndDate(), input.getRoomType())), HttpStatus.OK);

	}

	@PostMapping("/room/update")
	public ResponseEntity<Boolean> updateVacanciesQuantity(@RequestBody DailyRoomDetailWrapper updated){
		return new ResponseEntity<Boolean>
		(dailyRoomSer.UpdateVacanciesQuantity(updated),HttpStatus.OK);
	}
	
	//predictBookingCancellationRate
	//Need to connect to machine learning API

}
