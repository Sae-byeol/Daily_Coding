package com.example.dailycoding.ui;

import android.widget.ImageView;

public class NewsData {
    private String title;
    private String content;
    private String hash;
    private String review;
    private String course;
    private ImageView imageView;

    public NewsData(String title, String content, String hash) {
        this.title = title;
        this.content = content;
        this.hash = hash;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String review) {
        this.hash = review;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

}
