package com.sync.docs.data.network.repository;

import android.arch.lifecycle.MutableLiveData;

import com.sync.docs.data.network.model.message.Databases;

public interface Message {
    void createMessage(String url, String requestId);

    void readMessage(String fullUrl, String requestId);

    MutableLiveData<Databases> getDatabases();

    void onClear();
}
