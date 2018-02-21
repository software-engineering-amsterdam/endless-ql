package expression;

public class ExpressionFactory {
    public static Expression createExpression(String questionType) {
        switch (questionType) {
            case "boolean":
                return new ExpressionVariableBoolean(null);
            case "string":
                return new ExpressionVariableString(null);
            case "integer":
                return new ExpressionVariableInteger(null);
            case "date":
                return new ExpressionVariableDate(null);
            case "decimal":
                return new ExpressionVariableDecimal(null);
            case "money":
                return new ExpressionVariableMoney(null);
            default:
                throw new IllegalArgumentException("Unknown question type " + questionType);
        }
    }
}
