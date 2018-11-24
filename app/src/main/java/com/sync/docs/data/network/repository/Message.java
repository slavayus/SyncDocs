package com.sync.docs.data.network.repository;

import android.arch.lifecycle.MutableLiveData;

import com.sync.docs.data.network.model.message.Databases;

public interface Message {
    void createMessage(String baseUrl);

    void readMessage(String baseUrl);

    MutableLiveData<Databases> getDatabases();

    void onClear();
}
