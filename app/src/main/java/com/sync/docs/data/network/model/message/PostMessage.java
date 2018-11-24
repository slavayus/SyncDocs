package com.sync.docs.data.network.model.message;

public class PostMessage {
    private String messageType;
    private String requestId;

    public PostMessage(String messageType, String requestId) {
        this.messageType = messageType;
        this.requestId = requestId;
    }

    public PostMessage() {
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
