package ql.ast.statement;

import ql.ast.QLNode;
import ql.visitors.interfaces.ExpressionVisitable;
import ql.visitors.interfaces.StatementVisitable;

public abstract class Statement extends QLNode implements StatementVisitable, ExpressionVisitable {

    public abstract String toString();
    
}
