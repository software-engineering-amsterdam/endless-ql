package qlviz.model;

import java.util.List;

public class ConditionalBlock {
    private BooleanExpression condition;
    private List<QuestionBlock> questionBlocks;

    public List<QuestionBlock> getQuestionBlocks() {
        return questionBlocks;
    }

    public BooleanExpression getCondition() {
        return condition;
    }

    public ConditionalBlock(BooleanExpression condition, List<QuestionBlock> questionBlocks) {
        this.condition = condition;
        this.questionBlocks = questionBlocks;
    }
}
