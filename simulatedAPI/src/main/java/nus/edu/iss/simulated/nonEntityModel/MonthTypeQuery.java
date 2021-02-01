package nus.edu.iss.simulated.nonEntityModel;

import nus.edu.iss.simulated.model.RoomType;

public class MonthTypeQuery {
	
	private int month;
	private RoomType roomType;
	
	
	public MonthTypeQuery(int month, RoomType roomType) {
		super();
		this.month = month;
		this.roomType = roomType;
	}


	public int getMonth() {
		return month;
	}


	public void setMonth(int month) {
		this.month = month;
	}


	public RoomType getRoomType() {
		return roomType;
	}


	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	@Override
	public String toString() {
		return "MonthTypeQuery [month=" + month + ", roomType=" + roomType + "]";
	}

}
