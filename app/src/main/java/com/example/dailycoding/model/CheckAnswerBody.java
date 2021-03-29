package com.example.dailycoding.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckAnswerBody {
    @SerializedName("quest")
    @Expose
    private Integer quest;
    @SerializedName("answer")
    @Expose
    private Integer answer;
    @SerializedName("category")
    @Expose
    private String category;

    public CheckAnswerBody(Integer quest, Integer answer, String category) {
        this.quest = quest;
        this.answer = answer;
        this.category = category;
    }

    public Integer getQuest() {
        return quest;
    }

    public void setQuest(Integer quest) {
        this.quest = quest;
    }

    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(Integer answer) {
        this.answer = answer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
