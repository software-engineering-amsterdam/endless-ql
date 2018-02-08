public class VisitorCondition extends QLBaseVisitor<Condition>  {

    @Override
    public Condition visitCondition(QLParser.ConditionContext ctx) {

        VisitorBlock visitorBlock = new VisitorBlock();
        Block block = visitorBlock.visitBlock(ctx.block());

        VisitorExpression visitorExpression = new VisitorExpression();
        Expression expression = visitorExpression.visit(ctx.expression());

        return new Condition(expression, block);
    }

}
