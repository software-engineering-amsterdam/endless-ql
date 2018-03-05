package ast.model;

import ast.visitors.ASTNodeVisitor;

public interface VisitableASTNode {
    void accept(ASTNodeVisitor visitor);
}
