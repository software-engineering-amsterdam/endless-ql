package org.uva.forcepushql.parser.ast.elements.expressionnodes;

import org.uva.forcepushql.parser.ast.visitors.ASTExpressionVisitor;
import org.uva.forcepushql.parser.ast.visitors.ASTVisitor;
import org.uva.forcepushql.parser.ast.elements.ExpressionNode;

public class VariableNode extends ExpressionNode
{
    public String name;

    public VariableNode() {
        super(true);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
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
