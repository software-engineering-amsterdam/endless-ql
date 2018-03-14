package logic.type;

import ast.model.expressions.Expression;
import com.sun.istack.internal.NotNull;
import com.sun.tools.javac.util.Pair;

import java.math.BigDecimal;

public class MixedValue {

    private Expression.DataType type;
    private String stringValue;
    private BigDecimal decimalValue;
    private Integer integerValue;
    private Boolean booleanValue;

    private enum BinaryOperator {
        PLUS, MINUS, DIVIDE, MULTIPLY, AND, OR
    }

    private enum ComparisionOperator {
        GT, GE, LT, LE, EQ, NEQ
    }

    public static MixedValue createValue(@NotNull Expression.DataType type, String text) {

        switch (type) {
            case INTEGER:
                Integer safeInteger = text.isEmpty() ? 0 : Integer.parseInt(text);
                return new MixedValue(type, safeInteger);
            case DECIMAL:
                BigDecimal safeDecimal = text.isEmpty() ? new BigDecimal(0) : new BigDecimal(text);
                return new MixedValue(type, safeDecimal);
            case BOOLEAN:
                // @TODO: Check it - I don't like the string here
                Boolean safeBoolean = text.toUpperCase().equals("TRUE");
                return new MixedValue(type, safeBoolean);
            case STRING:
                return new MixedValue(type, text);
        }
        return null;
    }

    private MixedValue(@NotNull Expression.DataType type, @NotNull String stringValue) {
        this.type = type;
        this.stringValue = stringValue;
    }

    private MixedValue(@NotNull Expression.DataType type, @NotNull BigDecimal decimalValue) {
        this.type = type;
        this.decimalValue = decimalValue;
    }

    private MixedValue(@NotNull Expression.DataType type, @NotNull Integer integerValue) {
        this.type = type;
        this.integerValue = integerValue;
    }

