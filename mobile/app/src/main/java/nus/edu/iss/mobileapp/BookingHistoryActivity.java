package nus.edu.iss.mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import nus.edu.iss.mobileapp.nonEntityModel.Booking;
import nus.edu.iss.mobileapp.nonEntityModel.JsonBookingAPIController;
import nus.edu.iss.mobileapp.nonEntityModel.JsonProductAPIController;
import nus.edu.iss.mobileapp.nonEntityModel.Product;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookingHistoryActivity extends AppCompatActivity {

    private JsonBookingAPIController jsonBookingAPIController;
    BookingHistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_history);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.10.104:8080/api/booking/").addConverterFactory(GsonConverterFactory.create()).build();
        jsonBookingAPIController = retrofit.create(JsonBookingAPIController.class);
        Intent intent = getIntent();
        Call<List<Booking>> call = jsonBookingAPIController.getBookingHistory(intent.getStringExtra("username"));
        adapter = new BookingHistoryAdapter(this, 0);
        call.enqueue(new Callback<List<Booking>>() {
            @Override
            public void onResponse(Call<List<Booking>> call, Response<List<Booking>> response) {

                //get the result of the API call
                List<Booking> bookings = response.body();
                adapter.setData(bookings);
                ListView listView = findViewById(R.id.bklistView);
                if (listView != null) {
                    listView.setAdapter(adapter);
//                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//                        @Override
//                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                            Context context = getApplicationContext();
//                            Intent intent = new Intent(context, ProductDetailsActivity.class);
//                            intent.putExtra("Product", products.get(position));
//                            startActivity(intent);
//                        }
//                    });
                }
            }

            @Override
            public void onFailure(Call<List<Booking>> call, Throwable t) {
                System.out.println("error: " + t.getMessage());
            }
        });

    }
}