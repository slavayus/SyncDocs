package com.sync.docs.data.network;

import com.sync.docs.data.network.model.GetMessage;
import com.sync.docs.data.network.model.GetMessageRequestModel;
import com.sync.docs.data.network.model.PostMessage;

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
}
