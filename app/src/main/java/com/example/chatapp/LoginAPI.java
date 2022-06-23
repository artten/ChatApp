package com.example.chatapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chatapp.entities.Contact;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginAPI extends AppCompatActivity {

    Retrofit retrofit;
    WebServiceAPI webServiceAPI;
    private Context context;
    public  LoginAPI(Context context) {
        this.context = context;
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApp.context.getString(R.string.BaseURL))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);


    }

    public void getLogin(String id, String password) {
        Call<Object> call = webServiceAPI.getLogin(id, password);
        final int[] ret = {0,0};
        call.enqueue(new Callback<Object>() {

                         @Override
                         public void onResponse(Call<Object> call, Response<Object> response) {
                             if(response.code() == 200) {

                                 Intent intent = new Intent(context.getApplicationContext(), ChatContacts.class);
                                 intent.putExtra("User", response.body().toString());
                                 //intent.putExtra("User", "{\"id\":\"e\", \"password\":\"e\",\"contacts\":[{\"id\":\"1\",\"name\":\"1Name\"},{\"id\":\"2\",\"name\":\"2name\"}]}");
                                 //intent.putExtra("User", "{\"foos\" : [{\"prop1\":\"value1\",\"prop2\":\"value2\"}, {\"prop1\":\"value3\",\"prop2\":\"value4\"}]}"
                                 context.startActivity(intent);

                             }

                         }

                         @Override
                         public void onFailure(Call<Object> call, Throwable t) {
                         }
        }
        );


    }
}
