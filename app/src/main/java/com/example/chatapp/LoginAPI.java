package com.example.chatapp;

import android.util.Log;

import com.google.android.gms.common.api.Response;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginAPI {

    Retrofit retrofit;
    WebServiceAPI webServiceAPI;

    public  LoginAPI() {

        retrofit = new Retrofit.Builder()
                .baseUrl(MyApp.context.getString(Integer.parseInt("http://IP:5263/api/Login/e/e")))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);

    }

    public void get() {
        Call<String> call = webServiceAPI.getLogin();
        call.enqueue(new Callback<String>() {

                         @Override
                         public void onResponse(Call<String> call, retrofit2.Response<String> response) {
                             response.body();
                             Log.d("Hello ", "onResponse: " + response.body());
                         }

                         @Override
                         public void onFailure(Call<String> call, Throwable t) {

                         }
        }
        );
    }
}
