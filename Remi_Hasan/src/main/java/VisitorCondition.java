import expression.Expression;
import model.BlockElement;
import model.Condition;

import java.util.ArrayList;

public class VisitorCondition extends QLBaseVisitor<Condition> {

    @Override
    public Condition visitCondition(QLParser.ConditionContext ctx) {
        VisitorExpression visitorExpression = new VisitorExpression();
        Expression expression = visitorExpression.visit(ctx.expression());

        ArrayList<BlockElement> elements = new ArrayList<>();
        VisitorBlockElement visitorBlockElement = new VisitorBlockElement();
        for (QLParser.BlockElementContext x : ctx.block().blockElement()) {
            BlockElement blockElement = visitorBlockElement.visit(x);
            elements.add(blockElement);
        }

        Condition condition = new Condition(expression, elements);
        return condition;
    }

}
