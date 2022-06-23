package com.example.chatapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebServiceAPI {

    @GET("api/Login/{id}/{password}")
    Call<Object> getLogin(@Path("id") String id, @Path("password") String password);

    @GET("api/Contacts")
    Call<Object> getContacts();

    @POST("api/Register")
    Call<Object> postRegister(@Body RegisterInfo registerInfo);

    @POST("api/contacts/{id1}/messages")
    Call<Object> postMessage(@Path("id1") String id1, @Body String message);

    @GET("api/contacts/{id1}/messages")
    Call<Object> GetMessages(@Path("id1") String id1);

}
