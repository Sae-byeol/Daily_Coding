package com.example.dailycoding.ui;

public class WrongAnswerData {
    private String text;
    private boolean isCorrect;
    private String content;
    private boolean isExpanded;

    public WrongAnswerData(String text, boolean isCorrect,String content,boolean isExpanded) {
        this.text = text;
        this.isCorrect = isCorrect;
        this.content=content;
        this.isExpanded=isExpanded;
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