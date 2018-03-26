package ql.model.expression;

import ql.IQLVisitor;

import ql.model.QLNode;
import org.antlr.v4.runtime.Token;

public abstract class Expression extends QLNode {

    public Expression(Token start) {
        super(start);
    }

    public abstract <T> T accept(IQLVisitor<T> visitor);
}