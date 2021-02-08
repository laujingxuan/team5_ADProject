
package nus.edu.iss.adproject.controller;

import java.util.List;

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

import nus.edu.iss.adproject.model.Hotel;
import nus.edu.iss.adproject.model.RoomType;
import nus.edu.iss.adproject.model.User;
import nus.edu.iss.adproject.service.HotelService;
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
		List<Hotel> hotel=  hotelservice.findByUserId(user.getId());
		model.addAttribute("Hotels",hotel);
		 return "hotel";
	}
	
	//done adding restriction
	//edit a particular hotel
	@GetMapping("/edit/{id}")
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
	@GetMapping("/create")
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
			if (hotel.getUser()==null) {
				model.addAttribute("hotel", new Hotel());
			}else {
				model.addAttribute("hotel", hotelservice.findById(hotel.getId()));
			}
			return "hotel-form";
		}
		User user = (User) session.getAttribute("user");
		hotel.setUser(user);
		hotelservice.save(hotel);
		return "redirect:/hotel/hotels";
	}
	
	
	
	//show the location of the hotel
	@GetMapping("/map/{id}")
	public String getMap(Model model,@PathVariable("id") long id )
	{
		Hotel val = hotelservice.findById(id);    
		model.addAttribute("hotels",val);
		return "Map";
	}
	
	//get the roomtype details of a particular roomtype
	@GetMapping("/roomtypes/detail/{id}")
	public String viewRoomDetail(Model model, @PathVariable("id")Long id) {
		RoomType room = rservice.findById(id);
		model.addAttribute("roomtype", room);
		List<RoomType> RoomT= rservice.findRoomTypesByHotelId(room.getHotel().getId());
		model.addAttribute("rooms",RoomT);
		return "roomdetail";
	}


	
	//edit a particular roomtype
	@GetMapping("/roomtypes/edit/{id}")
	public String editRoomtypes(Model model,@PathVariable("id") Long id) {
		model.addAttribute("roomtype",rservice.findById(id));
		return "roomtypes-form";
	}
	
	//save changes on roomType editing
	@PostMapping("/saveRoom")
	public String saveRoomType(@ModelAttribute("roomtype") @Valid RoomType roomtype, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("roomtype", rservice.findById(roomtype.getId()));
			return "roomtypes-form";
		}
		rservice.save(roomtype);
		return "redirect:/hotel/roomtypes";
	}
	
//	//show the list of roomtypes
//	@GetMapping("/roomtypes")
//	public String viewRoomTypes(Model model) {
//		model.addAttribute("roomtype", rservice.findAll());
//		return "roomtypes";
//	}
	

}
