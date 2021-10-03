package com.example.code97mvvm.api;


import com.example.code97mvvm.ui.comments.model.Root;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ApiComments {

    // get comments
    @Headers("app-id: 615468e36c6274327568f068")
    @GET("comment")
    Call<Root> getComments();

    // delete comments
    @Headers("app-id: 615468e36c6274327568f068")
    @GET("comment/{id}")
    Call<Root> deleteComments(@Path("id") String id);
}
