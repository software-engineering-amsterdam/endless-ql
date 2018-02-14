package qlviz.model;

import java.util.List;

public class Form {
    private List<Question> questions;

    public Form(List<Question> questions) {
        this.questions = questions;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
