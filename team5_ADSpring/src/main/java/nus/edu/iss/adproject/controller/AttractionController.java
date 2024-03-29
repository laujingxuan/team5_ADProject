package nus.edu.iss.adproject.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import nus.edu.iss.adproject.model.Attraction;
import nus.edu.iss.adproject.model.Discount;
import nus.edu.iss.adproject.model.Product;
import nus.edu.iss.adproject.model.ProductReview;
import nus.edu.iss.adproject.model.User;
import nus.edu.iss.adproject.nonEntityModel.CartForm;
import nus.edu.iss.adproject.nonEntityModel.DailyAttractionDetail;
import nus.edu.iss.adproject.nonEntityModel.DailyDetailWrapper;
import nus.edu.iss.adproject.nonEntityModel.MonthTypeQuery;
import nus.edu.iss.adproject.nonEntityModel.MultipleDateQuery;
import nus.edu.iss.adproject.nonEntityModel.ProductType;
import nus.edu.iss.adproject.service.AttractionService;
import nus.edu.iss.adproject.service.DiscountService;
import nus.edu.iss.adproject.service.ProductReviewService;
import nus.edu.iss.adproject.service.ProductService;
import nus.edu.iss.adproject.service.SessionService;


@Controller
@RequestMapping("/attraction")
public class AttractionController {

	@Autowired
	private ProductService pservice;
	
	@Autowired
	private AttractionService aservice;
	
	@Autowired
	private SessionService session_svc;
	
	//done
	//show list of attractions
	@GetMapping("/attractions")
	public String getAttractions(Model model, HttpSession session){
		if (session_svc.isNotLoggedIn(session)) return "redirect:/user/login";
		if (session_svc.hasAttractionPermission(session) == false) {
			model.addAttribute("error", "No Permission");
			return "error";
		}
		User user = (User) session.getAttribute("user");
		List<Attraction> attractions=  aservice.findByUserId(user.getId());
		model.addAttribute("attractions", attractions);
		return "attractionList";
	}
	
	//done
	//show the location of the attraction
	@GetMapping("/map/{id}")
	public String getMap(Model model,@PathVariable("id") long id ){
		Attraction val = aservice.findById(id); 
		model.addAttribute("longi",val.getLongi());
		model.addAttribute("lat", val.getLat());
		return "map";
	}	
	
	
	@RequestMapping(value = "/available-date/{id}")
	public String getAttractionAvailibleDate(Model model,@PathVariable("id")Long productId)  {
			Product p = pservice.findProductById(productId);
			String URL = p.getAttraction().getAPI_URL()+ "booking/period";
			double price = p.getAttraction().getPrice();
			List<Discount> discountlist= p.getAttraction().getDiscount();
			List<String>discountList = new ArrayList<>();
			for(Discount d : discountlist) {
				discountList.add("Discount from "+d.getFrom_date().toString() + " to " + d.getTo_date().toString()
						+ ": " + d.getDiscount_rate() + "%");
			}
			RestTemplate restTemplate = new RestTemplate();
			//showing next 3 months availability, can changed the period accordingly
			MultipleDateQuery period = new MultipleDateQuery(LocalDate.now(),LocalDate.now().plusMonths(3));
			DailyDetailWrapper result =  restTemplate.postForObject(URL, period, DailyDetailWrapper.class);
			List<String> dates = new ArrayList<>() ;
			List<DailyAttractionDetail> list = result.getDailyDetails();
			for(DailyAttractionDetail d : list) {
				if(d != null && d.getQuantityLeft()>0) {
					LocalDate date = d.getDate();
					String date1 = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
					dates.add(date1);
				}
			}		
			model.addAttribute("price",price);
			model.addAttribute("dates1", dates);
			model.addAttribute("discountList",discountList);
			model.addAttribute("cartitem", new CartForm());
			model.addAttribute("productId", productId);
		return "Attraction-available-date";
	}
	
	
//	----------------------------------------------------------------------------------------------------------------------edit/create
	//done
	//edit attraction product
	@GetMapping("/edit/{id}")
	public String showEditForm(Model model, @PathVariable("id") Long id, HttpSession session) {
		if (session_svc.isNotLoggedIn(session)) return "redirect:/user/login";
		User user = (User) session.getAttribute("user");
		Attraction attraction = aservice.findById(id);
		if (attraction.getUser().getId() != user.getId()) {
			model.addAttribute("error", "No Permission");
			return "error";
		}
		model.addAttribute("attraction", attraction);
		return "editAttraction";
	}
	
	//done
	@GetMapping("/create")
	public String createAttraction(Model model, HttpSession session) {
		if (session_svc.isNotLoggedIn(session)) return "redirect:/user/login";
		if (session_svc.hasAttractionPermission(session) == false) {
			model.addAttribute("error", "No Permission");
			return "error";
		}
		model.addAttribute("attraction",new Attraction());
		return "editAttraction";
	}
	
	//done
	@PostMapping("/save")
	public String saveProductForm(@ModelAttribute("attraction") @Valid Attraction attraction, BindingResult bindingResult, Model model, HttpSession session) {
		if (bindingResult.hasErrors()) {
			return "editAttraction";
		}
		if (attraction.getProduct()==null) {
			Product newAttraction = new Product(ProductType.ATTRACTION);
			pservice.save(newAttraction);
			attraction.setProduct(newAttraction);
			User user = (User) session.getAttribute("user");
			attraction.setUser(user);
		}
		aservice.save(attraction);
		return "redirect:/attraction/attractions";
	}
	
//	----------------------------------------------------------------------------------------------------------------------delete
	//done
	@GetMapping("/delete/{id}")
	public String deleteAttraction(Model model, @PathVariable("id") Long attractionId, HttpSession session) {
		if (session_svc.isNotLoggedIn(session)) return "redirect:/user/login";
		User user = (User) session.getAttribute("user");
		Attraction attraction = aservice.findById(attractionId);
		if (attraction.getUser().getId() != user.getId()) {
			model.addAttribute("error", "No Permission");
			return "error";
		}
		aservice.delete(attraction);
		return "redirect:/attraction/attractions";
	}

}






