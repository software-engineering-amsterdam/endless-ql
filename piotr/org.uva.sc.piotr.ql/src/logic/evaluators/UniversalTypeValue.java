package logic.evaluators;

import ast.model.expressions.Expression;
import com.sun.istack.internal.NotNull;
import com.sun.tools.javac.util.Pair;

import java.math.BigDecimal;

public class UniversalTypeValue {

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

    public static UniversalTypeValue createValue(@NotNull Expression.DataType type, String text) {

        if (type == Expression.DataType.INTEGER) {
            Integer safeInteger = text.isEmpty() ? 0 : Integer.parseInt(text);
            return new UniversalTypeValue(type, safeInteger);
        } else if (type == Expression.DataType.DECIMAL) {
            BigDecimal safeDecimal = text.isEmpty() ? new BigDecimal(0) : new BigDecimal(text);
            return new UniversalTypeValue(type, safeDecimal);
        } else if (type == Expression.DataType.BOOLEAN) {
            // @TODO: Check it - I don't like the string here
            Boolean safeBoolean = text.toUpperCase().equals("TRUE");
            return new UniversalTypeValue(type, safeBoolean);
        } else if (type == Expression.DataType.STRING) {
            return new UniversalTypeValue(type, text);
        }
        return null;
    }

    public UniversalTypeValue(@NotNull Expression.DataType type, @NotNull String stringValue) {
        this.type = type;
        this.stringValue = stringValue;
    }

    public UniversalTypeValue(@NotNull Expression.DataType type, @NotNull BigDecimal decimalValue) {
        this.type = type;
        this.decimalValue = decimalValue;
    }

    public UniversalTypeValue(@NotNull Expression.DataType type, @NotNull Integer integerValue) {
        this.type = type;
        this.integerValue = integerValue;
    }

