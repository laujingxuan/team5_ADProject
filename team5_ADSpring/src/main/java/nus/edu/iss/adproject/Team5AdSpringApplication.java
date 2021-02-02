package nus.edu.iss.adproject;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import nus.edu.iss.adproject.model.Attraction;
import nus.edu.iss.adproject.model.RoomType;
import nus.edu.iss.adproject.model.TravelPackage;
import nus.edu.iss.adproject.repository.AttractionRepository;
import nus.edu.iss.adproject.repository.TravelPackageRepository;



@SpringBootApplication
public class Team5AdSpringApplication {

	@Autowired
	private TravelPackageRepository tpRepo;
	
	@Autowired
	private AttractionRepository attractRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(Team5AdSpringApplication.class, args);
	}
	

//	//Only run during the first time for data insertion
	/*
	 * @Bean CommandLineRunner runner() { return args -> { TravelPackage tp1 = new
	 * TravelPackage(2,3,5); tpRepo.save(tp1); TravelPackage tp2 = new
	 * TravelPackage(5,3,7); tpRepo.save(tp2); TravelPackage tp3 = new
	 * TravelPackage(7,3,10); tpRepo.save(tp3); }; }
	 */
	
	}
