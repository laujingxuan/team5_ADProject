package nus.edu.iss.adproject.nonEntityModel;

import java.util.ArrayList;
import java.util.List;

public class DailyDetailWrapper {
	private List<DailyAttractionDetail> dailyDetails;
	
//	@JsonCreator
//	public DailyDetailWrapper() {
//		super();
//	}

	public DailyDetailWrapper() {
		super();
		dailyDetails = new ArrayList<DailyAttractionDetail>();
	}

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
