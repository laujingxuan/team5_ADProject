package nus.edu.iss.adproject.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.swing.text.View;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import nus.edu.iss.adproject.model.Attraction;
import nus.edu.iss.adproject.model.BookingDetails;
import nus.edu.iss.adproject.model.Hotel;
import nus.edu.iss.adproject.model.Month;
import nus.edu.iss.adproject.model.RoomType;
import nus.edu.iss.adproject.model.User;
import nus.edu.iss.adproject.nonEntityModel.DailyRoomDetailWrapper;
import nus.edu.iss.adproject.nonEntityModel.DailyRoomTypeDetail;
import nus.edu.iss.adproject.nonEntityModel.DashboardQuery;
import nus.edu.iss.adproject.nonEntityModel.MonthTypeQuery;
import nus.edu.iss.adproject.nonEntityModel.RoleType;
import nus.edu.iss.adproject.service.AttractionService;
import nus.edu.iss.adproject.service.AttractionServiceImpl;
import nus.edu.iss.adproject.service.BookingService;
import nus.edu.iss.adproject.service.BookingServiceImp;
import nus.edu.iss.adproject.service.HotelService;
import nus.edu.iss.adproject.service.HotelServiceImpl;
import nus.edu.iss.adproject.service.RoomTypeService;
import nus.edu.iss.adproject.service.RoomTypeServiceImpl;
import nus.edu.iss.adproject.service.SessionService;
import nus.edu.iss.adproject.service.SessionServiceImpl;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	@Autowired
	private BookingService bookingservice;

	@Autowired
	private SessionService session_svc;
	
	@Autowired
	private HotelService hotel_svc;
	
	@Autowired
	private AttractionService attraction_svc;
	
	@Autowired
	private RoomTypeService room_svc;

	@Autowired
	public void SetImplimentation(BookingServiceImp bookingservice_impl, SessionServiceImpl session_svcimpl, HotelServiceImpl hotel_svcimpl, AttractionServiceImpl attr_svcimpl, RoomTypeServiceImpl room_svcimpl) {
		this.bookingservice = bookingservice_impl;
		this.session_svc = session_svcimpl;
		this.hotel_svc = hotel_svcimpl;
		this.attraction_svc = attr_svcimpl;
		this.room_svc = room_svcimpl;
	}
	
	Hotel hotel = new Hotel();
	
	@RequestMapping(value = "/dailyBookingVacancy")
	@ResponseBody
	public Map<Integer,Integer> getBookingData(@RequestParam String month,@RequestParam String room,@RequestParam Long hotel_id, HttpSession session) {
		//User user = (User) session.getAttribute("user");
		int monthValue = Month.valueOf(month).ordinal()+1;			
		
		String uri;
		RestTemplate restTemplate = new RestTemplate();
		Map<Integer, Integer> data_dailyVacancyRate = new LinkedHashMap<Integer, Integer>();
		hotel = hotel_svc.findById(hotel_id);
		System.out.println("URI : " + hotel.getAPI_URL());
		if(hotel.getAPI_URL()!=null) {
		uri = hotel.getAPI_URL()+"room/month";
			MonthTypeQuery monthTypeQuery = new MonthTypeQuery(monthValue, room);
			DailyRoomDetailWrapper result = restTemplate.postForObject(uri, monthTypeQuery,
					DailyRoomDetailWrapper.class);
			for (DailyRoomTypeDetail daily : result.getDailyList()) {
				data_dailyVacancyRate.put(daily.getDate().getDayOfMonth(), (int) daily.getNumVacancies());
				//data_dailyCancellationRate.put(daily.getDate().getDayOfMonth(), (int) daily.getNumCancellations());
			}
		}		
		return data_dailyVacancyRate;
	}
	
	@RequestMapping(value = "/dailyCancellation")
	@ResponseBody
	public Map<Integer,Integer> getCancellationData(@RequestParam String month,@RequestParam String room,@RequestParam Long hotel_id, HttpSession session) {
		
		int monthValue = Month.valueOf(month).ordinal()+1;
		System.out.println(hotel_id);
		
		String uri;
		RestTemplate restTemplate = new RestTemplate();
		Map<Integer, Integer> data_dailyCancellationRate = new LinkedHashMap<Integer, Integer>();
		
		User user = (User) session.getAttribute("user");	
		hotel = hotel_svc.findById(hotel_id);
		System.out.println("URI : " + hotel.getAPI_URL());
		if(hotel.getAPI_URL()!=null) {
		uri = hotel.getAPI_URL()+"room/month";
			MonthTypeQuery monthTypeQuery = new MonthTypeQuery(monthValue, room);
			DailyRoomDetailWrapper result = restTemplate.postForObject(uri, monthTypeQuery,
					DailyRoomDetailWrapper.class);
			for (DailyRoomTypeDetail daily : result.getDailyList()) {
				//data_dailyVacancyRate.put(daily.getDate().getDayOfMonth(), (int) daily.getNumVacancies());
				data_dailyCancellationRate.put(daily.getDate().getDayOfMonth(), (int) daily.getNumCancellations());
			}
		}		
		return data_dailyCancellationRate;
	}
	
	
	@RequestMapping(value = "/showDashboardByHotel",method = RequestMethod.GET )	
	@ResponseBody
	public RedirectView ShowDashboardByHotel(Model model, HttpSession session,@RequestParam Long hotel_id) {
		
		hotel.setId(hotel_id);
		
		 RedirectView rv = new RedirectView();
	        rv.setContextRelative(true);
	        rv.setUrl("/dashboard/hotel/"+hotel_id);
	        return rv;
		
		//return "home";
	}
	
	 @RequestMapping("hotel/{hotel_id}")
	    public String handleRequest (@PathVariable("hotel_id") Long hotel_id,Model model, HttpSession session) {
		 System.out.println(hotel_id);
		 Dashboard(model,hotel_id,session);
	        return "dashboard";
	    }
	
	 
	 @RequestMapping(value = "/showDashboardForPlatformManager",method = RequestMethod.GET )	
		@ResponseBody
		public RedirectView showDashboardForPlatformManager(Model model, HttpSession session,@RequestParam String month) {
			System.out.println(month);
			
			int monthValue = Month.valueOf(month).ordinal()+1;	
			 RedirectView rv = new RedirectView();
		        rv.setContextRelative(true);
		        rv.setUrl("/dashboard/hotelForPlatform/"+monthValue);
		        return rv;
			
			//return "home";
		}
	 
	 @RequestMapping("hotelForPlatform/{month}")
	    public String handleRequestFroPlatform (@PathVariable("month") Integer month,Model model, HttpSession session) {
		 System.out.println(month);
		 DashboardForPlatform(model, month);
		 model.addAttribute("month", month);
			return "platformDashboard";
	    }

	public void Dashboard(Model model,Long hotel_id, HttpSession session) {
		User user = (User) session.getAttribute("user");	
		Map<String, Integer> data = new LinkedHashMap<String, Integer>();
		Map<String, Double> data_revenue = new LinkedHashMap<String, Double>();
		Map<String, Integer> data_bookingRate = new LinkedHashMap<String, Integer>();
		Map<Integer, Integer> data_dailyVacancyRate = new LinkedHashMap<Integer, Integer>();
		Map<Integer, Integer> data_dailyCancellationRate = new LinkedHashMap<Integer, Integer>();

		
		RoomType roomType = new RoomType();
		List<Hotel> hotel = hotel_svc.findByUserId(user.getId());
		RestTemplate restTemplate = new RestTemplate();
		
		ArrayList<Object> rooms = room_svc.findDistinctRoomTypes();
		List<RoomType> room_list = new ArrayList<RoomType>();
		for (Object object : rooms) {
			roomType= new RoomType();
			roomType.setRoomType(object.toString());
			room_list.add(roomType);
		}		
		String uri,check_uri=null;
		
		Hotel h1= new Hotel();
		if(hotel_id >0) {
			h1 = hotel_svc.findById(hotel_id);
			uri = h1.getAPI_URL()+"room/month";
			check_uri = h1.getAPI_URL();
		}else {
			hotel_id = hotel.get(0).getId();
			uri = hotel.get(0).getAPI_URL()+"room/month";
			check_uri = hotel.get(0).getAPI_URL();
		}
//		if(check_uri != null || check_uri.trim() != "NA" || !check_uri.isEmpty())  {
		if(check_uri.startsWith(" "))  {
			MonthTypeQuery monthTypeQuery = new MonthTypeQuery(1, room_list.get(0).getRoomType());
			DailyRoomDetailWrapper result = restTemplate.postForObject(uri, monthTypeQuery,
					DailyRoomDetailWrapper.class);
			for (DailyRoomTypeDetail daily : result.getDailyList()) {
				data_dailyVacancyRate.put(daily.getDate().getDayOfMonth(), (int) daily.getNumVacancies());
				data_dailyCancellationRate.put(daily.getDate().getDayOfMonth(), (int) daily.getNumCancellations());
			}
		}
		
		
		List<Object> b_details = bookingservice.findMonthlyGuestByHotelId(hotel_id);
		List<Object> b_revenue = bookingservice.findMonthlyRevenueByHotelId(hotel_id);
		List<Object> monthly_bookingRate = bookingservice.findMonthlyBookingRateByHotelId(hotel_id);

//		List<Object> b_details = bookingservice.findGuestByMonth(hotel_id);
//		List<Object> b_revenue = bookingservice.findMonthlyRevenueByHotel(user.getId(), hotel_id);
//		List<Object> monthly_bookingRate = bookingservice.findMonthlyBookingRateByHotel(user.getId(), hotel_id);

		int month = 0;
		int total_guest = 0;
		double total_revenue = 0.0;
		int booking_rate = 0;
		int months[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };

		for (int i = 1; i <= months.length; i++) {
			month = i;
			total_guest = 0;
			total_revenue = 0.0;
			booking_rate = 0;

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
		
		model.addAttribute("hotel_id", hotel_id);
		model.addAttribute("hotel", hotel);
		model.addAttribute("roomTypes", room_list);
		model.addAttribute("month",Month.values());
		model.addAttribute("data", data);
		model.addAttribute("data_revenue", data_revenue);
		model.addAttribute("data_bookingRate", data_bookingRate);
		model.addAttribute("data_vacancyRate", data_dailyVacancyRate);
		model.addAttribute("data_cancellationRate", data_dailyCancellationRate);
	}
	

	@GetMapping("/reports")
	public String showDashboard(Model model, HttpSession session) {
		if (session_svc.isNotLoggedIn(session))
			return "redirect:/user/login";
		User user = (User) session.getAttribute("user");
		Hotel h_test = new Hotel();
		h_test.setId(0);
		
		if (user.getRole() == RoleType.HOTELMANAGER) {
			Dashboard(model,h_test.getId(), session);
			
			return "dashboard";
		}else if(user.getRole() == RoleType.PLATFORMMANAGER) {
			
			DashboardForPlatform(model, 1);
			return "platformDashboard";
		}
		else {
			return null;
		}
	}
	
	public void DashboardForPlatform(Model model, int month) {
		List<Hotel> hotel_list = hotel_svc.findAll();
		List<Attraction> attraction = attraction_svc.findAll();
		
		List<Object> dashboard_details = bookingservice.findMonthlyRevenueForAllHotels(month, 2021);
		List<Object> attraction_details = bookingservice.findMonthlyRevenueForAllAttractions(month, 2021);
		
		List<DashboardQuery> d_list = new ArrayList<DashboardQuery>();			
		for (Hotel h : hotel_list) {
			DashboardQuery query = new DashboardQuery();
			Boolean is_exist=false;
			for (Iterator z = dashboard_details.iterator(); z.hasNext();) {
				Object[] values = (Object[]) z.next();
				if(h.getId() == (Long)values[0]) {
					is_exist=true;
					query.setHotel_id(h.getId());
					query.setHotel_name(h.getName());
					query.setMonthly_revenue(Double.parseDouble(values[1].toString()));
					query.setMonthly_guest(Integer.parseInt(values[2].toString()));
					d_list.add(query);						
				}
			}
			if(is_exist == false) {
				query.setHotel_id(h.getId());
				query.setHotel_name(h.getName());
				query.setMonthly_revenue(0.00);
				query.setMonthly_guest(0.00);
				d_list.add(query);
			}
		}
		
		
		List<DashboardQuery> a_list = new ArrayList<DashboardQuery>();			
		for (Attraction a : attraction) {
			DashboardQuery query = new DashboardQuery();
			Boolean is_exist=false;
			for (Iterator z = attraction_details.iterator(); z.hasNext();) {
				Object[] values = (Object[]) z.next();
				if(a.getId() == (Long)values[0]) {
					is_exist=true;
					query.setAttraction_id(a.getId());
					query.setAttraction_name(a.getName());
					query.setMonthly_revenue(Double.parseDouble(values[1].toString()));
					query.setMonthly_guest(Integer.parseInt(values[2].toString()));
					a_list.add(query);						
				}
			}
			if(is_exist == false) {
				query.setAttraction_id(a.getId());
				query.setAttraction_name(a.getName());
				query.setMonthly_revenue(0.00);
				query.setMonthly_guest(0.00);
				a_list.add(query);
			}
		}

		
		model.addAttribute("month",1);
		model.addAttribute("attractoins", a_list);
		model.addAttribute("hotels", d_list);
	}

	@GetMapping("/view/{hotel_id}")
	public String showDashboardByHotel(@PathVariable("hotel_id") Long hotel_id,Model model, HttpSession session) {
		
		User user = (User) session.getAttribute("user");
		if (session_svc.isNotLoggedIn(session))
			return "redirect:/user/login";
		Hotel hotel = hotel_svc.findById(hotel_id);
		if (user.getRole() == RoleType.PLATFORMMANAGER) {			
			Dashboard(model, hotel_id, session);
			model.addAttribute("hotel", hotel);
			return "dashboard";
		}
		else {
			return null;
		}
	}
	
}
