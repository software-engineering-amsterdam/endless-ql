package model.expression;

import evaluation.IASTVisitor;
import model.Node;
import org.antlr.v4.runtime.Token;

public abstract class Expression extends Node {

    Expression(Token start) {
        super(start);
    }

    public abstract <T> T accept(IASTVisitor<T> visitor);

}