package com.example.code97mvvm.ui.posts.updatePosts;

import java.util.Date;
import java.util.List;

public class Root {

    public String id;
    public String image;
    public String likes;
    public Object link;
    public List<String> tags;
    public String text;
    public Date publishDate;
    public Owner owner;

    public Root(String id, String image, String likes, Object link, List<String> tags, String text, Date publishDate, Owner owner) {
        this.id = id;
        this.image = image;
        this.likes = likes;
        this.link = link;
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

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public Object getLink() {
        return link;
    }

    public void setLink(Object link) {
        this.link = link;
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
