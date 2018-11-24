package com.sync.docs.data.network.model.auth;

import com.google.gson.annotations.SerializedName;

public class PostAuthMessage {
    @SerializedName("MessageType")
    private String messageType;
    @SerializedName("RequestId")
    private String requestId;
    @SerializedName("Message")
    private String message;

    public PostAuthMessage() {
    }

    public PostAuthMessage(String messageType, String requestId, String message) {
        this.messageType = messageType;
        this.requestId = requestId;
        this.message = message;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
