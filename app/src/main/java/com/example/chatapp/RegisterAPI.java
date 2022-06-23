package com.example.chatapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterAPI extends AppCompatActivity {

    Retrofit retrofit;
    WebServiceAPI webServiceAPI;
    private Context context;
    public RegisterAPI(Context context) {
        this.context = context;
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApp.context.getString(R.string.BaseURL))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);


    }

    public void postRegister(String id, String password, String Nickname) {
        RegisterInfo registerInfo = new RegisterInfo(id, password, Nickname);
        Call<Object> call = webServiceAPI.postRegister(registerInfo);
        call.enqueue(new Callback<Object>() {

                         @Override
                         public void onResponse(Call<Object> call, Response<Object> response) {
                             Log.d("Hello ", "onResponse: " +  response);
                             if(response.code() == 201) {

                                 Intent intent = new Intent(context.getApplicationContext(), MainActivity.class);
                                 context.startActivity(intent);

                             }
                         }

                         @Override
                         public void onFailure(Call<Object> call, Throwable t) {
                             Log.d("Hello ", "onFailure " + t);
                         }
        }
        );


    }
}
