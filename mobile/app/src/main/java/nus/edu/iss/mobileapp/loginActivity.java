package nus.edu.iss.mobileapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import nus.edu.iss.mobileapp.nonEntityModel.JsonUserApiController;
import nus.edu.iss.mobileapp.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class loginActivity extends AppCompatActivity implements View.OnClickListener {

    private JsonUserApiController jsonuserAPIController;
    String username1;
    String password1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        findViewById(R.id.login1).setOnClickListener(this);
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.10.104:8080/api/user/").addConverterFactory(GsonConverterFactory.create()).build();
        jsonuserAPIController = retrofit.create(JsonUserApiController.class);
    }

    @Override
    public void onClick(View v) {
        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        username1 = username.getText().toString();
        password1 = password.getText().toString();

        if((username1 != null) && (password1 != null)){
            sendPost();
        }else{
            Toast.makeText(getApplicationContext(),"Please fill in both username and password",Toast.LENGTH_SHORT).show();
        }
    }

    public void sendPost(){
        User user = new User(username1,password1);
        Call<User> call = jsonuserAPIController.login(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(!response.isSuccessful()){
                    System.out.println("message: " + response.message());
                    System.out.println("message: " + response.errorBody());
                    System.out.println("message: " + response.headers());
                    Toast.makeText(getApplicationContext(),"Wrong username and password",Toast.LENGTH_SHORT).show();
                    return;
                }
                System.out.println(response.body().toString());
                User postResponse = response.body();
                String username = postResponse.getUserName();
                Intent intent = new Intent();
                intent.putExtra("username",username );
                setResult(RESULT_OK,intent);
                SharedPreferences pref = getSharedPreferences("user_credentials", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();

                editor.putString("username", username1);
                editor.putString("password", password1);

                editor.commit();

                Toast.makeText(getApplicationContext(), "login sucessful", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println("fail");
            }
        });
    }
}
