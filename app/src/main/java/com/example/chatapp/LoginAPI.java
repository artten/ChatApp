package com.example.chatapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chatapp.entities.Contact;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.concurrent.TimeUnit;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginAPI extends AppCompatActivity {

    private String Users;
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;
    private Context context;
    public  LoginAPI(Context context) {
        this.context = context;

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        CookieHandler cookieHandler = new CookieManager();
        OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(interceptor)
                .cookieJar(new JavaNetCookieJar(cookieHandler))
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(MyApp.context.getString(R.string.BaseURL))
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
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
                                 Users = response.body().toString();
                                 Call<Object> call2 = webServiceAPI.getContacts();


                                 call2.enqueue(new Callback<Object>() {

                                                   @Override
                                                   public void onResponse(Call<Object> call, Response<Object> response) {
                                                       if(response.code()/100 == 2) {

                                                           Intent intent = new Intent(context.getApplicationContext(), ChatContacts.class);


                                                           intent.putExtra("User", Users);
                                                           String temp = response.body().toString();
                                                           intent.putExtra("Contacts", "{\"contacts\":"+temp+"}");
                                                          // intent.put
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

                         @Override
                         public void onFailure(Call<Object> call, Throwable t) {
                             Log.d("hello 2","response.toString()");
                         }
        }
        );


    }
}
