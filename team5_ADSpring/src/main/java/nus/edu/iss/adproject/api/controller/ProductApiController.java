package nus.edu.iss.adproject.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nus.edu.iss.adproject.model.Hotel;
import nus.edu.iss.adproject.repository.AttractionRepository;
import nus.edu.iss.adproject.repository.ProductRepo;
import nus.edu.iss.adproject.service.AttractionService;
import nus.edu.iss.adproject.service.HotelService;
import nus.edu.iss.adproject.service.ProductService;
import nus.edu.iss.adproject.service.RoomTypeService;
import nus.edu.iss.adproject.service.SessionService;

@RestController
@CrossOrigin
@RequestMapping("/api/product")
public class ProductApiController {
	
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
	private RoomTypeService RTService;
	
	@Autowired
	private SessionService session_svc;
	
	// wrapper required 
	@GetMapping("/hotel")
	public ResponseEntity<Hotel> listOfHotel(){
		return new ResponseEntity<Hotel>((Hotel) hservice.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/hotel/{id}")
	public ResponseEntity<Hotel> findHotelById(@PathVariable("id") Long id){
		return new ResponseEntity<Hotel>(hservice.findById(id),HttpStatus.OK);
	}
	
	@GetMapping("/map/{id}")
	public ResponseEntity<Hotel> findHotelByIdGetMap(Model model,@PathVariable("id") long id )
	{
		return new ResponseEntity<Hotel>(hservice.findById(id),HttpStatus.OK);
	}
	


}
