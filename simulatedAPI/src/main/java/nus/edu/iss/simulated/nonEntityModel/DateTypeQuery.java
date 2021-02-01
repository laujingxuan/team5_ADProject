package nus.edu.iss.simulated.nonEntityModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import nus.edu.iss.simulated.model.RoomType;

public class DateTypeQuery {

	private LocalDate date;
	private RoomType roomType;
	
	public DateTypeQuery(String date, RoomType roomType) {
		super();
		this.roomType = roomType;
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.date = LocalDate.parse(date, df);
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	@Override
	public String toString() {
		return "DateTypeQuery [date=" + date + ", roomType=" + roomType + "]";
	}
	
}
