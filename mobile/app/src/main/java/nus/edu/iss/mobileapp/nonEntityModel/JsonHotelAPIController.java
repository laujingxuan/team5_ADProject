package nus.edu.iss.mobileapp.nonEntityModel;

import java.util.List;

import nus.edu.iss.mobileapp.model.Hotel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonHotelAPIController {

    @GET("hotels")
    Call<List<Hotel>>getHotels();
}
