package nus.edu.iss.simulated.nonEntityModel;

import java.util.List;

import nus.edu.iss.simulated.model.DailyAttractionDetail;

public class DailyDetailWrapper {
	private List<DailyAttractionDetail> dailyDetails;

	public DailyDetailWrapper(List<DailyAttractionDetail> dailyDetails) {
		super();
		this.dailyDetails = dailyDetails;
	}



	public List<DailyAttractionDetail> getDailyDetails() {
		return dailyDetails;
	}



	public void setDailyDetails(List<DailyAttractionDetail> dailyDetails) {
		this.dailyDetails = dailyDetails;
	}



	@Override
	public String toString() {
		return "DailyDetailWrapper [dailyDetails=" + dailyDetails + "]";
	}
	
	
}

