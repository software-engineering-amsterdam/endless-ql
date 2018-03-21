package com.chariotit.uva.sc.qdsl.ast;

public abstract class ExpressionValue {

    public abstract BooleanExpressionValue equalTo(ExpressionValue expressionValue);
    public abstract BooleanExpressionValue notEqualTo(ExpressionValue expressionValue);

    public static ExpressionValue create(Object o) {
        if (o instanceof Float) {
            return new MoneyExpressionValue((Float)o);
        } else if (o instanceof Integer) {
            return new IntegerExpressionValue((Integer)o);
        } else if (o instanceof Boolean) {
            return new BooleanExpressionValue((Boolean)o);
        } else if (o instanceof String) {
            return new StringExpressionValue((String)o);
        } else {
            throw new RuntimeException("Unsupported type");
        }
    }

    public static ExpressionValue getForType(ExpressionType expressionType, Object value) {
        switch (expressionType) {
            case MONEY:
                if (!(value instanceof Float)) {
                    throw new RuntimeException("Incompatible type");
                }
                return new MoneyExpressionValue((Float)value);
            case STRING:
                if (!(value instanceof String)) {
                    throw new RuntimeException("Incompatible type");
                }
                return new StringExpressionValue((String)value);
            case BOOLEAN:
                if (!(value instanceof Boolean)) {
                    throw new RuntimeException("Incompatible type");
                }
                return new BooleanExpressionValue((Boolean)value);
            case INTEGER:
                if (!(value instanceof Integer)) {
                    throw new RuntimeException("Incompatible type");
                }
                return new IntegerExpressionValue((Integer)value);
            default:
                throw new RuntimeException("Missing type");
        }
    }
}
