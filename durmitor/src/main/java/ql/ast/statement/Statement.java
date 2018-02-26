package ql.ast.statement;

import ql.ast.QLNode;
import ql.visitors.interfaces.StatementVisitable;

public abstract class Statement extends QLNode implements StatementVisitable {

    public abstract String toString();
    
}
