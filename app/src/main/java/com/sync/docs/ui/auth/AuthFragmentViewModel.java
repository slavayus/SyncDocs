package com.sync.docs.ui.auth;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;
import android.view.View;

import com.sync.docs.data.network.model.AuthRequest;
import com.sync.docs.data.network.model.Databases;
import com.sync.docs.data.network.repository.Message;

import java.util.UUID;

public class AuthFragmentViewModel extends ViewModel {
    private static final String TAG = "AuthFragmentViewModel";
    private Message messageRepository;
    private String requestId;

    void init(Message messageRepository) {
        this.messageRepository = messageRepository;
        requestId = UUID.randomUUID().toString();
        Log.d(TAG, "init: " + requestId);
    }

    public void onUpdateServerAddress(View view, boolean hasFocus) {
        if (!hasFocus) {
            createMessage();
        }
    }

    private void createMessage() {
        // TODO: 11/24/18 only for debug. remove hardcoded url
        messageRepository.createMessage("http://almaz2.digdes.com", requestId);
    }

    public void onClickEnter(AuthRequest authRequest) {
        Log.d(TAG, "onClickEnter: " + authRequest);
    }

    MutableLiveData<Databases> getDatabases() {
        return messageRepository.getDatabases();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        messageRepository.onClear();
    }
}
