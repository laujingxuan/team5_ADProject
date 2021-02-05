package nus.edu.iss.adproject.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
  
	@RequestMapping(value = "/available-date/{id}")
	public String getAttractionAvailibleDate(Model model,@PathVariable("id")Long id)  {
			Product p = pservice.findProductById(id);
			String URL = p.getAttraction().getAPI_URL()+ "booking/month";
			double price = p.getAttraction().getPrice();
			//System.out.println(price);
			//String attraction1 =  "http://localhost:8081/api/attraction/booking/month";
			RestTemplate restTemplate = new RestTemplate();
			
			MonthTypeQuery month = new MonthTypeQuery(1);	
			
			DailyDetailWrapper result =  restTemplate.postForObject(URL, month,DailyDetailWrapper.class);

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
			model.addAttribute("price",price);
			model.addAttribute("dates1", dates);
		
		return "Attraction-available-date";
	}
	
	@RequestMapping(value = "/room-available-date/{id}")
	public String gethotelRoomTypeAvailibleDate(Model model,@PathVariable("id")Long id)  {
		Product p = pservice.findProductById(id);
		String URL = p.getRoomType().getHotel().getAPI_URL()+"room/month";
		String RoomType = p.getRoomType().getRoomType();
		
		String hotel1 =  "http://localhost:8081/api/hotel/room/month";
		
		RestTemplate restTemplate = new RestTemplate();
		MonthTypeQuery roomtype = new MonthTypeQuery(1,"single");
		
		DailyRoomDetailWrapper result =  restTemplate.postForObject(URL, roomtype, DailyRoomDetailWrapper.class);
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
		model.addAttribute("RoomType",RoomType);
		return "hotel-roomType-availble-date";
	}
	
	
	@RequestMapping(method = RequestMethod.GET,value = "update/price")
	@ResponseBody
	public String check(HttpServletRequest request, HttpServletResponse response) {
		
		
		
		return null;
		
	}
	
}





