package com.example.dailycoding.ui;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class WrongAnswerData {
    private String text;
    private boolean isCorrect;
    private String content;
    private boolean isExpanded;
    //세개의 선택지
    private ArrayList<WrongAnswerCorrect> correctArrayList;

    public WrongAnswerData(String text, boolean isCorrect,String content,boolean isExpanded,ArrayList<WrongAnswerCorrect> correctArrayList) {
        this.text = text;
        this.isCorrect = isCorrect;
        this.content=content;
        this.isExpanded=isExpanded;
        this.correctArrayList=correctArrayList;
    }

    public ArrayList<WrongAnswerCorrect> getCorrectArrayList() {
        return correctArrayList;
    }

    public void setCorrectArrayList(ArrayList<WrongAnswerCorrect> correctArrayList) {
        this.correctArrayList = correctArrayList;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}
