package com.sync.docs.ui.auth;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sync.docs.R;
import com.sync.docs.databinding.FragmentAuthBinding;

public class AuthFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentAuthBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_auth, container, false);



        return binding.getRoot();
    }

    public static AuthFragment newInstance() {
        return new AuthFragment();
    }

    public static void startFragment(FragmentManager supportFragmentManager) {
        supportFragmentManager
                .beginTransaction()
                .replace(android.R.id.content, newInstance())
                .commit();
    }

}
