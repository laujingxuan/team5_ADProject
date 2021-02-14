package nus.edu.iss.adproject.api.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import nus.edu.iss.adproject.model.Discount;
import nus.edu.iss.adproject.model.Hotel;
import nus.edu.iss.adproject.model.Product;
import nus.edu.iss.adproject.model.RoomType;
import nus.edu.iss.adproject.model.User;
import nus.edu.iss.adproject.nonEntityModel.CartForm;
import nus.edu.iss.adproject.nonEntityModel.DailyRoomDetailWrapper;
import nus.edu.iss.adproject.nonEntityModel.DailyRoomTypeDetail;
import nus.edu.iss.adproject.nonEntityModel.HotelWrapper;
import nus.edu.iss.adproject.nonEntityModel.MonthTypeQuery;
import nus.edu.iss.adproject.nonEntityModel.RoomTypeWrapper;
import nus.edu.iss.adproject.repository.AttractionRepository;
import nus.edu.iss.adproject.repository.ProductRepo;
import nus.edu.iss.adproject.service.AttractionService;
import nus.edu.iss.adproject.service.DiscountService;
import nus.edu.iss.adproject.service.HotelService;
import nus.edu.iss.adproject.service.ProductService;
import nus.edu.iss.adproject.service.RoomTypeService;
import nus.edu.iss.adproject.service.SessionService;

@RestController
@CrossOrigin
@RequestMapping("/api/hotel")
public class HotelApiController {
	@Autowired
	private HotelService hotelservice;

	@Autowired
	private RoomTypeService rservice;
	
	@Autowired
	private SessionService session_svc;
	
	@Autowired
	private ProductService pservice;
	
	@Autowired
	private DiscountService discountService;
	
	//http://localhost:8080/api/hotel/hotels
	//show list of hotels
	@GetMapping("/hotels")
	public ResponseEntity<List<Hotel>> gethotel(Model model, HttpSession session){		
		List<Hotel> hotels1 = hotelservice.findAll();
		return new ResponseEntity<List<Hotel>>(hotels1,HttpStatus.OK);
	}
	
	//http://localhost:8080/api/hotel/map/1
	//show the location of the hotel
	@GetMapping("/map/{id}")
	public ResponseEntity<Hotel> getMap(@PathVariable("id") long id ){
		//Hotel val = hotelservice.findById(id);    
//		model.addAttribute("longi",val.getLongi());
//		model.addAttribute("lat", val.getLat());
		return new ResponseEntity<Hotel>(hotelservice.findById(id),HttpStatus.OK);
	}
	
	//http://localhost:8080/api/hotel/roomtypes/detail/1
	//just retrieving roomType details, logic should be fine
	//get the roomtype details of a particular roomtype
	@GetMapping("/roomtypes/detail/{roomId}")
	public ResponseEntity<RoomTypeWrapper> viewRoomDetail(Model model, @PathVariable("roomId")Long roomId) {
		System.out.println("roomID is " + roomId);
		RoomType room = rservice.findById(roomId);
		model.addAttribute("roomtype", room);
		List<RoomType> RoomT= rservice.findRoomTypesByHotelId(room.getHotel().getId());
		model.addAttribute("rooms",RoomT);
		// need to create wrapper 
		return  new ResponseEntity<RoomTypeWrapper>(new RoomTypeWrapper(RoomT),HttpStatus.OK);
	}
	
	
	//http://localhost:8080/api/hotel/room-available-date/4
	@GetMapping(value = "/room-available-date/{id}")
	public ResponseEntity<DailyRoomDetailWrapper> gethotelRoomTypeAvailibleDate(Model model,@PathVariable("id")Long id)  {
		Product p = pservice.findProductById(id);
		String URL = p.getRoomType().getHotel().getAPI_URL()+"room/month";
		String APIURL = p.getRoomType().getHotel().getAPI_URL()+"room/period";
		String RoomType = p.getRoomType().getRoomType();
		List<Discount> discountlist= p.getRoomType().getHotel().getDiscount();
		String discount = "";
		for(Discount d : discountlist) {
			discount += "Discount start from "+d.getFrom_date().toString() + " to " + d.getTo_date().toString()
					+ " the discount is " + d.getDiscount_rate() + "%";
		}
		
		RestTemplate restTemplate = new RestTemplate();
		MonthTypeQuery roomtype = new MonthTypeQuery(1,RoomType);
		
		DailyRoomDetailWrapper result =  restTemplate.postForObject(URL, roomtype, DailyRoomDetailWrapper.class);
		List<String> dates = new ArrayList<>() ;
		
		List<DailyRoomTypeDetail> list = result.getDailyList();
		for(DailyRoomTypeDetail d : list) {
			if(d.getNumVacancies()> 0) {
				LocalDate date = d.getDate();
				String date1 = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				dates.add(date1);
			}
		}
		model.addAttribute("dates1", dates);
		model.addAttribute("RoomType",RoomType);
		model.addAttribute("APIURL",APIURL);
		model.addAttribute("discount",discount);

		model.addAttribute("cartitem", new CartForm());
		model.addAttribute("productId", id);
		
		return  new ResponseEntity<DailyRoomDetailWrapper>(result,HttpStatus.OK);
	}
	
}
