package org.uva.forcepushql.parser.ast.elements;

import org.uva.forcepushql.parser.ast.visitors.ASTVisitor;
import org.uva.forcepushql.interpreter.gui.questions.Question;

public class QuestionAssignValueNode extends Node
{

    private Node previous;
    private Node expression;

    public QuestionAssignValueNode(){
        super(false);
    }

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
