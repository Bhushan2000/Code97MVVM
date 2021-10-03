package com.example.code97mvvm.ui.posts.repo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.code97mvvm.api.ApiBuilder;
import com.example.code97mvvm.api.ApiPosts;
import com.example.code97mvvm.ui.posts.model.Root;


import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsRepo {

    private final String TAG = getClass().getSimpleName();
    ApiPosts apiPosts = ApiBuilder.getClient().create(ApiPosts.class);


    public MutableLiveData<Root> requestData() {

        final MutableLiveData<Root> mutableLiveData = new MutableLiveData<>();


        apiPosts.getPosts().enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                Log.e(TAG, "getPostsList response=" + response);

                if (response.isSuccessful() && response.body() != null) {
                    Log.e(TAG, "getPostsList response.size=" + response.body());

                    mutableLiveData.setValue(response.body());


                } else {

                    Log.e(TAG, String.valueOf(response.errorBody()));

                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Log.e(TAG, "getPostsList onFailure" + call.toString());
                mutableLiveData.setValue(null);

            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<com.example.code97mvvm.ui.posts.deletePosts.Root> deletePost(String id) {
        MutableLiveData<com.example.code97mvvm.ui.posts.deletePosts.Root> mutableLiveData = new MutableLiveData<>();
        apiPosts.deletePosts(id).enqueue(new Callback<com.example.code97mvvm.ui.posts.deletePosts.Root>() {
            @Override
            public void onResponse(Call<com.example.code97mvvm.ui.posts.deletePosts.Root> call, Response<com.example.code97mvvm.ui.posts.deletePosts.Root> response) {
                Log.e(TAG, "deletePost response=" + response);

                if (response.isSuccessful() && response.body() != null) {
                    Log.e(TAG, "deletePost response.size=" + response.body());

                    mutableLiveData.setValue(response.body());


                } else {

                    Log.e(TAG, String.valueOf(response.errorBody()));


                }
            }

            @Override
            public void onFailure(Call<com.example.code97mvvm.ui.posts.deletePosts.Root> call, Throwable t) {
                Log.e(TAG, "deletePost onFailure" + call.toString());
                mutableLiveData.setValue(null);

            }
        });
        return mutableLiveData;

    }

    public MutableLiveData<com.example.code97mvvm.ui.posts.updatePosts.Root> updatePost(String id, String like,String text,String tag0,String tag1,String tag2) {

        MutableLiveData<com.example.code97mvvm.ui.posts.updatePosts.Root> mutableLiveData = new MutableLiveData<>();

        apiPosts.updatePosts(id, like,text,tag0,tag1,tag2).enqueue(new Callback<com.example.code97mvvm.ui.posts.updatePosts.Root>() {
            @Override
            public void onResponse(Call<com.example.code97mvvm.ui.posts.updatePosts.Root> call, Response<com.example.code97mvvm.ui.posts.updatePosts.Root> response) {
                Log.e(TAG, "updatePost response=" + response);

                if (response.isSuccessful() && response.body() != null) {
                    Log.e(TAG, "updatePost response.size=" + response.body());

                    mutableLiveData.setValue(response.body());


                } else {

                    Log.e(TAG, String.valueOf(response.errorBody()));


                }
            }

            @Override
            public void onFailure(Call<com.example.code97mvvm.ui.posts.updatePosts.Root> call, Throwable t) {
                Log.e(TAG, "updatePost onFailure" + call.toString());
                mutableLiveData.setValue(null);
            }
        });
        return mutableLiveData;

    }
}
