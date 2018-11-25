package com.sync.docs.data.network.model.auth;

import com.google.gson.annotations.SerializedName;

public class SessionToken {
    @SerializedName("EmployeeId")
    private String tmployeeId;
    @SerializedName("TenantId")
    private String tenantId;

    public String getTmployeeId() {
        return tmployeeId;
    }

    public void setTmployeeId(String tmployeeId) {
        this.tmployeeId = tmployeeId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
