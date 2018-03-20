package org.uva.forcepushql.ast;

import java.util.LinkedList;

public abstract class ConditionalNode extends Node {

    private Node condition;
    private LinkedList<Node> questions;
    private Node after;

    public ConditionalNode() {
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

    public void setOneQuestion(Node question) {
        questions.add(question);
    }

    public LinkedList<Node> getQuestions() {
        return questions;
    }

    public Node getAfter() {
        return after;
    }

    public void setAfter(Node after) {
        this.after = after;
    }
}

class ConditionalIfNode extends ConditionalNode {


}

class ConditionalIfElseNode extends ConditionalNode {

}

class ConditionalElseNode extends ConditionalNode{

}