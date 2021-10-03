package com.example.code97mvvm.ui.users;

import static com.example.code97mvvm.util.Constants.isAllFabsVisible;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.code97mvvm.databinding.FragmentUsersBinding;
import com.example.code97mvvm.ui.users.adapter.UsersAdapter;
import com.example.code97mvvm.ui.users.models.UsersResponse;
import com.example.code97mvvm.ui.users.viewModel.UsersViewModel;


public class UsersFragment extends Fragment {

    private UsersViewModel usersViewModel;
    private FragmentUsersBinding binding;
    UsersAdapter usersAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        usersViewModel =
                new ViewModelProvider(this).get(UsersViewModel.class);

        binding = FragmentUsersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();






        binding.recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        usersViewModel.getUsers().observe(getViewLifecycleOwner(), new Observer<UsersResponse>() {
            @Override
            public void onChanged(UsersResponse usersResponse) {

                if (usersResponse !=null){
                    binding.relativeLayout.setVisibility(View.GONE);
                    usersAdapter = new UsersAdapter(usersResponse.data, getContext());
                    binding.recyclerview.setAdapter(usersAdapter);
                    usersAdapter.notifyDataSetChanged();
                }else{
                    binding.relativeLayout.setVisibility(View.GONE);
                    Toast.makeText(getContext(),"Error While fetching data",Toast.LENGTH_LONG).show();
                }
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        binding = null;
    }
}