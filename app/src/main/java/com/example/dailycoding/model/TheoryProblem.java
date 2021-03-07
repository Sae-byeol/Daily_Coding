package com.example.dailycoding.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TheoryProblem {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("problemNumber")
    @Expose
    private Integer problemNumber;
    @SerializedName("category")
    @Expose
    private String category;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProblemNumber() {
        return problemNumber;
    }

    public void setProblemNumber(Integer problemNumber) {
        this.problemNumber = problemNumber;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "TheoryProblem{" +
                "id=" + id +
                ", problemNumber=" + problemNumber +
                ", category='" + category + '\'' +
                '}';
    }
}
