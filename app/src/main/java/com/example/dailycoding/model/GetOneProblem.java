package com.example.dailycoding.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetOneProblem {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("isTheory")
    @Expose
    private Boolean isTheory;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("problemNumber")
    @Expose
    private Integer problemNumber;
    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("option")
    @Expose
    private String option;
    @SerializedName("answer")
    @Expose
    private String answer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Boolean getIsTheory() {
        return isTheory;
    }

    public void setIsTheory(Boolean isTheory) {
        this.isTheory = isTheory;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getProblemNumber() {
        return problemNumber;
    }

    public void setProblemNumber(Integer problemNumber) {
        this.problemNumber = problemNumber;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "GetOneProblem{" +
                "id=" + id +
                ", language='" + language + '\'' +
                ", isTheory=" + isTheory +
                ", category='" + category + '\'' +
                ", problemNumber=" + problemNumber +
                ", question='" + question + '\'' +
                ", code='" + code + '\'' +
                ", option='" + option + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}