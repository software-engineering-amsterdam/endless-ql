package ast.visitors.evaluators;

import ast.model.expressions.Expression;
import com.sun.tools.javac.util.Pair;

import java.math.BigDecimal;

public class ExpressionResult {

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

    public static ExpressionResult createExpressionResult(Expression.DataType type, String text) {

        if (type == Expression.DataType.INTEGER) {
            Integer safeInteger = text.isEmpty() ? 0 : Integer.parseInt(text);
            return new ExpressionResult(type, safeInteger);
        } else if (type == Expression.DataType.DECIMAL) {
            BigDecimal safeDecimal = text.isEmpty() ? new BigDecimal(0) : new BigDecimal(text);
            return new ExpressionResult(type, safeDecimal);
        } else if (type == Expression.DataType.BOOLEAN) {
            // @TODO: Check it - I don't like the string here
            Boolean safeBoolean = text.toUpperCase().equals("TRUE");
            return new ExpressionResult(type, safeBoolean);
        } else if (type == Expression.DataType.STRING) {
            return new ExpressionResult(type, text);
        }
        return null;
    }

    public ExpressionResult(Expression.DataType type, String stringValue) {
        this.type = type;
        this.stringValue = stringValue;
    }

    public ExpressionResult(Expression.DataType type, BigDecimal decimalValue) {
        this.type = type;
        this.decimalValue = decimalValue;
    }

    public ExpressionResult(Expression.DataType type, Integer integerValue) {
        this.type = type;
        this.integerValue = integerValue;
    }

