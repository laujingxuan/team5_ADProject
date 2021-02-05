package nus.edu.iss.simulated.nonEntityModel;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import nus.edu.iss.simulated.model.HotelBooking;

public class MLearningVar {

	private int leadTime;
	
	private int weekendNights;
	
	private int numNights; 
	
	private double averageRate;
	
	private int numSpecialRequest;
	
	public MLearningVar() {
		super();
	}

	public MLearningVar(int leadTime, int weekendNights, int numNights, double averageRate, int numSpecialRequest) {
		super();
		this.leadTime = leadTime;
		this.weekendNights = weekendNights;
		this.numNights = numNights;
		this.averageRate = averageRate;
		this.numSpecialRequest = numSpecialRequest;
	}
	
	public MLearningVar(HotelBooking hotelBooking) {
		super();
		leadTime = (int)hotelBooking.getBookingDate().until(hotelBooking.getStartDate(), ChronoUnit.DAYS);
		weekendNights = 0;
		numNights = 0;
		for (LocalDate date = hotelBooking.getStartDate(); date.isBefore(hotelBooking.getEndDate()); date = date.plusDays(1)) {
			DayOfWeek d = date.getDayOfWeek();
			if (d == DayOfWeek.SATURDAY || d == DayOfWeek.SUNDAY) {
				weekendNights += 1;
			}
			numNights += 1;
		}
		averageRate = hotelBooking.getPrice()/numNights;
		//if the length of string in remarks>0, means something is written and there is remark
		if(hotelBooking.getRemarks().length()>0) {
			numSpecialRequest = 1;
		}else {
			numSpecialRequest = 0;
		}
	}

	public int getLeadTime() {
		return leadTime;
	}

	public void setLeadTime(int leadTime) {
		this.leadTime = leadTime;
	}

	public int getWeekendNights() {
		return weekendNights;
	}

	public void setWeekendNights(int weekendNights) {
		this.weekendNights = weekendNights;
	}

	public int getNumNights() {
		return numNights;
	}

	public void setNumNights(int numNights) {
		this.numNights = numNights;
	}

	public double getAverageRate() {
		return averageRate;
	}

	public void setAverageRate(double averageRate) {
		this.averageRate = averageRate;
	}

	public int getNumSpecialRequest() {
		return numSpecialRequest;
	}

	public void setNumSpecialRequest(int numSpecialRequest) {
		this.numSpecialRequest = numSpecialRequest;
	}

	@Override
	public String toString() {
		return "MLearningVar [leadTime=" + leadTime + ", weekendNights=" + weekendNights + ", numNights=" + numNights
				+ ", averageRate=" + averageRate + ", numSpecialRequest=" + numSpecialRequest + "]";
	}

}
