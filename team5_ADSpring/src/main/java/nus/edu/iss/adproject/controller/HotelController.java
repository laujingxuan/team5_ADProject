
package nus.edu.iss.adproject.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import nus.edu.iss.adproject.model.Discount;
import nus.edu.iss.adproject.model.Hotel;
import nus.edu.iss.adproject.model.Product;
import nus.edu.iss.adproject.model.ProductReview;
import nus.edu.iss.adproject.model.RoomType;
import nus.edu.iss.adproject.model.User;
import nus.edu.iss.adproject.nonEntityModel.CartForm;
import nus.edu.iss.adproject.nonEntityModel.DailyRoomDetailWrapper;
import nus.edu.iss.adproject.nonEntityModel.DailyRoomTypeDetail;
import nus.edu.iss.adproject.nonEntityModel.MonthTypeQuery;
import nus.edu.iss.adproject.nonEntityModel.ProductType;
import nus.edu.iss.adproject.service.DiscountService;
import nus.edu.iss.adproject.service.HotelService;
import nus.edu.iss.adproject.service.ProductReviewService;
import nus.edu.iss.adproject.service.ProductService;
import nus.edu.iss.adproject.service.RoomTypeService;
import nus.edu.iss.adproject.service.SessionService;

@Controller
@RequestMapping("/hotel")
public class HotelController {
	@Autowired
	private HotelService hotelservice;

	@Autowired
	private RoomTypeService rservice;
	
	@Autowired
	private SessionService session_svc;
	
	@Autowired
	ProductReviewService prservice;
	
	@Autowired
	private ProductService pservice;
	
	@Autowired
	private DiscountService discountService;

	//done adding restriction
	//show list of hotels
	@GetMapping("/hotels")
	public String gethotel(Model model, HttpSession session){
		if (session_svc.isNotLoggedIn(session)) return "redirect:/user/login";
		if (session_svc.hasHotelPermission(session) == false) {
			model.addAttribute("error", "No Permission");
			return "error";
		}
		User user = (User) session.getAttribute("user");
		List<Hotel> hotels=  hotelservice.findByUserId(user.getId());
		Map<Hotel, List<RoomType>> hotelMap = new HashMap<>();
		for (Hotel hotel: hotels) {
			hotelMap.put(hotel, rservice.findRoomTypesByHotelId(hotel.getId()));
		}
		model.addAttribute("hotelMap",hotelMap);
		return "hotelList";
	}
	
	//done
	//show the location of the hotel
	@GetMapping("/map/{id}")
	public String getMap(Model model,@PathVariable("id") long id ){
		Hotel val = hotelservice.findById(id);    
		model.addAttribute("longi",val.getLongi());
		model.addAttribute("lat", val.getLat());
		return "map";
	}
	
	//just retrieving roomType details, logic should be fine
	//get the roomtype details of a particular roomtype
	@GetMapping("/roomtypes/detail/{roomId}")
	public String viewRoomDetail(Model model, @PathVariable("roomId")Long roomId) {
		RoomType room = rservice.findById(roomId);
		model.addAttribute("roomtype", room);
		List<RoomType> RoomT= rservice.findRoomTypesByHotelId(room.getHotel().getId());
		model.addAttribute("rooms",RoomT);
		System.out.println(room.getProduct().getId());
		
		
		  Product product = pservice.findById(room.getProduct().getId()); 
		  List<ProductReview> reviewList = prservice.findReviewByProductId(room.getProduct().getId()); 
		  
		  for (Iterator iterator = reviewList.iterator(); iterator.hasNext();) {
			ProductReview productReview = (ProductReview) iterator.next();
			System.out.println(productReview.getRating());
		}
		  model.addAttribute("review", reviewList); 
		  model.addAttribute("product", product);
		  
		 // return "reviewList";
		 
		
		return "roomdetail";
	}
	
	@RequestMapping(value = "/room-available-date/{id}")
	public String gethotelRoomTypeAvailibleDate(Model model,@PathVariable("id")Long id)  {
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
		return "hotel-roomType-availble-date";
	}
	
//	----------------------------------------------------------------------------------------------------------------------edit/create
	//done adding restriction
	//edit a particular hotel
	@GetMapping("/hotel/edit/{id}")
	public String showEditForm(Model model, @PathVariable("id") Long id, HttpSession session) {
		if (session_svc.isNotLoggedIn(session)) return "redirect:/user/login";
		User user = (User) session.getAttribute("user");
		Hotel hotel = hotelservice.findByHotelIdAndUserId(id, user.getId());
		if (hotel == null) {
			model.addAttribute("error", "No Permission");
			return "error";
		}
		model.addAttribute("hotel", hotel);
		return "editHotel";
	}
	
