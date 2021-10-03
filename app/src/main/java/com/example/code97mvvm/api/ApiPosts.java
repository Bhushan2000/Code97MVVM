package com.example.code97mvvm.api;


import com.example.code97mvvm.ui.posts.model.Root;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface ApiPosts {

    // get posts
    @Headers("app-id: 615468e36c6274327568f068")
    @GET("post")
    Call<Root> getPosts();

    // delete posts
    @Headers("app-id: 615468e36c6274327568f068")
    @DELETE("post/{id}")
    Call<com.example.code97mvvm.ui.posts.deletePosts.Root> deletePosts(@Path("id") String Id);

    // update posts
    @FormUrlEncoded
    @Headers("app-id: 615468e36c6274327568f068")
    @PUT("post/{id}")
    Call<com.example.code97mvvm.ui.posts.updatePosts.Root> updatePosts(@Path("id") String Id,
                                                                       @Field("likes") String likes,
                                                                       @Field("text") String text,
                                                                       @Field("tags[0]") String tag0,
                                                                       @Field("tags[1]") String tag1,
                                                                       @Field("tags[2]") String tag2


    );

}
