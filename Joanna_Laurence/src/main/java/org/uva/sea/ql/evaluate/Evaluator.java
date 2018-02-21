package org.uva.sea.ql.evaluate;

import org.uva.sea.ql.parser.elements.ASTNode;

public interface Evaluator {
    ASTNode add(ASTNode lhs, ASTNode rhs);
    ASTNode div(ASTNode lhs, ASTNode rhs);
    ASTNode sub(ASTNode lhs, ASTNode rhs);
    ASTNode mul(ASTNode lhs, ASTNode rhs);

    ASTNode pos(ASTNode node);
    ASTNode neg(ASTNode node);
    ASTNode not(ASTNode node);

    ASTNode and(ASTNode lhs, ASTNode rhs);
    ASTNode eq(ASTNode lhs, ASTNode rhs);
    ASTNode gEq(ASTNode lhs, ASTNode rhs);
    ASTNode gThan(ASTNode lhs, ASTNode rhs);
    ASTNode lEq(ASTNode lhs, ASTNode rhs);
    ASTNode lThan(ASTNode lhs, ASTNode rhs);
    ASTNode nEq(ASTNode lhs, ASTNode rhs);
    ASTNode or(ASTNode lhs, ASTNode rhs);
}
