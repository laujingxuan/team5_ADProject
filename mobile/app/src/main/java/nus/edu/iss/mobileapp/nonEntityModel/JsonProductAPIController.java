package nus.edu.iss.mobileapp.nonEntityModel;

import java.util.List;

import nus.edu.iss.mobileapp.model.Product;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonProductAPIController {
    @GET("search")
    Call<List<Product>> getSearchResults(@Query("keyword") String keyword);
}
