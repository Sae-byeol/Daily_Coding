package com.example.dailycoding.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class News {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("isBook")
    @Expose
    private Boolean isBook;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("introduction")
    @Expose
    private String introduction;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("hashTag")
    @Expose
    private String hashTag;
    @SerializedName("contentOrder")
    @Expose
    private String contentOrder;
    @SerializedName("recommendation")
    @Expose
    private String recommendation;
    @SerializedName("link")
    @Expose
    private String link;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIsBook() {
        return isBook;
    }

    public void setIsBook(Boolean isBook) {
        this.isBook = isBook;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getHashTag() {
        return hashTag;
    }

    public void setHashTag(String hashTag) {
        this.hashTag = hashTag;
    }

    public String getContentOrder() {
        return contentOrder;
    }

    public void setContentOrder(String contentOrder) {
        this.contentOrder = contentOrder;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", isBook=" + isBook +
                ", title='" + title + '\'' +
                ", introduction='" + introduction + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", hashTag='" + hashTag + '\'' +
                ", contentOrder='" + contentOrder + '\'' +
                ", recommendation='" + recommendation + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
