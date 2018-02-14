package qlviz.model;

import java.util.List;

public class QuestionBlock {
    private List<Question> questions;
    private List<QuestionBlock> blocks;

    public List<Question> getQuestions() {
        return questions;
    }

    public QuestionBlock(List<Question> questions) {
        this.questions = questions;
    }
}
