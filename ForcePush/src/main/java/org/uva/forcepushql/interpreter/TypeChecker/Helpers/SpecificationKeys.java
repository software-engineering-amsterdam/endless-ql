package org.uva.forcepushql.interpreter.TypeChecker.Helpers;

import org.uva.forcepushql.parser.ast.NodeTypes;

public class SpecificationKeys
{
    private final Class operator;
    private final NodeTypes leftType;
    private final NodeTypes rightType;

    public SpecificationKeys(Class operator, NodeTypes leftType, NodeTypes rightType)
    {
        this.operator = operator;
        this.leftType = leftType;
        this.rightType = rightType;
    }

    @Override
    public boolean equals(Object obj)
    {
        if ((obj instanceof SpecificationKeys))
        {
            SpecificationKeys key = (SpecificationKeys) obj;
            return this.operator.equals(key.operator) && (this.leftType == key.leftType) && (this.rightType == key.rightType);
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        return this.operator.hashCode() ^ this.leftType.hashCode() ^ this.rightType.hashCode();
    }
}

