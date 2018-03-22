package qlviz.model;

import org.antlr.v4.runtime.ParserRuleContext;
import qlviz.model.booleanExpressions.BooleanExpression;

import java.util.List;

public class ConditionalBlock extends Node {
    private BooleanExpression condition;
    private List<QuestionBlock> questionBlocks;

    public List<QuestionBlock> getQuestionBlocks() {
        return questionBlocks;
    }

    public BooleanExpression getCondition() {
        return condition;
    }

    public ConditionalBlock(BooleanExpression condition, List<QuestionBlock> questionBlocks, ParserRuleContext context) {
        super(context);
        this.condition = condition;
        this.questionBlocks = questionBlocks;
    }
}
