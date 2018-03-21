package ql.ast.model;

import ql.ast.visitors.ASTNodeVisitor;

public interface VisitableASTNode {
    <T> T accept(ASTNodeVisitor<T> visitor);
}
