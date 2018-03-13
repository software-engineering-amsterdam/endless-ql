package ql.model.expression;

import ql.evaluation.IExpressionVisitor;
import ql.model.Node;
import org.antlr.v4.runtime.Token;

public abstract class Expression extends Node {

    Expression(Token start) {
        super(start);
    }

    public abstract <T> T accept(IExpressionVisitor<T> visitor);
}