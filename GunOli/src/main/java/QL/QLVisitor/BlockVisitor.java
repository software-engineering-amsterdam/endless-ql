package QL.QLVisitor;

import QL.ParseObjectsQL.Block;
import QL.ParseObjectsQL.Condition;
import QL.ParseObjectsQL.Expressions.Expression;
import QL.ParseObjectsQL.Expressions.ExpressionConstants.BooleanConstant;
import QL.ParseObjectsQL.Question;
import QL.QLAntlrGen.QLBaseVisitor;
import QL.QLAntlrGen.QLParser;

import java.util.ArrayList;

public class BlockVisitor extends QLBaseVisitor<Block> {
    private ExpressionTable expressionTable;
    private Expression condition;

    public BlockVisitor(ExpressionTable exprTable){
        setExpressionTable(exprTable);
        setCondition(new BooleanConstant(true));
    }

    public BlockVisitor(ExpressionTable exprTable, Expression condition){
        setExpressionTable(exprTable);
        setCondition(condition);
    }

    private void setCondition(Expression condition){
        this.condition = condition;
    }

    private void setExpressionTable(ExpressionTable exprTable){
        this.expressionTable = exprTable;
    }

    @Override
    public Block visitBlock(QLParser.BlockContext ctx){
        QuestionVisitor questionVisitor = new QuestionVisitor(expressionTable, condition);
        ConditionVisitor conditionVisitor = new ConditionVisitor(expressionTable);

        ArrayList<Question> questions = new ArrayList<Question>();
        ArrayList<Condition> conditions = new ArrayList<Condition>();
        for(QLParser.StatementContext statementCtx : ctx.statement()){
            if (statementCtx.question() != null) {
                Question question = questionVisitor.visitQuestion(statementCtx.question());
                questions.add(question);
            }  else if(statementCtx.condition() != null) {
                Condition condition = conditionVisitor.visitCondition(statementCtx.condition());
                conditions.add(condition);
                questions.addAll(condition.getBlock().getQuestions());
            }
        }
        return new Block(questions, conditions);
    }
}

