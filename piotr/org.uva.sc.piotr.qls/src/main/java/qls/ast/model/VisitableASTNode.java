package qls.ast.model;

import qls.ast.visitors.ASTNodeVisitor;

public interface VisitableASTNode {
    <T> T accept(ASTNodeVisitor<T> visitor);
}
