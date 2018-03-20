package ast.model;

import ast.visitors.ASTNodeVisitor;

public interface VisitableASTNode {
    <T> T accept(ASTNodeVisitor<T> visitor);
}
