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
    private Databases message;

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

    public Databases getMessage() {
        return message;
    }

    public void setMessage(Databases message) {
        this.message = message;
    }
}
