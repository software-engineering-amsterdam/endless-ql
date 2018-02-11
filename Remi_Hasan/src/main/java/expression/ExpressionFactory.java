package expression;

public class ExpressionFactory {
    public static Expression createExpression(String questionType) {
        switch(questionType){
            case "boolean":
                return new ExpressionBoolean(null);
            case "string":
                return new ExpressionString(null);
            case "integer":
                return new ExpressionInteger(null);
            case "date":
                return new ExpressionDate(null);
            case "decimal":
                return new ExpressionDecimal(null);
            case "money":
                return new ExpressionMoney(null);
            default:
                throw new IllegalArgumentException("Unknown question type " + questionType);
        }
    }
}
