package com.sync.docs.data.network.repository;

import android.arch.lifecycle.MutableLiveData;

import com.sync.docs.data.network.model.auth.AuthRequest;

public interface AuthMessage {
    void createAuthMessage(String baseUrl, AuthRequest authRequest);

    void readAuthMessage(String baseUrl, String requestId);

    MutableLiveData<String> getAuthMessage();

    void onClear();
}
