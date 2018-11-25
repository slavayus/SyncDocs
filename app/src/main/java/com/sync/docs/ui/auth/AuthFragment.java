package com.sync.docs.ui.auth;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sync.docs.R;
import com.sync.docs.data.network.model.auth.AuthRequest;
import com.sync.docs.data.network.repository.AuthMessageImpl;
import com.sync.docs.data.network.repository.MessageImpl;
import com.sync.docs.databinding.FragmentAuthBinding;

public class AuthFragment extends Fragment {
    public static final String TAG = "AuthFragment";
    private FragmentAuthBinding binding;
    private AuthRequest authRequest;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_auth, container, false);

        initViewModel();

        return binding.getRoot();
    }

    private void initViewModel() {
        AuthFragmentViewModel viewModel = ViewModelProviders.of(this).get(AuthFragmentViewModel.class);
        viewModel.init(new MessageImpl(), new AuthMessageImpl());
        authRequest = new AuthRequest();
        binding.setAuthRequest(authRequest);
        binding.setViewModel(viewModel);
        initObservables(viewModel);
    }

    private void initObservables(AuthFragmentViewModel viewModel) {
        viewModel.getDatabases().observe(this, databases -> {
            if (databases != null) {
                binding.databaseEditText.setText(databases.getDatabases().get(0).getDatabaseId());
                authRequest.setTenantId(databases.getDatabases().get(0).getDatabaseId());
            }
        });
        viewModel.getAuthMessages().observe(this, message -> {
            if (message == null) {
                Toast.makeText(getContext(), R.string.authorization_was_successful, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
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
