package com.example.chatapp;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WebServiceAPI {

    @GET("login")
    Call<String> getLogin();

}
