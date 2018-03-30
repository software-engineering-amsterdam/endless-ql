package qlviz.model.expressions.booleanExpressions;

import org.antlr.v4.runtime.ParserRuleContext;
import qlviz.interpreter.linker.BooleanExpressionVisitor;
import qlviz.interpreter.linker.TypedBooleanExpressionVisitor;
import qlviz.model.Node;
import qlviz.model.expressions.ExpressionVisitor;
import qlviz.model.expressions.TypedExpressionVisitor;
import qlviz.model.expressions.numericExpressions.NumericExpression;

public class NumericComparison extends Node implements BooleanExpression {

    private final NumericExpression leftSide;
    private final NumericExpression rightSide;
    private final NumericComparisonOperator opeartor;
    private NumericComparisonOperator operator;

    public NumericExpression getLeftSide() {
        return leftSide;
    }

    public NumericExpression getRightSide() {
        return rightSide;
    }

    public NumericComparison(NumericExpression leftSide, NumericExpression rightSide, NumericComparisonOperator opeartor, ParserRuleContext context) {
        super(context);
        this.leftSide = leftSide;
        this.rightSide = rightSide;
        this.opeartor = opeartor;
    }

    public void accept(BooleanExpressionVisitor visitor){
        visitor.visit(this);
    }

    @Override
    public <T> T accept(TypedBooleanExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public NumericComparisonOperator getOperator() {
        return operator;
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
