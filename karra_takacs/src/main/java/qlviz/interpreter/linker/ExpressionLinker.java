package qlviz.interpreter.linker;

import qlviz.model.expressions.booleanExpressions.BooleanExpression;
import qlviz.model.expressions.ExpressionVisitor;
import qlviz.model.expressions.numericExpressions.NumericExpression;

public class ExpressionLinker implements ExpressionVisitor {

    private final BooleanExpressionLinker booleanExpressionLinker;
    private final NumericExpressionLinker numericExpressionLinker;


    public ExpressionLinker(BooleanExpressionLinker booleanExpressionLinker, NumericExpressionLinker numericExpressionLinker) {
        this.booleanExpressionLinker = booleanExpressionLinker;
        this.numericExpressionLinker = numericExpressionLinker;
    }

    @Override
    public void visit(NumericExpression numericExpression) {
        numericExpression.accept(this.numericExpressionLinker);
    }

    @Override
    public void visit(BooleanExpression booleanExpression) {
        booleanExpression.accept(this.booleanExpressionLinker);
    }
}
