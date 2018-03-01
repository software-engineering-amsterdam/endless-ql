package expression;

import expression.variable.*;

import java.math.BigDecimal;

public class ExpressionFactory {
    public static Expression createExpression(ReturnType type, String value) {
        switch (type) {
            case NUMBER:
            case INTEGER:
            case DECIMAL:
            case MONEY:
                Expression expressionVariableNumber = new ExpressionVariableNumber(new BigDecimal(0));
                expressionVariableNumber.setValue(value);
                return expressionVariableNumber;
            case BOOLEAN:
                Expression expressionVariableBoolean = new ExpressionVariableBoolean(null);
                expressionVariableBoolean.setValue(value);
                return expressionVariableBoolean;
            case STRING:
                Expression expressionVariableString = new ExpressionVariableString(null);
                expressionVariableString.setValue(value);
                return expressionVariableString;
            case DATE:
                Expression expressionVariableDate = new ExpressionVariableDate(null);
                expressionVariableDate.setValue(value);
                return expressionVariableDate;
            default:
                throw new IllegalArgumentException("Unknown question type " + type);
        }
    }
//    public static Expression createExpression(String questionType) {
//        switch (questionType) {
//            case "boolean":
//                return new ExpressionVariableBoolean(false);
//            case "string":
//                return new ExpressionVariableString(null);
//            case "integer":
//            case "decimal":
//            case "money":
//                // TODO: get rid of 0
//                return new ExpressionVariableNumber(new BigDecimal(0));
//            case "date":
//                return new ExpressionVariableDate(null);
//            default:
//                throw new IllegalArgumentException("Unknown question type " + questionType);
//        }
//    }
}
