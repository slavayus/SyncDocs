package com.sync.docs.data.network.model;

import com.google.gson.annotations.SerializedName;

public class GetMessage {
    @SerializedName("Id")
    private String id;
    @SerializedName("RequestId")
    private String requestId;
    @SerializedName("MessageType")
    private String messageType;
    @SerializedName("SettingsHash")
    private int settingsHash;
    @SerializedName("SessionToken")
    private String sessionToken;
    @SerializedName("Message")
    private String message;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public int getSettingsHash() {
        return settingsHash;
    }

    public void setSettingsHash(int settingsHash) {
        this.settingsHash = settingsHash;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "GetMessage{" +
                "id='" + id + '\'' +
                ", requestId='" + requestId + '\'' +
                ", messageType='" + messageType + '\'' +
                ", settingsHash=" + settingsHash +
                ", sessionToken='" + sessionToken + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
