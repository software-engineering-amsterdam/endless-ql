package model.expression;

import evaluation.IASTVisitor;

public abstract class Expression {

    public abstract <T> T accept(IASTVisitor<T> visitor);

}