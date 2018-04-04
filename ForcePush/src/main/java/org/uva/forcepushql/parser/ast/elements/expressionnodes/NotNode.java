package org.uva.forcepushql.parser.ast.elements.expressionnodes;

import org.uva.forcepushql.parser.ast.visitors.ASTExpressionVisitor;
import org.uva.forcepushql.parser.ast.visitors.ASTVisitor;
import org.uva.forcepushql.parser.ast.elements.ExpressionNode;

public class NotNode extends ExpressionNode
{
    private ExpressionNode InnerNode;

    public NotNode() {
        super(true);
    }

    public void setInnerNode(ExpressionNode innerNode)
    {
        this.InnerNode = innerNode;
    }

    public ExpressionNode getInnerNode()
    {
        return InnerNode;
    }

    @Override
    public String accept(ASTVisitor visitor)
    {
        return visitor.visit(this);
    }

    @Override
    public Boolean accept(ASTExpressionVisitor visitor)
    {
        return visitor.visit(this);
    }
}
