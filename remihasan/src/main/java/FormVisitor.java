public class FormVisitor extends QLBaseVisitor {

    @Override
    public Object visitQuestionType(QLParser.QuestionTypeContext ctx) {
        Object result = visit(ctx.expression());
        System.out.println(result);
        return result;
    }

    @Override
    public Double visitOpExpr(QLParser.OpExprContext ctx) {
        // Inspired by: https://stackoverflow.com/a/23092428
        String op = ctx.op.getText();
        Double left = (Double) visit(ctx.left);
        Double right = (Double) visit(ctx.right);

        switch (op) {
            case "+": return left + right;
            case "-": return left - right;
            case "*": return left * right;
            case "/": return left / right;
            default: throw new IllegalArgumentException("Unknown operator " + op);
        }
    }

    @Override
    public Double visitNegExpr(QLParser.NegExprContext ctx) {
        return -1.0 * (Double) visit(ctx.expr);
    }

    @Override
    public Boolean visitNotExpr(QLParser.NotExprContext ctx) {
        return !((Boolean) visit(ctx.expr));
    }

    @Override
    public Boolean visitBoolExpr(QLParser.BoolExprContext ctx) {
        String op = ctx.op.getText();
        Double left = (Double) visit(ctx.left);
        Double right = (Double) visit(ctx.right);

        switch (op) {
            case ">": return left > right;
            case ">=": return left >= right;
            case "<": return left < right;
            case "<=": return left <= right;
            default: throw new IllegalArgumentException("Unknown operator " + op);
        }
    }

    @Override
    public Boolean visitCompExpr(QLParser.CompExprContext ctx) {
        String op = ctx.op.getText();
        Object left = visit(ctx.left);
        Object right = visit(ctx.right);

        switch (op) {
            case "==": return left == right;
            case "!=": return left != right;
            default: throw new IllegalArgumentException("Unknown operator " + op);
        }
    }

    @Override
    public Boolean visitAndOrExpr(QLParser.AndOrExprContext ctx) {
        String op = ctx.op.getText();
        Boolean left = (Boolean) visit(ctx.left);
        Boolean right = (Boolean) visit(ctx.right);

        switch (op) {
            case "&&": return left && right;
            case "||": return left || right;
            default: throw new IllegalArgumentException("Unknown operator " + op);
        }
    }

    @Override
    public Double visitConstExpr(QLParser.ConstExprContext ctx) {
        return Double.valueOf(ctx.getText());
    }
}
