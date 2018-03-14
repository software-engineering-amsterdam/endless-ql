package ql.analysis;

import ql.evaluation.IExpressionVisitor;
import ql.model.Form;
import ql.model.Question;
import ql.model.expression.Expression;
import ql.model.expression.ExpressionBinary;
import ql.model.expression.ExpressionIdentifier;
import ql.model.expression.ReturnType;
import ql.model.expression.binary.*;
import ql.model.expression.unary.ExpressionUnaryNeg;
import ql.model.expression.unary.ExpressionUnaryNot;
import ql.model.expression.variable.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TypeChecker implements IExpressionVisitor<ReturnType> {

    private final Form form;
    private final SymbolTable symbolTable;

    public TypeChecker(Form form, SymbolTable symbolTable) {
        this.form = form;
        this.symbolTable = symbolTable;
    }

    public void detectDuplicateQuestionsWithDifferentTypes() {
        Map<String, ReturnType> types = new HashMap<>();
        Set<String> duplicates = new HashSet<>();

        for (Question question : form.questions) {
            if (types.containsKey(question.name) && types.get(question.name) != question.type) {
                duplicates.add(question.name + " " + question.getLocation());
            } else {
                types.put(question.name, question.type);
            }
        }

        if (!duplicates.isEmpty()) {
            throw new IllegalArgumentException("Redeclaration of question(s) with different type: " + duplicates);
        }
    }

    public void typeCheck() {
        for (Question question : form.questions) {
            this.visit(question.condition);

            // Only check expression when it is a predefined expression
            if (question.isComputed()) {
                ReturnType defaultAnswerType = this.visit(question.defaultAnswer);

                // Any type of number expression can be assigned to another number type field
                ReturnType questionType = question.type.isNumber() ? ReturnType.NUMBER : question.type;

                // Check if question type is same as assigned expression type
                if (defaultAnswerType != questionType) {
                    throw new IllegalArgumentException("Invalid assignment: cannot assign " + defaultAnswerType + " to " + question.type +
                            question.getLocation());
                }
            }
        }
    }

    private ReturnType checkBinaryArithmetic(ExpressionBinary expression, String operation) {
        // Check whether operation can be applied to left and right expression
        boolean selfValid = expression.left.accept(this).isNumber()
                && expression.right.accept(this).isNumber();

        if (!selfValid) {
            throw new IllegalArgumentException("Invalid " + operation + ": non-numeric value in expression "
                    + expression.getLocation());
        }

        return ReturnType.NUMBER;
    }

    private ReturnType checkBinaryComparison(ExpressionBinary expression, String operation) {
        // Check whether operation can be applied to left and right expression
        boolean selfValid = expression.left.accept(this).isNumber()
                && expression.right.accept(this).isNumber();

        if (!selfValid) {
            throw new IllegalArgumentException("Invalid " + operation + ": non-numeric value in expression"
                    + expression.getLocation());
        }

        return ReturnType.BOOLEAN;
    }

    private ReturnType checkBinaryBoolean(ExpressionBinary expression, String operation) {
        // Check whether operation can be applied to left and right expression
        boolean selfValid = expression.left.accept(this) == ReturnType.BOOLEAN
                && expression.right.accept(this) == ReturnType.BOOLEAN;

        if (!selfValid) {
            throw new IllegalArgumentException("Invalid " + operation + ": non-boolean value in expression"
                    + expression.getLocation());
        }

        return ReturnType.BOOLEAN;
    }

    @Override
    public ReturnType visit(Expression expression) {
        return expression.accept(this);
    }

    @Override
    public ReturnType visit(ExpressionArithmeticDivide expression) {
        return checkBinaryArithmetic(expression, "division");
    }

    @Override
    public ReturnType visit(ExpressionArithmeticMultiply expression) {
        return checkBinaryArithmetic(expression, "multiplication");
    }

    @Override
    public ReturnType visit(ExpressionArithmeticSubtract expression) {
        return checkBinaryArithmetic(expression, "subtraction");
    }

    @Override
    public ReturnType visit(ExpressionArithmeticSum expression) {
        return checkBinaryArithmetic(expression, "addition");
    }

    @Override
    public ReturnType visit(ExpressionComparisonEq expression) {
        boolean selfValid = expression.left.accept(this) == expression.right.accept(this);

        if (!selfValid) {
            throw new IllegalArgumentException("Invalid EQ: comparing values of different types"
                    + expression.getLocation());
        }

        return ReturnType.BOOLEAN;
    }

    @Override
    public ReturnType visit(ExpressionComparisonGE expression) {
        return checkBinaryComparison(expression, "GE");
    }

    @Override
    public ReturnType visit(ExpressionComparisonGT expression) {
        return checkBinaryComparison(expression, "GT");
    }

    @Override
    public ReturnType visit(ExpressionComparisonLE expression) {
        return checkBinaryComparison(expression, "LE");
    }

    @Override
    public ReturnType visit(ExpressionComparisonLT expression) {
        return checkBinaryComparison(expression, "LT");
    }

    @Override
    public ReturnType visit(ExpressionLogicalAnd expression) {
        return checkBinaryBoolean(expression, "AND");
    }

    @Override
    public ReturnType visit(ExpressionLogicalOr expression) {
        return checkBinaryBoolean(expression, "OR");
    }

    @Override
    public ReturnType visit(ExpressionUnaryNot expression) {
        boolean selfValid = expression.value.accept(this) == ReturnType.BOOLEAN;

        if (!selfValid) {
            throw new IllegalArgumentException("Invalid NOT: non-boolean expression " + expression.getLocation());
        }

        return ReturnType.BOOLEAN;
    }

    @Override
    public ReturnType visit(ExpressionUnaryNeg expression) {
        boolean selfValid = expression.value.accept(this) == ReturnType.NUMBER;

        if (!selfValid) {
            throw new IllegalArgumentException("Invalid NEG: non-numeric expression " + expression.getLocation());
        }

        return ReturnType.NUMBER;
    }

    @Override
    public ReturnType visit(ExpressionVariableBoolean expression) {
        return ReturnType.BOOLEAN;
    }

    @Override
    public ReturnType visit(ExpressionVariableDate expression) {
        return ReturnType.DATE;
    }

    @Override
    public ReturnType visit(ExpressionVariableInteger expression) {
        return ReturnType.INTEGER;
    }

    @Override
    public ReturnType visit(ExpressionVariableDecimal expression) {
        return ReturnType.DECIMAL;
    }

    @Override
    public ReturnType visit(ExpressionVariableMoney expression) {
        return ReturnType.MONEY;
    }

    @Override
    public ReturnType visit(ExpressionVariableString expression) {
        return ReturnType.STRING;
    }

    @Override
    public ReturnType visit(ExpressionVariableUndefined expression) {
        return expression.getReturnType();
    }

    @Override
    public ReturnType visit(ExpressionIdentifier expression) {
        return this.symbolTable.getExpression(expression.identifier).accept(this);
    }
}