    public ExpressionResult(Expression.DataType type, Boolean booleanValue) {
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

    public Expression.DataType getType() {
        return type;
    }

    // unary operations

    // logical and arithmetical negation
    public ExpressionResult negate() {
        if (this.type == Expression.DataType.BOOLEAN) {
            return new ExpressionResult(this.type, !this.booleanValue);
        } else if (this.type == Expression.DataType.INTEGER) {
            return new ExpressionResult(this.type, -this.integerValue);
        } else if (this.type == Expression.DataType.DECIMAL) {
            return new ExpressionResult(this.type, this.decimalValue.negate());
        } else {
            throw new RuntimeException("Logical negation on type " + this.type + " is illegal.");
        }
    }

    // binary operations
    public ExpressionResult add(ExpressionResult rhs) {
        return this.binaryOperation(BinaryOperator.PLUS, rhs);
    }

    public ExpressionResult min(ExpressionResult rhs) {
        return this.binaryOperation(BinaryOperator.MINUS, rhs);
    }

    public ExpressionResult multiply(ExpressionResult rhs) {
        return this.binaryOperation(BinaryOperator.MULTIPLY, rhs);
    }

    public ExpressionResult divide(ExpressionResult rhs) {
        return this.binaryOperation(BinaryOperator.DIVIDE, rhs);
    }

    public ExpressionResult and(ExpressionResult rhs) {
        return this.binaryOperation(BinaryOperator.AND, rhs);
    }

    public ExpressionResult or(ExpressionResult rhs) {
        return this.binaryOperation(BinaryOperator.OR, rhs);
    }

    // comparision operators
    public ExpressionResult equals(ExpressionResult rhs) {
        return this.comparision(ComparisionOperator.EQ, rhs);
    }

    public ExpressionResult notEquals(ExpressionResult rhs) {
        return this.comparision(ComparisionOperator.NEQ, rhs);
    }

    public ExpressionResult greaterThan(ExpressionResult rhs) {
        return this.comparision(ComparisionOperator.GT, rhs);
    }

    public ExpressionResult greaterEqual(ExpressionResult rhs) {
        return this.comparision(ComparisionOperator.GE, rhs);
    }

    public ExpressionResult lessThan(ExpressionResult rhs) {
        return this.comparision(ComparisionOperator.LT, rhs);
    }

    public ExpressionResult lessEqual(ExpressionResult rhs) {
        return this.comparision(ComparisionOperator.LE, rhs);
    }

    private ExpressionResult binaryOperation(BinaryOperator operator, ExpressionResult secondResult) {

        // by default the types must be the same, except when there is a pair of integer and decimal, then the result will be decimal
        Pair<ExpressionResult, ExpressionResult> unifiedResults = unifyDataTypes(this, secondResult);

        ExpressionResult lhs = unifiedResults.fst;
        ExpressionResult rhs = unifiedResults.snd;

        // for string only concatenation is allowed (PLUS sign)
        if (lhs.type == Expression.DataType.STRING) {
            if (operator == BinaryOperator.PLUS) {
                return new ExpressionResult(lhs.type, lhs.stringValue + rhs.stringValue);
            } else {
                throw new RuntimeException("Operation " + operator.name() + " on type " + lhs.type + " is illegal.");
            }
        }

        if (this.type == Expression.DataType.DECIMAL) {
            if (operator == BinaryOperator.PLUS) {
                return new ExpressionResult(lhs.type, lhs.decimalValue.add(rhs.decimalValue));
            } else if (operator == BinaryOperator.MINUS) {
                return new ExpressionResult(lhs.type, lhs.decimalValue.min(rhs.decimalValue));
            } else if (operator == BinaryOperator.MULTIPLY) {
                return new ExpressionResult(lhs.type, lhs.decimalValue.multiply(rhs.decimalValue));
            } else if (operator == BinaryOperator.DIVIDE) {
                return new ExpressionResult(lhs.type, lhs.decimalValue.divide(rhs.decimalValue));
            } else {
                throw new RuntimeException("Operation " + operator.name() + " on type " + lhs.type + " is illegal.");
            }
        }

        if (this.type == Expression.DataType.INTEGER) {
            if (operator == BinaryOperator.PLUS) {
                return new ExpressionResult(lhs.type, lhs.integerValue + rhs.integerValue);
            } else if (operator == BinaryOperator.MINUS) {
                return new ExpressionResult(lhs.type, lhs.integerValue - rhs.integerValue);
            } else if (operator == BinaryOperator.MULTIPLY) {
                return new ExpressionResult(lhs.type, lhs.integerValue * rhs.integerValue);
            } else if (operator == BinaryOperator.DIVIDE) {
                return new ExpressionResult(lhs.type, lhs.integerValue / rhs.integerValue);
            } else {
                throw new RuntimeException("Operation " + operator.name() + " on type " + lhs.type + " is illegal.");
            }
        }

        if (this.type == Expression.DataType.BOOLEAN) {
            if (operator == BinaryOperator.AND) {
                return new ExpressionResult(lhs.type, lhs.booleanValue && rhs.booleanValue);
            } else if (operator == BinaryOperator.OR) {
                return new ExpressionResult(lhs.type, lhs.booleanValue || rhs.booleanValue);
            } else {
                throw new RuntimeException("Operation " + operator.name() + " on type " + lhs.type + " is illegal.");
            }
        }

        throw new RuntimeException("Unable to perform operation " + operator.name() + ".");

    }

    private ExpressionResult comparision(ComparisionOperator operator, ExpressionResult secondResult) {

        // by default the types must be the same, except when there is a pair of integer and decimal, then the result will be decimal
        Pair<ExpressionResult, ExpressionResult> unifiedResults = unifyDataTypes(this, secondResult);

        ExpressionResult lhs = unifiedResults.fst;
        ExpressionResult rhs = unifiedResults.snd;

        if (lhs.type == Expression.DataType.STRING) {
            if (operator == ComparisionOperator.EQ) {
                return new ExpressionResult(Expression.DataType.BOOLEAN, lhs.stringValue.equals(rhs.stringValue));
            } else if (operator == ComparisionOperator.NEQ) {
                return new ExpressionResult(Expression.DataType.BOOLEAN, !lhs.stringValue.equals(rhs.stringValue));
            } else {
                throw new RuntimeException("Operation " + operator.name() + " on type " + lhs.type + " is illegal.");
            }
        }

        if (this.type == Expression.DataType.DECIMAL) {
            if (operator == ComparisionOperator.GT) {
                return new ExpressionResult(Expression.DataType.BOOLEAN, lhs.decimalValue.compareTo(rhs.decimalValue) > 0);
            } else if (operator == ComparisionOperator.GE) {
                return new ExpressionResult(Expression.DataType.BOOLEAN, lhs.decimalValue.compareTo(rhs.decimalValue) >= 0);
            } else if (operator == ComparisionOperator.LT) {
                return new ExpressionResult(Expression.DataType.BOOLEAN, lhs.decimalValue.compareTo(rhs.decimalValue) < 0);
            } else if (operator == ComparisionOperator.LE) {
                return new ExpressionResult(Expression.DataType.BOOLEAN, lhs.decimalValue.compareTo(rhs.decimalValue) <= 0);
            } else if (operator == ComparisionOperator.EQ) {
                return new ExpressionResult(Expression.DataType.BOOLEAN, lhs.decimalValue.compareTo(rhs.decimalValue) == 0);
            } else if (operator == ComparisionOperator.NEQ) {
                return new ExpressionResult(Expression.DataType.BOOLEAN, lhs.decimalValue.compareTo(rhs.decimalValue) != 0);
            } else {
                throw new RuntimeException("Operation " + operator.name() + " on type " + lhs.type + " is illegal.");
            }
        }

        if (this.type == Expression.DataType.INTEGER) {
            if (operator == ComparisionOperator.GT) {
                return new ExpressionResult(Expression.DataType.BOOLEAN, lhs.integerValue > rhs.integerValue);
            } else if (operator == ComparisionOperator.GE) {
                return new ExpressionResult(Expression.DataType.BOOLEAN, lhs.integerValue >= rhs.integerValue);
            } else if (operator == ComparisionOperator.LT) {
                return new ExpressionResult(Expression.DataType.BOOLEAN, lhs.integerValue < rhs.integerValue);
            } else if (operator == ComparisionOperator.LE) {
                return new ExpressionResult(Expression.DataType.BOOLEAN, lhs.integerValue <= rhs.integerValue);
            } else if (operator == ComparisionOperator.EQ) {
                return new ExpressionResult(Expression.DataType.BOOLEAN, lhs.integerValue.equals(rhs.integerValue));
            } else if (operator == ComparisionOperator.NEQ) {
                return new ExpressionResult(Expression.DataType.BOOLEAN, !lhs.integerValue.equals(rhs.integerValue));
            } else {
                throw new RuntimeException("Operation " + operator.name() + " on type " + lhs.type + " is illegal.");
            }
        }

        if (this.type == Expression.DataType.BOOLEAN) {
            if (operator == ComparisionOperator.EQ) {
                return new ExpressionResult(Expression.DataType.BOOLEAN, lhs.booleanValue == rhs.booleanValue);
            } else if (operator == ComparisionOperator.NEQ) {
                return new ExpressionResult(Expression.DataType.BOOLEAN, lhs.booleanValue != rhs.booleanValue);
            } else {
                throw new RuntimeException("Operation " + operator.name() + " on type " + lhs.type + " is illegal.");
            }
        }

        throw new RuntimeException("Unable to perform comparision operation " + operator.name() + ".");
    }

    private Pair<ExpressionResult, ExpressionResult> unifyDataTypes(ExpressionResult lhs, ExpressionResult rhs) {
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
