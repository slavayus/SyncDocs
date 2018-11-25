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
import android.widget.AdapterView;
import android.widget.Toast;

import com.sync.docs.R;
import com.sync.docs.data.network.model.auth.AuthRequest;
import com.sync.docs.data.network.model.message.Database;
import com.sync.docs.data.network.repository.AuthMessageImpl;
import com.sync.docs.data.network.repository.MessageImpl;
import com.sync.docs.databinding.FragmentAuthBinding;

public class AuthFragment extends Fragment {
    public static final String TAG = "AuthFragment";
    private FragmentAuthBinding binding;
    private AuthRequest authRequest;
    private DatabaseAdapter databaseAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_auth, container, false);

        initViewModel();

        initSpinnerAdapter();

        return binding.getRoot();
    }

    private void initSpinnerAdapter() {
        databaseAdapter = new DatabaseAdapter(getContext(), android.R.layout.simple_spinner_item);
        binding.database.setAdapter(databaseAdapter);
        binding.database.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Database item = databaseAdapter.getItem(position);
                if (item != null) {
                    authRequest.setTenantId(item.getDatabaseId());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
            if (databases != null && databases.getDatabases() != null) {
                authRequest.setTenantId(databases.getDatabases().size() == 0 ? "" : databases.getDatabases().get(0).getDatabaseId());
                databaseAdapter.setDatabases(databases.getDatabases());
            }
        });
        viewModel.getAuthMessages().observe(this, message -> {
            viewModel.getProgressBarVisibility().set(View.GONE);
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
