package qlviz.model.numericExpressions;

import org.antlr.v4.runtime.ParserRuleContext;
import qlviz.interpreter.linker.NumericExpressionVisitor;
import qlviz.interpreter.linker.TypedNumericExpressionVisitor;
import qlviz.model.Node;

import java.math.BigDecimal;

public class NumericNegation extends Node implements NumericExpression {

    private final NumericExpression innerExpression;

    public NumericNegation(NumericExpression innerExpression, ParserRuleContext context) {
        super(context);
        this.innerExpression = innerExpression;
    }


    @Override
    public void accept(NumericExpressionVisitor numericExpressionVisitor) {
        numericExpressionVisitor.visit(this);
    }

    @Override
    public <T> T accept(TypedNumericExpressionVisitor<T> numericExpressionVisitor) {
        return numericExpressionVisitor.visit(this);
    }

    public NumericExpression getInnerExpression() {
        return innerExpression;
    }
}
