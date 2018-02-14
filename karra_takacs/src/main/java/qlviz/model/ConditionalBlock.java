package qlviz.model;

import java.util.List;

public class ConditionalBlock {
    private List<QuestionBlock> questionBlocks;

    public List<QuestionBlock> getQuestionBlocks() {
        return questionBlocks;
    }

    public ConditionalBlock(List<QuestionBlock> questionBlocks) {
        this.questionBlocks = questionBlocks;
    }
}
