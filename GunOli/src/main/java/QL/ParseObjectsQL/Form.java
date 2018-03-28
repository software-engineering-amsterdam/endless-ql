package QL.ParseObjectsQL;

import QL.QLVisitor.ExpressionTable;

import java.util.ArrayList;

public class Form extends ASTNode {
    private String name;
    private ArrayList<Question> questions;
    private ExpressionTable expressionTable;

    public Form(String name, ArrayList<Question> questions, ExpressionTable exprTable, int lineNumber){
        super(lineNumber);
        this.name = name;
        this.questions = questions;
        this.expressionTable = exprTable;
    }

    public ExpressionTable getExpressionTable(){
        return expressionTable;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Question> getQuestions() { return questions; }
}