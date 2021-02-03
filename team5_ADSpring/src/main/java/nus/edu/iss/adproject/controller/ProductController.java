package nus.edu.iss.adproject.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import nus.edu.iss.adproject.nonEntityModel.DailyAttractionDetail;
import nus.edu.iss.adproject.nonEntityModel.DailyDetailWrapper;
import nus.edu.iss.adproject.nonEntityModel.DailyRoomTypeDetail;
import nus.edu.iss.adproject.nonEntityModel.DailyRoomTypeDetailWrapper;
import nus.edu.iss.adproject.nonEntityModel.MonthTypeQuery;
import nus.edu.iss.adproject.repository.ProductRepo;



@Controller
@RequestMapping("/products")
public class ProductController {
	
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
		DailyRoomTypeDetailWrapper result =  restTemplate.postForObject(hotel1, roomtype,DailyRoomTypeDetailWrapper.class);
		System.out.println(result.getDailydetails());
		List<String> dates = new ArrayList<>() ;
		
		List<DailyRoomTypeDetail> list = result.getDailydetails();
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
}


