package qlviz.model;

import org.antlr.v4.runtime.ParserRuleContext;
import qlviz.model.question.Question;

import java.util.List;

public class QuestionBlock extends Node {
    private List<Question> questions;
    private List<ConditionalBlock> blocks;

    public List<Question> getQuestions() {
        return questions;
    }

    public List<ConditionalBlock> getBlocks() {
        return blocks;
    }

    public QuestionBlock(List<Question> questions, List<ConditionalBlock> blocks, ParserRuleContext context) {
        super(context);
        this.questions = questions;
        this.blocks = blocks;
    }
}
