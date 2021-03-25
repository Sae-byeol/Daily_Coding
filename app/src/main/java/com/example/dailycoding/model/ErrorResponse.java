package com.example.dailycoding.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ErrorResponse {


    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("introduction")
    @Expose
    private String introduction;


    public String getMessage() {
        return title;
    }

    public void setMessage(String title) {
        this.title = title;
    }

    public String getPath() {
        return introduction;
    }

    public void setData(String introduction) {
        this.introduction = introduction;
    }



}