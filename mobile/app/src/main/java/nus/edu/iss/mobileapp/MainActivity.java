package nus.edu.iss.mobileapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
import nus.edu.iss.mobileapp.nonEntityModel.JsonProductAPIController;
import nus.edu.iss.mobileapp.nonEntityModel.Product;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextView;
    private JsonProductAPIController jsonProductAPIController;
    MyCustomAdapter adapter;
    Button login;
    private int USER_LOGIN_RESPONSE = 1;
    String username = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.textView);
        Button buttonParse = findViewById(R.id.button);
        login = findViewById(R.id.login);
        findViewById(R.id.login).setOnClickListener(this);
        //Need to type ipconfig in command prompt to check your ip address
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.10.126:8080/api/product/").addConverterFactory(GsonConverterFactory.create()).build();

//        JsonHotelAPIController jsonHotelAPIController = retrofit.create(JsonHotelAPIController.class);
        jsonProductAPIController = retrofit.create(JsonProductAPIController.class);
        buttonParse.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if(id == R.id.login){
            if(login.getText()=="login"){
                Intent intent = new Intent(this,loginActivity.class);
                startActivityForResult(intent,USER_LOGIN_RESPONSE);
            }else{
                username = "";
                login.setText("login");
                SharedPreferences pref = getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = pref.edit();
                edit.remove("username");
                edit.remove("password");
                edit.commit();
            }

        }

        adapter = new MyCustomAdapter(this, 0);
        EditText search = findViewById(R.id.search_bar);
        Call<List<Product>> call = jsonProductAPIController.getSearchResults(search.getText().toString());
        //checking path called
//                System.out.println(jsonHotelAPIController.getHotels().request().url().toString());
        //.enqueue helps to run at background thread as else will have exception and causing freeze of app
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                if(!response.isSuccessful()){
                    mTextView.setText("Code: " + response.code());
                    return;
                }
                //get the result of the API call
                List<Product> products = response.body();

                for (Product product: products){
                    String content = "";
                    content += product.toString() + "\n\n";
                    System.out.println(product);
                    mTextView.append(content);
                }
                adapter.setData(products);
                ListView listView = findViewById(R.id.listView);
                if (listView != null) {
                    listView.setAdapter(adapter);
                    listView.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                mTextView.setText(t.getMessage());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if(resultCode!= RESULT_OK){
            return;
        }
        if(requestCode == USER_LOGIN_RESPONSE){
            String newQuote = intent.getStringExtra("username");
            if(newQuote !=null)
            {
                username=newQuote;
                login.setText("logout");
            }
        }
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