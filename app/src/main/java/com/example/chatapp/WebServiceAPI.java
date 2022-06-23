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
    Call<Object> postRegister(@Body String id, @Body String password, @Body String Nickname);

    @GET("api/contacts/{id1}/messages/{id2}")
    Call<Object> GetMessages(@Body String id1, @Body String id2);

}
