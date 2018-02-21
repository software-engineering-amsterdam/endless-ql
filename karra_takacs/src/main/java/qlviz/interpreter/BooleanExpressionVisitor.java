package qlviz.interpreter;

import qlviz.QLBaseVisitor;
import qlviz.QLParser;
import qlviz.model.*;

public class BooleanExpressionVisitor extends QLBaseVisitor<BooleanExpression> {

    private final QLBaseVisitor<NumericExpression> numericExpressionVisitor;
    private final BinaryBooleanOperatorTranslator binaryBooleanOperatorTranslator;
    private final NumericComparisonOperatorTranslator numericComparisonOperatorTranslator;

    public BooleanExpressionVisitor(QLBaseVisitor<NumericExpression> numericExpressionVisitor,
                                    BinaryBooleanOperatorTranslator binaryBooleanOperatorTranslator,
                                    NumericComparisonOperatorTranslator numericComparisonOperatorTranslator) {
        this.numericExpressionVisitor = numericExpressionVisitor;
        this.binaryBooleanOperatorTranslator = binaryBooleanOperatorTranslator;
        this.numericComparisonOperatorTranslator = numericComparisonOperatorTranslator;
    }

    @Override
    public BooleanExpression visitBooleanExpression(QLParser.BooleanExpressionContext ctx) {
        if (ctx.BOOLEAN() != null) {
           return new BooleanLiteral(ctx.BOOLEAN().getSymbol().getText().compareToIgnoreCase("true") == 0) ;
        }
        else if (ctx.booleanExpression().size() == 2) {
            BooleanExpression left = ctx.booleanExpression(0).accept(this);
            BooleanExpression right = ctx.booleanExpression(1).accept(this);
            BinaryBooleanOperator operator =
                    this.binaryBooleanOperatorTranslator.translate(ctx.BINARY_BOOLEAN_OPERATOR().getSymbol().getText());
            return new BinaryBooleanOperation(left, right, operator);
        }
        else if (ctx.booleanExpression().size() == 1) {
            BooleanExpression negatedExpression = ctx.booleanExpression(0).accept(this);
            return new Negation(negatedExpression);
        }
        else if (ctx.numericExpression().size() == 2) {
            NumericExpression left = ctx.numericExpression(0).accept(this.numericExpressionVisitor);
            NumericExpression right = ctx.numericExpression(1).accept(this.numericExpressionVisitor);
            NumericComparisonOperator operator = numericComparisonOperatorTranslator.translate(ctx.COMPARISON_OPERATOR().getSymbol().getText());
            return new NumericComparison(left, right, operator);
        }
        else if (ctx.IDENTIFIER() != null) {
            return new BooleanQuestionReference(ctx.IDENTIFIER().getText());
        }
        return null;
    }
}
