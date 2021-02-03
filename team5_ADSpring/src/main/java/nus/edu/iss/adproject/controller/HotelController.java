package nus.edu.iss.adproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import nus.edu.iss.adproject.model.Hotel;
import nus.edu.iss.adproject.model.RoomType;
import nus.edu.iss.adproject.repository.HotelRepository;
import nus.edu.iss.adproject.repository.RoomTypeRepo;
import nus.edu.iss.adproject.service.HotelService;
import nus.edu.iss.adproject.service.RoomTypeService;

@Controller
@RequestMapping("/list")
public class HotelController {
	@Autowired
	private HotelService hotelservice;

	@GetMapping("/Hotel")
	public String gethotel(Model model){
		List<Hotel> hotel=  hotelservice.findAll();
		System.out.print(hotel);
		model.addAttribute("Hotels",hotel);
		 return "Hotel";
	}
	@GetMapping("/Map/{id}")
	public String getMap(Model model,@PathVariable("id") long id )
	{
		var val = hotelservice.findById(id);
        if (val.isPresent()) {
            System.out.println(val.get());
        } else {
            System.out.printf("No hotels found with id %d%n", id);
        }
     
		System.out.print(hotelservice.findById(id));
		model.addAttribute("hotels",val.get());
		
		return "Map";
	}
	
	

//
//	@RequestMapping(value="/SimilarRoom", method=RequestMethod.POST, params="action=Similar_Room")
//	public String Similar() {
//		return "SimilarRoom";
//	}
	
	


}
