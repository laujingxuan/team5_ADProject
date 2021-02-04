package nus.edu.iss.adproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import nus.edu.iss.adproject.model.RoomType;
import nus.edu.iss.adproject.service.RoomTypeService;
@Controller
@RequestMapping("/Room")
public class RoomTypeController {
	@Autowired
	private RoomTypeService RTservice;
	@GetMapping("/SimilarRoom")
	public String GetSimlilarRoom(Model model)
	{
		List<RoomType> RoomT=  RTservice.findAll();
		System.out.print(RoomT);
		model.addAttribute("Rooms",RoomT);		
		return "SimilarRoom";
	}
	@GetMapping("/SimilarRoom/{roomtype}")
	public String getsimilar(@PathVariable("roomtype") String roomT,Model model)
	{
		String checkst= roomT.toLowerCase();
		List<RoomType> RoomT= RTservice.findbyName(checkst);
		System.out.println("this is room string"+checkst);
		System.out.print("this is obj" + RoomT);
		model.addAttribute("Rooms",RoomT);
		return "SimilarRoom";
	}

}
