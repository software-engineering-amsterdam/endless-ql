package QL.QLVisitor;

import QL.ParseObjectsQL.Expressions.BinaryExpressions.AndExpression;
import QL.ParseObjectsQL.Expressions.Expression;
import QL.ParseObjectsQL.Expressions.UnaryExpressions.NotExpression;
import QL.ParseObjectsQL.Question;
import QL.QLAntlrGen.QLBaseVisitor;
import QL.QLAntlrGen.QLParser;

import java.util.ArrayList;

public class ConditionVisitor extends QLBaseVisitor{
    private ExpressionTable expressionTable;
    private Expression condition;

    public ConditionVisitor(ExpressionTable exprTable, Expression condition){
        this.expressionTable = exprTable;
        this.condition = condition;
    }

    @Override
    public ArrayList<Question> visitCondition(QLParser.ConditionContext ctx){
        ArrayList<Question> questions = new ArrayList<>();
        ExpressionVisitor expressionVisitor = new ExpressionVisitor(expressionTable);
        QLParser.ExpressionContext expressionCtx = ctx.expression();
        Expression condition = expressionVisitor.visit(expressionCtx);

        QLParser.BlockContext blockCtx = ctx.trueBlock;

        if(ctx.falseBlock != null){
            QLParser.BlockContext falseBlockCtx = ctx.falseBlock;
            Expression negatedCondition = new NotExpression(condition);
            Expression conditionChain = new AndExpression(negatedCondition, this.condition);
            BlockVisitor falseBlockVisitor = new BlockVisitor(expressionTable, conditionChain);
            ArrayList<Question> falseBlockQuestions = falseBlockVisitor.visitBlock(falseBlockCtx);
            questions.addAll(falseBlockQuestions);
        }

        Expression conditionChain = new AndExpression(condition, this.condition);
        BlockVisitor trueBlockVisitor = new BlockVisitor(expressionTable, conditionChain);
        ArrayList<Question> blockQuestions = trueBlockVisitor.visitBlock(blockCtx);
        questions.addAll(blockQuestions);
        return questions;
    }
}

