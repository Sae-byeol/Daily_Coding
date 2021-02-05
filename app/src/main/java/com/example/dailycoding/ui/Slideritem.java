package com.example.dailycoding.ui;

public class Slideritem {
    private int image;
    private String text;
    Slideritem(int image, String text){
        this.image=image;
        this.text=text;
    }

    public int getImage() {
        return image;
    }

    public String getText() {
        return text;
    }
}