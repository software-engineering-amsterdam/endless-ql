import expression.Expression;
import model.Block;
import model.BlockElement;
import model.Condition;
import model.Question;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
