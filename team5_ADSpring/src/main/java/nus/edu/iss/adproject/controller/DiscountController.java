package nus.edu.iss.adproject.controller;

import java.util.Iterator;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;

import nus.edu.iss.adproject.model.Discount;
import nus.edu.iss.adproject.model.Hotel;
import nus.edu.iss.adproject.service.DiscountService;
import nus.edu.iss.adproject.service.DiscountServiceImpl;
import nus.edu.iss.adproject.service.HotelService;
import nus.edu.iss.adproject.service.HotelServiceImpl;

@Controller
@RequestMapping("/discount")
public class DiscountController {

	@Autowired
	private DiscountService discount_svc;
	
	@Autowired
	private HotelService hotel_svc;
	
	@Autowired
	public void SetImplimentation(DiscountServiceImpl disc_impl, HotelServiceImpl hotel_impl) {
		this.discount_svc = disc_impl;
		this.hotel_svc = hotel_impl;
	}
	
	@InitBinder()
	protected void initBinder(WebDataBinder binder) {
		
	}
	
	@GetMapping("/save")
	public String saveDiscountForm(@ModelAttribute("discount") @Valid Discount discount, BindingResult bindingResult,
			Model model) {
		System.out.println("Save");
		discount_svc.save(discount);
		
		model.addAttribute("hotel", hotel_svc.findAll());
		model.addAttribute("discount", new Discount());
		return "discountForm";
	}
	
	@GetMapping("/discounts")
	public String viewDiscounts(Model model, HttpSession session) {

		
		model.addAttribute("hotel", hotel_svc.findAll());
		model.addAttribute("discount", new Discount());
		return "discountForm";
	}
}
