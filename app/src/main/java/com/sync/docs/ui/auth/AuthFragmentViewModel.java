package com.sync.docs.ui.auth;

import android.arch.lifecycle.ViewModel;
import android.util.Log;
import android.view.View;

public class AuthFragmentViewModel extends ViewModel {
    private static final String TAG = "AuthFragmentViewModel";

    public void onUpdateServerAddress(View view, boolean hasFocus){
        Log.d(TAG, "onUpdateServerAddress: "+hasFocus);
    }
}
