package QL.parsing.checkers;

import QL.classes.Question;
import QL.classes.values.BooleanValue;
import QL.classes.values.NumericValue;
import QL.classes.values.Value;
import QL.parsing.checkers.errors.TypeMismatchError;
import QL.parsing.gen.QLBaseVisitor;
import QL.parsing.gen.QLParser;
import QL.parsing.visitors.TypeVisitor;
import QL.parsing.visitors.expressions.ExpressionVisitor;
import QL.parsing.visitors.refactor_tmp.BaseVisitor;

import java.util.Date;
import java.util.HashMap;

public class TypeChecker extends QLBaseVisitor {
    // Typechecker checks if there are any inconsistensies in the types that were given in the syntax of the code
    private ExpressionVisitor expressionVisitor;
    private TypeVisitor typeVisitor;
    private HashMap<String, Question> questionMap;

    public TypeChecker(QLParser.FormContext ctx, HashMap<String, Question> questionMap) {
        this.questionMap = questionMap;
        this.expressionVisitor = new ExpressionVisitor(questionMap);
        this.typeVisitor = new TypeVisitor();

        visitBlock(ctx.block());
    }

    @Override
    public Object visitFixedQuestion(QLParser.FixedQuestionContext ctx) {
        String id = ctx.IDENTIFIER().getText();
        String type = ctx.type().getText();
        Object value = expressionVisitor.visitExpression(ctx.expression());
        boolean correctMatch = false;

        switch (type) {
            case Value.MONEY:
            case Value.DECIMAL:
            case Value.INTEGER:
                correctMatch = isNumeric(value);
                break;
            case Value.DATE:
                correctMatch = isDate(value);
                break;
            case Value.STRING:
                correctMatch = isString(value);
                break;
            case Value.BOOLEAN:
                correctMatch = isBoolean(value);
                break;
        }

        if (!correctMatch) {
            throw new TypeMismatchError(id, ctx.type().getText());
        }

        return questionMap;
    }

    @Override
    public Object visitBoolIdentifier(QLParser.BoolIdentifierContext ctx) {
        String id = ctx.getText();

        if (!questionMap.get(id).getValue().getType().equals(Value.BOOLEAN)) {
            throw new TypeMismatchError(id, "boolean");
        }

        return super.visitBoolIdentifier(ctx);
    }

    @Override
    public Object visitNumIdentifier(QLParser.NumIdentifierContext ctx) {
        String id = ctx.getText();

        if (!isNumeric(questionMap.get(id).getValue().getValue())){
            throw new TypeMismatchError(id, "number");
        }

        return questionMap;
    }

    private boolean isNumeric(Object value) {
        return value.getClass().isAssignableFrom(Number.class);
    }

    private boolean isBoolean(Object value) {
        return value instanceof Boolean;
    }

    private boolean isDate(Object value) {
        return value instanceof Date;
    }

    private boolean isString(Object value) {
        return value instanceof String;
    }
}
