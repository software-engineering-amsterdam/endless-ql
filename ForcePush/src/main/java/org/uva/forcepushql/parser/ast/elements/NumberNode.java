package org.uva.forcepushql.parser.ast.elements;

import org.uva.forcepushql.parser.ast.visitors.ASTExpressionVisitor;
import org.uva.forcepushql.parser.ast.visitors.ASTVisitor;

public class NumberNode extends ExpressionNode
{
    private int value;

    public NumberNode() {
        super(false);
    }

    public void setValue(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return this.value;
    }

    @Override
    public String accept(ASTVisitor visitor)
    {
        return visitor.visit(this);
    }

    @Override
    public Double accept(ASTExpressionVisitor visitor)
    {
        return visitor.visit(this);
    }
}
