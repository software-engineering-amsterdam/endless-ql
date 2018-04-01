package org.uva.forcepushql.parser.ast.elements;

import org.uva.forcepushql.parser.ast.visitors.ASTVisitor;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public class FormNode extends Node
{
    private LinkedList<Node> questions;
    private String name;

    public FormNode()
    {
        super(false);
        questions = new LinkedList<Node>();
    }

    public List<Node> getQuestions()
    {
        return questions;
    }

    public void addOneQuestion(Node question)
    {
        if (question != null)
            questions.add(question);
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public List<JPanel> accept(ASTVisitor visitor)
    {
        return visitor.visit(this);
    }
}

