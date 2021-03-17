package com.example.dailycoding.ui;

import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable {
    private int id;

    private int problemNumber;
    private String title;
    private String content;
    private boolean isExpanded;

    public Course(){}

    public Course(String title, boolean isExpanded) {
        this.title = title;
        this.isExpanded = isExpanded;
    }

    public Course(String title, String content, boolean isExpanded) {
        this.title = title;
        this.content=content;
        this.isExpanded = isExpanded;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getProblemNumber() {
        return problemNumber;
    }

    public void setProblemNumber(int problemNumber) {
        this.problemNumber = problemNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }
}
