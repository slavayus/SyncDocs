package com.sync.docs.data.network.repository;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.google.gson.Gson;
import com.sync.docs.App;
import com.sync.docs.data.network.model.message.Databases;
import com.sync.docs.data.network.model.message.GetMessageRequestModel;
import com.sync.docs.data.network.model.message.PostMessage;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MessageImpl implements Message {
    private static final String POST_MESSAGE_END = "/syncwebservice/api/SyncMessage/PostMessage";
    private static final String GET_MESSAGE_END = "/syncwebservice/api/SyncMessage/GetMessages/";
    private static final String DV_DATABASE_REQUEST = "Dv.DatabasesRequest";
    private static final String TAG = "MessageImpl";
    private final String requestId;
    private Disposable postMessageDisposable;
    private Disposable readMessageDisposable;
    private MutableLiveData<Databases> databasesLiveData = new MutableLiveData<>();

    public MessageImpl() {
        requestId = UUID.randomUUID().toString();
    }

    @Override
    public void createMessage(String baseUrl) {
        disposeReadMessage();
        PostMessage postMessage = new PostMessage(DV_DATABASE_REQUEST, requestId);
        String fullUrl = baseUrl + POST_MESSAGE_END;
        postMessageDisposable = App.getApi()
                .postMessage(fullUrl, postMessage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> readMessage(baseUrl),
                        throwable -> Log.d(TAG, "createMessage: "));
    }

    @Override
    public void readMessage(String baseUrl) {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        String fullUrl = baseUrl + GET_MESSAGE_END;
        GetMessageRequestModel requestModel = new GetMessageRequestModel(requestId);
        readMessageDisposable = App.getApi()
                .getMessage(fullUrl, requestModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .repeatWhen(message -> message.flatMap(size -> {
                    if (!atomicBoolean.get()) {
                        return Flowable.just(this).delay(500, TimeUnit.MILLISECONDS);
                    } else {
                        return Flowable.empty();
                    }
                }))
                .subscribe(getMessage -> {
                    if (getMessage.size() == 0) {
                        return;
                    }
                    atomicBoolean.set(true);
                    String message = getMessage.get(0).getMessage();
                    Databases databases = new Gson().fromJson(message, Databases.class);
                    databasesLiveData.setValue(databases);
                }, throwable -> Log.d(TAG, "readMessage: " + throwable));
    }

    @Override
    public MutableLiveData<Databases> getDatabases() {
        return databasesLiveData;
    }

    @Override
    public void onClear() {
        if (postMessageDisposable != null && !postMessageDisposable.isDisposed()) {
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
