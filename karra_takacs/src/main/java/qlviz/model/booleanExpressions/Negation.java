package qlviz.model.booleanExpressions;

import org.antlr.v4.runtime.ParserRuleContext;
import qlviz.interpreter.linker.BooleanExpressionVisitor;
import qlviz.interpreter.linker.TypedBooleanExpressionVisitor;
import qlviz.model.Node;

public class Negation extends Node implements BooleanExpression {
    private final BooleanExpression operand;

    @Override
    public void accept(BooleanExpressionVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public <T> T accept(TypedBooleanExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public Negation(BooleanExpression operand, ParserRuleContext context) {
        super(context);
        this.operand = operand;
    }

    public BooleanExpression getOperand() {
        return operand;
    }
}

