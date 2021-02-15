package nus.edu.iss.adproject.api.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nus.edu.iss.adproject.model.Hotel;
import nus.edu.iss.adproject.model.RoomType;
import nus.edu.iss.adproject.nonEntityModel.HotelWrapper;
import nus.edu.iss.adproject.nonEntityModel.ProductWrapper;
import nus.edu.iss.adproject.nonEntityModel.RoomTypeWrapper;
import nus.edu.iss.adproject.service.RoomTypeService;

@RestController
@CrossOrigin
@RequestMapping("/api/roomtype")
public class roomTyoeApiController {
	@Autowired
	private RoomTypeService rtservice;
	@GetMapping("/roomtype")
	public ResponseEntity<RoomTypeWrapper> getroomType(Model model, HttpSession session){
		List<RoomType> roomType = rtservice.findAll();
		
		return new ResponseEntity<RoomTypeWrapper>(new RoomTypeWrapper(roomType),HttpStatus.OK);
		
	}
	

}
