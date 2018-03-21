package org.uva.forcepushql.ast;

import org.uva.forcepushql.questions.Question;

public abstract class QuestionTypeNode extends Node { }

class QuestionNode extends QuestionTypeNode{

    private Node left;
    private Node center;
    private Node right;

    public void setLeft (Node left)   { this.left = left; }
    public void setRight (Node right) { this.right = right; }
    public void setCenter(Node center) { this.center = center; }

    public Node getLeft ()    {  return this.left;  }
    public Node getRight ()   {  return this.right; }
    public Node getCenter() { return center; }

    @Override
    public Question accept(ASTVisitor visitor) {
            return visitor.visit(this);
    }
}

class QuestionAssignValueNode extends QuestionTypeNode{

    private Node previous;
    private Node expression;

    public Node getExpression() {
        return expression;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setExpression(Node expression) {
        this.expression = expression;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    @Override
    public Question accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }
}

class NameNode extends Node{
    private String name;

    public void setName (String name) { this.name = name; }

    public String getName ()          { return this.name;  }

    @Override
    public String accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }
}

class TypeNode extends Node{
    private String type;

    public void setType(String type) { this.type = type; }

    public String getType()          { return this.type;  }

    @Override
    public String accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }
}

class LabelNode extends Node{

    private String label;

    public void setLabel (String label) { this.label = label; }

    public String getLabel ()           { return this.label;  }

    @Override
    public String accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }
}

class MathNode extends QuestionNode{

}