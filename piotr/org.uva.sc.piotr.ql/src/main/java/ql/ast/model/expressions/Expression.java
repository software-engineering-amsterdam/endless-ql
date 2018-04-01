package ql.ast.model.expressions;

import ql.ast.model.ASTNode;

abstract public class Expression extends ASTNode {
    public enum DataType {
        STRING,
        BOOLEAN,
        INTEGER,
        DECIMAL,
        MONEY,
        DATE
    }
    protected Expression(MetaInformation metaInformation) {
        super(metaInformation);
    }

    protected Expression() {
        super();
    }
}
