package org.uva.sea.ql.parser.elements;

import org.uva.sea.ql.parser.elements.types.Type;

public abstract class ASTNode {

    public abstract Type getExprType();

    public abstract boolean checkType();
}
