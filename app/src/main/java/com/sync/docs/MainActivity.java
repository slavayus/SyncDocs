package com.sync.docs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sync.docs.ui.auth.AuthFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AuthFragment.startFragment(getSupportFragmentManager());
    }
}
