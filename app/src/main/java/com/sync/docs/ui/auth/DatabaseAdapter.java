package com.sync.docs.ui.auth;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sync.docs.data.network.model.message.Database;

import java.util.ArrayList;
import java.util.List;

class DatabaseAdapter extends ArrayAdapter<Database> {
    private List<Database> data = new ArrayList<>();

    DatabaseAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    void setDatabases(List<Database> databases) {
        this.data = databases;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Nullable
    @Override
    public Database getItem(int position) {
        return data.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView label = (TextView) super.getView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        label.setText(data.get(position).getDisplayName());
        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        label.setText(data.get(position).getDisplayName());
        return label;
    }
}
