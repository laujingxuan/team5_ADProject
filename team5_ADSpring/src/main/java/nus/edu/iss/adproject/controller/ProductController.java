package nus.edu.iss.adproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import nus.edu.iss.adproject.model.Attraction;
import nus.edu.iss.adproject.model.Hotel;
import nus.edu.iss.adproject.model.Product;
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
	
}
