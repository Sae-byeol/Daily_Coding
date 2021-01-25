package com.example.dailycoding.ui;

public class NewsData {
    private String title;
    private String content;
    private String review;

    public NewsData(String title, String content, String review) {
        this.title = title;
        this.content = content;
        this.review = review;
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

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
