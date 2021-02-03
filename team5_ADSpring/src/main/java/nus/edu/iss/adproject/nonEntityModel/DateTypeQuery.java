package nus.edu.iss.adproject.NonEntityModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTypeQuery {

	private LocalDate date;
	private String roomType;
	
	public DateTypeQuery(LocalDate date, String roomType) {
		super();
		this.roomType = roomType;
//		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.date = date;
	}

	public DateTypeQuery(LocalDate date) {
		super();
		this.date = date;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	@Override
	public String toString() {
		return "DateTypeQuery [date=" + date + ", roomType=" + roomType + "]";
	}
	
}
