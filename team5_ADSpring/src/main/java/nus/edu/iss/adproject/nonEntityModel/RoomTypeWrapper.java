package nus.edu.iss.adproject.nonEntityModel;

import java.util.ArrayList;
import java.util.List;

import nus.edu.iss.adproject.model.Hotel;
import nus.edu.iss.adproject.model.RoomType;

public class RoomTypeWrapper {
	private List<RoomType> RoomTypeList;

	
	
	public RoomTypeWrapper() {
		super();
		RoomTypeList = new ArrayList<RoomType>();
		// TODO Auto-generated constructor stub
	}

	public RoomTypeWrapper(List<RoomType> roomTypeList) {
		super();
		RoomTypeList = roomTypeList;
	}

	public List<RoomType> getRoomTypeList() {
		return RoomTypeList;
	}

	public void setRoomTypeList(List<RoomType> roomTypeList) {
		RoomTypeList = roomTypeList;
	}
	
	
}
