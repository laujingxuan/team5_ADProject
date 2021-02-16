package nus.edu.iss.mobileapp.nonEntityModel;

import nus.edu.iss.mobileapp.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface JsonUserApiController {

    @POST("authenticate")
    Call<User> login(@Body User user);
}
