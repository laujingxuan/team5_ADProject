package nus.edu.iss.adproject.nonEntityModel;

import java.util.ArrayList;
import java.util.List;

import nus.edu.iss.adproject.model.Hotel;

public class HotelWrapper {
	private List<Hotel> hotelList;

	public HotelWrapper() {
		super();
		hotelList= new ArrayList<Hotel>();
	}

	public HotelWrapper(List<Hotel> hotelList) {
		super();
		this.hotelList = hotelList;
	}

	public List<Hotel> getHotelList() {
		return hotelList;
	}

	public void setHotelList(List<Hotel> hotelList) {
		this.hotelList = hotelList;
	}
	
	
}
