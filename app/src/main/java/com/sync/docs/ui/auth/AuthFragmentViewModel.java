package com.sync.docs.ui.auth;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.view.View;
import android.widget.EditText;

import com.sync.docs.data.network.model.auth.AuthRequest;
import com.sync.docs.data.network.model.message.Databases;
import com.sync.docs.data.network.repository.AuthMessage;
import com.sync.docs.data.network.repository.Message;

public class AuthFragmentViewModel extends ViewModel {
    private static final String TAG = "AuthFragmentViewModel";
    private Message messageRepository;
    private AuthMessage authMessageRepository;
    private String baserUrl;

    void init(Message messageRepository, AuthMessage authMessage) {
        this.messageRepository = messageRepository;
        this.authMessageRepository = authMessage;
    }

    public void onUpdateServerAddress(View view, boolean hasFocus) {
        if (!hasFocus) {
            baserUrl = ((EditText) view).getText().toString();
            // TODO: 11/24/18 only for debug. remove hardcoded url
            baserUrl = "http://almaz2.digdes.com";
            createMessage();
        }
    }

    private void createMessage() {
        messageRepository.createMessage(baserUrl);
    }

    public void onClickEnter(AuthRequest authRequest) {
        // TODO: 11/24/18 check base url
        authMessageRepository.createAuthMessage(baserUrl, authRequest);
    }

    MutableLiveData<Databases> getDatabases() {
        return messageRepository.getDatabases();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        messageRepository.onClear();
        authMessageRepository.onClear();
    }

    MutableLiveData<String> getAuthMessages() {
        return authMessageRepository.getAuthMessage();
    }
}
