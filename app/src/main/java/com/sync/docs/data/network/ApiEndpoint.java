package com.sync.docs.data.network;

import com.sync.docs.data.network.model.PostMessage;

import io.reactivex.Completable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ApiEndpoint {

    @POST
    Completable postMessage(@Url String url, @Body PostMessage postMessage);
}
