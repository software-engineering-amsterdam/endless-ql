package org.uva.sea.ql.evaluate;

import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.parser.elements.types.Bool;
import org.uva.sea.ql.parser.elements.types.Dec;

public class EvaluatorDecimal implements Evaluator {

    @Override
    public ASTNode add(ASTNode lhs, ASTNode rhs) {
        return new Dec(((Dec)lhs).getValue() + ((Dec)rhs).getValue());
    }

    @Override
    public ASTNode div(ASTNode lhs, ASTNode rhs) {
        double rhsValue = ((Dec) rhs).getValue();
        if(rhsValue == 0)
            return null;

        return new Dec(((Dec)lhs).getValue() / rhsValue);
    }

    @Override
    public ASTNode sub(ASTNode lhs, ASTNode rhs) {
        return new Dec(((Dec)lhs).getValue() - ((Dec)rhs).getValue());
    }

    @Override
    public ASTNode mul(ASTNode lhs, ASTNode rhs) {
        return new Dec(((Dec)lhs).getValue() * ((Dec)rhs).getValue());
    }

    @Override
    public ASTNode pos(ASTNode node) {
        return new Dec(+((Dec)node).getValue());
    }

    @Override
    public ASTNode neg(ASTNode node) {
        return new Dec(-((Dec)node).getValue());
    }

    @Override
    public ASTNode not(ASTNode node) {
        return null;
    }

    @Override
    public ASTNode and(ASTNode lhs, ASTNode rhs) {
        return new Bool(((Dec)lhs).getValue() != 0 && ((Dec)rhs).getValue() != 0);
    }

    @Override
    public ASTNode eq(ASTNode lhs, ASTNode rhs) {
        return new Bool(((Dec)lhs).getValue() == ((Dec)rhs).getValue());
    }

    @Override
    public ASTNode gEq(ASTNode lhs, ASTNode rhs) {
        return new Bool(((Dec)lhs).getValue() >= ((Dec)rhs).getValue());
    }

    @Override
    public ASTNode gThan(ASTNode lhs, ASTNode rhs) {
        return new Bool(((Dec)lhs).getValue() > ((Dec)rhs).getValue());
    }

    @Override
    public ASTNode lEq(ASTNode lhs, ASTNode rhs) {
        return new Bool(((Dec)lhs).getValue() <= ((Dec)rhs).getValue());
    }

    @Override
    public ASTNode lThan(ASTNode lhs, ASTNode rhs) {
        return new Bool(((Dec)lhs).getValue() < ((Dec)rhs).getValue());
    }

    @Override
    public ASTNode nEq(ASTNode lhs, ASTNode rhs) {
        return new Bool(((Dec)lhs).getValue() != ((Dec)rhs).getValue());
    }

    @Override
    public ASTNode or(ASTNode lhs, ASTNode rhs) {
        return new Bool(((Dec)lhs).getValue() != 0 || ((Dec)rhs).getValue() != 0);
    }
}