    private MixedValue(@NotNull Expression.DataType type, @NotNull Boolean booleanValue) {
        this.type = type;
        this.booleanValue = booleanValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public BigDecimal getDecimalValue() {
        return decimalValue;
    }

    public Integer getIntegerValue() {
        return integerValue;
    }

    public Boolean getBooleanValue() {
        return booleanValue;
    }

    public void setStringValue(String stringValue) {
        if (this.type == Expression.DataType.STRING) {
            this.stringValue = stringValue;
        } else {
            throw new RuntimeException("Illegal value assignment: String value cannot be assigned to " + this.type.name() + " type.");
        }
    }

    public void setDecimalValue(BigDecimal decimalValue) {
        if (this.type == Expression.DataType.DECIMAL) {
            this.decimalValue = decimalValue;
        } else {
            throw new RuntimeException("Illegal value assignment: Decimal value cannot be assigned to " + this.type.name() + " type.");
        }
    }

    public void setIntegerValue(Integer integerValue) {
        if (this.type == Expression.DataType.INTEGER) {
            this.integerValue = integerValue;
        } else {
            throw new RuntimeException("Illegal value assignment: Integer value cannot be assigned to " + this.type.name() + " type.");
        }
    }

    public void setBooleanValue(Boolean booleanValue) {
        if (this.type == Expression.DataType.BOOLEAN) {
            this.booleanValue = booleanValue;
        } else {
            throw new RuntimeException("Illegal value assignment: Boolean value cannot be assigned to " + this.type.name() + " type.");
        }
    }

    public Expression.DataType getType() {
        return type;
    }

    // unary operations

    // logical and arithmetical negation
    public MixedValue negate() {
        switch (this.type) {
            case BOOLEAN:
                return new MixedValue(this.type, !this.booleanValue);
            case INTEGER:
                return new MixedValue(this.type, -this.integerValue);
            case DECIMAL:
                return new MixedValue(this.type, this.decimalValue.negate());
            default:
                throw new RuntimeException("Logical negation on type " + this.type + " is illegal.");
        }
    }

    // binary operations
    public MixedValue add(MixedValue rhs) {
        return this.binaryOperation(BinaryOperator.PLUS, rhs);
    }

    public MixedValue subtract(MixedValue rhs) {
        return this.binaryOperation(BinaryOperator.MINUS, rhs);
    }

    public MixedValue multiply(MixedValue rhs) {
        return this.binaryOperation(BinaryOperator.MULTIPLY, rhs);
    }

    public MixedValue divide(MixedValue rhs) {
        return this.binaryOperation(BinaryOperator.DIVIDE, rhs);
    }

    public MixedValue and(MixedValue rhs) {
        return this.binaryOperation(BinaryOperator.AND, rhs);
    }

    public MixedValue or(MixedValue rhs) {
        return this.binaryOperation(BinaryOperator.OR, rhs);
    }

    // comparision operators
    public MixedValue equals(MixedValue rhs) {
        return this.comparision(ComparisionOperator.EQ, rhs);
    }

    public MixedValue notEquals(MixedValue rhs) {
        return this.comparision(ComparisionOperator.NEQ, rhs);
    }

    public MixedValue greaterThan(MixedValue rhs) {
        return this.comparision(ComparisionOperator.GT, rhs);
    }

    public MixedValue greaterEqual(MixedValue rhs) {
        return this.comparision(ComparisionOperator.GE, rhs);
    }

    public MixedValue lessThan(MixedValue rhs) {
        return this.comparision(ComparisionOperator.LT, rhs);
    }

    public MixedValue lessEqual(MixedValue rhs) {
        return this.comparision(ComparisionOperator.LE, rhs);
    }

    private MixedValue binaryOperation(BinaryOperator operator, MixedValue secondResult) {

        // by default the types must be the same, except when there is a pair of integer and decimal, then the result will be decimal
        Pair<MixedValue, MixedValue> unifiedResults = unifyDataTypes(this, secondResult);

        MixedValue lhs = unifiedResults.fst;
        MixedValue rhs = unifiedResults.snd;

        // for string only concatenation is allowed (PLUS sign)
        if (lhs.type == Expression.DataType.STRING) {
            if (operator == BinaryOperator.PLUS) {
                return new MixedValue(lhs.type, lhs.stringValue + rhs.stringValue);
            } else {
                throw new RuntimeException("Operation " + operator.name() + " on type " + lhs.type + " is illegal.");
            }
        }

        if (this.type == Expression.DataType.DECIMAL) {
            switch (operator) {
                case PLUS:
                    return new MixedValue(lhs.type, lhs.decimalValue.add(rhs.decimalValue));
                case MINUS:
                    return new MixedValue(lhs.type, lhs.decimalValue.subtract(rhs.decimalValue));
                case MULTIPLY:
                    return new MixedValue(lhs.type, lhs.decimalValue.multiply(rhs.decimalValue));
                case DIVIDE:
                    if (rhs.decimalValue.compareTo(BigDecimal.ZERO) != 0) {
                        return new MixedValue(lhs.type, lhs.decimalValue.divide(rhs.decimalValue, 4, BigDecimal.ROUND_DOWN));
                    }
                    throw new RuntimeException("Division by zero!");
                default:
                    throw new RuntimeException("Operation " + operator.name() + " on type " + lhs.type + " is illegal.");
            }
        }

        if (this.type == Expression.DataType.INTEGER) {
            switch (operator) {
                case PLUS:
                    return new MixedValue(lhs.type, lhs.integerValue + rhs.integerValue);
                case MINUS:
                    return new MixedValue(lhs.type, lhs.integerValue - rhs.integerValue);
                case MULTIPLY:
                    return new MixedValue(lhs.type, lhs.integerValue * rhs.integerValue);
                case DIVIDE:
                    if (rhs.integerValue != 0) {
                        if (lhs.integerValue % rhs.integerValue == 0) {
                            return new MixedValue(lhs.type, lhs.integerValue / rhs.integerValue);
                        } else {
                            return new MixedValue(
                                    Expression.DataType.DECIMAL,
                                    new BigDecimal(lhs.integerValue).divide(new BigDecimal(rhs.integerValue), 4, BigDecimal.ROUND_DOWN));
                        }
                    }
                    throw new RuntimeException("Division by zero!");
                default:
                    throw new RuntimeException("Operation " + operator.name() + " on type " + lhs.type + " is illegal.");
            }
        }

        if (this.type == Expression.DataType.BOOLEAN) {
            switch (operator) {
                case AND:
                    return new MixedValue(lhs.type, lhs.booleanValue && rhs.booleanValue);
                case OR:
                    return new MixedValue(lhs.type, lhs.booleanValue || rhs.booleanValue);
                default:
                    throw new RuntimeException("Operation " + operator.name() + " on type " + lhs.type + " is illegal.");
            }
        }

        throw new RuntimeException("Unable to perform operation " + operator.name() + ".");

    }

    private MixedValue comparision(ComparisionOperator operator, MixedValue secondResult) {

        // by default the types must be the same, except when there is a pair of integer and decimal, then the result will be decimal
        Pair<MixedValue, MixedValue> unifiedResults = unifyDataTypes(this, secondResult);

        MixedValue lhs = unifiedResults.fst;
        MixedValue rhs = unifiedResults.snd;

        if (lhs.type == Expression.DataType.STRING) {
            switch (operator) {
                case EQ:
                    return new MixedValue(Expression.DataType.BOOLEAN, lhs.stringValue.equals(rhs.stringValue));
                case NEQ:
                    return new MixedValue(Expression.DataType.BOOLEAN, !lhs.stringValue.equals(rhs.stringValue));
                default:
                    throw new RuntimeException("Operation " + operator.name() + " on type " + lhs.type + " is illegal.");
            }
        }

        if (this.type == Expression.DataType.DECIMAL) {
            switch (operator) {
                case GT:
                    return new MixedValue(Expression.DataType.BOOLEAN, lhs.decimalValue.compareTo(rhs.decimalValue) > 0);
                case GE:
                    return new MixedValue(Expression.DataType.BOOLEAN, lhs.decimalValue.compareTo(rhs.decimalValue) >= 0);
                case LT:
                    return new MixedValue(Expression.DataType.BOOLEAN, lhs.decimalValue.compareTo(rhs.decimalValue) < 0);
                case LE:
                    return new MixedValue(Expression.DataType.BOOLEAN, lhs.decimalValue.compareTo(rhs.decimalValue) <= 0);
                case EQ:
                    return new MixedValue(Expression.DataType.BOOLEAN, lhs.decimalValue.compareTo(rhs.decimalValue) == 0);
                case NEQ:
                    return new MixedValue(Expression.DataType.BOOLEAN, lhs.decimalValue.compareTo(rhs.decimalValue) != 0);
                default:
                    throw new RuntimeException("Operation " + operator.name() + " on type " + lhs.type + " is illegal.");
            }
        }

        if (this.type == Expression.DataType.INTEGER) {
            switch (operator) {
                case GT:
                    return new MixedValue(Expression.DataType.BOOLEAN, lhs.integerValue > rhs.integerValue);
                case GE:
                    return new MixedValue(Expression.DataType.BOOLEAN, lhs.integerValue >= rhs.integerValue);
                case LT:
                    return new MixedValue(Expression.DataType.BOOLEAN, lhs.integerValue < rhs.integerValue);
                case LE:
                    return new MixedValue(Expression.DataType.BOOLEAN, lhs.integerValue <= rhs.integerValue);
                case EQ:
                    return new MixedValue(Expression.DataType.BOOLEAN, lhs.integerValue.equals(rhs.integerValue));
                case NEQ:
                    return new MixedValue(Expression.DataType.BOOLEAN, !lhs.integerValue.equals(rhs.integerValue));
                default:
                    throw new RuntimeException("Operation " + operator.name() + " on type " + lhs.type + " is illegal.");
            }
        }

        if (this.type == Expression.DataType.BOOLEAN) {
            switch (operator) {
                case EQ:
                    return new MixedValue(Expression.DataType.BOOLEAN, lhs.booleanValue == rhs.booleanValue);
                case NEQ:
                    return new MixedValue(Expression.DataType.BOOLEAN, lhs.booleanValue != rhs.booleanValue);
                default:
                    throw new RuntimeException("Operation " + operator.name() + " on type " + lhs.type + " is illegal.");
            }
        }

        throw new RuntimeException("Unable to perform comparision operation " + operator.name() + ".");
    }

    private Pair<MixedValue, MixedValue> unifyDataTypes(MixedValue lhs, MixedValue rhs) {
        if (lhs.type != rhs.type) {
            if (lhs.type == Expression.DataType.INTEGER && rhs.type == Expression.DataType.DECIMAL) {
                // casting lhs to decimal
                lhs.type = Expression.DataType.DECIMAL;
                lhs.decimalValue = new BigDecimal(lhs.integerValue);
                lhs.integerValue = null;
            } else if (lhs.type == Expression.DataType.DECIMAL && rhs.type == Expression.DataType.INTEGER) {
                // casting rhs to decimal
                rhs.type = Expression.DataType.DECIMAL;
                rhs.decimalValue = new BigDecimal(rhs.integerValue);
                rhs.integerValue = null;
            } else {
                throw new RuntimeException("Types not compatible: " + lhs.type.name() + " and " + rhs.type.name());
            }
        }
        return new Pair<>(lhs, rhs);
    }

    @Override
    public String toString() {
        switch (this.getType()) {
            case INTEGER:
                return this.integerValue.toString();
            case DECIMAL:
                return this.decimalValue.toString();
            case BOOLEAN:
                return this.booleanValue.toString();
            case STRING:
                return this.stringValue;
        }
        return null;
    }
}
