package com.sync.docs.data.network.repository;

import android.util.Log;

import com.google.gson.Gson;
import com.sync.docs.App;
import com.sync.docs.data.network.model.Databases;
import com.sync.docs.data.network.model.GetMessageRequestModel;
import com.sync.docs.data.network.model.PostMessage;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MessageImpl implements Message {
    private static final String POST_MESSAGE_END = "/syncwebservice/api/SyncMessage/PostMessage";
    private static final String GET_MESSAGE_END = "/syncwebservice/api/SyncMessage/GetMessages/";
    private static final String DV_DATABASE_REQUEST = "Dv.DatabasesRequest";
    private static final String TAG = "MessageImpl";
    private Disposable postMessageDisposable;
    private Disposable readMessageDisposable;

    @Override
    public void createMessage(String baseUrl, String requestId) {
        disposeReadMessage();
        PostMessage postMessage = new PostMessage(DV_DATABASE_REQUEST, requestId);
        String fullUrl = baseUrl + POST_MESSAGE_END;
        postMessageDisposable = App.getApi()
                .postMessage(fullUrl, postMessage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> readMessage(baseUrl, requestId));
    }

    @Override
    public void readMessage(String baseUrl, String requestId) {
        String fullUrl = baseUrl + GET_MESSAGE_END;
        GetMessageRequestModel requestModel = new GetMessageRequestModel(requestId);
        readMessageDisposable = App.getApi()
                .getMessage(fullUrl, requestModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .repeatWhen(message -> message.flatMap(size -> {
                    if ((int) size == 0) {
                        return Flowable.empty().delay(3000, TimeUnit.MILLISECONDS);
                    } else {
                        return message;
                    }
                }))
                .subscribe(getMessage -> {
                    String message = getMessage.get(0).getMessage();
                    Databases databases = new Gson().fromJson(message, Databases.class);
                    Log.d(TAG, "readMessage: " + databases);
                }, throwable -> {
                    Log.d(TAG, "readMessage: " + throwable);
                });
    }

    @Override
    public void onClear() {
        if (!postMessageDisposable.isDisposed()) {
            postMessageDisposable.dispose();
        }
        disposeReadMessage();
    }

    private void disposeReadMessage() {
        if (readMessageDisposable != null && !readMessageDisposable.isDisposed()) {
            readMessageDisposable.dispose();
        }
    }
}
