package org.uva.forcepushql.ast;

import org.uva.forcepushql.questions.Question;

public class QuestionNode extends Node{

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