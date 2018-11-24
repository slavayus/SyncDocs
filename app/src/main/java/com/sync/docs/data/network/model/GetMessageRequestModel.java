package com.sync.docs.data.network.model;

import com.google.gson.annotations.SerializedName;

public class GetMessageRequestModel {
    @SerializedName("RequestId")
    private String requestId;

    public GetMessageRequestModel() {
    }

    public GetMessageRequestModel(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
