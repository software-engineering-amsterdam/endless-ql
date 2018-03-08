package ast.model.statements;

import ast.model.ASTNode;

public abstract class Statement extends ASTNode {
    public Statement(MetaInformation metaInformation) {
        super(metaInformation);
    }
}