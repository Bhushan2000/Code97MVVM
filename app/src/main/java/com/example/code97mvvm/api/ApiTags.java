package com.example.code97mvvm.api;

import com.example.code97mvvm.ui.tags.model.RootTags;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiTags {
    @Headers("app-id: 615468e36c6274327568f068")
    @GET("tag")
    Call<RootTags> getTags();
}
