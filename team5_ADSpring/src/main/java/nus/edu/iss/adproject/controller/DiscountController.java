package nus.edu.iss.adproject.controller;


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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import nus.edu.iss.adproject.model.Attraction;
import nus.edu.iss.adproject.model.Discount;
import nus.edu.iss.adproject.model.Hotel;
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
	private SessionService session_svc;
	
	@Autowired
	public void SetImplimentation(DiscountServiceImpl disc_impl,SessionServiceImpl session_svcimpl, HotelServiceImpl hotel_impl, AttractionServiceImpl attraction_impl) {
		this.discount_svc = disc_impl;
		this.hotel_svc = hotel_impl;
		this.attraction_svc = attraction_impl;
		this.session_svc = session_svcimpl;
	}
	
	@InitBinder()
	protected void initBinder(WebDataBinder binder) {
		
	}
	
	@GetMapping("/save")
	public String saveDiscountForm(@ModelAttribute("discount") @Valid Discount discount, BindingResult bindingResult,
			Model model,HttpSession session)  {
		discount_svc.save(discount);
		
		/*
		 * User user = (User) session.getAttribute("user"); if(user.getRole().toString()
		 * == "HOTELMANAGER") { model.addAttribute("name", "Hotel");
		 * model.addAttribute("product", hotel_svc.findAll()); } else
		 * if(user.getRole().toString() == "ATTRACTIONMANAGER") {
		 * model.addAttribute("name", "Attraction"); model.addAttribute("product",
		 * attraction_svc.findAll()); }
		 * 
		 * model.addAttribute("discount", new Discount());
		 */
		return "forward:/discount/list";
	}
	
	@GetMapping("/create")
	public String viewDiscounts(Model model, HttpSession session) {		
		if (session_svc.isNotLoggedIn(session)) return "redirect:/user/login";
		if (session_svc.hasHotelPermission(session) == false && session_svc.hasAttractionPermission(session) == false) {
			model.addAttribute("error", "No Permission");
			return "error";
		}
		User user = (User) session.getAttribute("user");
		List<Hotel> hotel = hotel_svc.findByUserId(user.getId());
		List<Attraction> attraction = attraction_svc.findByUserId(user.getId());
		if(hotel.size()> 0) {
			model.addAttribute("name", "Hotel");
			model.addAttribute("hotel", hotel);
		} else if(attraction.size()>0) {
			model.addAttribute("name", "Attraction");
			model.addAttribute("attraction", attraction);
		}		
		model.addAttribute("discount", new Discount());
		return "discountForm";
	}
	
	@GetMapping("/edit/{id}")
	public String showEditForm(Model model, @PathVariable("id") Long id, HttpSession session) {
		if (session_svc.isNotLoggedIn(session)) return "redirect:/user/login";
		if (session_svc.hasHotelPermission(session) == false && session_svc.hasAttractionPermission(session) == false) {
			model.addAttribute("error", "No Permission");
			return "error";
		}
		Discount discount = discount_svc.findById(id);
		if(discount.getHotel()!=null) {
			model.addAttribute("name", "Hotel");
			model.addAttribute("hotel", hotel_svc.findByUserId(discount.getHotel().getUser().getId()));
		} else if(discount.getAttraction()!=null) {
			model.addAttribute("name", "Attraction");
			model.addAttribute("attraction", attraction_svc.findByUserId(discount.getAttraction().getUser().getId()));
		}
		model.addAttribute("discount", discount_svc.findById(id));
		return "discountForm";
	}
	
	@GetMapping("/list")
	public String listDiscountForm(Model model,HttpSession session) {	
		if (session_svc.isNotLoggedIn(session)) return "redirect:/user/login";
		if (session_svc.hasHotelPermission(session) == false && session_svc.hasAttractionPermission(session) == false) {
			model.addAttribute("error", "No Permission");
			return "error";
		}
		User user = (User) session.getAttribute("user");
		List<Discount> hotel_dis = discount_svc.findDiscountByHotelUserId(user.getId());
		List<Discount> attraction_dis = discount_svc.findDiscountByAttractionUserId(user.getId());
		if(hotel_dis.size()>0) {
			model.addAttribute("discounts", hotel_dis);
		} else if(attraction_dis.size()>0) {
			model.addAttribute("discounts", attraction_dis);
		}
		return "discounts";
	}
	
	@RequestMapping(value = "/delete/{id}")
	public String deleteSupplier(@PathVariable("id") Long id, HttpSession session, Model model) {		
		if (session_svc.isNotLoggedIn(session)) return "redirect:/user/login";
		if (session_svc.hasHotelPermission(session) == false && session_svc.hasAttractionPermission(session) == false) {
			model.addAttribute("error", "No Permission");
			return "error";
		}
		discount_svc.delete(discount_svc.findById(id));
		return "redirect:/discount/list";
	}
}
