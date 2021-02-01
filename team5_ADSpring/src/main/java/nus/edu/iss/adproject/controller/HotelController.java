
package nus.edu.iss.adproject.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import nus.edu.iss.adproject.repository.HotelRepo;

@Controller
@RequestMapping("/hotel")
public class HotelController {
	@Autowired
	private HotelRepo hotel_repo;
	
	@GetMapping("/list")
	public String viewUser(Model model, HttpSession session) {
		
		return "discountForm";
	}

	@Autowired
	private HotelService hotelservice;

	@GetMapping("/Hotel")
	public String gethotel(Model model){
		List<Hotel> hotel=  hotelservice.findAll();
		model.addAttribute("Hotels",hotel);
		 return "Hotel";
	}
	@GetMapping("/Map/{id}")
	public String getMap(Model model,@PathVariable("id") long id )
	{
		model.addAttribute("hotels",hotelservice.OptionalFindById(id));
		return "Map";
	}
//	@GetMapping("/edit/{id}")
//	public String showEditForm(Model model, @PathVariable("id") Long id) {
//		model.addAttribute("supplier", supplier_svc.findAll());
//		model.addAttribute("product", product_svc.findById(id));
//		return "productform";
//	}


}
