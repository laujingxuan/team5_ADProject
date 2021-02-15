package nus.edu.iss.adproject.api.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import nus.edu.iss.adproject.model.Attraction;
import nus.edu.iss.adproject.model.Discount;
import nus.edu.iss.adproject.model.Product;
import nus.edu.iss.adproject.model.User;
import nus.edu.iss.adproject.nonEntityModel.AttractionWrapper;
import nus.edu.iss.adproject.nonEntityModel.CartForm;
import nus.edu.iss.adproject.nonEntityModel.DailyAttractionDetail;
import nus.edu.iss.adproject.nonEntityModel.DailyDetailWrapper;
import nus.edu.iss.adproject.nonEntityModel.MonthTypeQuery;
import nus.edu.iss.adproject.service.AttractionService;
import nus.edu.iss.adproject.service.DiscountService;
import nus.edu.iss.adproject.service.ProductService;
import nus.edu.iss.adproject.service.SessionService;

@RestController
@CrossOrigin
@RequestMapping("/api/attraction")
public class AttractionApiController {
	@Autowired
	private ProductService pservice;
	
	@Autowired
	private AttractionService aservice;
	
	@Autowired
	private SessionService session_svc;
	
	@Autowired
	private DiscountService discountService;
	
//	http://localhost:8080/api/attraction/attractions
	//show list of attractions

	
//	public ResponseEntity<AttractionWrapper> getAttraction(HttpSession session){
////		if (session_svc.isNotLoggedIn(session)) return "redirect:/user/login";
////		if (session_svc.hasAttractionPermission(session) == false) {
////			model.addAttribute("error", "No Permission");
////			return "error";
////		}
////		User user = (User) session.getAttribute("user");
////		List<Attraction> attractions=  aservice.findByUserId(user.getId());
////		model.addAttribute("attractions", attractions);
//		List<Attraction> attractions=  aservice.findAll();
//		return new ResponseEntity<AttractionWrapper>(new AttractionWrapper(attractions) , HttpStatus.OK);
//	}
	@GetMapping("/attractions")
	public ResponseEntity<AttractionWrapper> getAttraction(HttpSession session){
//		if (session_svc.isNotLoggedIn(session)) return "redirect:/user/login";
//		if (session_svc.hasAttractionPermission(session) == false) {
//			model.addAttribute("error", "No Permission");
//			return "error";
//		}
//		User user = (User) session.getAttribute("user");
//		List<Attraction> attractions=  aservice.findByUserId(user.getId());
//		model.addAttribute("attractions", attractions);
		List<Attraction> attractions=  aservice.findAll();
		return new ResponseEntity<AttractionWrapper>(new AttractionWrapper(attractions) , HttpStatus.OK);
	}
//	http://localhost:8080/api/attraction/map/1	
	//show the location of the attraction
	@GetMapping("/map/{id}")
	public ResponseEntity<Attraction> getMap(@PathVariable("id") long id ){
		Attraction val = aservice.findById(id); 
//		model.addAttribute("longi",val.getLongi());
//		model.addAttribute("lat", val.getLat());
		return new ResponseEntity<Attraction>(val,HttpStatus.OK);
	}	
	
//	http://localhost:8080/api/attraction/available-date/1
	@RequestMapping(value = "/available-date/{id}")
	public ResponseEntity<DailyDetailWrapper> getAttractionAvailibleDate(Model model,@PathVariable("id")Long productId)  {
			Product p = pservice.findProductById(productId);
			String URL = p.getAttraction().getAPI_URL()+ "booking/month";
			double price = p.getAttraction().getPrice();
			List<Discount> discountlist= p.getAttraction().getDiscount();
			String discount = "";
			for(Discount d : discountlist) {
				discount += "Discount start from "+d.getFrom_date().toString() + " to " + d.getTo_date().toString()
						+ " the discount is " + d.getDiscount_rate() + "%";
			}
			RestTemplate restTemplate = new RestTemplate();
			MonthTypeQuery month = new MonthTypeQuery(1);	
			DailyDetailWrapper result =  restTemplate.postForObject(URL, month,DailyDetailWrapper.class);
			
//			List<String> dates = new ArrayList<>() ;
//			List<DailyAttractionDetail> list = result.getDailyDetails();
//			for(DailyAttractionDetail d : list) {
//				if(d.getQuantityLeft()>0) {
//					LocalDate date = d.getDate();
//					String date1 = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
//					dates.add(date1);
//				}
//			}		
//			model.addAttribute("price",price);
//			model.addAttribute("dates1", dates);
//			model.addAttribute("discount",discount);
//		
//			model.addAttribute("cartitem", new CartForm());
//			model.addAttribute("productId", productId);
//			
		return new ResponseEntity<DailyDetailWrapper>(result,HttpStatus.OK);
	}
	
}
