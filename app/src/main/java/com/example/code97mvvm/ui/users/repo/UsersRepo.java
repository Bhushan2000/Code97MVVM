package com.example.code97mvvm.ui.users.repo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.code97mvvm.api.ApiBuilder;
import com.example.code97mvvm.api.ApiUsers;
import com.example.code97mvvm.ui.users.createUser.CreateResponse;
import com.example.code97mvvm.ui.users.models.UsersResponse;
import com.example.code97mvvm.ui.users.models.RootUsers;
import com.example.code97mvvm.ui.users.updateUser.Root;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersRepo {

    private final String TAG = getClass().getSimpleName();
    ApiUsers apiUsers = ApiBuilder.getClient().create(ApiUsers.class);

    public MutableLiveData<UsersResponse> requestData() {

        final MutableLiveData<UsersResponse> mutableLiveData = new MutableLiveData<>();

        apiUsers.getUsers().enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                Log.e(TAG, "getUsersList response=" + response);

                if (response.isSuccessful() && response.body() != null) {
                    Log.e(TAG, "Users response.size=" + response.body().data.size());
                    mutableLiveData.setValue(response.body());

                }else{

                    Log.e(TAG, String.valueOf(response.errorBody()));

                }
            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {
                Log.e(TAG, "getProdList onFailure" + call.toString());
                mutableLiveData.setValue(null);
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<RootUsers> getUserById(String userId) {
        final MutableLiveData<RootUsers> userDetails = new MutableLiveData<>();


        apiUsers.getUserDetailsById(userId).enqueue(new Callback<RootUsers>() {
            @Override
            public void onResponse(Call<RootUsers> call, Response<RootUsers> response) {
                Log.e(TAG, "getUsersList response Successful=" + response);

                if (response.isSuccessful() && response.body() != null) {
                    Log.e(TAG, "Users response.size=" + response.body());
                    userDetails.setValue(response.body());

                }else{

                    Log.e(TAG, String.valueOf(response.errorBody()));

                }
            }

            @Override
            public void onFailure(Call<RootUsers> call, Throwable t) {
                Log.e(TAG, "getUsersList by id onFailure" + call.toString());
                userDetails.setValue(null);
            }
        });
        return userDetails;
    }


    public MutableLiveData<CreateResponse> createUser(String firstName, String lastName, String email) {

        final MutableLiveData<CreateResponse> mutableLiveData = new MutableLiveData<>();

        Map<String, String> map = new HashMap<>();
        map.put("firstName", firstName);

        map.put("lastName", lastName);
        map.put("email", email);

        apiUsers.createUser(map).enqueue(new Callback<CreateResponse>() {
            @Override
            public void onResponse(Call<CreateResponse> call, Response<CreateResponse> response) {
                Log.e(TAG, "Create user response=" + response);

                if (response.isSuccessful() && response.body() != null) {
                    Log.e(TAG, "Create user  response.size=" + response.body());
                    mutableLiveData.setValue(response.body());

                }else{

                    Log.e(TAG, String.valueOf(response.errorBody()));

                }
            }

            @Override
            public void onFailure(Call<CreateResponse> call, Throwable t) {
                Log.e(TAG, "Create user  onFailure" + call.toString());

                mutableLiveData.setValue(null);

                if (t instanceof IOException) {
                    Log.d(TAG, "onFailure: " + "this is an actual network failure :( inform the user and possibly retry");
                }
            }


        });
        return mutableLiveData;


    }

    public MutableLiveData<Root> updateUser(String userId, String firstName, String lastName) {

        final MutableLiveData<Root> mutableLiveData = new MutableLiveData<>();


        Map<String, String> map = new HashMap<>();

        map.put("firstName", firstName);
        map.put("lastName", lastName);


        apiUsers.updateUser(userId,map).enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                Log.e(TAG, "updateUser response=" + response);

                if (response.isSuccessful() && response.body() != null) {
                    Log.e(TAG, "updateUser response.size=" + response.body());
                    mutableLiveData.setValue(response.body());

                }else{

                    Log.e(TAG, String.valueOf(response.errorBody()));

                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Log.e(TAG, "updateUser onFailure" + call.toString());
                mutableLiveData.setValue(null);
            }
        });
        return mutableLiveData;

    }


    public MutableLiveData<com.example.code97mvvm.ui.users.deleteUser.Root> deleteUser(String id) {
        final MutableLiveData<com.example.code97mvvm.ui.users.deleteUser.Root> mutableLiveData = new MutableLiveData<>();



        apiUsers.deleteUser(id).enqueue(new Callback<com.example.code97mvvm.ui.users.deleteUser.Root>() {
            @Override
            public void onResponse(Call<com.example.code97mvvm.ui.users.deleteUser.Root> call, Response<com.example.code97mvvm.ui.users.deleteUser.Root> response) {
                Log.e(TAG, "deleteUser response=" + response);

                if (response.isSuccessful() && response.body() != null) {
                    Log.e(TAG, "deleteUser response.size=" + response.body());
                    mutableLiveData.setValue(response.body());

                }else{

                    Log.e(TAG, String.valueOf(response.errorBody()));

                }

            }

            @Override
            public void onFailure(Call<com.example.code97mvvm.ui.users.deleteUser.Root> call, Throwable t) {
                Log.e(TAG, "deleteUser onFailure" + call.toString());
                mutableLiveData.setValue(null);
            }
        });

        return mutableLiveData;

    }
}
