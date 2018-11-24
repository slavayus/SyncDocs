package com.sync.docs.data.network.repository;

import com.sync.docs.data.network.model.auth.AuthRequest;

public interface AuthMessage {
    void createAuthMessage(String baseUrl, AuthRequest authRequest);

    void readAuthMessage(String fullUrl, String requestId);

    void onClear();
}
