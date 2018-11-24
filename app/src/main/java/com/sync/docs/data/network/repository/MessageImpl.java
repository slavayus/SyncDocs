package com.sync.docs.data.network.repository;

import com.sync.docs.App;
import com.sync.docs.data.network.model.PostMessage;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MessageImpl implements Message {
    private static final String POST_MESSAGE_END = "/syncwebservice/api/SyncMessage/PostMessage";
    private static final String DV_DATABASE_REQUEST = "Dv.DatabasesRequest";
    private Disposable postMessageDisposable;

    @Override
    public void createMessage(String baseUrl, String requestId) {
        PostMessage postMessage = new PostMessage(DV_DATABASE_REQUEST, requestId);
        postMessageDisposable = App.getApi()
                .postMessage(baseUrl + POST_MESSAGE_END, postMessage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    @Override
    public void readMessage() {

    }

    @Override
    public void onClear() {
        if (!postMessageDisposable.isDisposed()) {
            postMessageDisposable.dispose();
        }
    }
}
