package QL.ParseObjectsQL;

import QL.QLVisitor.ExpressionTable;

import java.util.ArrayList;

public class Form extends ASTNode {
    private String name;
    private ArrayList<Question> questions;
    private ExpressionTable expressionTable;

    public Form(String name, ArrayList<Question> questions, ExpressionTable exprTable, int line){
        super(line);
        setName(name);
        setQuestions(questions);
        setExpressionTable(exprTable);
    }

    public void setExpressionTable(ExpressionTable exprTable){
        this.expressionTable = exprTable;
    }

    public ExpressionTable getExpressionTable(){
        return expressionTable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Question> getQuestions() { return questions; }

    public void setQuestions(ArrayList<Question> questions) { this.questions = questions;}

}