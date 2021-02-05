package nus.edu.iss.adproject.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import nus.edu.iss.adproject.model.Booking;
import nus.edu.iss.adproject.model.BookingDetails;
import nus.edu.iss.adproject.model.Product;
import nus.edu.iss.adproject.model.User;
import nus.edu.iss.adproject.service.BookingService;
import nus.edu.iss.adproject.service.ProductService;
import nus.edu.iss.adproject.service.SessionService;
import nus.edu.iss.adproject.service.UserService;

@Controller
public class MainController {
	
	@Autowired
	private ProductService pservice;
	
	@Autowired
	private BookingService bookService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SessionService session_svc;
	
	@RequestMapping("/")
	public String listProductForm(Model model, @Param("keyword") String keyword, HttpSession session) {
		System.out.println("keyword" + keyword);
		
		List<Product> listproducts = pservice.listAllSearchAttractions(keyword);
		List<Product> listhotels = pservice.listAllSearchHotels(keyword);
		listproducts.addAll(listhotels);
		model.addAttribute("product", listproducts);
		model.addAttribute("keyword", keyword); 
		System.out.println("checklogin" + keyword);
		if (session_svc.isNotLoggedIn(session) == false) {
			User user = (User) session.getAttribute("user");
			Set<Product> userRecommendation = retrieveRecommendationByUser(userService.findById(user.getId()));
			model.addAttribute("userRecommendation", userRecommendation);
		}
		System.out.println("before" + keyword);
		model.addAttribute("hotSellers",retrieveHotSellingProducts());
		System.out.println("last" + keyword);
		return "productslist";
	}
	
	public Set<Product> retrieveRecommendationByUser(User user){
		//retrieve 5 latest bookings of the user
		List<Booking>bookings = bookService.findLatestBookingsByUser(user);
		int i = 0;
		Map<Product, Integer> map = new HashMap<>();
		//Only get the latest 5 items bought
		for (Booking booking: bookings) {
			for (BookingDetails bk: booking.getBookingDetails()) {
				if (map.get(bk.getProduct())==null) {
					map.put(bk.getProduct(), 1);
					i += 1;
				}
				if (i == 5) {
					break;
				}
			}
			if (i == 5) {
				break;
			}
		}
		return map.keySet();
	}
	
	public List<Product> retrieveHotSellingProducts(){
		//Retrieve top 5 hot selling items for the past 1 month (Appear in the most bookings)
		List<Booking> bookings = bookService.findPastOneMonthBookings();
		//Loop through every booking details and count items by product id
		Map<Product, Integer> map = new HashMap<>();
		for (Booking booking: bookings) {
			System.out.println(booking.getBookingDate());
			for (BookingDetails bk: booking.getBookingDetails()) {
				if (map.get(bk.getProduct())==null) {
					map.put(bk.getProduct(), 1);
				}else {
					map.put(bk.getProduct(), map.get(bk.getProduct())+1);
				}
			}
		}
		//get the top 5 largest integer in the map
		List<Product> hotSellers = new ArrayList<Product>();
		int i = 0;
		while (map.isEmpty()==false && i < 5)
		{
		    Map.Entry<Product, Integer> maxEntry = null;

			for (Map.Entry<Product, Integer> entry : map.entrySet())
			{
			    if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
			    {
			        maxEntry = entry;
			    }
			}
			hotSellers.add(maxEntry.getKey());
			map.remove(maxEntry.getKey());
			i+=1;
		}
		return hotSellers;
	}
}
