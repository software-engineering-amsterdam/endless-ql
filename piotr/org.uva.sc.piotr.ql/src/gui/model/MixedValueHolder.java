package gui.model;

import ast.model.expressions.Expression;
import com.sun.istack.internal.NotNull;
import com.sun.tools.javac.util.Pair;

import java.math.BigDecimal;

// TODO: Should the methods return new instances od MixedValueHolder, or rather modified (possibly) left one?
public class MixedValueHolder {

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

    public static MixedValueHolder createValueHolder(@NotNull Expression.DataType type, String text) {

        switch (type) {
            case INTEGER:
                Integer safeInteger = text.isEmpty() ? 0 : Integer.parseInt(text);
                return new MixedValueHolder(type, safeInteger);
            case DECIMAL:
                BigDecimal safeDecimal = text.isEmpty() ? new BigDecimal(0) : new BigDecimal(text);
                return new MixedValueHolder(type, safeDecimal);
            case BOOLEAN:
                // @TODO: Check it - I don't like the string here
                Boolean safeBoolean = text.toUpperCase().equals("TRUE");
                return new MixedValueHolder(type, safeBoolean);
            case STRING:
                return new MixedValueHolder(type, text);
        }
        return null;
    }

    private MixedValueHolder(@NotNull Expression.DataType type, @NotNull String stringValue) {
        this.type = type;
        this.stringValue = stringValue;
    }

    private MixedValueHolder(@NotNull Expression.DataType type, @NotNull BigDecimal decimalValue) {
        this.type = type;
        this.decimalValue = decimalValue;
    }

    private MixedValueHolder(@NotNull Expression.DataType type, @NotNull Integer integerValue) {
        this.type = type;
        this.integerValue = integerValue;
    }

