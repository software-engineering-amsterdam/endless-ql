package ast.model.statements;

import ast.model.ASTNode;

public abstract class Statement extends ASTNode {
    Statement(MetaInformation metaInformation) {
        super(metaInformation);
    }
}