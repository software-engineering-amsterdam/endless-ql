package parsing.visitors;

import classes.Question;
import classes.values.Value;
import parsing.gen.QLBaseVisitor;
import parsing.gen.QLParser;
import parsing.visitors.expressions.ExpressionVisitor;

import java.util.HashMap;

public class UpdateVisitor extends QLBaseVisitor {
    private HashMap<String, Question> questionMap;
    private ExpressionVisitor expVisitor;
    private TypeVisitor typeVisitor;
    private boolean isVisible;

    public UpdateVisitor(HashMap<String, Question> questionMap, boolean isVisible){
        this.questionMap = questionMap;
        this.expVisitor = new ExpressionVisitor(questionMap);
        this.typeVisitor = new TypeVisitor();
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
        QLParser.BlockContext block = ctx.block();

        if(isVisible && condition){
            new UpdateVisitor(questionMap, true).visitBlock(block);
        }else{
            new UpdateVisitor(questionMap, false).visitBlock(block);
        }

        return questionMap;
    }
}