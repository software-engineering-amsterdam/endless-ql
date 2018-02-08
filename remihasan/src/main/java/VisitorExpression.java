public class VisitorExpression extends QLBaseVisitor<Expression> {


    @Override
    public Expression visitOpExpr(QLParser.OpExprContext ctx) {
        // Inspired by: https://stackoverflow.com/a/23092428
        String op = ctx.op.getText();
        Expression left = visit(ctx.left);
        Expression right = visit(ctx.right);

        switch (op) {
            case "+": return new ExpressionSum(left, right);
            case "-": return new ExpressionSubtract(left, right);
            case "*": return new ExpressionMultiply(left, right);
            case "/": return new ExpressionDivide(left, right);
            default: throw new IllegalArgumentException("Unknown operator " + op);
        }
    }

//    @Override
//    public Expression visitNegExpr(QLParser.NegExprContext ctx) {
//        return -1.0 * (Double) visit(ctx.expr);
//    }
//
//    @Override
//    public Expression visitNotExpr(QLParser.NotExprContext ctx) {
//        return !((Boolean) visit(ctx.expr));
//    }
//
//    @Override
//    public Expression visitBoolExpr(QLParser.BoolExprContext ctx) {
//        String op = ctx.op.getText();
//        Double left = (Double) visit(ctx.left);
//        Double right = (Double) visit(ctx.right);
//
//        switch (op) {
//            case ">": return left > right;
//            case ">=": return left >= right;
//            case "<": return left < right;
//            case "<=": return left <= right;
//            default: throw new IllegalArgumentException("Unknown operator " + op);
//        }
//    }
//
    @Override
    public Expression visitCompExpr(QLParser.CompExprContext ctx) {
        String op = ctx.op.getText();
        Expression left = visit(ctx.left);
        Expression right = visit(ctx.right);

        switch (op) {
            case "==": return new ExpressionEq(left, right);
            case "!=": return new ExpressionNeg(new ExpressionEq(left, right));
            default: throw new IllegalArgumentException("Unknown operator " + op);
        }
    }
//
//    @Override
//    public Expression visitAndOrExpr(QLParser.AndOrExprContext ctx) {
//        String op = ctx.op.getText();
//        Boolean left = (Boolean) visit(ctx.left);
//        Boolean right = (Boolean) visit(ctx.right);
//
//        switch (op) {
//            case "&&": return left && right;
//            case "||": return left || right;
//            default: throw new IllegalArgumentException("Unknown operator " + op);
//        }
//    }
//

    @Override
    public Expression visitConstant_integer(QLParser.Constant_integerContext ctx) {
        return new ExpressionDouble(Integer.valueOf(ctx.getText()));
    }

    @Override
    public Expression visitConstant_decimal(QLParser.Constant_decimalContext ctx) {
        return new ExpressionDouble(Double.valueOf(ctx.getText()));
    }

    @Override
    public Expression visitConstant_date(QLParser.Constant_dateContext ctx) {
        return new ExpressionDate(ctx.getText());
    }

    @Override
    public Expression visitConstant_money(QLParser.Constant_moneyContext ctx) {
        // TODO: Same as decimal?
        return new ExpressionDouble(Double.valueOf(ctx.getText()));
    }

    @Override
    public Expression visitConstant_string(QLParser.Constant_stringContext ctx) {
        return new ExpressionString(ctx.getText());
    }

//    @Override
//    public Expression visitIdentifier(QLParser.IdentifierContext ctx) {
//        return new ExpressionIdentifier(ctx.getText());
//    }

    @Override
    public Expression visitConstant_identifier(QLParser.Constant_identifierContext ctx) {
        return new ExpressionIdentifier(ctx.getText());
    }
}
