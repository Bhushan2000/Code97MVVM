package com.example.code97mvvm.ui.tags.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.code97mvvm.ui.tags.model.RootTags;
import com.example.code97mvvm.ui.tags.repo.TagsRepo;

public class TagViewModel extends ViewModel {

    private MutableLiveData<RootTags> tagsDetails;
    private final TagsRepo tagsRepo;


    public TagViewModel() {
        tagsRepo = new TagsRepo();
    }


    public MutableLiveData<RootTags> getTags() {
        if (tagsDetails == null) {
            tagsDetails = tagsRepo.requestData();
        }
        return tagsDetails;

    }


    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
