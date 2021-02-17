package nus.edu.iss.mobileapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import nus.edu.iss.mobileapp.nonEntityModel.JsonProductAPIController;
import nus.edu.iss.mobileapp.nonEntityModel.JsonUserApiController;
import nus.edu.iss.mobileapp.model.Product;
import nus.edu.iss.mobileapp.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private JsonProductAPIController jsonProductAPIController;
    private MyCustomAdapter adapter;
    private int USER_LOGIN_RESPONSE = 1;
    private SharedPreferences pref;
    private boolean isLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonParse = findViewById(R.id.button);
        buttonParse.setOnClickListener(this);
        pref = getSharedPreferences("user_credentials", MODE_PRIVATE);
        checkLogin();

        //Need to type ipconfig in command prompt to check your ip address
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.10.104:8080/api/product/").addConverterFactory(GsonConverterFactory.create()).build();
        jsonProductAPIController = retrofit.create(JsonProductAPIController.class);
        adapter = new MyCustomAdapter(this, 0);
        Call<List<Product>> call = jsonProductAPIController.getHotSellingItems();
        queueCall(call);
    }

    @Override
    public void onClick(View v) {
        adapter = new MyCustomAdapter(this, 0);
        EditText search = findViewById(R.id.search_bar);
        Call<List<Product>> call = jsonProductAPIController.getSearchResults(search.getText().toString());
        queueCall(call);
    }

    public void queueCall(Call<List<Product>> call){
        //.enqueue helps to run at background thread as else will have exception and causing freeze of app
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                //get the result of the API call
                List<Product> products = response.body();
                adapter.setData(products);
                ListView listView = findViewById(R.id.listView);
                if (listView != null) {
                    listView.setAdapter(adapter);
                    listView.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Context context = getApplicationContext();
                            Intent intent = new Intent(context, ProductDetailsActivity.class);
                            intent.putExtra("Product", products.get(position));
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.login:
                //intent to login page
                if(item.getTitle().equals("Login")){
                    Intent loginInt = new Intent(this,loginActivity.class);
                    startActivityForResult(loginInt,USER_LOGIN_RESPONSE);
                }else{
                    SharedPreferences.Editor edit = pref.edit();
                    edit.remove("username");
                    edit.remove("password");
                    edit.commit();
                    isLogin = false;
                    invalidateOptionsMenu();
                }
                break;

            case R.id.bookingHistory:
                if (isLogin == false){
                    Intent loginInt = new Intent(this,loginActivity.class);
                    startActivityForResult(loginInt,USER_LOGIN_RESPONSE);
                }else{
                    String username = pref.getString("username", null);
                    Intent intent = new Intent(this, BookingHistoryActivity.class);
                    //hard coded username now, else should be retrieved from session
                    intent.putExtra("username", username);
                    startActivity(intent);
                    break;
                }
        }
        return true;
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if(resultCode!= RESULT_OK){
            return;
        }
        if(requestCode == USER_LOGIN_RESPONSE){
            isLogin = true;
            invalidateOptionsMenu();
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.login);
        if (isLogin == true){
            item.setTitle("Logout");
        }else{
            item.setTitle("Login");
        }
        return super.onPrepareOptionsMenu(menu);
    }

    //check if user is login when user started the application
    public void checkLogin(){
        if (pref.contains("username") && pref.contains("password")) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.10.104:8080/api/user/").addConverterFactory(GsonConverterFactory.create()).build();
            JsonUserApiController jsonuserAPIController = retrofit.create(JsonUserApiController.class);
            User user = new User(pref.getString("username", null), pref.getString("password", null));
            Call<User> call = jsonuserAPIController.login(user);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (!response.isSuccessful()) {
                        isLogin = false;
                        SharedPreferences.Editor edit = pref.edit();
                        edit.remove("username");
                        edit.remove("password");
                        edit.commit();
                        return;
                    } else {
                        isLogin = true;
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    System.out.println("fail");
                }
            });
        }
    }
}