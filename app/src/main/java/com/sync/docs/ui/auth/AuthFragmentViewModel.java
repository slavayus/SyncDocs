package com.sync.docs.ui.auth;

import android.arch.lifecycle.ViewModel;
import android.util.Log;
import android.view.View;

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

    @Override
    protected void onCleared() {
        super.onCleared();
        messageRepository.onClear();
    }
}
