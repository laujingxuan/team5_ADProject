package nus.edu.iss.simulated.controller;

import java.time.LocalDate;

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
import org.springframework.web.client.RestTemplate;

import nus.edu.iss.simulated.model.DailyRoomTypeDetail;
import nus.edu.iss.simulated.model.HotelBooking;
import nus.edu.iss.simulated.nonEntityModel.DailyRoomDetailWrapper;
import nus.edu.iss.simulated.nonEntityModel.DateTypeQuery;
import nus.edu.iss.simulated.nonEntityModel.MLearningVar;
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
		return new ResponseEntity<HotelBooking>(hotelBookSer.findBookingById(id), HttpStatus.OK);
	}
	
	//createNewHotelBooking
	@PostMapping("/booking")
	public ResponseEntity<HotelBooking> newBooking (@RequestBody HotelBooking hotelBooking) {
		HotelBooking newHotelBooking = hotelBookSer.createBooking(hotelBooking);
<<<<<<< HEAD
		//int isCancelled = predictBookingCancellationRate(newHotelBooking);
		//System.out.println(isCancelled);
		System.out.println(newHotelBooking);
=======
		int isCancelled = predictBookingCancellationRate(newHotelBooking);
>>>>>>> branch 'master' of https://github.com/laujingxuan/team5_ADProject.git
		return new ResponseEntity<HotelBooking>(newHotelBooking, HttpStatus.OK);
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
		(dailyRoomSer.updateVacanciesQuantity(updated),HttpStatus.OK);
	}
	
	//EVERY BOOKINGS of the hotel need to pass through this method!
	public int predictBookingCancellationRate(HotelBooking hotelBooking) {
		//connect to machine learning api
		final String uri = "http://127.0.0.1:5000/model";
		RestTemplate restTemplate = new RestTemplate();
		MLearningVar mLearning = new MLearningVar(hotelBooking);
		String isCancelled = restTemplate.postForObject( uri, mLearning, String.class);
		int isCancel = Integer.parseInt(isCancelled);
		//1 means predicted to cancel
		//need to update the database on vacancy and number of cancellations
		if (isCancel == 1) {
			for (LocalDate date = hotelBooking.getStartDate(); date.isBefore(hotelBooking.getEndDate()); date = date.plusDays(1)) {
				DailyRoomTypeDetail temp = dailyRoomSer.findRoomDetailByDateAndType(date, hotelBooking.getRoomType());
				//increase 0.7*number of booked rooms for vacancy and number of cancellations because only 70% accuracy
				//(risk-averse) in case predicted cancelled rooms are not cancelled.
				temp.setNumVacancies(temp.getNumVacancies()+0.7*hotelBooking.getNumRooms());
				temp.setNumCancellations(temp.getNumCancellations()+hotelBooking.getNumRooms());
				dailyRoomSer.updateDailyRoomTypeDetail(temp);
			}
		}
		return isCancel;
	}
	
}
