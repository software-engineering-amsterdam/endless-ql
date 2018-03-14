package ast.model.expressions;

import ast.model.ASTNode;

abstract public class Expression extends ASTNode {
    public enum DataType {
        STRING,
        BOOLEAN,
        INTEGER,
        DECIMAL
    }
    protected Expression(MetaInformation metaInformation) {
        super(metaInformation);
    }
}
