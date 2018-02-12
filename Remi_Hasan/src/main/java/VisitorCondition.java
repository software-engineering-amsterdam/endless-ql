import expression.Expression;
import model.Block;
import model.Condition;
import model.Question;

import java.util.ArrayList;

public class VisitorCondition extends QLBaseVisitor<ArrayList<Question>>  {

    private final ArrayList<Expression> conditions;

    VisitorCondition(ArrayList<Expression> conditions){
        this.conditions = conditions;
    }

    @Override
    public ArrayList<Question> visitCondition(QLParser.ConditionContext ctx) {
        VisitorExpression visitorExpression = new VisitorExpression();
        Expression condition = visitorExpression.visit(ctx.expression());

        ArrayList<Expression> subConditions = new ArrayList<>();
        subConditions.addAll(conditions);
        subConditions.add(condition);

        VisitorBlock visitorBlock = new VisitorBlock(subConditions);
        Block block = visitorBlock.visitBlock(ctx.block());
        return block.questions;
    }

}
