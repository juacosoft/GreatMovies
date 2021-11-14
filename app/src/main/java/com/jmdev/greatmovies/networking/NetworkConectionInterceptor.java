package com.jmdev.greatmovies.networking;

import android.content.Context;
import android.net.ConnectivityManager;

import androidx.annotation.NonNull;

import com.jmdev.greatmovies.test.util.ApiException;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class NetworkConectionInterceptor implements Interceptor {
    final Context context;
    public NetworkConectionInterceptor(Context context){
        this.context=context.getApplicationContext();
    }
    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        if(isInternetAviable()) throw new ApiException("non_conection");
        return chain.proceed(chain.request());
    }
    private boolean isInternetAviable(){
        ConnectivityManager connectivityManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo().isConnected();

    }
}
