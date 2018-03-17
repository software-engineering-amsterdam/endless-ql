package parsing.visitors.expressions;

import classes.Question;
import parsing.gen.QLBaseVisitor;
import parsing.gen.QLParser;

import java.util.HashMap;

public class ExpressionVisitor extends QLBaseVisitor {
    private HashMap<String, Question> questionMap;
    private BooleanVisitor boolVisitor;
    private NumberVisitor numVisitor;

    public ExpressionVisitor(HashMap<String, Question> questionMap){
        this.questionMap = questionMap;
        this.numVisitor = new NumberVisitor(questionMap);
        this.boolVisitor = new BooleanVisitor(questionMap, numVisitor);
    }

    public Object visitExpression(QLParser.ExpressionContext ctx){
        return visit(ctx);
    }

    @Override
    public Object visitIdentifier(QLParser.IdentifierContext ctx) {
        String id = ctx.IDENTIFIER().getText();
        return questionMap.get(id).getValue().getValue();
    }

    @Override
    public String visitString(QLParser.StringContext ctx) {
        return ctx.getText();
    }

    @Override
    public Boolean visitBoolExpression(QLParser.BoolExpressionContext ctx) {
        return boolVisitor.visitBoolExpression(ctx.booleanExpression());
    }

    public Boolean visitBoolExpression(QLParser.BooleanExpressionContext ctx) {
        return boolVisitor.visitBoolExpression(ctx);
    }

    @Override
    public Double visitNumExpression(QLParser.NumExpressionContext ctx) {
        return numVisitor.visitNumberExpression(ctx.numberExpression());
    }

    public Double visitNumExpression(QLParser.NumberExpressionContext ctx) {
        return numVisitor.visitNumberExpression(ctx);
    }
}