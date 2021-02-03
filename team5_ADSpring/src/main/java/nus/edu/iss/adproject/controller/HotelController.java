
package nus.edu.iss.adproject.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import nus.edu.iss.adproject.model.Hotel;
import nus.edu.iss.adproject.model.RoomType;
import nus.edu.iss.adproject.service.HotelService;
import nus.edu.iss.adproject.service.RoomTypeService;

@Controller
@RequestMapping("/hotel")
public class HotelController {
	@Autowired
	private HotelService hotelservice;
	
	@Autowired
	private RoomTypeService rservice;
	
	
	@GetMapping("/list")
	public String viewUser(Model model, HttpSession session) {
		return "discountForm";
	}

	@GetMapping("/Hotel")
	public String gethotel(Model model){
		List<Hotel> hotel=  hotelservice.findAll();
		model.addAttribute("Hotels",hotel);
		 return "Hotel";
	}
	
	@GetMapping("/Map/{id}")
	public String getMap(Model model,@PathVariable("id") long id ){
		model.addAttribute("hotels",hotelservice.findById(id));
		return "Map";
	}

	
	@GetMapping("/roomtypes/{id}")
	public String viewRoomTypes(Model model, @PathVariable("id")Long id) {
		model.addAttribute("roomtype", rservice.findRoomTypesByHotelId(id));
		return "roomtypes";
	}
	
	@GetMapping("/roomtypes/detail/{id}")
	public String viewRoomDetail(Model model, @PathVariable("id")Long id) {
		System.out.println(id);
		RoomType room = rservice.findById(id);
		System.out.println(room);
		model.addAttribute("roomtype", room);
		return "roomdetail";
	}
}
