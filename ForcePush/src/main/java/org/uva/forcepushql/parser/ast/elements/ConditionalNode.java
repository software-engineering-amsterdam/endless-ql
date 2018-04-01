package org.uva.forcepushql.parser.ast.elements;

import org.uva.forcepushql.interpreter.gui.JPanelGUI;
import org.uva.forcepushql.parser.ast.visitors.ASTVisitor;

import java.util.LinkedList;
import java.util.List;

public class ConditionalNode extends Node
{

    private Node condition;
    private LinkedList<Node> questions;
    private Node after;

    public ConditionalNode()
    {
        super(false);
        questions = new LinkedList<Node>();
    }

    @Override
    public List<JPanelGUI> accept(ASTVisitor visitor)
    {
        return visitor.visit(this);
    }

    public void setCondition(Node condition)
    {
        this.condition = condition;
    }

    public Node getCondition()
    {
        return condition;
    }

    public void addOneQuestion(Node question)
    {
        questions.add(question);
    }

    public List<Node> getQuestions()
    {
        return questions;
    }

    public Node getAfter()
    {
        return after;
    }

    public void setAfter(Node after)
    {
        this.after = after;
    }
}

