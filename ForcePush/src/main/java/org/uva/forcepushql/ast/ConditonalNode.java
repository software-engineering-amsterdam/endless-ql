package org.uva.forcepushql.ast;

import java.util.LinkedList;

public abstract class ConditonalNode extends Node { }

class ConditionalIfNode extends ConditonalNode{

    private Node condition;
    private LinkedList<Node> questions;

    public ConditionalIfNode(){
        questions = new LinkedList<Node>();
    }

    public void setCondition(Node condition) {
        this.condition = condition;
    }

    public Node getCondition() {
        return condition;
    }

    public void setQuestions(LinkedList<Node> questions) {
        this.questions = questions;
    }

    public void setOneQuestion(Node question){
        questions.add(question);
    }

    public LinkedList<Node> getQuestions() {
        return questions;
    }
}