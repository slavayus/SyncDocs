package com.sync.docs.data.network.repository;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.google.gson.Gson;
import com.sync.docs.App;
import com.sync.docs.data.network.model.auth.AuthRequest;
import com.sync.docs.data.network.model.auth.AuthResponse;
import com.sync.docs.data.network.model.auth.PostAuthMessage;
import com.sync.docs.data.network.model.message.GetMessageRequestModel;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AuthMessageImpl implements AuthMessage {
    private static final String POST_AUTH_MESSAGE_END = "/syncwebservice/api/SyncMessage/PostAuthMessage";
    private static final String GET_AUTH_MESSAGE_END = "/syncwebservice/api/SyncMessage/GetAuthMessages/";
    private static final String DV_AUTH_REQUEST = "Dv.AuthRequest";
    private static final String TAG = "AuthMessageImpl";
    private Disposable postAuthMessageDisposable;
    private Disposable readAuthMessageDisposable;
    private MutableLiveData<String> authMessageLiveData = new MutableLiveData<>();

    @Override
    public void createAuthMessage(String baseUrl, AuthRequest authRequest) {
        final String requestId = UUID.randomUUID().toString();
        disposeAuthReadMessage();
        String fullUrl = baseUrl + POST_AUTH_MESSAGE_END;
        PostAuthMessage postAuthMessage = new PostAuthMessage(DV_AUTH_REQUEST, requestId, new Gson().toJson(authRequest));
        postAuthMessageDisposable = App.getApi()
                .postAuthMessage(fullUrl, postAuthMessage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> readAuthMessage(baseUrl, requestId));

    }

    @Override
    public void readAuthMessage(String baseUrl, String requestId) {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        String fullUrl = baseUrl + GET_AUTH_MESSAGE_END;
        GetMessageRequestModel requestModel = new GetMessageRequestModel(requestId);
        readAuthMessageDisposable = App.getApi()
                .getAuthMessage(fullUrl, requestModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .repeatWhen(message -> message.flatMap(size -> {
                    Log.d(TAG, "readAuthMessage: " + size);
                    if (!atomicBoolean.get()) {
                        return Flowable.just(this).delay(500, TimeUnit.MILLISECONDS);
                    } else {
                        return Flowable.empty();
                    }
                }))
                .subscribe(getMessage -> {
                    if (getMessage.size() <= 0) {
                        return;
                    }
                    atomicBoolean.set(true);
                    String message = getMessage.get(getMessage.size() - 1).getMessage();
                    AuthResponse authResponse = new Gson().fromJson(message, AuthResponse.class);
                    authMessageLiveData.setValue(authResponse.getMessage());
                }, throwable -> {
                    Log.d(TAG, "readMessage: " + throwable);
                });
    }

    @Override
    public MutableLiveData<String> getAuthMessage() {
        return authMessageLiveData;
    }

    @Override
    public void onClear() {
        if (postAuthMessageDisposable != null && !postAuthMessageDisposable.isDisposed()) {
            postAuthMessageDisposable.dispose();
        }
        disposeAuthReadMessage();
    }

    private void disposeAuthReadMessage() {
        if (readAuthMessageDisposable != null && !readAuthMessageDisposable.isDisposed()) {
            readAuthMessageDisposable.dispose();
        }
    }
}
