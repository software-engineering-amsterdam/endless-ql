package qlviz.model;

import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public class Form extends Node {
    private final String title;
    private List<QuestionBlock> questions;

    public Form(String title, List<QuestionBlock> questions, ParserRuleContext context) {
        super(context);
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
