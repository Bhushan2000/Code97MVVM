package com.example.code97mvvm.ui.posts.deletePosts;

public class Root {


    public String id;

    public String error;

    public Root(String id) {
        this.id = id;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

