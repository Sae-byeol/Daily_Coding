package com.example.dailycoding.ui;

import java.util.ArrayList;

public class Course {
//    private int id;
    private String title;
    private ArrayList<String> content;
    private boolean isExpanded;

    public Course(){}

    public Course(String title, ArrayList<String> content, boolean isExpanded) {
        this.title = title;
        this.content = content;
        this.isExpanded = isExpanded;
    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getContent() {
        return content;
    }

    public void setContent(ArrayList<String> content) {
        this.content = content;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }
}
