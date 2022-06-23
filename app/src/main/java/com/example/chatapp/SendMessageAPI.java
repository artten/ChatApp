package com.example.chatapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chatapp.ChatContacts;
import com.example.chatapp.MyApp;
import com.example.chatapp.R;
import com.example.chatapp.WebServiceAPI;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SendMessageAPI extends AppCompatActivity {

    Retrofit retrofit;
    WebServiceAPI webServiceAPI;
    private Context context;
    public SendMessageAPI(Context context) {
        this.context = context;
        webServiceAPI = SingletonWebApi.getWebServiceAPI();


    }

    public void postMessage(String id, String message) {
        String temp = "{\"content\":" +message+"}";
        JSONObject mes = null;
        try {
            mes = new JSONObject(temp);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<Object> call = webServiceAPI.postMessage(id, mes);
        //Call<Object> call = webServiceAPI.postMessagez(message);
        call.enqueue(new Callback<Object>() {

                         @Override
                         public void onResponse(Call<Object> call, Response<Object> response) {
                             Log.d("Hello ", "onResponse: " +  response);
                             if(response.code() == 201) {

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

