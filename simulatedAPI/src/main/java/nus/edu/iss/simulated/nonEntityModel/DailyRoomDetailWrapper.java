package nus.edu.iss.simulated.nonEntityModel;

import java.util.List;

import nus.edu.iss.simulated.model.DailyRoomTypeDetail;

public class DailyRoomDetailWrapper {

	private List<DailyRoomTypeDetail> dailyList;
	
	public DailyRoomDetailWrapper() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DailyRoomDetailWrapper(List<DailyRoomTypeDetail> dailyList) {
		super();
		this.dailyList = dailyList;
	}

	public List<DailyRoomTypeDetail> getDailyList() {
		return dailyList;
	}

	public void setDailyList(List<DailyRoomTypeDetail> dailyList) {
		this.dailyList = dailyList;
	}

	@Override
	public String toString() {
		return "DailyDetailWrapper [dailyList=" + dailyList + "]";
	}
	
}