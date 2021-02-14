package com.example.dailycoding.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class UserRank {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("star")
    @Expose
    private String star;
    @SerializedName("profileUrl")
    @Expose
    private Object profileUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public Object getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(Object profileUrl) {
        this.profileUrl = profileUrl;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }


}