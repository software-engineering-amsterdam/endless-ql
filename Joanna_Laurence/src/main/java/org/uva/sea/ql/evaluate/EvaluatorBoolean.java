package org.uva.sea.ql.evaluate;

import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.parser.elements.types.Bool;

public class EvaluatorBoolean implements Evaluator {

    @Override
    public ASTNode add(ASTNode lhs, ASTNode rhs) {
        return null;
    }

    @Override
    public ASTNode div(ASTNode lhs, ASTNode rhs) {
        return null;
    }

    @Override
    public ASTNode sub(ASTNode lhs, ASTNode rhs) {
        return null;
    }

    @Override
    public ASTNode mul(ASTNode lhs, ASTNode rhs) {
        return null;
    }

    @Override
    public ASTNode pos(ASTNode node) {
        return new Bool(node.getToken(),true);
    }

    @Override
    public ASTNode neg(ASTNode node) {
        return new Bool(node.getToken(),false);
    }

    @Override
    public ASTNode not(ASTNode node) {
        return new Bool(node.getToken(),!((Bool)node).isTrue());
    }

    @Override
    public ASTNode and(ASTNode lhs, ASTNode rhs) {
        return new Bool(lhs.getToken(),((Bool)lhs).isTrue() == ((Bool)rhs).isTrue());
    }

    @Override
    public ASTNode eq(ASTNode lhs, ASTNode rhs) {
        return new Bool(lhs.getToken(),((Bool)lhs).isTrue() == ((Bool)rhs).isTrue());
    }

    @Override
    public ASTNode gEq(ASTNode lhs, ASTNode rhs) {
        return null;
    }

    @Override
    public ASTNode gThan(ASTNode lhs, ASTNode rhs) {
        return null;
    }

    @Override
    public ASTNode lEq(ASTNode lhs, ASTNode rhs) {
        return null;
    }

    @Override
    public ASTNode lThan(ASTNode lhs, ASTNode rhs) {
        return null;
    }

    @Override
    public ASTNode nEq(ASTNode lhs, ASTNode rhs) {
        return new Bool(lhs.getToken(),((Bool)lhs).isTrue() != ((Bool)rhs).isTrue());
    }

    @Override
    public ASTNode or(ASTNode lhs, ASTNode rhs) {
        return new Bool(lhs.getToken(),((Bool)lhs).isTrue() || ((Bool)rhs).isTrue());
    }
}
