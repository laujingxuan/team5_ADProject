package nus.edu.iss.adproject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

//import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import net.minidev.json.JSONObject;
import nus.edu.iss.adproject.model.Attraction;
import nus.edu.iss.adproject.model.Cart;
import nus.edu.iss.adproject.model.Hotel;
import nus.edu.iss.adproject.model.TravelPackage;
import nus.edu.iss.adproject.model.User;
import nus.edu.iss.adproject.nonEntityModel.CartForm;
import nus.edu.iss.adproject.nonEntityModel.CartWrapper;
import nus.edu.iss.adproject.nonEntityModel.DailyRoomDetailWrapper;
import nus.edu.iss.adproject.nonEntityModel.DailyRoomTypeDetail;
import nus.edu.iss.adproject.nonEntityModel.MultipleDateQuery;
import nus.edu.iss.adproject.nonEntityModel.ProductType;
import nus.edu.iss.adproject.service.CartService;
import nus.edu.iss.adproject.service.ProductService;
import nus.edu.iss.adproject.service.SessionService;
import nus.edu.iss.adproject.service.TravelPackageService;


@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cart_svc;
	
	@Autowired
	private SessionService session_svc;
	
	@Autowired
	private ProductService pservice;
	
	@Autowired
	private TravelPackageService tpservice;

	//done
	//view cart
    @GetMapping("/list")
    public String ListCartItems(Model model, HttpSession session){    
    	if (session_svc.isNotLoggedIn(session)) return "redirect:/user/login";
    	User user = (User) session.getAttribute("user");
        List<Cart> carts = cart_svc.findByUserId(user.getId());
        //map value is price of the cart item
        Map<Cart, Double> cartMap = new HashMap<Cart,Double>();
        //price for 1 room/1 attraction
        double price;
        for (Cart cart: carts) {
        	if(cart.getProduct().getType() == ProductType.HOTEL) {
        		price = getTotalNightPriceAfterHotelDiscount(cart);
        	}else {
        		price = getTotalPriceAfterAttractionDiscount(cart);
        	}
        	cartMap.put(cart, price);
        }
        
		List<TravelPackage> tpList = tpservice.retrieveAll();
		model.addAttribute("tpList", tpList);
        model.addAttribute("carts", cartMap);
        return "cart";
    }
    
	//done
	//add to cart
	@PostMapping("/add/{id}")
	public String AddItemToCart(@ModelAttribute("cartitem") @Valid CartForm cartitem, @PathVariable("id") Long productId, BindingResult bindingResult, Model model, HttpSession session) {
		if (session_svc.isNotLoggedIn(session)) return "redirect:/user/login";
		User user = (User) session.getAttribute("user");
		Cart newCart = new Cart(cartitem);
		newCart.setUser(user);
		if (newCart.getQuantity()==0) {
			newCart.setQuantity(1);
		}
		newCart.setProduct(pservice.findProductById(productId));;
		cart_svc.save(newCart);
		return "redirect:/cart/list";
	}
	
	//done
	@PostMapping("/update")
	@ResponseBody
	public ResponseEntity<Boolean> editCart(@RequestBody CartWrapper wrapper, HttpSession session) {
		Cart cart = cart_svc.findById(Long.parseLong(wrapper.getCartId()));
		cart.setQuantity(Integer.parseInt(wrapper.getQuantity()));
		cart.setNumGuests(Integer.parseInt(wrapper.getNumGuest()));
		if (!wrapper.getRemarks().equals("attractionPlaceHolder")) {
			cart.setRemarks(wrapper.getRemarks());
		}
		cart_svc.save(cart);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	@GetMapping("/delete/{id}")
	public String deleteMethod(Model model, @PathVariable("id") Long id, HttpSession session) {
		if (session_svc.isNotLoggedIn(session)) return "redirect:/user/login";
		User user = (User) session.getAttribute("user");
		Cart cartitem = cart_svc.findById(id);
		if (user.getId()!= cartitem.getUser().getId()) {
			model.addAttribute("error", "The item does not belongs to you!");
			return "error";
		}
		cart_svc.delete(cartitem);
		return "redirect:/cart/list";
	}
	
	public double getTotalNightPriceAfterHotelDiscount(Cart cart) {
		final String uri = cart.getProduct().getRoomType().getHotel().getAPI_URL()+ "room/period";	
	    RestTemplate restTemplate = new RestTemplate();
	    MultipleDateQuery query = new MultipleDateQuery(cart.getStartDate(), cart.getEndDate(), cart.getProduct().getRoomType().getRoomType());
	    DailyRoomDetailWrapper result = restTemplate.postForObject( uri, query, DailyRoomDetailWrapper.class);
	    double total = 0;
    	for (DailyRoomTypeDetail daily: result.getDailyList()) {
    		Hotel hotel = cart.getProduct().getRoomType().getHotel();
    		double discount_rate = 0;
    		//getting the largest discount rate for that day (The special discount set by the hotel manager)
    		for(int i = 0; i < hotel.getDiscount().size(); i++) {
    			if(daily.getDate().isAfter(hotel.getDiscount().get(i).getFrom_date()) && daily.getDate().isBefore(hotel.getDiscount().get(i).getTo_date())){
    				if(hotel.getDiscount().get(i).getDiscount_rate()>discount_rate) {
    					discount_rate = hotel.getDiscount().get(i).getDiscount_rate();
    				}
    			}
    		}
	    	total += daily.getDailyPrice()*(100-discount_rate)/100;
	    }
    	return total;
	}
	
	public double getTotalPriceAfterAttractionDiscount(Cart cart) {	
		//calculate the price based on the original price and discount percentage;
		Attraction attraction = cart.getProduct().getAttraction();
		double discount_rate = 0;
		//check every discount under the attraction and get the largest applicable discount
		for (int i = 0; i < attraction.getDiscount().size(); i++) {
			if (cart.getStartDate().isAfter(attraction.getDiscount().get(i).getFrom_date()) && cart.getStartDate().isBefore(attraction.getDiscount().get(i).getTo_date())) {
				if (attraction.getDiscount().get(i).getDiscount_rate()>discount_rate) {
					discount_rate = attraction.getDiscount().get(i).getDiscount_rate();
				}
			}
		}
		return cart.getProduct().getAttraction().getPrice() * (100-discount_rate)/100;
	}
	
	@ResponseBody
	@GetMapping("/getQuantityByUserId")
	public JSONObject CartItemTotalQuantity (Model model, HttpSession session) {
		if (session_svc.isNotLoggedIn(session)) {
			return null;
		}
		
		User user = (User)session.getAttribute("user");
		Long userId = user.getId();
		
		int quantity = cart_svc.getQuantityByUserId(userId);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", "success");
		map.put("total", quantity);
		
		JSONObject jsonObj = new JSONObject(map);
		
		return jsonObj;
	}
	
}