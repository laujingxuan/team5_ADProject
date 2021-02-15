package nus.edu.iss.adproject.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nus.edu.iss.adproject.model.Attraction;
import nus.edu.iss.adproject.model.Booking;
import nus.edu.iss.adproject.model.BookingDetails;
import nus.edu.iss.adproject.model.Hotel;
import nus.edu.iss.adproject.model.Product;
import nus.edu.iss.adproject.model.User;
import nus.edu.iss.adproject.nonEntityModel.ProductType;
import nus.edu.iss.adproject.nonEntityModel.ProductWrapper;
import nus.edu.iss.adproject.service.AttractionService;
import nus.edu.iss.adproject.service.BookingService;
import nus.edu.iss.adproject.service.HotelService;
import nus.edu.iss.adproject.service.ProductService;
import nus.edu.iss.adproject.service.RoomTypeService;
import nus.edu.iss.adproject.service.SessionService;
import nus.edu.iss.adproject.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/api/product")
public class ProductApiController {
	
	@Autowired
	private ProductService pservice;
	
	@Autowired
	private AttractionService aservice;
	
	@Autowired
	private HotelService hotelservice;
	
	@Autowired
	private RoomTypeService roomService;
	
	@GetMapping("/search")
	public ResponseEntity<List<Product>> listProductForm(@Param("keyword") String keyword) {
		List<Product> listproducts;
		listproducts = pservice.listAllSearchAttractions(keyword);
		List<Product> listRoomTypes = pservice.listAllSearchHotels(keyword);
		listproducts.addAll(listRoomTypes);
		
		return new ResponseEntity<List<Product>>(listproducts ,HttpStatus.OK);
	}
	
////	http://localhost:8080/api/product/product/detail/5
//	@GetMapping("/product/detail/{id}")
//	public <T> ResponseEntity<T> viewProductDetail(Model model, @PathVariable("id")Long id) {
//		Product product = pservice.findProductById(id);
//		model.addAttribute("product", product);
//		if(product.getType().equals(ProductType.ATTRACTION)) {
//			Attraction attraction = aservice.findAttractionByProductId(id);
//			//model.addAttribute("attraction", attraction);
//			return new ResponseEntity<T> ( (T)attraction,HttpStatus.OK);
//		}else {
//			Hotel hotel = hotelservice.findHotelByProductId(id);
//			model.addAttribute("hotel", hotel);
//			model.addAttribute("roomType", roomService.findRoomTypesByHotelId(hotel.getId()));
//			return new ResponseEntity<T> ((T) hotel,HttpStatus.OK);
//		}
//	}

}
