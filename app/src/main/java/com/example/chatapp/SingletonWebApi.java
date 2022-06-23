package com.example.chatapp;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.concurrent.TimeUnit;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SingletonWebApi {

    static private WebServiceAPI webServiceAPI;
    private Retrofit retrofit;


    public SingletonWebApi() {
        if(webServiceAPI == null){
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
            this.webServiceAPI = retrofit.create(WebServiceAPI.class);
        }
    }

    static public WebServiceAPI getWebServiceAPI() {
        if(webServiceAPI == null){
            new SingletonWebApi();
        }
        return webServiceAPI;
    }
}
