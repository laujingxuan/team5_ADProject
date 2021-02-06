package nus.edu.iss.adproject.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import nus.edu.iss.adproject.model.RoomType;
import nus.edu.iss.adproject.nonEntityModel.DailyAttractionDetail;
import nus.edu.iss.adproject.nonEntityModel.DailyDetailWrapper;
import nus.edu.iss.adproject.nonEntityModel.DailyRoomDetailWrapper;
import nus.edu.iss.adproject.nonEntityModel.DailyRoomTypeDetail;
import nus.edu.iss.adproject.nonEntityModel.MonthTypeQuery;
import nus.edu.iss.adproject.nonEntityModel.ProductType;
import nus.edu.iss.adproject.repository.AttractionRepository;
import nus.edu.iss.adproject.repository.ProductRepo;
import nus.edu.iss.adproject.repository.RoomTypeRepo;
import nus.edu.iss.adproject.service.AttractionService;
import nus.edu.iss.adproject.service.HotelService;
import nus.edu.iss.adproject.service.ProductService;
import nus.edu.iss.adproject.service.RoomTypeService;


@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired 
	ProductRepo pRepo;
	
	@Autowired
	AttractionRepository Attrepo;
	@Autowired
	private ProductService pservice;
	
	@Autowired
	private AttractionService aservice;
	
	@Autowired
	private HotelService hservice;
	
	@Autowired
	private RoomTypeRepo RTrepo;
	
	@Autowired
	private RoomTypeService RTService;
	

	@GetMapping("/detail/{id}")
	public String viewProductDetail(Model model, @PathVariable("id")Long id) {
		Product product = pservice.findProductById(id);
		model.addAttribute("product", product);
		System.out.println(product.getType());
		if(product.getType().equals(ProductType.ATTRACTION)) {
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
		MonthTypeQuery roomtype = new MonthTypeQuery(1,RoomType);
		
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

	@GetMapping("/createHotel")
	public String createHotel(Model model)
	{
		model.addAttribute("hotel", new Hotel());
		return "ProductHotelCreate";
	}
	
	
	@GetMapping("/saveHotel")
	public String saveHotel(@ModelAttribute("hotel") @Valid Hotel hotel, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("Hotel", hservice.findAll());
		
			return "ProductHotelCreate";
		}
		System.out.println("this is hotel object"+hotel);
		hservice.save(hotel);
		return "forward:/product/createRoom";
  }
	
	@GetMapping("/createRoom")
	public String createProduct(Model model)
	{
		model.addAttribute("Hotels", hservice.findAll());
		//model.addAttribute("ProductType", ProductType.values());	
//		
//		System.out.println("this new product id "+newroom.getId());
//		model.addAttribute("newP",newroom);
		model.addAttribute("room", new RoomType());
		return "ProductCreation";
	}
	
	@GetMapping("/saveRoom")
	public String saveRoom(@ModelAttribute("room") @Valid RoomType room, BindingResult bindingResult,
			Model model)
	{
		if (bindingResult.hasErrors()) {
			model.addAttribute("Hotels", hservice.findAll());
			//model.addAttribute("ProductType", ProductType.values());
			model.addAttribute("room", new RoomType());		
			return "ProductCreation";
		}
		System.out.println("this is room object"+room);
		Product newroom= new Product(ProductType.HOTEL);
		pservice.save(newroom);
		room.setProduct(newroom);
		RTService.save(room);
		return "forward:/hotel/roomtypes";
	}
	@GetMapping("/delete/room/{id}")
	public String deleteMethod(Model model, @PathVariable("id") Long id) {
		RTrepo.deleteById(id);
//		RoomType Rtype = RTService.findById(id);
//		RTService.delete(Rtype);
		return "forward:/hotel/roomtypes";
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
			return "product-form";
		}
		
		aservice.save(attraction);
		return "forward:/product/list";
	}
	@GetMapping("/CreateAttraction")
	public String CreateAtt(Model model)
	
	{
		model.addAttribute("attraction",new Attraction());
		return "CreateAttraction";
	}
	@GetMapping("/saveAttraction")
	public String saveAtt(@ModelAttribute("attraction") @Valid Attraction attraction, BindingResult bindingResult,
			Model model) {
		
		if (bindingResult.hasErrors()) {
			return "CreateAttraction";
		}
		Product newAtt= new Product(ProductType.ATTRACTION);
		pservice.save(newAtt);
		attraction.setProduct(newAtt);
		aservice.save(attraction);
		System.out.println(attraction);
		return "forward:/product/AttractionOverall";
	}
	@GetMapping("/AttractionOverall")
	public String attoverall(Model model)
	{
		List<Attraction> AttAll= aservice.findAll();
		System.out.println("this is attraction after create att : "+ AttAll);
		model.addAttribute("attractions",AttAll);
		return "attractionOverAll";
	}
	@GetMapping("/delete/AttDel/{id}")
	public String deleteAtt(Model model, @PathVariable("id") Long id) {
		Attrepo.deleteById(id);

		return "forward:/product/AttractionOverall";
	}

}






