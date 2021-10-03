package com.example.code97mvvm.ui.tags.repo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.code97mvvm.api.ApiBuilder;
import com.example.code97mvvm.api.ApiTags;
import com.example.code97mvvm.ui.tags.model.RootTags;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TagsRepo {
    private final String TAG = getClass().getSimpleName();
    ApiTags apiTags = ApiBuilder.getClient().create(ApiTags.class);

    public MutableLiveData<RootTags> requestData() {

        final MutableLiveData<RootTags> mutableLiveData = new MutableLiveData<>();

        apiTags.getTags().enqueue(new Callback<RootTags>() {
            @Override
            public void onResponse(Call<RootTags> call, Response<RootTags> response) {
                Log.e(TAG, "getTagsList response=" + response);

                if (response.isSuccessful() && response.body() != null) {
                    Log.e(TAG, "getTagsList response.size=" + response.body());
                    mutableLiveData.setValue(response.body());

                }else{

                    Log.e(TAG, String.valueOf(response.errorBody()));

                }
            }

            @Override
            public void onFailure(Call<RootTags> call, Throwable t) {
                Log.e(TAG, "getTagsList onFailure" + call.toString());
                mutableLiveData.setValue(null);
            }
        });
        return mutableLiveData;
    }
}
