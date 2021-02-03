package nus.edu.iss.simulated.model;

import java.util.List;

public class DailyRoomTypeDetailWrapper {
	
	private List<DailyRoomTypeDetail> dailydetails;
	
	

	public DailyRoomTypeDetailWrapper(List<DailyRoomTypeDetail> dailydetails) {
		super();
		this.dailydetails = dailydetails;
	}

	public List<DailyRoomTypeDetail> getDailydetails() {
		return dailydetails;
	}

	public void setDailydetails(List<DailyRoomTypeDetail> dailydetails) {
		this.dailydetails = dailydetails;
	}

	@Override
	public String toString() {
		return "DailyRoomTypeDetailWrapper [dailydetails=" + dailydetails + "]";
	}
	

	
}
