package parsing.visitors;

import classes.Question;
import classes.values.*;
import parsing.gen.QLBaseVisitor;
import parsing.gen.QLParser;
import parsing.visitors.expressions.ExpressionVisitor;

import java.util.HashMap;

public class BlockVisitor extends QLBaseVisitor {
    private HashMap<String, Question> questionMap;
    private ExpressionVisitor expVisitor;
    private TypeVisitor typeVisitor;
    private boolean isVisible;

    public BlockVisitor(HashMap<String, Question> questionMap, boolean isVisible){
        this.questionMap = questionMap;
        this.expVisitor = new ExpressionVisitor(questionMap);
        this.typeVisitor = new TypeVisitor();
        this.isVisible = isVisible;
    }

    @Override
    public Object visitNormalQuestion(QLParser.NormalQuestionContext ctx) {
        String id = ctx.IDENTIFIER().getText();
        String questionString = ctx.STR().getText();
        Value value = typeVisitor.visitType(ctx.type());

        Question question = new Question(questionString, value, false, isVisible);
        questionMap.put(id, question);

        return questionMap;
    }

    @Override
    public Object visitFixedQuestion(QLParser.FixedQuestionContext ctx) {
        String id = ctx.IDENTIFIER().getText();
        String questionString = ctx.STR().getText();
        Value value = typeVisitor.visitType(ctx.type());
        Object expression = expVisitor.visitExpression(ctx.expression());
        value.setValueGeneric(expression);

        Question question = new Question(questionString, value, true, isVisible);
        questionMap.put(id, question);

        return questionMap;
    }

    @Override
    public Object visitIfStatement(QLParser.IfStatementContext ctx) {
        Boolean condition = expVisitor.visitBoolExpression(ctx.booleanExpression());
        QLParser.BlockContext ifBlock = ctx.ifBlock;
        QLParser.BlockContext elseBlock = ctx.elseBlock;
        QLParser.IfStatementContext elseIfStatement = ctx.ifStatement();

        new BlockVisitor(questionMap, isVisible && condition).visitBlock(ifBlock);

        if(elseBlock != null){
            new BlockVisitor(questionMap, isVisible && !condition).visitBlock(elseBlock);
        }else if(elseIfStatement != null){
            this.visitIfStatement(elseIfStatement);
        }

        return questionMap;
    }
}