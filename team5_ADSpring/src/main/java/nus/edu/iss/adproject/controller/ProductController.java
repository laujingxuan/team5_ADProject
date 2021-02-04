package nus.edu.iss.adproject.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import nus.edu.iss.adproject.model.Attraction;
import nus.edu.iss.adproject.model.Hotel;
import nus.edu.iss.adproject.model.Product;
import nus.edu.iss.adproject.nonEntityModel.DailyAttractionDetail;
import nus.edu.iss.adproject.nonEntityModel.DailyDetailWrapper;
import nus.edu.iss.adproject.nonEntityModel.DailyRoomDetailWrapper;
import nus.edu.iss.adproject.nonEntityModel.DailyRoomTypeDetail;
import nus.edu.iss.adproject.nonEntityModel.MonthTypeQuery;
import nus.edu.iss.adproject.repository.ProductRepo;
import nus.edu.iss.adproject.service.AttractionService;
import nus.edu.iss.adproject.service.HotelService;
import nus.edu.iss.adproject.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService pservice;
	
	@Autowired
	private ProductRepo prepo;
	
	@Autowired
	private AttractionService aservice;
	
	@Autowired
	private HotelService hservice;
	
	
	@GetMapping("/list")
	public String listProductForm(Model model, @Param("keyword") String keyword) {
		List<Product> listattractions = pservice.listAllSearchAttractions(keyword);
		List<Product> listhotels = pservice.listAllSearchHotels(keyword);
		model.addAttribute("product", listattractions);
		model.addAttribute("product", listhotels);
		model.addAttribute("keyword", keyword); 
		
		return "productslist";
	}
	
	
	@GetMapping("/detail/{id}")
	public String viewProductDetail(Model model, @PathVariable("id")Long id) {
		Product product = pservice.findProductById(id);
		model.addAttribute("product", product);
		if(product.getType().equals("Attraction")) {
			Attraction attraction = aservice.findAttractionByProductId(id);
			model.addAttribute("attraction", attraction);
			return "attractiondetail";
		}else {
			Hotel hotel = hservice.findHotelByProductId(id);
			model.addAttribute("hotel", hotel);
			return "hoteldetail";
		}
	}
  
  	@Autowired ProductRepo pRepo;
	
	@RequestMapping(value = "/available-date")
	public String getAttractionAvailibleDate(Model model)  {

			String attraction1 =  "http://localhost:8081/api/attraction/booking/month";
			RestTemplate restTemplate = new RestTemplate();
			
			MonthTypeQuery month = new MonthTypeQuery(1);
			
			DailyDetailWrapper result =  restTemplate.postForObject(attraction1, month,DailyDetailWrapper.class);
			
			//System.out.println(result.getDailyDetails());
			List<String> dates = new ArrayList<>() ;
			
			List<DailyAttractionDetail> list = result.getDailyDetails();
			for(DailyAttractionDetail d : list) {
				if(d.getQuantityLeft()>0) {
					LocalDate date = d.getDate();
					String date1 = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
					dates.add(date1);
				}
			}		
			System.out.println(dates);		
			model.addAttribute("dates1", dates);
		
		return "Attraction-available-date";
	}
	
	@RequestMapping(value = "/room-available-date")
	public String gethotelRoomTypeAvailibleDate(Model model)  {
		
		String hotel1 =  "http://localhost:8081/api/hotel/room/month";
		
		RestTemplate restTemplate = new RestTemplate();
		MonthTypeQuery roomtype = new MonthTypeQuery(1,"single");
		DailyRoomDetailWrapper result =  restTemplate.postForObject(hotel1, roomtype, DailyRoomDetailWrapper.class);
		System.out.println(result.getDailyList());
		List<String> dates = new ArrayList<>() ;
		
		List<DailyRoomTypeDetail> list = result.getDailyList();
		for(DailyRoomTypeDetail d : list) {
			if(d.getNumVacancies()> 0) {
				LocalDate date = d.getDate();
				String date1 = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				dates.add(date1);
			}
		}
		System.out.println(dates);	
		model.addAttribute("dates1", dates);
		
		return "hotel-roomType-availble-date";
	}
	
	@GetMapping("/edit/{id}")
	public String showEditForm(Model model, @PathVariable("id") Long id) {
		model.addAttribute("attraction", aservice.findById(id));
		return "product-form";
	}
	
	@GetMapping("/save")
	public String saveProductForm(@ModelAttribute("attraction") @Valid Attraction attraction, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			return "productform";
		}
		aservice.save(attraction);
		return "forward:/product/list";
	}
}





