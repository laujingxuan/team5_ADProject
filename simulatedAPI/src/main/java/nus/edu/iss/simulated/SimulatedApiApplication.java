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
	private DailyAttractionDetailRepo dailyAttractRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(SimulatedApiApplication.class, args);
	}

	//Only run during the first time for data insertion
	@Bean
	CommandLineRunner runner() {
		return args -> { 
			//Single room from 15/1/2021 to 30/1/2021 created
			DailyRoomTypeDetail roomTypeDetail = new DailyRoomTypeDetail("SINGLE", LocalDate.of(2021, 1, 30), 300, 20, 1);
			roomRepo.save(roomTypeDetail);
			DailyRoomTypeDetail roomTypeDetail1 = new DailyRoomTypeDetail("SINGLE", LocalDate.of(2021, 1, 25), 300, 15, 1);
			roomRepo.save(roomTypeDetail1);
			DailyRoomTypeDetail roomTypeDetail2 = new DailyRoomTypeDetail("SINGLE", LocalDate.of(2021, 1, 23), 300, 28, 1);
			roomRepo.save(roomTypeDetail2);
			DailyRoomTypeDetail roomTypeDetail3 = new DailyRoomTypeDetail("SINGLE", LocalDate.of(2021, 2, 23), 300, 7, 1);
			roomRepo.save(roomTypeDetail3);
			roomRepo.save(new DailyRoomTypeDetail("SINGLE", LocalDate.of(2021, 1, 15), 300, 7, 1));
			roomRepo.save(new DailyRoomTypeDetail("SINGLE", LocalDate.of(2021, 1, 16), 300, 7, 1));
			roomRepo.save(new DailyRoomTypeDetail("SINGLE", LocalDate.of(2021, 1, 17), 300, 7, 1));
			roomRepo.save(new DailyRoomTypeDetail("SINGLE", LocalDate.of(2021, 1, 18), 300, 7, 1));
			roomRepo.save(new DailyRoomTypeDetail("SINGLE", LocalDate.of(2021, 1, 19), 300, 7, 1));
			roomRepo.save(new DailyRoomTypeDetail("SINGLE", LocalDate.of(2021, 1, 20), 300, 7, 1));
			roomRepo.save(new DailyRoomTypeDetail("SINGLE", LocalDate.of(2021, 1, 21), 300, 7, 1));
			roomRepo.save(new DailyRoomTypeDetail("SINGLE", LocalDate.of(2021, 1, 22), 300, 7, 1));
			roomRepo.save(new DailyRoomTypeDetail("SINGLE", LocalDate.of(2021, 1, 24), 300, 7, 1));
			roomRepo.save(new DailyRoomTypeDetail("SINGLE", LocalDate.of(2021, 1, 26), 300, 7, 1));
			roomRepo.save(new DailyRoomTypeDetail("SINGLE", LocalDate.of(2021, 1, 27), 300, 7, 1));
			roomRepo.save(new DailyRoomTypeDetail("SINGLE", LocalDate.of(2021, 1, 28), 300, 7, 1));
			roomRepo.save(new DailyRoomTypeDetail("SINGLE", LocalDate.of(2021, 1, 29), 300, 7, 1));
			
			//Double room from 15/1/2021 to 20/1/2021 created
			roomRepo.save(new DailyRoomTypeDetail("DOUBLE", LocalDate.of(2021, 1, 15), 300, 7, 1));
			roomRepo.save(new DailyRoomTypeDetail("DOUBLE", LocalDate.of(2021, 1, 16), 300, 7, 1));
			roomRepo.save(new DailyRoomTypeDetail("DOUBLE", LocalDate.of(2021, 1, 17), 300, 7, 1));
			roomRepo.save(new DailyRoomTypeDetail("DOUBLE", LocalDate.of(2021, 1, 18), 300, 7, 1));
			roomRepo.save(new DailyRoomTypeDetail("DOUBLE", LocalDate.of(2021, 1, 19), 300, 7, 1));
			roomRepo.save(new DailyRoomTypeDetail("DOUBLE", LocalDate.of(2021, 1, 20), 300, 7, 1));
			
			HotelBooking booking1 = new HotelBooking("SINGLE", 1, 3, "No breakfast", 300, LocalDate.of(2021, 1, 15), LocalDate.of(2021, 2, 15), LocalDate.of(2021, 2, 20));
			hotelRepo.save(booking1);
			HotelBooking booking2 = new HotelBooking("SINGLE", 1, 2, "2 breakfast", 250, LocalDate.of(2021, 1, 15), LocalDate.of(2021, 3, 1),  LocalDate.of(2021, 3, 5));
			hotelRepo.save(booking2);
			HotelBooking booking3 = new HotelBooking("DOUBLE", 2, 4, "4 breakfast", 400, LocalDate.of(2021, 1, 15), LocalDate.of(2021, 4, 15), LocalDate.of(2021, 4, 20));
			hotelRepo.save(booking3);
			
			AttractionBooking attraction1 = new AttractionBooking("Zoo",5,LocalDate.of(2021, 1, 15));
			attractionRepo.save(attraction1);
			
			DailyAttractionDetail attractDetail1 = new DailyAttractionDetail("Zoo",LocalDate.of(2021, 1, 15), 50);
			dailyAttractRepo.save(attractDetail1);
		};
//			BookingDetails bookDetail1 = new BookingDetails(booking1, attraction1.getProduct(), "1", "23456", 4, 300);
//			BookingDetails bookDetail2 = new BookingDetails(booking1, attraction1.getProduct(), "2", "23457", 3, 250);
//			BookingDetails bookDetail3 = new BookingDetails(booking1, attraction1.getProduct(), "3", "23458", 5, 400);
	}
}
