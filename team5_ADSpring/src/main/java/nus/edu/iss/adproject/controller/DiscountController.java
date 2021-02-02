package nus.edu.iss.adproject.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import nus.edu.iss.adproject.model.Discount;
import nus.edu.iss.adproject.model.User;
import nus.edu.iss.adproject.service.AttractionService;
import nus.edu.iss.adproject.service.AttractionServiceImpl;
import nus.edu.iss.adproject.service.DiscountService;
import nus.edu.iss.adproject.service.DiscountServiceImpl;
import nus.edu.iss.adproject.service.HotelService;
import nus.edu.iss.adproject.service.HotelServiceImpl;
import nus.edu.iss.adproject.service.SessionService;
import nus.edu.iss.adproject.service.SessionServiceImpl;

@Controller
@RequestMapping("/discount")
public class DiscountController {

	@Autowired
	private DiscountService discount_svc;
	
	@Autowired
	private HotelService hotel_svc;
	
	@Autowired
	private AttractionService attraction_svc;
	
	@Autowired
	public void SetImplimentation(DiscountServiceImpl disc_impl, HotelServiceImpl hotel_impl, AttractionServiceImpl attraction_impl) {
		this.discount_svc = disc_impl;
		this.hotel_svc = hotel_impl;
		this.attraction_svc = attraction_impl;
	}
	
	@InitBinder()
	protected void initBinder(WebDataBinder binder) {
		
	}
	
	@PostMapping("/save")
	public String saveDiscountForm(@ModelAttribute("discount") @Valid Discount discount, BindingResult bindingResult,
			Model model,HttpSession session)  {
		
		System.out.println(discount.toString());
		discount_svc.save(discount);
		
		User user = (User) session.getAttribute("user");
		if(user.getRole().toString() == "HOTELMANAGER") {
			model.addAttribute("name", "Hotel");
			model.addAttribute("product", hotel_svc.findAll());
		} else if(user.getRole().toString() == "ATTRACTIONMANAGER") {
			model.addAttribute("name", "Attraction");
			model.addAttribute("product", attraction_svc.findAll());
		}
		
		model.addAttribute("discount", new Discount());
		return "discountForm";
	}
	
	@GetMapping("/discounts")
	public String viewDiscounts(Model model, HttpSession session) {		
		User user = (User) session.getAttribute("user");
		if(user.getRole().toString() == "HOTELMANAGER") {
			model.addAttribute("name", "Hotel");
			model.addAttribute("product", hotel_svc.findAll());
		} else if(user.getRole().toString() == "ATTRACTIONMANAGER") {
			model.addAttribute("name", "Attraction");
			model.addAttribute("product", attraction_svc.findAll());
		}
		
		model.addAttribute("discount", new Discount());
		return "discountForm";
	}
}
