package nus.edu.iss.mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import nus.edu.iss.mobileapp.model.Booking;
import nus.edu.iss.mobileapp.model.BookingDetails;
import nus.edu.iss.mobileapp.model.Product;
import nus.edu.iss.mobileapp.nonEntityModel.AttractionBooking;
import nus.edu.iss.mobileapp.nonEntityModel.BookingWrapper;
import nus.edu.iss.mobileapp.nonEntityModel.HotelBooking;
import nus.edu.iss.mobileapp.nonEntityModel.JsonBookingAPIController;
import nus.edu.iss.mobileapp.nonEntityModel.ProductType;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookingDetailsActivity extends AppCompatActivity {

    private JsonBookingAPIController jsonBookingAPIController;
    private BookingDetailsAdapter adapter;
    private Booking booking;
    private Map<BookingDetails, BookingWrapper> bookingMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.10.104:8080/api/booking/").addConverterFactory(GsonConverterFactory.create()).build();
        jsonBookingAPIController = retrofit.create(JsonBookingAPIController.class);
        adapter = new BookingDetailsAdapter(this, 0);
        Intent intent = getIntent();
        booking = (Booking) intent.getSerializableExtra("Booking");
        bookingMap = new LinkedHashMap<>();
        for (BookingDetails bkDetail: booking.getBookingDetails()){
//            System.out.println("bookingId = " + bkDetail.getId());
            if (bkDetail.getProduct().getType().equals(ProductType.ATTRACTION)){
                Call<AttractionBooking> call = jsonBookingAPIController.getAttractionBookingDetails(bkDetail.getId());
                call.enqueue(new Callback <AttractionBooking>() {
                    @Override
                    public void onResponse(Call<AttractionBooking> call, Response<AttractionBooking> response) {
                        //get the result of the API call
                        AttractionBooking attractionBook = response.body();
                        bookingMap.put(bkDetail, attractionBook);
                    }

                    @Override
                    public void onFailure(Call<AttractionBooking> call, Throwable t) {
                        System.out.println("error: " + t.getMessage());
                    }
                });
            }else{
                Call<HotelBooking> call = jsonBookingAPIController.getHotelBookingDetails(bkDetail.getId());
                call.enqueue(new Callback <HotelBooking>() {
                    @Override
                    public void onResponse(Call<HotelBooking> call, Response<HotelBooking> response) {
                        //get the result of the API call
                        HotelBooking hotelBook = response.body();
//                        System.out.println("map" + hotelBook);
                        bookingMap.put(bkDetail, hotelBook);
                    }

                    @Override
                    public void onFailure(Call<HotelBooking> call, Throwable t) {
                        System.out.println("error: " + t.getMessage());
                    }
                });
            }
        }
        //wait for all the data finished loaded from API
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                adapter.setData(bookingMap);
                ListView listView = findViewById(R.id.bkDListView);
                if (listView != null) {
                    listView.setAdapter(adapter);
                }
            }
        }, 500);
    }
}