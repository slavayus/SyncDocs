package com.sync.docs.data.network.repository;

import android.util.Log;

import com.google.gson.Gson;
import com.sync.docs.App;
import com.sync.docs.data.network.model.auth.AuthRequest;
import com.sync.docs.data.network.model.auth.PostAuthMessage;

import java.util.UUID;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AuthMessageImpl implements AuthMessage {
    private static final String POST_AUTH_MESSAGE_END = "/syncwebservice/api/SyncMessage/PostAuthMessage";
    private static final String GET_MESSAGE_END = "/syncwebservice/api/SyncMessage/GetAuthMessages/";
    private static final String DV_AUTH_REQUEST = "Dv.AuthRequest";
    private static final String TAG = "AuthMessageImpl";
    private final String requestId;
    private Disposable postAuthMessageDisposable;

    public AuthMessageImpl() {
        requestId = UUID.randomUUID().toString();
        Log.d(TAG, "AuthMessageImpl: "+requestId);
    }

    @Override
    public void createAuthMessage(String baseUrl, AuthRequest authRequest) {
        String fullUrl = baseUrl + POST_AUTH_MESSAGE_END;
        PostAuthMessage postAuthMessage = new PostAuthMessage(DV_AUTH_REQUEST, requestId, new Gson().toJson(authRequest));
        postAuthMessageDisposable = App.getApi()
                .postAuthMessage(fullUrl, postAuthMessage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();

    }

    @Override
    public void readAuthMessage(String fullUrl, String requestId) {

    }

    @Override
    public void onClear() {
        if (postAuthMessageDisposable != null && !postAuthMessageDisposable.isDisposed()) {
            postAuthMessageDisposable.dispose();
        }
    }
}
