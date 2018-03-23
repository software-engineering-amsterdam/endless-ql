package qlviz.interpreter;

import com.google.inject.Inject;
import qlviz.QLBaseVisitor;
import qlviz.QLVisitor;
import qlviz.QLParser;
import qlviz.model.numericExpressions.*;
import qlviz.model.question.NumericQuestionReference;

import java.math.BigDecimal;

public class NumericExpressionParser extends QLBaseVisitor<NumericExpression> {


    private final BinaryNumericOperatorTranslator binaryNumericOperatorTranslator;

    @Inject
    public NumericExpressionParser(BinaryNumericOperatorTranslator binaryNumericOperatorTranslator) {
        this.binaryNumericOperatorTranslator = binaryNumericOperatorTranslator;
    }

    @Override
    public NumericExpression visitNumericExpression(QLParser.NumericExpressionContext ctx) {

        if (ctx.numericExpression().size() == 2) {
            NumericExpression left = ctx.numericExpression(0).accept(this);
            NumericExpression right = ctx.numericExpression(1).accept(this);
            var operatorString = ctx.MULTIPLICATIVE_OPERATION() != null
                                    ? ctx.MULTIPLICATIVE_OPERATION().getSymbol().getText()
                                    : ctx.ADDITIVE_OPERATION().getSymbol().getText();
            BinaryNumericOperator operator =
                    this.binaryNumericOperatorTranslator.translate(operatorString);
            return new BinaryNumericOperation(left, right, operator, ctx);
        }
        NumericExpression innerResult = null;
        if (ctx.NUMBER() != null) {
           innerResult = new NumericLiteral(new BigDecimal(ctx.NUMBER().getText()), ctx);
        } else if (ctx.PAREN_OPEN() != null && ctx.PAREN_CLOSE() != null) {
           innerResult = ctx.numericExpression(0).accept(this);
        }
        else if (ctx.IDENTIFIER() != null) {
           innerResult = new NumericQuestionReference(ctx.IDENTIFIER().getText());
        }

        if (ctx.ADDITIVE_OPERATION() != null) {
            if (ctx.ADDITIVE_OPERATION().getSymbol().getText().equals("-"))
            {
                return new NumericNegation(innerResult, ctx);
            }
        }
        return innerResult;
    }
}
