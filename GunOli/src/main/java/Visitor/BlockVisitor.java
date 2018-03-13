package Visitor;

import ParseObjectsQL.Block;
import ParseObjectsQL.Condition;
import ParseObjectsQL.Question;
import QLAntlrGen.QLBaseVisitor;
import QLAntlrGen.QLParser;

import java.util.ArrayList;

public class BlockVisitor extends QLBaseVisitor<Block> {
    private ExpressionTable expressionTable;

    public BlockVisitor(ExpressionTable exprTable){
        super();
        this.expressionTable = exprTable;
    }

    @Override
    public Block visitBlock(QLParser.BlockContext ctx){
        QuestionVisitor questionVisitor = new QuestionVisitor(expressionTable);
        ConditionVisitor conditionVisitor = new ConditionVisitor(expressionTable);

        ArrayList<Question> questions = new ArrayList<Question>();
        ArrayList<Condition> conditions = new ArrayList<Condition>();
        for(QLParser.StatementContext statementCtx : ctx.statement()){
            if (statementCtx.question() != null) {
                Question question = questionVisitor.visitQuestion(statementCtx.question());
                questions.add(question);
            }  else {
                Condition condition = conditionVisitor.visitCondition(statementCtx.condition());
                conditions.add(condition);
                questions.addAll(condition.getBlock().getQuestions());
            }
        }
        return new Block(questions, conditions);
    }
}

