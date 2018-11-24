package com.sync.docs.data.network;

import com.sync.docs.data.network.model.auth.PostAuthMessage;
import com.sync.docs.data.network.model.message.GetMessage;
import com.sync.docs.data.network.model.message.GetMessageRequestModel;
import com.sync.docs.data.network.model.message.PostMessage;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ApiEndpoint {
    @POST
    Completable postMessage(@Url String url, @Body PostMessage postMessage);

    @POST
    Single<List<GetMessage>> getMessage(@Url String url, @Body GetMessageRequestModel requestId);

    @POST
    Completable postAuthMessage(@Url String url, @Body PostAuthMessage postAuthMessage);
}
