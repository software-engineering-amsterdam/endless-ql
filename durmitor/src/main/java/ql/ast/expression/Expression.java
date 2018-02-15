package ql.ast.expression;

import ql.ast.QLNode;
import ql.ast.type.Type;

public abstract class Expression extends QLNode {

    protected Type type;
    
    public abstract String toString();
    
}
