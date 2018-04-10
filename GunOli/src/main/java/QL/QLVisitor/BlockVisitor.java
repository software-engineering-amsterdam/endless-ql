package QL.QLVisitor;

import QL.AST.Expressions.Expression;
import QL.AST.Expressions.ExpressionConstants.BooleanConstant;
import QL.AST.Question;
import QL.QLAntlrGen.QLBaseVisitor;
import QL.QLAntlrGen.QLParser;

import java.util.ArrayList;

public class BlockVisitor extends QLBaseVisitor<ArrayList<Question>> {
    private ExpressionTable expressionTable;
    private Expression condition;

    public BlockVisitor(ExpressionTable exprTable){
        this.expressionTable = exprTable;
        this.condition = new BooleanConstant(true, 0);
    }

    public BlockVisitor(ExpressionTable exprTable, Expression condition){
        this.expressionTable = exprTable;
        this.condition = condition;
    }

    @Override
    public ArrayList<Question> visitBlock(QLParser.BlockContext ctx){
        QuestionVisitor questionVisitor = new QuestionVisitor(expressionTable, condition);
        ConditionVisitor conditionVisitor = new ConditionVisitor(expressionTable, condition);

        ArrayList<Question> questions = new ArrayList<Question>();
        for(QLParser.StatementContext statementCtx : ctx.statement()){
            if (statementCtx.question() != null) {
                Question question = questionVisitor.visitQuestion(statementCtx.question());
                questions.add(question);
            }

            if(statementCtx.condition() != null) {
                ArrayList<Question> conditionQuestions = conditionVisitor.visitCondition(statementCtx.condition());
                questions.addAll(conditionQuestions);
            }
        }
        return questions;
    }
}