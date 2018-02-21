package qlviz.interpreter;

import qlviz.QLBaseVisitor;
import qlviz.QLParser;
import qlviz.model.*;

import java.math.BigDecimal;

public class NumericExpressionVisitor extends QLBaseVisitor<NumericExpression> {


    private final BinaryNumericOperatorTranslator binaryNumericOperatorTranslator;

    public NumericExpressionVisitor(BinaryNumericOperatorTranslator binaryNumericOperatorTranslator) {
        this.binaryNumericOperatorTranslator = binaryNumericOperatorTranslator;
    }

    @Override
    public NumericExpression visitNumericExpression(QLParser.NumericExpressionContext ctx) {
        if (ctx.NUMBER() != null) {
           return new NumericLiteral(new BigDecimal(ctx.NUMBER().getText()));
        }
        else if (ctx.numericExpression().size() == 2) {
            NumericExpression left = ctx.numericExpression(0).accept(this);
            NumericExpression right = ctx.numericExpression(1).accept(this);
            BinaryNumericOperator operator =
                    this.binaryNumericOperatorTranslator.translate(ctx.BINARY_NUMERIC_OPERATOR().getSymbol().getText());
            return new BinaryNumericOperation(left, right, operator);
        }
        else if (ctx.numericExpression().size() == 1) {
            NumericExpression innerExpression = ctx.numericExpression(0).accept(this);
            return new NumericNegation(innerExpression);
        }
        else if (ctx.IDENTIFIER() != null) {
            return new NumericQuestionReference(ctx.IDENTIFIER().getText());
        }
        return null;
    }
}
