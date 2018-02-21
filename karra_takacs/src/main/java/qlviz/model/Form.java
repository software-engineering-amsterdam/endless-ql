package qlviz.model;

import java.util.List;

public class Form {
    private final String title;
    private List<QuestionBlock> questions;

    public Form(String title, List<QuestionBlock> questions) {
        this.title = title;
        this.questions = questions;
    }

    public List<QuestionBlock> getQuestions() {
        return questions;
    }

    public String getTitle() {
        return title;
    }
}
