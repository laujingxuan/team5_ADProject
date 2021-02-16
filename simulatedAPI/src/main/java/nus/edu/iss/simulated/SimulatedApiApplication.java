package nus.edu.iss.simulated;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import nus.edu.iss.simulated.model.AttractionBooking;

import nus.edu.iss.simulated.model.DailyAttractionDetail;
import nus.edu.iss.simulated.model.DailyRoomTypeDetail;
import nus.edu.iss.simulated.model.HotelBooking;

import nus.edu.iss.simulated.repository.AttractionBookingRepo;
import nus.edu.iss.simulated.repository.DailyAttractionDetailRepo;
import nus.edu.iss.simulated.repository.DailyRoomTypeDetailRepo;
import nus.edu.iss.simulated.repository.HotelBookingRepo;

@SpringBootApplication
public class SimulatedApiApplication {

	@Autowired
	private DailyRoomTypeDetailRepo roomRepo;
	
	@Autowired
	private HotelBookingRepo hotelRepo;
	
	@Autowired
	private AttractionBookingRepo attractionRepo;
	
	@Autowired
	private DailyAttractionDetailRepo dapRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(SimulatedApiApplication.class, args);
	}

	//Only run during the first time for data insertion
	@Bean
	CommandLineRunner runner() {
		return args -> { 

			//Single room from 15/1/2021 to 30/1/2021 created

			DailyRoomTypeDetail roomTypeDetail = new DailyRoomTypeDetail("SINGLE", LocalDate.of(2021, 3, 7), 300, 20, 1);
			roomRepo.save(roomTypeDetail);
			DailyRoomTypeDetail roomTypeDetail1 = new DailyRoomTypeDetail("SINGLE", LocalDate.of(2021, 3, 8), 300, 15, 1);
			roomRepo.save(roomTypeDetail1);
			DailyRoomTypeDetail roomTypeDetail2 = new DailyRoomTypeDetail("SINGLE", LocalDate.of(2021, 3, 9), 300, 28, 1);
			roomRepo.save(roomTypeDetail2);
			DailyRoomTypeDetail roomTypeDetail3 = new DailyRoomTypeDetail("SINGLE", LocalDate.of(2021, 3, 10), 300, 7, 1);
			roomRepo.save(roomTypeDetail3);

			
			AttractionBooking attractionbooking1 = new AttractionBooking("Zoo",20,LocalDate.of(2021, 2, 23));
			attractionRepo.save(attractionbooking1);
			
			AttractionBooking attraction1 = new AttractionBooking("Zoo",2,LocalDate.of(2021, 1, 15));
			attractionRepo.save(attraction1);
			
			AttractionBooking attraction3 = new AttractionBooking("Zoo",3,LocalDate.of(2021, 1, 15));
			attractionRepo.save(attraction3);
			
			DailyAttractionDetail dap1 = new DailyAttractionDetail("Zoo",LocalDate.of(2021, 2, 23) , 10);
			dapRepo.save(dap1);
			
			DailyAttractionDetail dap2 = new DailyAttractionDetail("Zoo",LocalDate.of(2021, 2, 21) , 10);
			dapRepo.save(dap2);
			
			DailyAttractionDetail dap3 = new DailyAttractionDetail("Zoo",LocalDate.of(2021, 2, 22) , 10);
			dapRepo.save(dap3);
			
			DailyAttractionDetail attractDetail1 = new DailyAttractionDetail("Zoo",LocalDate.of(2021, 2, 25), 50);
			dapRepo.save(attractDetail1);
			
		
			roomRepo.save(new DailyRoomTypeDetail("SINGLE", LocalDate.of(2021, 3, 1), 300, 7, 1));
			roomRepo.save(new DailyRoomTypeDetail("SINGLE", LocalDate.of(2021, 3, 2), 300, 7, 1));
			roomRepo.save(new DailyRoomTypeDetail("SINGLE", LocalDate.of(2021, 3, 3), 300, 7, 1));
			roomRepo.save(new DailyRoomTypeDetail("SINGLE", LocalDate.of(2021, 3, 4), 300, 7, 1));
			roomRepo.save(new DailyRoomTypeDetail("SINGLE", LocalDate.of(2021, 3, 5), 300, 7, 1));
			roomRepo.save(new DailyRoomTypeDetail("SINGLE", LocalDate.of(2021, 2, 20), 300, 7, 1));
			roomRepo.save(new DailyRoomTypeDetail("SINGLE", LocalDate.of(2021, 2, 21), 300, 7, 1));
			roomRepo.save(new DailyRoomTypeDetail("SINGLE", LocalDate.of(2021, 2, 22), 300, 7, 1));
			roomRepo.save(new DailyRoomTypeDetail("SINGLE", LocalDate.of(2021, 2, 24), 300, 7, 1));
			roomRepo.save(new DailyRoomTypeDetail("SINGLE", LocalDate.of(2021, 2, 26), 300, 7, 1));
			roomRepo.save(new DailyRoomTypeDetail("SINGLE", LocalDate.of(2021, 2, 27), 300, 7, 1));
			roomRepo.save(new DailyRoomTypeDetail("SINGLE", LocalDate.of(2021, 2, 28), 300, 7, 1));
			roomRepo.save(new DailyRoomTypeDetail("SINGLE", LocalDate.of(2021, 3, 6), 300, 7, 1));
			
			//Double room from 15/2/2021 to 20/2/2021 created
			roomRepo.save(new DailyRoomTypeDetail("DOUBLE", LocalDate.of(2021, 2, 21), 400, 7, 1));
			roomRepo.save(new DailyRoomTypeDetail("DOUBLE", LocalDate.of(2021, 2, 22), 400, 7, 1));
			roomRepo.save(new DailyRoomTypeDetail("DOUBLE", LocalDate.of(2021, 2, 23), 400, 7, 1));
			roomRepo.save(new DailyRoomTypeDetail("DOUBLE", LocalDate.of(2021, 2, 24), 400, 7, 1));
			roomRepo.save(new DailyRoomTypeDetail("DOUBLE", LocalDate.of(2021, 2, 25), 400, 7, 1));
			roomRepo.save(new DailyRoomTypeDetail("DOUBLE", LocalDate.of(2021, 2, 26), 400, 7, 1));
			
			HotelBooking booking1 = new HotelBooking("SINGLE", 1, 3, "No breakfast", 300, LocalDate.of(2021, 1, 15), LocalDate.of(2021, 2, 15), LocalDate.of(2021, 2, 20));
			hotelRepo.save(booking1);
			HotelBooking booking2 = new HotelBooking("SINGLE", 1, 2, "2 breakfast", 250, LocalDate.of(2021, 1, 15), LocalDate.of(2021, 3, 1),  LocalDate.of(2021, 3, 5));
			hotelRepo.save(booking2);
			HotelBooking booking3 = new HotelBooking("DOUBLE", 2, 4, "4 breakfast", 400, LocalDate.of(2021, 1, 15), LocalDate.of(2021, 4, 15), LocalDate.of(2021, 4, 20));
			hotelRepo.save(booking3);
			HotelBooking booking4 = new HotelBooking("DOUBLE", 2, 5, "Check in at 12pm", 4000, LocalDate.of(2021, 1, 15), LocalDate.of(2021, 4, 15), LocalDate.of(2021, 4, 20));
			hotelRepo.save(booking4);
			HotelBooking booking5 = new HotelBooking("SINGLE", 5, 5, "Check in at 6pm", 7500, LocalDate.of(2021, 1, 15), LocalDate.of(2021, 4, 15), LocalDate.of(2021, 4, 20));
			hotelRepo.save(booking5);

		};
	}
}
