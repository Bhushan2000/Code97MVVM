package com.example.code97mvvm.ui.tags;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.code97mvvm.R;
import com.example.code97mvvm.databinding.TagsFragmentBinding;
import com.example.code97mvvm.ui.tags.adapter.TagsAdapter;
import com.example.code97mvvm.ui.tags.model.RootTags;
import com.example.code97mvvm.ui.tags.viewModel.TagViewModel;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.Objects;

public class TagsFragment extends Fragment {

    private TagViewModel mViewModel;

    TagsFragmentBinding binding;

    TagViewModel tagViewModel;
    TagsAdapter adapter;


    public static TagsFragment newInstance() {
        return new TagsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        tagViewModel = new ViewModelProvider(this).get(TagViewModel.class);

        binding = TagsFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        binding.recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        tagViewModel.getTags().observe(getViewLifecycleOwner(), new Observer<RootTags>() {
            @Override
            public void onChanged(RootTags rootTags) {

                if (rootTags != null) {
                    binding.relativeLayout.setVisibility(View.GONE);
                    adapter = new TagsAdapter(rootTags);
                    binding.recyclerview.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                } else {
                    binding.relativeLayout.setVisibility(View.GONE);
                    Toast.makeText(getContext().getApplicationContext(), "Error While fetching data", Toast.LENGTH_LONG).show();

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