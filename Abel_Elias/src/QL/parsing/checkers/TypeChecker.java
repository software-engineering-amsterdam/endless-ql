package QL.parsing.checkers;

import QL.classes.Question;
import QL.classes.values.Value;
import QL.parsing.checkers.errors.TypeMismatchError;
import QL.parsing.gen.QLBaseVisitor;
import QL.parsing.gen.QLParser;
import QL.parsing.visitors.ExpressionVisitor;
import QL.parsing.visitors.TypeVisitor;

import java.util.Date;
import java.util.HashMap;

public class TypeChecker extends QLBaseVisitor{
    private ExpressionVisitor exprVisitor;
    private TypeVisitor typeVisitor;
    HashMap<String, Question> questionMap;

    TypeChecker(){
        questionMap = new HashMap();
        exprVisitor = new ExpressionVisitor(questionMap);
        typeVisitor = new TypeVisitor();
    }

    public void checkForm(QLParser.FormContext form){
        visit(form);
    }

    @Override
    public Object visitNormalQuestion(QLParser.NormalQuestionContext ctx) {
        String id = ctx.IDENTIFIER().getText();
        String type = ctx.type().getText();
        Value value = typeVisitor.visitType(ctx.type());

        questionMap.put(id, new Question("", value, false,false));
        return true;
    }

    @Override
    public Object visitFixedQuestion(QLParser.FixedQuestionContext ctx) {
        String id = ctx.IDENTIFIER().getText();
        String typeString = ctx.type().getText();
        Value value = typeVisitor.visitType(ctx.type());
        Object expressionValue = exprVisitor.visitExpression(ctx.expression());
        value.setValueGeneric(expressionValue);

        if(!isCorrectType(typeString, expressionValue)){
            throw new TypeMismatchError(ctx.expression().getText(), typeString);
        }else{
            questionMap.put(id, new Question("", value, true,false));
        }

        return true;
    }

    @Override
    public Object visitIfStatement(QLParser.IfStatementContext ctx) {
        Object value = exprVisitor.visitExpression(ctx.expression());

        if(!isCorrectType(Value.BOOLEAN, value)){
            throw new TypeMismatchError(ctx.expression().getText(), Value.BOOLEAN);
        }

        return true;
    }

    static boolean isCorrectType(String type, Object value){
        boolean correct = true;

        switch(type){
            case "boolean":
                return value instanceof Boolean;
            case "string":
                return value instanceof String;
            case "date":
                return value instanceof Date;
            case "integer":
            case "decimal":
            case "money":
                return value instanceof Number;
        }

        return false;
    }
}
