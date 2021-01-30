package nus.edu.iss.simulated;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import nus.edu.iss.simulated.model.DailyRoomTypeDetail;
import nus.edu.iss.simulated.model.RoomType;
import nus.edu.iss.simulated.repository.DailyRoomTypeDetailRepo;

@SpringBootApplication
public class SimulatedApiApplication {

	@Autowired
	DailyRoomTypeDetailRepo roomRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(SimulatedApiApplication.class, args);
	}

	//Only run during the first time for data insertion
	@Bean
	CommandLineRunner runner() {
		return args -> { 
			DailyRoomTypeDetail roomTypeDetail = new DailyRoomTypeDetail(RoomType.SINGLE, LocalDate.of(2021, 1, 30), 300, 20, 1);
			roomRepo.save(roomTypeDetail);
			DailyRoomTypeDetail roomTypeDetail1 = new DailyRoomTypeDetail(RoomType.SINGLE, LocalDate.of(2021, 1, 25), 300, 15, 1);
			roomRepo.save(roomTypeDetail1);
			DailyRoomTypeDetail roomTypeDetail2 = new DailyRoomTypeDetail(RoomType.SINGLE, LocalDate.of(2021, 1, 23), 300, 28, 1);
			roomRepo.save(roomTypeDetail2);
			DailyRoomTypeDetail roomTypeDetail3 = new DailyRoomTypeDetail(RoomType.SINGLE, LocalDate.of(2021, 2, 23), 300, 7, 1);
			roomRepo.save(roomTypeDetail3);
			};
	}
}
