package qlviz.interpreter;

import qlviz.QLBaseVisitor;
import qlviz.QLParser;
import qlviz.model.numericExpressions.*;
import qlviz.model.question.NumericQuestionReference;

import java.math.BigDecimal;

public class NumericExpressionParser extends QLBaseVisitor<NumericExpression> {


    private final BinaryNumericOperatorTranslator binaryNumericOperatorTranslator;

    public NumericExpressionParser(BinaryNumericOperatorTranslator binaryNumericOperatorTranslator) {
        this.binaryNumericOperatorTranslator = binaryNumericOperatorTranslator;
    }

    @Override
    public NumericExpression visitNumericExpression(QLParser.NumericExpressionContext ctx) {
        if (ctx.NUMBER() != null) {
           return new NumericLiteral(new BigDecimal(ctx.NUMBER().getText()), ctx);
        }
        else if (ctx.numericExpression().size() == 2) {
            NumericExpression left = ctx.numericExpression(0).accept(this);
            NumericExpression right = ctx.numericExpression(1).accept(this);
            BinaryNumericOperator operator =
                    this.binaryNumericOperatorTranslator.translate(ctx.BINARY_NUMERIC_OPERATOR().getSymbol().getText());
            return new BinaryNumericOperation(left, right, operator, ctx);
        }
        else if (ctx.numericExpression().size() == 1)
        {
            // If we have parentheses, we know based on the grammar that we have one inner expression that we parse recursively.
            if (ctx.PAREN_OPEN() != null && ctx.PAREN_CLOSE() != null) {
                return ctx.numericExpression(0).accept(this);
            }
            // If we don't have parentheses, but only have one expression, we know that we're dealing with a negation
            else
            {
                NumericExpression innerExpression = ctx.numericExpression(0).accept(this);
                return new NumericNegation(innerExpression, ctx);
            }
        }
        else if (ctx.IDENTIFIER() != null) {
            return new NumericQuestionReference(ctx.IDENTIFIER().getText());
        }
        return null;
    }
}
