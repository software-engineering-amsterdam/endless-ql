import expression.*;

public class VisitorExpression extends QLBaseVisitor<Expression> {

    @Override
    public Expression visitOpExpr(QLParser.OpExprContext ctx) {
        // Inspired by: https://stackoverflow.com/a/23092428
        String op = ctx.op.getText();
        Expression left = visit(ctx.left);
        Expression right = visit(ctx.right);

        switch (op) {
            case "+": return new ExpressionArithmeticSum(left, right);
            case "-": return new ExpressionArithmeticSubtract(left, right);
            case "*": return new ExpressionArithmeticMultiply(left, right);
            case "/": return new ExpressionArithmeticDivide(left, right);
            default: throw new IllegalArgumentException("Unknown operator " + op);
        }
    }

    @Override
    public Expression visitNegExpr(QLParser.NegExprContext ctx) {
        Expression value = visit(ctx.expr);
        return new ExpressionNeg(value);
    }

    @Override
    public Expression visitNotExpr(QLParser.NotExprContext ctx) {
        Expression value = visit(ctx.expr);
        return new ExpressionNot(value);
    }

    @Override
    public Expression visitBoolExpr(QLParser.BoolExprContext ctx) {
        String op = ctx.op.getText();
        Expression left = visit(ctx.left);
        Expression right = visit(ctx.right);

        switch (op) {
            case ">": return new ExpressionComparisonGT(left, right);
            case ">=": return new ExpressionComparisonGE(left, right);
            case "<": return new ExpressionComparisonLT(left, right);
            case "<=": return new ExpressionComparisonLE(left, right);
            default: throw new IllegalArgumentException("Unknown operator " + op);
        }
    }

    @Override
    public Expression visitCompExpr(QLParser.CompExprContext ctx) {
        String op = ctx.op.getText();
        Expression left = visit(ctx.left);
        Expression right = visit(ctx.right);

        switch (op) {
            case "==": return new ExpressionComparisonEq(left, right);
            case "!=": return new ExpressionNot(new ExpressionComparisonEq(left, right));
            default: throw new IllegalArgumentException("Unknown operator " + op);
        }
    }

    @Override
    public Expression visitAndOrExpr(QLParser.AndOrExprContext ctx) {
        String op = ctx.op.getText();
        Expression left = visit(ctx.left);
        Expression right = visit(ctx.right);

        switch (op) {
            case "&&": return new ExpressionBinaryLogicalAnd(left, right);
            case "||": return new ExpressionBinaryLogicalOr(left, right);
            default: throw new IllegalArgumentException("Unknown operator " + op);
        }
    }


    @Override
    public Expression visitConstant_integer(QLParser.Constant_integerContext ctx) {
        // TODO do we have to use integer? what if we do a sum of int + double?
        return new ExpressionVariableInteger(Integer.valueOf(ctx.getText()));
    }

    @Override
    public Expression visitConstant_decimal(QLParser.Constant_decimalContext ctx) {
        return new ExpressionVariableDecimal(Double.valueOf(ctx.getText()));
    }

    @Override
    public Expression visitConstant_date(QLParser.Constant_dateContext ctx) {
        return new ExpressionVariableDate(ctx.getText());
    }

    @Override
    public Expression visitConstant_money(QLParser.Constant_moneyContext ctx) {
        // TODO: Same as decimal?
        return new ExpressionVariableDecimal(Double.valueOf(ctx.getText()));
    }

    @Override
    public Expression visitConstant_string(QLParser.Constant_stringContext ctx) {
        String textWithQuotes = ctx.getText();
        return new ExpressionVariableString(textWithQuotes.substring(1, textWithQuotes.length() - 1));
    }

    // TODO do we need this?
//    @Override
//    public Expression visitIdentifier(QLParser.IdentifierContext ctx) {
//        return new ExpressionIdentifier(ctx.getText());
//    }


    @Override
    public Expression visitParenExpr(QLParser.ParenExprContext ctx) {
        return super.visit(ctx.expression());
    }

    @Override
    public Expression visitConstant_identifier(QLParser.Constant_identifierContext ctx) {
        return new ExpressionIdentifier(ctx.getText());
    }
}
