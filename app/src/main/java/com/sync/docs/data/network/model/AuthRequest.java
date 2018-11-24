package com.sync.docs.data.network.model;

import com.google.gson.annotations.SerializedName;

public class AuthRequest {
    @SerializedName("TenantId")
    private String tenantId;
    @SerializedName("Login")
    private String login;
    @SerializedName("Password")
    private String password;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String isPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AuthRequest{" +
                "tenantId='" + tenantId + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}