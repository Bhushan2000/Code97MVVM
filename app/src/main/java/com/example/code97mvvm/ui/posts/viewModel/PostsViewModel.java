package com.example.code97mvvm.ui.posts.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.code97mvvm.ui.posts.repo.PostsRepo;
import com.example.code97mvvm.ui.posts.model.Root;

import java.util.Map;

public class PostsViewModel extends ViewModel {

    private MutableLiveData<Root> postDetails;
    private MutableLiveData<com.example.code97mvvm.ui.posts.deletePosts.Root> postDelete;
    private MutableLiveData<com.example.code97mvvm.ui.posts.updatePosts.Root> postUpdate;
    private final PostsRepo postRepo;


    public PostsViewModel() {
        postRepo = new PostsRepo();
    }


    public LiveData<Root> getPosts() {
        if (postDetails == null) {
            postDetails = postRepo.requestData();
        }
        return postDetails;

    }

    public LiveData<com.example.code97mvvm.ui.posts.deletePosts.Root> deletePost(String id) {
        if (postDelete == null) {
            postDelete = postRepo.deletePost(id);
        }
        return postDelete;

    }
    public LiveData<com.example.code97mvvm.ui.posts.updatePosts.Root> updatingPost(String id, String like,String text,String tag0,String tag1,String tag2) {
        if (postUpdate == null) {
            postUpdate = postRepo.updatePost(id, like,text,tag0,tag1,tag2);
        }
        return postUpdate;

    }
//    public void onComplete(Result<Root> result){
//        if (result instanceof Result.Success){
//            // Happy Path
//
//        }else{
//            // show error on UI
//
//        }
//    }


    @Override
    protected void onCleared() {
        super.onCleared();
    }


}
