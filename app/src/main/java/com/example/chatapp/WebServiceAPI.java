package com.example.chatapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WebServiceAPI {

    @GET("api/Login/{id}/{password}")
    Call<Object> getLogin(@Path("id") String id, @Path("password") String password);

}
