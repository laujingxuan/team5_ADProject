package nus.edu.iss.simulated.service;

import nus.edu.iss.simulated.model.HotelBooking;

public interface HotelBookingService {

	public HotelBooking findBookingById(long Id);
	public HotelBooking createBooking(HotelBooking hotelBooking);
}
