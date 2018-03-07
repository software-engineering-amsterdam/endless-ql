package ast.model.expressions;

import ast.model.ASTNode;

abstract public class Expression extends ASTNode {

    public Expression(Integer startLine, Integer endLine) {
        super(startLine, endLine);
    }
}
