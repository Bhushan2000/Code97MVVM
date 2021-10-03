package com.example.code97mvvm.ui.posts.model;

import java.util.List;

public class Root {
    public List<Datum> data;
    public int total;
    public int page;
    public int limit;

    public Root(List<Datum> data, int total, int page, int limit) {
        this.data = data;
        this.total = total;
        this.page = page;
        this.limit = limit;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
