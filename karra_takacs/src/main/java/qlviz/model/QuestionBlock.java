package qlviz.model;

import java.util.List;

public class QuestionBlock {
    private List<Question> questions;
    private List<ConditionalBlock> blocks;

    public List<Question> getQuestions() {
        return questions;
    }

    public List<ConditionalBlock> getBlocks() {
        return blocks;
    }

    public QuestionBlock(List<Question> questions, List<ConditionalBlock> blocks) {
        this.questions = questions;
        this.blocks = blocks;
    }
}
