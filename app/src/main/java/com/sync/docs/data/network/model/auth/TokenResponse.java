package com.sync.docs.data.network.model.auth;

import com.google.gson.annotations.SerializedName;

public class TokenResponse {
    @SerializedName("AccessToken")
    private String accessToken;
    @SerializedName("ExpiresIn")
    private int expiresIn;
    @SerializedName("RefreshToken")
    private String refreshToken;
    @SerializedName("TokenType")
    private String tokenType;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
