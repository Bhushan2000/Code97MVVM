package com.example.code97mvvm.ui.posts;

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

import com.example.code97mvvm.databinding.FragmentPostsBinding;
import com.example.code97mvvm.ui.posts.model.Root;
import com.example.code97mvvm.ui.posts.adapter.PostsAdapter;
import com.example.code97mvvm.ui.posts.viewModel.PostsViewModel;

public class PostsFragment extends Fragment {

    private PostsViewModel postsViewModel;
    private FragmentPostsBinding binding;
    PostsAdapter adapter;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        postsViewModel =
                new ViewModelProvider(this).get(PostsViewModel.class);

        binding = FragmentPostsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        binding.recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        postsViewModel = new ViewModelProvider(this).get(PostsViewModel.class);

        postsViewModel.getPosts().observe(getViewLifecycleOwner(), new Observer<Root>() {
            @Override
            public void onChanged(Root root) {
                if (root != null) {

                    binding.relativeLayout.setVisibility(View.GONE);
                    adapter = new PostsAdapter(root.getData(), getContext());
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