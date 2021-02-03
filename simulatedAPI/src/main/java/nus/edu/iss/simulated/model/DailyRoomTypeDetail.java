package nus.edu.iss.simulated.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

@Entity
public class DailyRoomTypeDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	private String roomType;
	private LocalDate date;
	private double dailyPrice;
	private double numVacancies;
	private double numCancellations;
	
	public DailyRoomTypeDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DailyRoomTypeDetail(String roomType, LocalDate date, double dailyPrice, double numVacancies,
			double numCancellations) {
		super();
		this.roomType = roomType;
		this.date = date;
		this.dailyPrice = dailyPrice;
		this.numVacancies = numVacancies;
		this.numCancellations = numCancellations;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getDailyPrice() {
		return dailyPrice;
	}

	public void setDailyPrice(double dailyPrice) {
		this.dailyPrice = dailyPrice;
	}

	public double getNumVacancies() {
		return numVacancies;
	}

	public void setNumVacancies(double numVacancies) {
		this.numVacancies = numVacancies;
	}

	public double getNumCancellations() {
		return numCancellations;
	}

	public void setNumCancellations(double numCancellations) {
		this.numCancellations = numCancellations;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "DailyRoomTypeDetail [id=" + id + ", roomType=" + roomType + ", date=" + date + ", dailyPrice="
				+ dailyPrice + ", numVacancies=" + numVacancies + ", numCancellations=" + numCancellations + "]";
	}

	

}
