package QL.parsing.visitors;

import QL.classes.Question;
import QL.classes.values.Value;
import QL.parsing.gen.QLBaseVisitor;
import QL.parsing.gen.QLParser;
import QL.parsing.visitors.expressions.ExpressionVisitor;

import java.util.HashMap;

public class UpdateVisitor extends QLBaseVisitor {
    public HashMap<String, Question> questionMap;
    private ExpressionVisitor expVisitor;
    private boolean isVisible;

    public UpdateVisitor(HashMap<String, Question> questionMap, boolean isVisible){
        this.questionMap = questionMap;
        this.expVisitor = new ExpressionVisitor(this.questionMap);
        this.isVisible = isVisible;
    }

    @Override
    public Object visitNormalQuestion(QLParser.NormalQuestionContext ctx) {
        String id = ctx.IDENTIFIER().getText();
        Question question = questionMap.get(id);
        question.setVisibility(isVisible);

        return questionMap;
    }

    @Override
    public Object visitFixedQuestion(QLParser.FixedQuestionContext ctx) {
        String id = ctx.IDENTIFIER().getText();
        Question question = questionMap.get(id);
        question.setVisibility(isVisible);

        Object expression = expVisitor.visitExpression(ctx.expression());
        question.getValue().setValueGeneric(expression);

        return questionMap;
    }

    @Override
    public Object visitIfStatement(QLParser.IfStatementContext ctx) {
        Boolean condition = expVisitor.visitBoolExpression(ctx.booleanExpression());
        QLParser.BlockContext ifBlock = ctx.ifBlock;
        QLParser.BlockContext elseBlock = ctx.elseBlock;
        QLParser.IfStatementContext elseIfStatement = ctx.ifStatement();

        new UpdateVisitor(questionMap, isVisible && condition).visitBlock(ifBlock);

        if(elseBlock != null){
            new UpdateVisitor(questionMap, isVisible && !condition).visitBlock(ifBlock);
        }else if(elseIfStatement != null){
            this.visitIfStatement(elseIfStatement);
        }

        return questionMap;
    }
}