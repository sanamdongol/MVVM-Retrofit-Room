package com.test.mvvmretro.pagination;

public class User {
    String id;
    String author;
    String width;
    String url;


    public User(String id, String author, String width, String url) {
        this.id = id;
        this.author = author;
        this.width = width;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
