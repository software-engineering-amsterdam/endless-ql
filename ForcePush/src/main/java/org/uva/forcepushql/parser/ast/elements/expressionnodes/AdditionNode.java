package org.uva.forcepushql.parser.ast.elements.expressionnodes;

import org.uva.forcepushql.parser.ast.visitors.ASTExpressionVisitor;
import org.uva.forcepushql.parser.ast.visitors.ASTVisitor;
import org.uva.forcepushql.parser.ast.elements.InfixExpressionNode;

public class AdditionNode extends InfixExpressionNode
{
    public AdditionNode(){
        super(false);
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
