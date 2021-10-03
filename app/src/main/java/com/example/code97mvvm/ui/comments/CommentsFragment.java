package com.example.code97mvvm.ui.comments;

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

import com.example.code97mvvm.R;
import com.example.code97mvvm.databinding.FragmentCommentsBinding;
import com.example.code97mvvm.ui.comments.model.Root;
import com.example.code97mvvm.ui.comments.adapter.CommentsAdapter;
import com.example.code97mvvm.ui.comments.viewModel.CommentsViewModel;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class CommentsFragment extends Fragment {

    private CommentsViewModel commentsViewModel;
    private FragmentCommentsBinding binding;
    CommentsAdapter adapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        commentsViewModel = new ViewModelProvider(this).get(CommentsViewModel.class);
        binding = FragmentCommentsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));



        commentsViewModel.getTags().observe(getViewLifecycleOwner(), new Observer<Root>() {
            @Override
            public void onChanged(Root root) {


                if (root != null) {

                    binding.relativeLayout.setVisibility(View.GONE);

                    adapter = new CommentsAdapter(root.getData(), getContext());
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