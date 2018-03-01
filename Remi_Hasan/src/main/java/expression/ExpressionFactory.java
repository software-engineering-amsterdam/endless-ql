package expression;

import expression.variable.*;

import java.math.BigDecimal;

public class ExpressionFactory {
    public static Expression createExpression(String questionType) {
        switch (questionType) {
            case "boolean":
                return new ExpressionVariableBoolean(false);
            case "string":
                return new ExpressionVariableString(null);
            case "integer":
            case "decimal":
            case "money":
                return new ExpressionVariableNumber("");
            case "date":
                return new ExpressionVariableDate(null);
            default:
                throw new IllegalArgumentException("Unknown question type " + questionType);
        }
    }
}
