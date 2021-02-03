package nus.edu.iss.simulated.nonEntityModel;



public class MonthTypeQuery {
	
	private int month;
	private String roomType;
	private String attractionName;
	



	public MonthTypeQuery(int month, String roomType, String attractionName) {
		super();
		this.month = month;
		this.roomType = roomType;
		this.attractionName = attractionName;
	}


	public String getAttractionName() {
		return attractionName;
	}


	public void setAttractionName(String attractionName) {
		this.attractionName = attractionName;
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
