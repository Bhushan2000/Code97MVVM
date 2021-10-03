package com.example.code97mvvm.ui.comments.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.code97mvvm.ui.comments.repo.CommentsRepo;
import com.example.code97mvvm.ui.comments.model.Root;

public class CommentsViewModel extends ViewModel {

    private MutableLiveData<Root> postDetails;
    private final CommentsRepo commentsRepo;


    public CommentsViewModel() {
        commentsRepo = new CommentsRepo();
    }


    public MutableLiveData<Root> getTags() {
        if (postDetails == null) {
            postDetails = commentsRepo.requestData();
        }
        return postDetails;

    }


    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
