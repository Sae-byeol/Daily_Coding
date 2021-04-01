package com.example.dailycoding.ui;

import android.widget.ImageView;

public class NewsData {
    private String title;
    private String content;
    private String hash;
    private String review;
    private String course;
    private String imageUrl;
    private String link;
    public NewsData(String title, String content, String hash,String course, String review,String imageUrl,String link) {
        this.title = title;
        this.content = content;
        this.hash = hash;
        this.course=course;
        this.review=review;
        this.imageUrl=imageUrl;
        this.link=link;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
