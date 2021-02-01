package nus.edu.iss.simulated.controller;

import java.util.List;

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

import nus.edu.iss.simulated.model.AttractionBooking;
import nus.edu.iss.simulated.model.DailyAttractionDetail;
import nus.edu.iss.simulated.nonEntityModel.DateTypeQuery;
import nus.edu.iss.simulated.nonEntityModel.MonthTypeQuery;
import nus.edu.iss.simulated.service.AttractionBookingService;
import nus.edu.iss.simulated.service.DailyAttractionDetailService;

@CrossOrigin
@RestController
@RequestMapping("api/attraction")
public class AttractionController {
	
	
	@Autowired 
	private AttractionBookingService attractionBookingServ;
	
	@Autowired
	private DailyAttractionDetailService dailyAttractionDetailServ;
	
	
	@GetMapping("booking/{id}")
	public ResponseEntity<AttractionBooking> findbookingById(@PathVariable Long id){
		return new ResponseEntity<AttractionBooking>(attractionBookingServ.findBookingById(id),HttpStatus.OK);
		
	}
	
	@PostMapping("booking")
	public ResponseEntity<AttractionBooking> newBooking(@RequestBody AttractionBooking attractionBooking){
		return new ResponseEntity<AttractionBooking>(attractionBookingServ.createBooking(attractionBooking),HttpStatus.OK);
	}
	
	@GetMapping("/booking/date")
	public ResponseEntity<List<DailyAttractionDetail>> findAttractionDetailByDate(@RequestBody DateTypeQuery input ){
		return new ResponseEntity<List<DailyAttractionDetail>>
		(dailyAttractionDetailServ.findAttractionDetailByDate(input.getDate()),HttpStatus.OK);
	}
	@GetMapping("/booking/month")
	public ResponseEntity<List<DailyAttractionDetail>> findAttractionDetailByMonth(@RequestBody MonthTypeQuery input){
		return new ResponseEntity<List<DailyAttractionDetail>>
		(dailyAttractionDetailServ.findAttractionDetailByMonth(input.getMonth()),HttpStatus.OK);
	}
	
	
	
	

}
