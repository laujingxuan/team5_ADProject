package nus.edu.iss.adproject.nonEntityModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import nus.edu.iss.adproject.model.RoomType;

public class DateTypeQuery {

	private LocalDate date;
	private RoomType roomType;
	
	private String attractionName;
	
	public DateTypeQuery() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public DateTypeQuery(LocalDate date, String attractionName) {
		super();
		this.date = date;
		this.attractionName = attractionName;
	}



	public DateTypeQuery(String date, RoomType roomType) {
		super();
		this.roomType = roomType;
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.date = LocalDate.parse(date, df);
	}
	
	

	public String getAttractionName() {
		return attractionName;
	}



	public void setAttractionName(String attractionName) {
		this.attractionName = attractionName;
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