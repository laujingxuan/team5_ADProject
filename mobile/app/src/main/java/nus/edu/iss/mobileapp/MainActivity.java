package nus.edu.iss.mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import nus.edu.iss.mobileapp.nonEntityModel.Hotel;
import nus.edu.iss.mobileapp.nonEntityModel.JsonHotelAPIController;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
//    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.textView);
        Button buttonParse = findViewById(R.id.button);
//        mQueue = Volley.newRequestQueue(this);

        //Need to type ipconfig in command prompt to check your ip address
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.10.104:8080/api/hotel/").addConverterFactory(GsonConverterFactory.create()).build();

        JsonHotelAPIController jsonHotelAPIController = retrofit.create(JsonHotelAPIController.class);

        buttonParse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Call<List<Hotel>> call = jsonHotelAPIController.getHotels();
                //checking path called
//                System.out.println(jsonHotelAPIController.getHotels().request().url().toString());
                //.enqueue helps to run at background thread as else will have exception and causing freeze of app
                call.enqueue(new Callback<List<Hotel>>() {
                    @Override
                    public void onResponse(Call<List<Hotel>> call, Response<List<Hotel>> response) {

                        if(!response.isSuccessful()){
                            mTextView.setText("Code: " + response.code());
                            return;
                        }
                        //get the result of the API call
                        List<Hotel> hotels = response.body();

                        for (Hotel hotel: hotels){
                            String content = "";
                            content += hotel.toString() + "\n\n";
                            mTextView.append(content);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Hotel>> call, Throwable t) {
                        mTextView.setText(t.getMessage());
                    }
                });
            }
        });
    }

    // Trying for volley
//    private void jsonParse(){
//        EditText search = findViewById(R.id.search_bar);
//        String url = "http://localhost:8080/api/hotel/hotels";
//
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try {
//                    JSONArray jsonArray = response.getJSONArray("employees");
//
//                    for (int i = 0; i < jsonArray.length(); i++){
//                        JSONObject employee = jsonArray.getJSONObject(i);
//
//
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        });
//    }
}