package org.uva.forcepushql.ast;

public abstract class FormNode { }

abstract class QuestionNode extends FormNode{

    private ExpressionNode Left;
    private ExpressionNode Right;

    public void setLeft (ExpressionNode left)   { this.Left = left; }
    public void setRight (ExpressionNode right) { this.Right = right; }

    public ExpressionNode getLeft ()    {  return this.Left;  }
    public ExpressionNode getRight ()   {  return this.Right; }
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