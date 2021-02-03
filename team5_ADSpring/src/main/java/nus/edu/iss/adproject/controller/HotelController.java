
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
import org.springframework.web.bind.annotation.RequestMapping;

import nus.edu.iss.adproject.model.Hotel;
import nus.edu.iss.adproject.service.HotelService;

@Controller
@RequestMapping("/hotel")
public class HotelController {
	@Autowired
	private HotelService hotelservice;

	@GetMapping("/list")
	public String viewUser(Model model, HttpSession session) {
		return "discountForm";
	}



	public String gethotel(Model model){
		List<Hotel> hotel=  hotelservice.findAll();
		System.out.print(hotel);
		model.addAttribute("Hotels",hotel);
		 return "Hotel";
	}


	public String getMap(Model model,@PathVariable("id") long id )
	{

		Hotel val = hotelservice.findById(id);
//        if (val.isPresent()) {
//            System.out.println(val.get());
//        } else {
//            System.out.printf("No hotels found with id %d%n", id);
//        }
     
		System.out.print(hotelservice.findById(id));
		model.addAttribute("hotels",val);
		
		return "Map";
	}
	
	

//
//	@RequestMapping(value="/SimilarRoom", method=RequestMethod.POST, params="action=Similar_Room")
//	public String Similar() {
//		return "SimilarRoom";
//	}
	
	


	@GetMapping("/edit/{id}")
	public String showEditForm(Model model, @PathVariable("id") Long id) {
		model.addAttribute("hotel", hotelservice.findById(id));
		return "productForm";
	}

	@GetMapping("/save")
	public String saveProductForm(@ModelAttribute("product") @Valid Hotel hotel, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			return "productform";
		}
		hotelservice.save(hotel);
		return "forward:/hotel/Hotel";
	}
}
