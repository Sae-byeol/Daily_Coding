package com.example.dailycoding.ui;

//세개의 선택지
//답 내용, 맞는 답인지 , 내가 선택한 답인지
public class WrongAnswerCorrect {
    private String answer;
    private boolean isCorrect;
    private boolean isChosen;

    public WrongAnswerCorrect(String answer, boolean isCorrect, boolean isChosen) {
        this.answer = answer;
        this.isCorrect = isCorrect;
        this.isChosen = isChosen;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public boolean isChosen() {
        return isChosen;
    }

    public void setChosen(boolean chosen) {
        isChosen = chosen;
    }
}
