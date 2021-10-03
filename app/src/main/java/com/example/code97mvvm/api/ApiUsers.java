package com.example.code97mvvm.api;


import com.example.code97mvvm.ui.users.createUser.CreateResponse;
import com.example.code97mvvm.ui.users.models.RootUsers;
import com.example.code97mvvm.ui.users.models.UsersResponse;
import com.example.code97mvvm.ui.users.updateUser.Root;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface ApiUsers {

    // get
    @Headers("app-id: 615468e36c6274327568f068")
    @GET("user")
    Call<UsersResponse> getUsers();

    // get
    @Headers({"app-id: 615468e36c6274327568f068"})
    @GET("user/{id}")
    Call<RootUsers> getUserDetailsById(@Path("id") String Id);

    // create
    @FormUrlEncoded
    @Headers({"app-id: 615468e36c6274327568f068"})
    @POST("user/create")
    Call<CreateResponse> createUser(@FieldMap Map<String,String> params);


    // update
    @FormUrlEncoded
    @Headers({"app-id: 615468e36c6274327568f068"})
    @PUT("user/{id}")
    Call<Root> updateUser(@Path("id") String id,@FieldMap Map<String,String> params);


    // delete
    @Headers({"app-id: 615468e36c6274327568f068"})
    @DELETE("user/{id}")
    Call<com.example.code97mvvm.ui.users.deleteUser.Root> deleteUser(@Path("id") String id);


}
