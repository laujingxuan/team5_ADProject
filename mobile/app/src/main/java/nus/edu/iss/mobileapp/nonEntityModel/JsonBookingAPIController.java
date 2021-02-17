package nus.edu.iss.mobileapp.nonEntityModel;

import java.util.List;
import java.util.Map;

import nus.edu.iss.mobileapp.model.Booking;
import nus.edu.iss.mobileapp.model.BookingDetails;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonBookingAPIController {
    @GET("list")
    Call<List<Booking>> getBookingHistory(@Query("username") String username);

    @GET("attraction/bookingdetails")
    Call<AttractionBooking> getAttractionBookingDetails(@Query("detailId") Long detailId);

    @GET("hotel/bookingdetails")
    Call<HotelBooking> getHotelBookingDetails(@Query("detailId") Long detailId);
}
