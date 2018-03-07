package qlviz.model.numericExpressions;

import org.antlr.v4.runtime.ParserRuleContext;
import qlviz.interpreter.linker.NumericExpressionVisitor;
import qlviz.interpreter.linker.TypedNumericExpressionVisitor;
import qlviz.model.Node;

import java.math.BigDecimal;

public class NumericLiteral extends Node implements NumericExpression {
    private final BigDecimal value;

    public NumericLiteral(BigDecimal value, ParserRuleContext context) {
        super(context);
        this.value = value;
    }

    public BigDecimal evaluate() {
        return this.value;
    }

    @Override
    public void accept(NumericExpressionVisitor numericExpressionVisitor) {
        numericExpressionVisitor.visit(this);
    }

    @Override
    public <T> T accept(TypedNumericExpressionVisitor<T> numericExpressionVisitor) {
        return numericExpressionVisitor.visit(this);
    }
}
