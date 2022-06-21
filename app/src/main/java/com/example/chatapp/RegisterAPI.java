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

    public int postRegister(String id, String password, String Nickname) {
        Call<Object> call = webServiceAPI.postRegister(id, password, Nickname);
        final int[] ret = {0,0};
        call.enqueue(new Callback<Object>() {

                         @Override
                         public void onResponse(Call<Object> call, Response<Object> response) {
                             if(response.code() == 201) {
                                 ret[0] = 1;

                                 Intent intent = new Intent(context.getApplicationContext(), ChatContacts.class);
                                 context.startActivity(intent);

                             }
                             else{
                                 ret[0] = 0;
                             }
                             Log.d("Hello ", "onResponse: " +  ret[0]);
                             ret[1] = 1;

                         }

                         @Override
                         public void onFailure(Call<Object> call, Throwable t) {
                             Log.d("Hello ", "onFailure " + t);
                             ret[0] = 0;
                             ret[1] = 1;
                         }
        }
        );

//        while(true){
//            if (ret[1] == 1) {
//                Log.d("Hello ", "too: " +  ret[0]);
//                return ret[0];
//            }
//        }
        return ret[0];

    }
}