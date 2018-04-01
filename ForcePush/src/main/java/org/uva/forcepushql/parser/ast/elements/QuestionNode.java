package org.uva.forcepushql.parser.ast.elements;

import org.uva.forcepushql.parser.ast.visitors.ASTVisitor;
import org.uva.forcepushql.interpreter.gui.questions.Question;

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

    public QuestionNode(){
        super(false);
    }

    @Override
    public Question accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}