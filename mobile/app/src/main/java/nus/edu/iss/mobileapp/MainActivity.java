package nus.edu.iss.mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

    private JsonProductAPIController jsonProductAPIController;
    MyCustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonParse = findViewById(R.id.button);

        //Need to type ipconfig in command prompt to check your ip address
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.10.104:8080/api/product/").addConverterFactory(GsonConverterFactory.create()).build();

//        JsonHotelAPIController jsonHotelAPIController = retrofit.create(JsonHotelAPIController.class);
        jsonProductAPIController = retrofit.create(JsonProductAPIController.class);
        buttonParse.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        adapter = new MyCustomAdapter(this, 0);
        EditText search = findViewById(R.id.search_bar);
        Call<List<Product>> call = jsonProductAPIController.getSearchResults(search.getText().toString());
        //checking path called
//                System.out.println(jsonHotelAPIController.getHotels().request().url().toString());
        //.enqueue helps to run at background thread as else will have exception and causing freeze of app
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

//                if(!response.isSuccessful()){
//                    mTextView.setText("Code: " + response.code());
//                    return;
//                }
                //get the result of the API call
                List<Product> products = response.body();

//                for (Product product: products){
//                    String content = "";
//                    content += product.toString() + "\n\n";
//                    System.out.println(product);
//                    mTextView.append(content);
//                }
                adapter.setData(products);
                ListView listView = findViewById(R.id.listView);
                if (listView != null) {
                    listView.setAdapter(adapter);
                    listView.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Context context = getApplicationContext();
//                            Toast toast = Toast.makeText(context, products.get(position).toString(), Toast.LENGTH_SHORT);
//                            toast.show();

                            Intent intent = new Intent(context, ProductDetailsActivity.class);
                            intent.putExtra("Product", products.get(position));
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
//                mTextView.setText(t.getMessage());
            }
        });
    }
}