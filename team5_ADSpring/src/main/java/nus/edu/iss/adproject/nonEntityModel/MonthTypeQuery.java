package nus.edu.iss.adproject.nonEntityModel;

public class MonthTypeQuery {
	
	private int month;
	private String roomType;
	
	
	public MonthTypeQuery(int month, String roomType) {
		super();
		this.month = month;
		this.roomType = roomType;
	}
	
	

	public MonthTypeQuery(int month) {
		super();
		this.month = month;
	}


	public int getMonth() {
		return month;
	}


	public void setMonth(int month) {
		this.month = month;
	}


	public String getRoomType() {
		return roomType;
	}


	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	@Override
	public String toString() {
		return "MonthTypeQuery [month=" + month + ", roomType=" + roomType + "]";
	}

}