    public MixedValueHolder(@NotNull Expression.DataType type, @NotNull Boolean booleanValue) {
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
    public MixedValueHolder negate() {
        switch (this.type) {
            case BOOLEAN:
                return new MixedValueHolder(this.type, !this.booleanValue);
            case INTEGER:
                return new MixedValueHolder(this.type, -this.integerValue);
            case DECIMAL:
                return new MixedValueHolder(this.type, this.decimalValue.negate());
            default:
                throw new RuntimeException("Logical negation on type " + this.type + " is illegal.");
        }
    }

    // binary operations
    public MixedValueHolder add(MixedValueHolder rhs) {
        return this.binaryOperation(BinaryOperator.PLUS, rhs);
    }

    public MixedValueHolder subtract(MixedValueHolder rhs) {
        return this.binaryOperation(BinaryOperator.MINUS, rhs);
    }

    public MixedValueHolder multiply(MixedValueHolder rhs) {
        return this.binaryOperation(BinaryOperator.MULTIPLY, rhs);
    }

    public MixedValueHolder divide(MixedValueHolder rhs) {
        return this.binaryOperation(BinaryOperator.DIVIDE, rhs);
    }

    public MixedValueHolder and(MixedValueHolder rhs) {
        return this.binaryOperation(BinaryOperator.AND, rhs);
    }

    public MixedValueHolder or(MixedValueHolder rhs) {
        return this.binaryOperation(BinaryOperator.OR, rhs);
    }

    // comparision operators
    public MixedValueHolder equals(MixedValueHolder rhs) {
        return this.comparision(ComparisionOperator.EQ, rhs);
    }

    public MixedValueHolder notEquals(MixedValueHolder rhs) {
        return this.comparision(ComparisionOperator.NEQ, rhs);
    }

    public MixedValueHolder greaterThan(MixedValueHolder rhs) {
        return this.comparision(ComparisionOperator.GT, rhs);
    }

    public MixedValueHolder greaterEqual(MixedValueHolder rhs) {
        return this.comparision(ComparisionOperator.GE, rhs);
    }

    public MixedValueHolder lessThan(MixedValueHolder rhs) {
        return this.comparision(ComparisionOperator.LT, rhs);
    }

    public MixedValueHolder lessEqual(MixedValueHolder rhs) {
        return this.comparision(ComparisionOperator.LE, rhs);
    }

    private MixedValueHolder binaryOperation(BinaryOperator operator, MixedValueHolder secondResult) {

        // by default the types must be the same, except when there is a pair of integer and decimal, then the result will be decimal
        Pair<MixedValueHolder, MixedValueHolder> unifiedResults = unifyDataTypes(this, secondResult);

        MixedValueHolder lhs = unifiedResults.fst;
        MixedValueHolder rhs = unifiedResults.snd;

        // for string only concatenation is allowed (PLUS sign)
        if (lhs.type == Expression.DataType.STRING) {
            if (operator == BinaryOperator.PLUS) {
                return new MixedValueHolder(lhs.type, lhs.stringValue + rhs.stringValue);
            } else {
                throw new RuntimeException("Operation " + operator.name() + " on type " + lhs.type + " is illegal.");
            }
        }

        if (this.type == Expression.DataType.DECIMAL) {
            switch (operator) {
                case PLUS:
                    return new MixedValueHolder(lhs.type, lhs.decimalValue.add(rhs.decimalValue));
                case MINUS:
                    return new MixedValueHolder(lhs.type, lhs.decimalValue.subtract(rhs.decimalValue));
                case MULTIPLY:
                    return new MixedValueHolder(lhs.type, lhs.decimalValue.multiply(rhs.decimalValue));
                case DIVIDE:
                    return new MixedValueHolder(lhs.type, lhs.decimalValue.divide(rhs.decimalValue, 4, BigDecimal.ROUND_DOWN));
                default:
                    throw new RuntimeException("Operation " + operator.name() + " on type " + lhs.type + " is illegal.");
            }
        }

        if (this.type == Expression.DataType.INTEGER) {
            switch (operator) {
                case PLUS:
                    return new MixedValueHolder(lhs.type, lhs.integerValue + rhs.integerValue);
                case MINUS:
                    return new MixedValueHolder(lhs.type, lhs.integerValue - rhs.integerValue);
                case MULTIPLY:
                    return new MixedValueHolder(lhs.type, lhs.integerValue * rhs.integerValue);
                case DIVIDE:
                    return new MixedValueHolder(lhs.type, lhs.integerValue / rhs.integerValue);
                default:
                    throw new RuntimeException("Operation " + operator.name() + " on type " + lhs.type + " is illegal.");
            }
        }

        if (this.type == Expression.DataType.BOOLEAN) {
            switch (operator) {
                case AND:
                    return new MixedValueHolder(lhs.type, lhs.booleanValue && rhs.booleanValue);
                case OR:
                    return new MixedValueHolder(lhs.type, lhs.booleanValue || rhs.booleanValue);
                default:
                    throw new RuntimeException("Operation " + operator.name() + " on type " + lhs.type + " is illegal.");
            }
        }

        throw new RuntimeException("Unable to perform operation " + operator.name() + ".");

    }

    private MixedValueHolder comparision(ComparisionOperator operator, MixedValueHolder secondResult) {

        // by default the types must be the same, except when there is a pair of integer and decimal, then the result will be decimal
        Pair<MixedValueHolder, MixedValueHolder> unifiedResults = unifyDataTypes(this, secondResult);

        MixedValueHolder lhs = unifiedResults.fst;
        MixedValueHolder rhs = unifiedResults.snd;

        if (lhs.type == Expression.DataType.STRING) {
            switch (operator) {
                case EQ:
                    return new MixedValueHolder(Expression.DataType.BOOLEAN, lhs.stringValue.equals(rhs.stringValue));
                case NEQ:
                    return new MixedValueHolder(Expression.DataType.BOOLEAN, !lhs.stringValue.equals(rhs.stringValue));
                default:
                    throw new RuntimeException("Operation " + operator.name() + " on type " + lhs.type + " is illegal.");
            }
        }

        if (this.type == Expression.DataType.DECIMAL) {
            switch (operator) {
                case GT:
                    return new MixedValueHolder(Expression.DataType.BOOLEAN, lhs.decimalValue.compareTo(rhs.decimalValue) > 0);
                case GE:
                    return new MixedValueHolder(Expression.DataType.BOOLEAN, lhs.decimalValue.compareTo(rhs.decimalValue) >= 0);
                case LT:
                    return new MixedValueHolder(Expression.DataType.BOOLEAN, lhs.decimalValue.compareTo(rhs.decimalValue) < 0);
                case LE:
                    return new MixedValueHolder(Expression.DataType.BOOLEAN, lhs.decimalValue.compareTo(rhs.decimalValue) <= 0);
                case EQ:
                    return new MixedValueHolder(Expression.DataType.BOOLEAN, lhs.decimalValue.compareTo(rhs.decimalValue) == 0);
                case NEQ:
                    return new MixedValueHolder(Expression.DataType.BOOLEAN, lhs.decimalValue.compareTo(rhs.decimalValue) != 0);
                default:
                    throw new RuntimeException("Operation " + operator.name() + " on type " + lhs.type + " is illegal.");
            }
        }

        if (this.type == Expression.DataType.INTEGER) {
            switch (operator) {
                case GT:
                    return new MixedValueHolder(Expression.DataType.BOOLEAN, lhs.integerValue > rhs.integerValue);
                case GE:
                    return new MixedValueHolder(Expression.DataType.BOOLEAN, lhs.integerValue >= rhs.integerValue);
                case LT:
                    return new MixedValueHolder(Expression.DataType.BOOLEAN, lhs.integerValue < rhs.integerValue);
                case LE:
                    return new MixedValueHolder(Expression.DataType.BOOLEAN, lhs.integerValue <= rhs.integerValue);
                case EQ:
                    return new MixedValueHolder(Expression.DataType.BOOLEAN, lhs.integerValue.equals(rhs.integerValue));
                case NEQ:
                    return new MixedValueHolder(Expression.DataType.BOOLEAN, !lhs.integerValue.equals(rhs.integerValue));
                default:
                    throw new RuntimeException("Operation " + operator.name() + " on type " + lhs.type + " is illegal.");
            }
        }

        if (this.type == Expression.DataType.BOOLEAN) {
            switch (operator) {
                case EQ:
                    return new MixedValueHolder(Expression.DataType.BOOLEAN, lhs.booleanValue == rhs.booleanValue);
                case NEQ:
                    return new MixedValueHolder(Expression.DataType.BOOLEAN, lhs.booleanValue != rhs.booleanValue);
                default:
                    throw new RuntimeException("Operation " + operator.name() + " on type " + lhs.type + " is illegal.");
            }
        }

        throw new RuntimeException("Unable to perform comparision operation " + operator.name() + ".");
    }

    private Pair<MixedValueHolder, MixedValueHolder> unifyDataTypes(MixedValueHolder lhs, MixedValueHolder rhs) {
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
