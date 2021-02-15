package nus.edu.iss.mobileapp.nonEntityModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonBookingAPIController {
    @GET("list")
    Call<List<Booking>> getBookingHistory(@Query("username") String username);
}
