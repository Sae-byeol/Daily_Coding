package com.example.dailycoding.model;

public class LastProblemData {
    int userIdx, quest, answer, questIdx;
    String category, choiceAns, createdate;

    public int getUserIdx() {
        return userIdx;
    }

    public void setUserIdx(int userIdx) {
        this.userIdx = userIdx;
    }

    public int getQuest() {
        return quest;
    }

    public void setQuest(int quest) {
        this.quest = quest;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getQuestIdx() {
        return questIdx;
    }

    public void setQuestIdx(int questIdx) {
        this.questIdx = questIdx;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getChoiceAns() {
        return choiceAns;
    }

    public void setChoiceAns(String choiceAns) {
        this.choiceAns = choiceAns;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }
}
