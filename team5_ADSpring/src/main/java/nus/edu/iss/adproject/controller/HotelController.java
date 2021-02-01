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

}
