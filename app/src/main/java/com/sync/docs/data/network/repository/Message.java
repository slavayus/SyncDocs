package com.sync.docs.data.network.repository;

public interface Message {
    void createMessage(String url, String requestId);

    void readMessage();

    void onClear();
}
