package qlviz.model.expressions.booleanExpressions;

import org.antlr.v4.runtime.ParserRuleContext;
import qlviz.interpreter.linker.BooleanExpressionVisitor;
import qlviz.interpreter.linker.TypedBooleanExpressionVisitor;
import qlviz.model.Node;
import qlviz.model.expressions.ExpressionVisitor;
import qlviz.model.expressions.TypedExpressionVisitor;

public class BooleanLiteral extends Node implements BooleanExpression {

    private final boolean value;

    public BooleanLiteral(boolean value, ParserRuleContext context) {
        super(context);
        this.value = value;
    }

    public boolean getValue() {
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

    @Override
    public void accept(ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public <T> T accept(TypedExpressionVisitor<T> typedExpressionVisitor) {
        return typedExpressionVisitor.visit(this);
    }
}

