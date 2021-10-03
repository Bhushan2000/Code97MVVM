package com.example.code97mvvm.ui.comments.repo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.code97mvvm.api.ApiBuilder;
import com.example.code97mvvm.api.ApiComments;
import com.example.code97mvvm.ui.comments.model.Root;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentsRepo {
    private final String TAG = getClass().getSimpleName();
    ApiComments apiComments = ApiBuilder.getClient().create(ApiComments.class);

    public MutableLiveData<Root> requestData() {

        final MutableLiveData<Root> mutableLiveData = new MutableLiveData<>();


        apiComments.getComments().enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                Log.e(TAG, "getCommentsList response=" + response);

                if (response.isSuccessful() && response.body() != null) {
                    Log.e(TAG, "getPostsList response.size=" + response.body());
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Log.e(TAG, "getCommentsList onFailure" + call.toString());
                mutableLiveData.setValue(null);

            }
        });


        return mutableLiveData;
    }

}