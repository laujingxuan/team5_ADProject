package nus.edu.iss.adproject.nonEntityModel;

import java.util.ArrayList;
import java.util.List;

public class DailyRoomTypeDetailWrapper {
	
	private List<DailyRoomTypeDetail> dailydetails;
	
	public DailyRoomTypeDetailWrapper() {
		super();
		dailydetails = new ArrayList<DailyRoomTypeDetail>();
	}

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
