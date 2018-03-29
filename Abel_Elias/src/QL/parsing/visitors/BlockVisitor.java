package QL.parsing.visitors;

import QL.classes.Question;
import QL.classes.values.*;
import QL.parsing.gen.QLBaseVisitor;
import QL.parsing.gen.QLParser;

import java.util.LinkedHashMap;

public class BlockVisitor extends QLBaseVisitor {
    private LinkedHashMap<String, Question> questionMap;
    private ExpressionVisitor expVisitor;
    private TypeVisitor typeVisitor;
    private boolean isVisible;

    public BlockVisitor(LinkedHashMap<String, Question> questionMap, boolean isVisible){
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

        Question question = new Question(id, questionString, value, false, isVisible);
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

        Question question = new Question(id, questionString, value, true, isVisible);
        questionMap.put(id, question);

        return questionMap;
    }

    @Override
    public Object visitIfStatement(QLParser.IfStatementContext ctx) {
        Boolean condition = (Boolean) expVisitor.visitExpression(ctx.expression());
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