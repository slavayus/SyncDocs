package com.sync.docs;

import android.app.Application;

import com.sync.docs.data.network.ApiEndpoint;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static ApiEndpoint api;

    @Override
    public void onCreate() {
        super.onCreate();
        api = initRetrofit().create(ApiEndpoint.class);
    }

    private Retrofit initRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://localhost/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(initClient())
                .build();
    }

    private OkHttpClient initClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    public static ApiEndpoint getApi() {
        return api;
    }
}
