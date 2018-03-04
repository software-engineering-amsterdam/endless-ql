package qlviz.model.booleanExpressions;

import org.antlr.v4.runtime.ParserRuleContext;
import qlviz.interpreter.linker.BooleanExpressionVisitor;
import qlviz.interpreter.linker.TypedBooleanExpressionVisitor;
import qlviz.model.Node;

public class BooleanLiteral extends Node implements BooleanExpression {

    private final boolean value;

    public BooleanLiteral(boolean value, ParserRuleContext context) {
        super(context);
        this.value = value;
    }

    public boolean evaluate() {
        return this.value;
    }

    @Override
    public void accept(BooleanExpressionVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public <T> T accept(TypedBooleanExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

