package Visitor;

import ParseObjects.Block;
import ParseObjects.Condition;
import ParseObjects.Question;
import antlrGen.QLBaseVisitor;
import antlrGen.QLParser;

import java.util.ArrayList;

public class BlockVisitor extends QLBaseVisitor<Block> {

    @Override
    public Block visitBlock(QLParser.BlockContext ctx){
        QuestionVisitor questionVisitor = new QuestionVisitor();
        ConditionVisitor conditionVisitor = new ConditionVisitor();

        ArrayList<Question> questions = new ArrayList<Question>();
        ArrayList<Condition> conditions = new ArrayList<Condition>();
        for(QLParser.StatementContext statementCtx : ctx.statement()){
            if (!statementCtx.question().isEmpty()) {
                Question question = questionVisitor.visitQuestion(statementCtx.question());
                questions.add(question);
            }  else {
                Condition condition = conditionVisitor.visitCondition(statementCtx.condition());
                conditions.add(condition);
            }
        }
        return new Block(questions, conditions);
    }
}
