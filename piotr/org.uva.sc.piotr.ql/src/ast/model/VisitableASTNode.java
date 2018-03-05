package ast.model;

import ast.visitors.ASTNodeVisitor;

public interface VisitableASTNode {
    public void accept(ASTNodeVisitor visitor);
}
