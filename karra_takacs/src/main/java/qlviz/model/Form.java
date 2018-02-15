package qlviz.model;

import java.util.List;

public class Form {
    private List<QuestionBlock> questions;

    public Form(List<QuestionBlock> questions) {
        this.questions = questions;
    }

    public List<QuestionBlock> getQuestions() {
        return questions;
    }
}
