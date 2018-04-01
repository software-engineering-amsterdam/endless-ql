package org.uva.forcepushql.parser.ast.elements.expressionnodes;

import org.uva.forcepushql.parser.ast.visitors.ASTExpressionVisitor;
import org.uva.forcepushql.parser.ast.visitors.ASTVisitor;
import org.uva.forcepushql.parser.ast.elements.ExpressionNode;

public class DecimalNode extends ExpressionNode
{
    private double value;

    public DecimalNode() {
        super(false);
    }

    public void setValue(double value)
    {
        this.value = value;
    }

    public double getValue()
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
