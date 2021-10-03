package com.example.code97mvvm.ui.comments.model;

public class Data
{
    private String id;

    private String message;

    private Owner owner;

    private String post;

    private String publishDate;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return this.message;
    }
    public void setOwner(Owner owner){
        this.owner = owner;
    }
    public Owner getOwner(){
        return this.owner;
    }
    public void setPost(String post){
        this.post = post;
    }
    public String getPost(){
        return this.post;
    }
    public void setPublishDate(String publishDate){
        this.publishDate = publishDate;
    }
    public String getPublishDate(){
        return this.publishDate;
    }
}