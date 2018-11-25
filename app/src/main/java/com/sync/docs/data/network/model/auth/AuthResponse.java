package com.sync.docs.data.network.model.auth;

import com.google.gson.annotations.SerializedName;

public class AuthResponse {
    @SerializedName("SessionToken")
    private SessionToken sessionToken;
    @SerializedName("JwtToken")
    private TokenResponse jwtToken;
    @SerializedName("Message")
    private String message;

    public SessionToken getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(SessionToken sessionToken) {
        this.sessionToken = sessionToken;
    }

    public TokenResponse getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(TokenResponse jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
