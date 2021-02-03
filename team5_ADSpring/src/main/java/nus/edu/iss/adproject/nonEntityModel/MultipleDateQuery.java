package nus.edu.iss.adproject.nonEntityModel;

import java.time.LocalDate;

public class MultipleDateQuery {
	private LocalDate startDate;
	private LocalDate endDate;
	private String roomType;
	
	public MultipleDateQuery(LocalDate startDate, LocalDate endDate, String roomType) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.roomType = roomType;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	@Override
	public String toString() {
		return "MultipleDateQuery [startDate=" + startDate + ", endDate=" + endDate + ", roomType=" + roomType + "]";
	}
	
	
}
