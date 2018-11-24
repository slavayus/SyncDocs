package com.sync.docs.data.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Databases {
    @SerializedName("Databases")
    private List<Database> databases;

    public List<Database> getDatabases() {
        return databases;
    }

    public void setDatabases(List<Database> databases) {
        this.databases = databases;
    }

    @Override
    public String toString() {
        return "Databases{" +
                "databases=" + databases +
                '}';
    }
}
