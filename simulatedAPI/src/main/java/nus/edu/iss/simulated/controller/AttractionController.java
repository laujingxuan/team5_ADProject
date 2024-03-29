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

import nus.edu.iss.simulated.model.AttractionBooking;
import nus.edu.iss.simulated.model.DailyAttractionDetail;
import nus.edu.iss.simulated.nonEntityModel.DailyDetailWrapper;
import nus.edu.iss.simulated.nonEntityModel.DateTypeQuery;
import nus.edu.iss.simulated.nonEntityModel.MonthTypeQuery;
import nus.edu.iss.simulated.nonEntityModel.MultipleDateQuery;
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
	
	
	@GetMapping("/booking/{id}")
	public ResponseEntity<AttractionBooking> findbookingById(@PathVariable Long id){
		return new ResponseEntity<AttractionBooking>(attractionBookingServ.findBookingById(id),HttpStatus.OK);
	}
	
	@PostMapping("/booking")
	public ResponseEntity<AttractionBooking> newBooking(@RequestBody AttractionBooking attractionBooking){
		return new ResponseEntity<AttractionBooking>(attractionBookingServ.createBooking(attractionBooking),HttpStatus.OK);
	}
	
	@PostMapping("/booking/date")
	public ResponseEntity<DailyAttractionDetail> findAttractionDetailByDate(@RequestBody DateTypeQuery input ){
		return new ResponseEntity<DailyAttractionDetail>
		(dailyAttractionDetailServ.findAttractionDetailByDate(input.getDate()),HttpStatus.OK);
	}
	
	@PostMapping(value = "/booking/month")
	public ResponseEntity<DailyDetailWrapper> findAttractionDetailByMonth(@RequestBody MonthTypeQuery input){
		return new ResponseEntity<DailyDetailWrapper>
		(new DailyDetailWrapper(dailyAttractionDetailServ.findAttractionDetailByMonthAndAttractionName(input.getMonth())),HttpStatus.OK);
	}

	
	@PostMapping("/booking/update")
	public ResponseEntity<Boolean> updateTicketQuantity(@RequestBody DailyAttractionDetail updated){
		return new ResponseEntity<Boolean>
		(dailyAttractionDetailServ.UpdateTicketQuantity(updated),HttpStatus.OK);
	}
	
	@PostMapping("/booking/period")
	public ResponseEntity<DailyDetailWrapper>findAttractionByTypePeriod(@RequestBody MultipleDateQuery input){
		return new ResponseEntity<DailyDetailWrapper>(new DailyDetailWrapper(dailyAttractionDetailServ.findAttractionByPeriod(input.getStartDate(), input.getEndDate())), HttpStatus.OK);
	}

}
