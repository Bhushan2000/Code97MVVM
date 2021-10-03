package com.example.code97mvvm.ui.posts.model;

import java.util.Date;
import java.util.List;

public class Datum {
    public String id;
    public String image;
    public int likes;
    public List<String> tags;
    public String text;
    public Date publishDate;
    public Owner owner;


    public Datum(String id, String image, int likes, List<String> tags, String text, Date publishDate, Owner owner) {
        this.id = id;
        this.image = image;
        this.likes = likes;
        this.tags = tags;
        this.text = text;
        this.publishDate = publishDate;
        this.owner = owner;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}

