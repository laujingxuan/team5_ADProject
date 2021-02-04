package nus.edu.iss.adproject.controller;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import nus.edu.iss.adproject.model.Booking;
import nus.edu.iss.adproject.model.BookingDetails;
import nus.edu.iss.adproject.repository.BookingDetailsRepo;
import nus.edu.iss.adproject.service.AttractionServiceImpl;
import nus.edu.iss.adproject.service.BookingService;
import nus.edu.iss.adproject.service.BookingServiceImp;
import nus.edu.iss.adproject.service.DiscountServiceImpl;
import nus.edu.iss.adproject.service.HotelServiceImpl;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	@Autowired
	private BookingService bookingservice;
	
	@Autowired
	public void SetImplimentation(BookingServiceImp bookingservice_impl) {
		this.bookingservice = bookingservice_impl;
	}

	@GetMapping("/reports")
    public String showDashboard(Model model) {
		List<Object> b_details = bookingservice.findGuestByMonth();
		 
		Map<String, Integer> data = new LinkedHashMap<String, Integer>();
		int month = 0;
		int total_guest = 0;
		int months[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
		
		for (int i = 1; i <= months.length; i++) {
			month = i; total_guest =0;
		

			for (Iterator z = b_details.iterator(); z.hasNext();) {
				Object[] values = (Object[]) z.next();
				
//				for (int j = 0; j < values.length; j++) {
					
//					if (j == 0) {
						
						LocalDate date = LocalDate.parse(values[0].toString());
						if(month == date.getMonthValue()) {
							total_guest = Integer.parseInt(values[1].toString());
							
						}
//					}
//
//					else if (j == 1) {
						switch (month) {
						case (1):
							data.put("Jan", total_guest);
							break;
						case (2):
							data.put("Feb", total_guest);
							break;
						case (3):
							data.put("Mar", total_guest);
							break;
						case (4):
							data.put("Apr", total_guest);
							break;
						case (5):
							data.put("May", total_guest);
							break;
						case (6):
							data.put("Jun", total_guest);
							break;
						case (7):
							data.put("Jul", total_guest);
							break;
						case (8):
							data.put("Aug", total_guest);
							break;
						case (9):
							data.put("Sep", total_guest);
							break;
						case (10):
							data.put("Oct", total_guest);
							break;
						case (11):
							data.put("Nov", total_guest);
							break;
						case (12):
							data.put("Dec", total_guest);
							break;
						default:
							break;
						}
//					}
//				}
			}
		}

        model.addAttribute("data", data);
        return "dashboard";
    }
}
