package org.uva.forcepushql.ast;

import org.uva.forcepushql.questions.Question;

public class QuestionNode extends Node
{

    private Node left;
    private Node right;
    private Node center;

    public void setLeft (Node left)     { this.left = left;     }
    public void setRight (Node right)   { this.right = right;   }
    public void setCenter(Node center)  { this.center = center; }

    public Node getLeft ()    {  return left;  }
    public Node getRight ()   {  return right; }
    public Node getCenter()   {  return center;}

    @Override
    public Question accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}