	//done adding restriction
	//create a new hotel
	@GetMapping("/hotel/create")
	public String createHotel(Model model, HttpSession session)
	{
		if (session_svc.isNotLoggedIn(session)) return "redirect:/user/login";
		if (session_svc.hasHotelPermission(session) == false) {
			model.addAttribute("error", "No Permission");
			return "error";
		}
		model.addAttribute("hotel", new Hotel());
		return "editHotel";
	}
	
	//done
	//save changes on hotel editing
	@PostMapping("/save")
	public String saveHotelForm(@ModelAttribute("hotel") @Valid Hotel hotel, BindingResult bindingResult,
			Model model, HttpSession session) {
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult);
			if (hotel.getUser()==null) {
				model.addAttribute("hotel", new Hotel());
			}
//			else {
//				model.addAttribute("hotel", hotelservice.findById(hotel.getId()));
//			}
			return "editHotel";
		}
		User user = (User) session.getAttribute("user");
		hotel.setUser(user);
		hotelservice.save(hotel);
		return "redirect:/hotel/hotels";
	}

	//done
	//edit a particular roomtype
	@GetMapping("/roomtypes/edit/{id}")
	public String editRoomtypes(Model model, @PathVariable("id") Long id, HttpSession session) {
		if (session_svc.isNotLoggedIn(session)) return "redirect:/user/login";
		User user = (User) session.getAttribute("user");
		Hotel hotel = hotelservice.findByHotelIdAndUserId(rservice.findById(id).getHotel().getId(), user.getId());
		if (hotel == null) {
			model.addAttribute("error", "No Permission");
			return "error";
		}
		model.addAttribute("room",rservice.findById(id));
		return "editRoomType";
	}
	
	//done
	@GetMapping("/roomtypes/create")
	public String createProduct(Model model, HttpSession session){
		if (session_svc.isNotLoggedIn(session)) return "redirect:/user/login";
		if (session_svc.hasHotelPermission(session) == false) {
			model.addAttribute("error", "No Permission");
			return "error";
		}
		User user = (User) session.getAttribute("user");
		model.addAttribute("Hotels", hotelservice.findByUserId(user.getId()));
		model.addAttribute("room", new RoomType());
		return "createRoom";
	}
	
	//done
	//save changes on roomType editing
	@PostMapping("/saveRoom")
	public String saveRoomType(@ModelAttribute("roomtype") @Valid RoomType roomType, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult);
			System.out.println(roomType.getProduct());
			if (roomType.getProduct()==null) {
				model.addAttribute("room", new RoomType());
				return "createRoom";
			}else{
				model.addAttribute("room", rservice.findById(roomType.getId()));
				return "editRoomType";
			}
		}
		if (roomType.getProduct()==null) {
			Product newRoom= new Product(ProductType.HOTEL);
			pservice.save(newRoom);
			roomType.setProduct(newRoom);
		}
		RoomType updatedRoom = rservice.save(roomType);
		return "redirect:/hotel/hotels";
	}
	
//	----------------------------------------------------------------------------------------------------------------------delete
	//done
	//only owner of the hotel can delete the room
	@GetMapping("/roomtypes/delete/{id}")
	public String deleteRoom(Model model, @PathVariable("id") Long id, HttpSession session) {
		if (session_svc.isNotLoggedIn(session)) return "redirect:/user/login";
		User user = (User) session.getAttribute("user");
		Hotel hotel = hotelservice.findByHotelIdAndUserId(rservice.findById(id).getHotel().getId(), user.getId());
		if (hotel == null) {
			model.addAttribute("error", "No Permission");
			return "error";
		}
		rservice.delete(rservice.findById(id));
		return "redirect:/hotel/hotels";
	}
	
	//done
	//deleteHotel
	@GetMapping("hotel/delete/{hotelId}")
	public String deleteHotel(Model model, @PathVariable("hotelId") Long hotelId, HttpSession session) {
		if (session_svc.isNotLoggedIn(session)) return "redirect:/user/login";
		User user = (User) session.getAttribute("user");
		Hotel hotel = hotelservice.findByHotelIdAndUserId(hotelId, user.getId());
		if (hotel == null) {
			model.addAttribute("error", "No Permission");
			return "error";
		}
		
		//to remove every dependencies on the hotel before removing the hotel
		List<RoomType> roomTypes = rservice.findRoomTypesByHotelId(hotelId);
		for (RoomType roomtype: roomTypes) {
			rservice.delete(roomtype);
		}
		
		List<Discount> discounts = discountService.findDiscountByHotelId(hotelId);
		for (Discount discount: discounts) {
			discountService.delete(discount);
		}
		hotelservice.delete(hotelservice.findById(hotelId));
		return "redirect:/hotel/hotels";
	}
}