    public UniversalTypeValue(@NotNull Expression.DataType type, @NotNull Boolean booleanValue) {
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
            // TODO: inform observer
            System.out.println("String value changed to: \"" + stringValue + "\"");
        } else {
            throw new RuntimeException("Illegal value assignment: String value cannot be assigned to " + this.type.name() + " type.");
        }
    }

    public void setDecimalValue(BigDecimal decimalValue) {
        if (this.type == Expression.DataType.DECIMAL) {
            this.decimalValue = decimalValue;
            // TODO: inform observer
            System.out.println("Decimal value changed to: " + decimalValue);
        } else {
            throw new RuntimeException("Illegal value assignment: Decimal value cannot be assigned to " + this.type.name() + " type.");
        }
    }

    public void setIntegerValue(Integer integerValue) {
        if (this.type == Expression.DataType.INTEGER) {
            this.integerValue = integerValue;
            // TODO: inform observer
            System.out.println("Integer value changed to: " + integerValue);
        } else {
            throw new RuntimeException("Illegal value assignment: Integer value cannot be assigned to " + this.type.name() + " type.");
        }
    }

    public void setBooleanValue(Boolean booleanValue) {
        if (this.type == Expression.DataType.BOOLEAN) {
            this.booleanValue = booleanValue;
            // TODO: inform observer
            System.out.println("Boolean value changed to: " + booleanValue);
        } else {
            throw new RuntimeException("Illegal value assignment: Boolean value cannot be assigned to " + this.type.name() + " type.");
        }
    }

    public Expression.DataType getType() {
        return type;
    }

    // unary operations

    // logical and arithmetical negation
    public UniversalTypeValue negate() {
        if (this.type == Expression.DataType.BOOLEAN) {
            return new UniversalTypeValue(this.type, !this.booleanValue);
        } else if (this.type == Expression.DataType.INTEGER) {
            return new UniversalTypeValue(this.type, -this.integerValue);
        } else if (this.type == Expression.DataType.DECIMAL) {
            return new UniversalTypeValue(this.type, this.decimalValue.negate());
        } else {
            throw new RuntimeException("Logical negation on type " + this.type + " is illegal.");
        }
    }

    // binary operations
    public UniversalTypeValue add(UniversalTypeValue rhs) {
        return this.binaryOperation(BinaryOperator.PLUS, rhs);
    }

    public UniversalTypeValue subtract(UniversalTypeValue rhs) {
        return this.binaryOperation(BinaryOperator.MINUS, rhs);
    }

    public UniversalTypeValue multiply(UniversalTypeValue rhs) {
        return this.binaryOperation(BinaryOperator.MULTIPLY, rhs);
    }

    public UniversalTypeValue divide(UniversalTypeValue rhs) {
        return this.binaryOperation(BinaryOperator.DIVIDE, rhs);
    }

    public UniversalTypeValue and(UniversalTypeValue rhs) {
        return this.binaryOperation(BinaryOperator.AND, rhs);
    }

    public UniversalTypeValue or(UniversalTypeValue rhs) {
        return this.binaryOperation(BinaryOperator.OR, rhs);
    }

    // comparision operators
    public UniversalTypeValue equals(UniversalTypeValue rhs) {
        return this.comparision(ComparisionOperator.EQ, rhs);
    }

    public UniversalTypeValue notEquals(UniversalTypeValue rhs) {
        return this.comparision(ComparisionOperator.NEQ, rhs);
    }

    public UniversalTypeValue greaterThan(UniversalTypeValue rhs) {
        return this.comparision(ComparisionOperator.GT, rhs);
    }

    public UniversalTypeValue greaterEqual(UniversalTypeValue rhs) {
        return this.comparision(ComparisionOperator.GE, rhs);
    }

    public UniversalTypeValue lessThan(UniversalTypeValue rhs) {
        return this.comparision(ComparisionOperator.LT, rhs);
    }

    public UniversalTypeValue lessEqual(UniversalTypeValue rhs) {
        return this.comparision(ComparisionOperator.LE, rhs);
    }

    private UniversalTypeValue binaryOperation(BinaryOperator operator, UniversalTypeValue secondResult) {

        // by default the types must be the same, except when there is a pair of integer and decimal, then the result will be decimal
        Pair<UniversalTypeValue, UniversalTypeValue> unifiedResults = unifyDataTypes(this, secondResult);

        UniversalTypeValue lhs = unifiedResults.fst;
        UniversalTypeValue rhs = unifiedResults.snd;

        // for string only concatenation is allowed (PLUS sign)
        if (lhs.type == Expression.DataType.STRING) {
            if (operator == BinaryOperator.PLUS) {
                return new UniversalTypeValue(lhs.type, lhs.stringValue + rhs.stringValue);
            } else {
                throw new RuntimeException("Operation " + operator.name() + " on type " + lhs.type + " is illegal.");
            }
        }

        if (this.type == Expression.DataType.DECIMAL) {
            if (operator == BinaryOperator.PLUS) {
                return new UniversalTypeValue(lhs.type, lhs.decimalValue.add(rhs.decimalValue));
            } else if (operator == BinaryOperator.MINUS) {
                return new UniversalTypeValue(lhs.type, lhs.decimalValue.subtract(rhs.decimalValue));
            } else if (operator == BinaryOperator.MULTIPLY) {
                return new UniversalTypeValue(lhs.type, lhs.decimalValue.multiply(rhs.decimalValue));
            } else if (operator == BinaryOperator.DIVIDE) {
                return new UniversalTypeValue(lhs.type, lhs.decimalValue.divide(rhs.decimalValue));
            } else {
                throw new RuntimeException("Operation " + operator.name() + " on type " + lhs.type + " is illegal.");
            }
        }

        if (this.type == Expression.DataType.INTEGER) {
            if (operator == BinaryOperator.PLUS) {
                return new UniversalTypeValue(lhs.type, lhs.integerValue + rhs.integerValue);
            } else if (operator == BinaryOperator.MINUS) {
                return new UniversalTypeValue(lhs.type, lhs.integerValue - rhs.integerValue);
            } else if (operator == BinaryOperator.MULTIPLY) {
                return new UniversalTypeValue(lhs.type, lhs.integerValue * rhs.integerValue);
            } else if (operator == BinaryOperator.DIVIDE) {
                return new UniversalTypeValue(lhs.type, lhs.integerValue / rhs.integerValue);
            } else {
                throw new RuntimeException("Operation " + operator.name() + " on type " + lhs.type + " is illegal.");
            }
        }

        if (this.type == Expression.DataType.BOOLEAN) {
            if (operator == BinaryOperator.AND) {
                return new UniversalTypeValue(lhs.type, lhs.booleanValue && rhs.booleanValue);
            } else if (operator == BinaryOperator.OR) {
                return new UniversalTypeValue(lhs.type, lhs.booleanValue || rhs.booleanValue);
            } else {
                throw new RuntimeException("Operation " + operator.name() + " on type " + lhs.type + " is illegal.");
            }
        }

        throw new RuntimeException("Unable to perform operation " + operator.name() + ".");

    }

    private UniversalTypeValue comparision(ComparisionOperator operator, UniversalTypeValue secondResult) {

        // by default the types must be the same, except when there is a pair of integer and decimal, then the result will be decimal
        Pair<UniversalTypeValue, UniversalTypeValue> unifiedResults = unifyDataTypes(this, secondResult);

        UniversalTypeValue lhs = unifiedResults.fst;
        UniversalTypeValue rhs = unifiedResults.snd;

        if (lhs.type == Expression.DataType.STRING) {
            if (operator == ComparisionOperator.EQ) {
                return new UniversalTypeValue(Expression.DataType.BOOLEAN, lhs.stringValue.equals(rhs.stringValue));
            } else if (operator == ComparisionOperator.NEQ) {
                return new UniversalTypeValue(Expression.DataType.BOOLEAN, !lhs.stringValue.equals(rhs.stringValue));
            } else {
                throw new RuntimeException("Operation " + operator.name() + " on type " + lhs.type + " is illegal.");
            }
        }

        if (this.type == Expression.DataType.DECIMAL) {
            if (operator == ComparisionOperator.GT) {
                return new UniversalTypeValue(Expression.DataType.BOOLEAN, lhs.decimalValue.compareTo(rhs.decimalValue) > 0);
            } else if (operator == ComparisionOperator.GE) {
                return new UniversalTypeValue(Expression.DataType.BOOLEAN, lhs.decimalValue.compareTo(rhs.decimalValue) >= 0);
            } else if (operator == ComparisionOperator.LT) {
                return new UniversalTypeValue(Expression.DataType.BOOLEAN, lhs.decimalValue.compareTo(rhs.decimalValue) < 0);
            } else if (operator == ComparisionOperator.LE) {
                return new UniversalTypeValue(Expression.DataType.BOOLEAN, lhs.decimalValue.compareTo(rhs.decimalValue) <= 0);
            } else if (operator == ComparisionOperator.EQ) {
                return new UniversalTypeValue(Expression.DataType.BOOLEAN, lhs.decimalValue.compareTo(rhs.decimalValue) == 0);
            } else if (operator == ComparisionOperator.NEQ) {
                return new UniversalTypeValue(Expression.DataType.BOOLEAN, lhs.decimalValue.compareTo(rhs.decimalValue) != 0);
            } else {
                throw new RuntimeException("Operation " + operator.name() + " on type " + lhs.type + " is illegal.");
            }
        }

        if (this.type == Expression.DataType.INTEGER) {
            if (operator == ComparisionOperator.GT) {
                return new UniversalTypeValue(Expression.DataType.BOOLEAN, lhs.integerValue > rhs.integerValue);
            } else if (operator == ComparisionOperator.GE) {
                return new UniversalTypeValue(Expression.DataType.BOOLEAN, lhs.integerValue >= rhs.integerValue);
            } else if (operator == ComparisionOperator.LT) {
                return new UniversalTypeValue(Expression.DataType.BOOLEAN, lhs.integerValue < rhs.integerValue);
            } else if (operator == ComparisionOperator.LE) {
                return new UniversalTypeValue(Expression.DataType.BOOLEAN, lhs.integerValue <= rhs.integerValue);
            } else if (operator == ComparisionOperator.EQ) {
                return new UniversalTypeValue(Expression.DataType.BOOLEAN, lhs.integerValue.equals(rhs.integerValue));
            } else if (operator == ComparisionOperator.NEQ) {
                return new UniversalTypeValue(Expression.DataType.BOOLEAN, !lhs.integerValue.equals(rhs.integerValue));
            } else {
                throw new RuntimeException("Operation " + operator.name() + " on type " + lhs.type + " is illegal.");
            }
        }

        if (this.type == Expression.DataType.BOOLEAN) {
            if (operator == ComparisionOperator.EQ) {
                return new UniversalTypeValue(Expression.DataType.BOOLEAN, lhs.booleanValue == rhs.booleanValue);
            } else if (operator == ComparisionOperator.NEQ) {
                return new UniversalTypeValue(Expression.DataType.BOOLEAN, lhs.booleanValue != rhs.booleanValue);
            } else {
                throw new RuntimeException("Operation " + operator.name() + " on type " + lhs.type + " is illegal.");
            }
        }

        throw new RuntimeException("Unable to perform comparision operation " + operator.name() + ".");
    }

    private Pair<UniversalTypeValue, UniversalTypeValue> unifyDataTypes(UniversalTypeValue lhs, UniversalTypeValue rhs) {
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
        return "ExpressionResult{" +
                "type=" + type +
                ", stringValue='" + stringValue + '\'' +
                ", decimalValue=" + decimalValue +
                ", integerValue=" + integerValue +
                ", booleanValue=" + booleanValue +
                '}';
    }
}
