package ql.ast.model.statements;

import ql.ast.model.ASTNode;

public abstract class Statement extends ASTNode {
    Statement(MetaInformation metaInformation) {
        super(metaInformation);
    }

    Statement() {
        super();
    }
}