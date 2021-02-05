package nus.edu.iss.adproject.controller;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import nus.edu.iss.adproject.model.Booking;
import nus.edu.iss.adproject.model.BookingDetails;
import nus.edu.iss.adproject.model.User;
import nus.edu.iss.adproject.repository.BookingDetailsRepo;
import nus.edu.iss.adproject.service.AttractionServiceImpl;
import nus.edu.iss.adproject.service.BookingService;
import nus.edu.iss.adproject.service.BookingServiceImp;
import nus.edu.iss.adproject.service.DiscountServiceImpl;
import nus.edu.iss.adproject.service.HotelServiceImpl;
import nus.edu.iss.adproject.service.SessionService;
import nus.edu.iss.adproject.service.SessionServiceImpl;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	@Autowired
	private BookingService bookingservice;
	
	@Autowired
	private SessionService sessionservice;
	
	@Autowired
	public void SetImplimentation(BookingServiceImp bookingservice_impl,SessionServiceImpl session_svcimpl) {
		this.bookingservice = bookingservice_impl;
		this.sessionservice = session_svcimpl;
	}

	@GetMapping("/reports")
    public String showDashboard(Model model, HttpSession session) {
//		User user = (User) session.getAttribute("user");
		User user = new User();
		user.setId(9);
		List<Object> b_details = bookingservice.findGuestByMonth();
		List<Object> b_revenue = bookingservice.findMonthlyRevenueByHotel(user.getId());
		List<Object> monthly_bookingRate = bookingservice.findMonthlyBookingRateByHotel(user.getId());
		 
		Map<String, Integer> data = new LinkedHashMap<String, Integer>();
		Map<String, Double> data_revenue = new LinkedHashMap<String, Double>();
		Map<String, Integer> data_bookingRate = new LinkedHashMap<String, Integer>();
		int month = 0;
//		int revenue_month=0; int booking_month = 0;
		int total_guest = 0; double total_revenue =0.0; int booking_rate =0;
		int months[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };

		for (int i = 1; i <= months.length; i++) {
			month = i;
//			revenue_month =i;
			total_guest = 0; total_revenue = 0.0; booking_rate= 0;

			for (Iterator z = b_details.iterator(); z.hasNext();) {
				Object[] values = (Object[]) z.next();

				LocalDate date = LocalDate.parse(values[0].toString());
				if (month == date.getMonthValue()) {
					total_guest = Integer.parseInt(values[1].toString());

				}
			}
			
			for (Iterator z = b_revenue.iterator(); z.hasNext();) {
				Object[] values = (Object[]) z.next();

				LocalDate date = LocalDate.parse(values[0].toString());
				if (month == date.getMonthValue()) {
					total_revenue = Double.parseDouble(values[1].toString());

				}
			}
			
			for (Iterator z = monthly_bookingRate.iterator(); z.hasNext();) {
				Object[] values = (Object[]) z.next();

				LocalDate date = LocalDate.parse(values[0].toString());
				if (month == date.getMonthValue()) {
					booking_rate = Integer.parseInt(values[1].toString());

				}
			}
			
			switch (month) {
			case (1):
				data.put("Jan", total_guest);
				data_revenue.put("Jan", total_revenue);
				data_bookingRate.put("Jan", booking_rate);
				break;
			case (2):
				data.put("Feb", total_guest);
				data_revenue.put("Feb", total_revenue);
				data_bookingRate.put("Feb", booking_rate);
				break;
			case (3):
				data.put("Mar", total_guest);
				data_revenue.put("Mar", total_revenue);
				data_bookingRate.put("Mar", booking_rate);
				break;
			case (4):
				data.put("Apr", total_guest);
				data_revenue.put("Apr", total_revenue);
				data_bookingRate.put("Apr", booking_rate);
				break;
			case (5):
				data.put("May", total_guest);
				data_revenue.put("May", total_revenue);
				data_bookingRate.put("May", booking_rate);
				break;
			case (6):
				data.put("Jun", total_guest);
				data_revenue.put("Jun", total_revenue);
				data_bookingRate.put("Jun", booking_rate);
				break;
			case (7):
				data.put("Jul", total_guest);
				data_revenue.put("Jul", total_revenue);
				data_bookingRate.put("Jul", booking_rate);
				break;
			case (8):
				data.put("Aug", total_guest);
				data_revenue.put("Aug", total_revenue);
				data_bookingRate.put("Aug", booking_rate);
				break;
			case (9):
				data.put("Sep", total_guest);
				data_revenue.put("Sep", total_revenue);
				data_bookingRate.put("Sep", booking_rate);
				break;
			case (10):
				data.put("Oct", total_guest);
				data_revenue.put("Oct", total_revenue);
				data_bookingRate.put("Oct", booking_rate);
				break;
			case (11):
				data.put("Nov", total_guest);
				data_revenue.put("Nov", total_revenue);
				data_bookingRate.put("Nov", booking_rate);
				break;
			case (12):
				data.put("Dec", total_guest);
				data_revenue.put("Dec", total_revenue);
				data_bookingRate.put("Dec", booking_rate);
				break;
			default:
				break;
			}

		}

        model.addAttribute("data", data);
        model.addAttribute("data_revenue", data_revenue);
        model.addAttribute("data_bookingRate", data_bookingRate);
        return "dashboard";
    }
}
