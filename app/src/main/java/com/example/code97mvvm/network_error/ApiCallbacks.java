package com.example.code97mvvm.network_error;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class ApiCallbacks <T> implements Callback<Response<T>> {

    @Override
    public void onResponse(Call<Response<T>> call, Response<Response<T>> response) {
        if (response.body() != null) {
            handleResponseData((T) response.body());
        } else {
            handleError(response);
        }
    }

    abstract protected void handleResponseData(T data);

    abstract protected void handleError(retrofit2.Response<Response<T>> response);

    abstract protected void handleException(Exception t);

    @Override
    public void onFailure(Call<Response<T>> call, Throwable t) {
        if (t instanceof Exception){
            handleException((Exception) t);
        }else{
            //do something else

        }
    }

}
