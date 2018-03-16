package parsing.visitors.refactor_tmp;

import classes.CodeBlock;
import classes.Question;
import classes.values.*;
import parsing.gen.QLBaseVisitor;
import parsing.gen.QLParser;

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

        Question question = new Question(questionString, value, false);
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

        Question question = new Question(questionString, value, true);
        questionMap.put(id, question);

        return questionMap;
    }

    @Override
    public Object visitIfStatement(QLParser.IfStatementContext ctx) {
        Boolean condition = expVisitor.visitBoolExpression(ctx.booleanExpression());
        QLParser.BlockContext block = ctx.block();

        if(condition){
            new BlockVisitor(questionMap, isVisible && condition).visitBlock(block);
        }else{
            new BlockVisitor(questionMap, false).visitBlock(block);
        }

        return questionMap;
    }
}