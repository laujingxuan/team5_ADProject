package nus.edu.iss.simulated.nonEntityModel;

import java.time.LocalDate;

import nus.edu.iss.simulated.model.RoomType;

public class DateTypeQuery {

	private LocalDate date;
	private RoomType roomType;
	
	public DateTypeQuery(LocalDate date, RoomType roomType) {
		super();
		this.date = date;
		this.roomType = roomType;
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
