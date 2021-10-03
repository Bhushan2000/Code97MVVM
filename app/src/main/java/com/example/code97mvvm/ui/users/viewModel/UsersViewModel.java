package com.example.code97mvvm.ui.users.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.code97mvvm.ui.users.createUser.CreateResponse;
import com.example.code97mvvm.ui.users.models.RootUsers;
import com.example.code97mvvm.ui.users.models.UsersResponse;
import com.example.code97mvvm.ui.users.repo.UsersRepo;
import com.example.code97mvvm.ui.users.updateUser.Root;

public class UsersViewModel extends ViewModel {
    private MutableLiveData<UsersResponse> users;
    private MutableLiveData<RootUsers> userDetails;
    private MutableLiveData<CreateResponse> userResponse;
    private MutableLiveData<com.example.code97mvvm.ui.users.deleteUser.Root> deleteUser;
    private MutableLiveData<Root> updateUser;


    private final UsersRepo usersRepo;


    public UsersViewModel() {
        usersRepo = new UsersRepo();
    }

    // get users
    public LiveData<UsersResponse> getUsers() {
        if (users == null) {
            users = usersRepo.requestData();

        }
        return users;

    }

    // get by id
    public LiveData<RootUsers> getUserById(String id) {
        if (userDetails == null) {

            userDetails = usersRepo.getUserById(id);

        }
        return userDetails;

    }

    // update by id
    public LiveData<Root> updateUser(String id, String firstName,String lastName) {
        if (updateUser == null) {

            updateUser = usersRepo.updateUser(id, firstName,lastName);

        }
        return updateUser;

    }


    // delete
    public LiveData<com.example.code97mvvm.ui.users.deleteUser.Root> deleteUser(String id) {
        if (deleteUser == null) {

            deleteUser = usersRepo.deleteUser(id);

        }
        return deleteUser;

    }

    // create
    public LiveData<CreateResponse> createUser(String firstName, String lastName, String email) {
        if (userResponse == null) {
            userResponse = usersRepo.createUser(firstName, lastName, email);
        }
        return userResponse;

    }


    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
