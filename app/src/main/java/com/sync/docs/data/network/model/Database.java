package com.sync.docs.data.network.model;

import com.google.gson.annotations.SerializedName;

public class Database {
    @SerializedName("DatabaseId")
    private String databaseId;
    @SerializedName("DisplayName")
    private String displayName;
    @SerializedName("IsDefault")
    private boolean isDefault;

    public String getDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(String databaseId) {
        this.databaseId = databaseId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }
}
