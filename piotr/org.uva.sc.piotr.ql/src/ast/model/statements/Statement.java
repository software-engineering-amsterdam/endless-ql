package ast.model.statements;

import ast.model.ASTNode;

public abstract class Statement extends ASTNode {
    Statement(Integer startLine, Integer endLine) {
        super(startLine, endLine);
    }
}