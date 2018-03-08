package qlviz.model.booleanExpressions;

import org.antlr.v4.runtime.ParserRuleContext;
import qlviz.interpreter.linker.BooleanExpressionVisitor;
import qlviz.interpreter.linker.TypedBooleanExpressionVisitor;
import qlviz.model.Node;
import qlviz.model.numericExpressions.NumericExpression;

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
}
