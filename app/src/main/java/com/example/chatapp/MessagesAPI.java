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

public class MessagesAPI extends AppCompatActivity {

    Retrofit retrofit;
    WebServiceAPI webServiceAPI;
    private Context context;
    public  MessagesAPI(Context context) {
        this.context = context;
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApp.context.getString(R.string.BaseURL))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);


    }

    public void getMessages(String id1, String id2, String user) {
        Call<Object> call = webServiceAPI.GetMessages(id1, id2);
        call.enqueue(new Callback<Object>() {

                         @Override
                         public void onResponse(Call<Object> call, Response<Object> response) {
                             if(response.code()/100 == 2) {

                                 Intent intent = new Intent(context.getApplicationContext(), ChatsRoom.class);
                                 intent.putExtra("Messages", response.body().toString());
                                 intent.putExtra("Contact", id2);
                                 intent.putExtra("Owner", id1);
                                 intent.putExtra("User", user);
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
