package QL.parsing.checkers;
import QL.classes.values.Value;
import QL.parsing.checkers.errors.TypeMismatchError;
import QL.parsing.gen.QLBaseVisitor;
import QL.parsing.gen.QLParser;
import org.antlr.v4.runtime.ParserRuleContext;
import java.util.Date;
import java.util.HashMap;

public class TypeChecker extends QLBaseVisitor{
    private HashMap<String, Object> typeMap;

    TypeChecker(){
        typeMap = new HashMap();
    }

    public void checkForm(QLParser.FormContext form){
        visit(form);
    }

    @Override
    public Object visitNormalQuestion(QLParser.NormalQuestionContext ctx) {
        String id = ctx.IDENTIFIER().getText();
        String type = ctx.type().getText();

        switch(type){
            case "boolean":
                return typeMap.put(id, true);
            case "string":
                return typeMap.put(id, "");
            case "date":
                return typeMap.put(id, new Date());
            case "integer":
            case "decimal":
            case "money":
                return typeMap.put(id, 0.0);
        }

        return true;
    }

    @Override
    public Object visitFixedQuestion(QLParser.FixedQuestionContext ctx) {
        String id = ctx.IDENTIFIER().getText();
        String typeString = ctx.type().getText();
        Object value = visit(ctx.expression());

        checkType(typeString, value, ctx.expression(), ctx);
        typeMap.put(id, value);

        return true;
    }

    @Override
    public Object visitIfStatement(QLParser.IfStatementContext ctx) {
        Object value = visit(ctx.expression());
        return checkType(Value.BOOLEAN, value, ctx.expression(), ctx);
    }

    @Override
    public Object visitIdentifier(QLParser.IdentifierContext ctx) {
        return typeMap.get(ctx.IDENTIFIER().getText());
    }

    @Override
    public Boolean visitEqExpression(QLParser.EqExpressionContext ctx) {
        return true;
    }

    @Override
    public Boolean visitBoolExpression(QLParser.BoolExpressionContext ctx) {
        checkType(Value.BOOLEAN, visit(ctx.left), ctx.left, ctx);
        checkType(Value.BOOLEAN, visit(ctx.right), ctx.right, ctx);

        return true;
    }

    @Override
    public Boolean visitCompExpression(QLParser.CompExpressionContext ctx) {
        checkType(Value.DECIMAL, visit(ctx.left), ctx.left, ctx);
        checkType(Value.DECIMAL, visit(ctx.right), ctx.right, ctx);

        return true;
    }

    @Override
    public Double visitNumExpression(QLParser.NumExpressionContext ctx) {
        checkType(Value.DECIMAL, visit(ctx.left), ctx.left, ctx);
        checkType(Value.DECIMAL, visit(ctx.right), ctx.right, ctx);

        return 0.0;
    }

    @Override
    public Object visitBraceExpression(QLParser.BraceExpressionContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public Boolean visitNotExpression(QLParser.NotExpressionContext ctx) {
        checkType(Value.BOOLEAN, visit(ctx.expression()), ctx.expression(), ctx);
        return true;
    }

    @Override
    public Boolean visitBoolValue(QLParser.BoolValueContext ctx) {
        return true;
    }

    @Override
    public String visitStrValue(QLParser.StrValueContext ctx) {
        return "";
    }

    @Override
    public Double visitNumValue(QLParser.NumValueContext ctx) {
        return 0.0;
    }

    private boolean checkType(String type, Object value, ParserRuleContext expression, ParserRuleContext code){
        boolean correct = false;

        switch(type){
            case "boolean":
                correct = value instanceof Boolean; break;
            case "string":
                correct = value instanceof String; break;
            case "date":
                correct = value instanceof Date; break;
            case "integer":
            case "decimal":
            case "money":
                correct = value instanceof Number; break;
        }

        if(!correct){
            throw new TypeMismatchError(expression.getText(), code.getText(), type);
        }

        return correct;
    }
}
