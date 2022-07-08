package com.zli.megaquiz.model;

public class Question {

    private String question;
    private String correctAnwser;
    private String wrongAnwser1;
    private String wrongAnwser2;
    private String wrongAnwser3;

    public Question() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnwser() {
        return correctAnwser;
    }

    public void setCorrectAnwser(String correctAnwser) {
        this.correctAnwser = correctAnwser;
    }

    public void setWrongAnwser1(String wrongAnwser1) {
        this.wrongAnwser1 = wrongAnwser1;
    }

    public String getWrongAnwser1() {
        return wrongAnwser1;
    }

    public void setWrongAnwser2(String wrongAnwser2) {
        this.wrongAnwser2 = wrongAnwser2;
    }

    public String getWrongAnwser2() {
        return wrongAnwser2;
    }

    public void setWrongAnwser3(String wrongAnwser3) {
        this.wrongAnwser3 = wrongAnwser3;
    }

    public String getWrongAnwser3() {
        return wrongAnwser3;
    }
}
