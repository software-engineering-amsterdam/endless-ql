package visitor;

import antlr.QLBaseVisitor;
import antlr.QLParser;
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
        for (QLParser.BlockElementContext blockElementContext : ctx.block().blockElement()) {
            BlockElement blockElement = visitorBlockElement.visit(blockElementContext);
            elements.add(blockElement);
        }

        return new Condition(expression, elements);
    }

}
