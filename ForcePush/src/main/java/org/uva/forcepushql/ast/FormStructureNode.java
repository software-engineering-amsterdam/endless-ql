package org.uva.forcepushql.ast;

import java.util.LinkedList;


public abstract class FormStructureNode extends Node{ }

class FormNode extends FormStructureNode{
    private LinkedList<Node> questions;
    private String name;

    public FormNode(){
        questions = new LinkedList<Node>();
    }

    public LinkedList<Node> getQuestions() {
        return questions;
    }

    public void setQuestions(LinkedList<Node> questions) {
        this.questions = questions;
    }

    public void setOneQuestion(Node question){
        if (question != null)
            questions.add(question);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class QuestionNode extends FormStructureNode{

    private Node Left;
    private Node Center;
    private Node Right;

    public void setLeft (Node left)   { this.Left = left; }
    public void setRight (Node right) { this.Right = right; }
    public void setCenter(Node center) { Center = center; }

    public Node getLeft ()    {  return this.Left;  }
    public Node getRight ()   {  return this.Right; }
    public Node getCenter() { return Center; }
}

class NameNode extends QuestionNode{
    private String name;

    public void setName (String name) { this.name = name; }

    public String getName ()          { return this.name;  }
}

class TypeNode extends QuestionNode{
    private String type;

    public void setType(String type) { this.type = type; }

    public String getType()          { return this.type;  }
}

class LabelNode extends QuestionNode{

    private String label;

    public void setLabel (String label) { this.label = label; }

    public String getLabel ()           { return this.label;  }
}

class MathNode extends QuestionNode{

}