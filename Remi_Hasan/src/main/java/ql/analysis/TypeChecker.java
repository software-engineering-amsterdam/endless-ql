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
            if (types.containsKey(question.identifier) && types.get(question.identifier) != question.type) {
                duplicates.add(question.identifier + " " + question.getLocation());
            } else {
                types.put(question.identifier, question.type);
            }
        }

        if (!duplicates.isEmpty()) {
            throw new IllegalArgumentException("Redeclaration of question(s) with different type: " + duplicates);
        }
    }

    public void typeCheck() {
        for (Question question : form.questions) {
            this.visit(question.condition);

            // Check type of computed answer is same as the question type
            if (question.isComputed()) {
                ReturnType computedAnswerType = this.visit(question.computedAnswer);

                if (!computedAnswerType.canBeAssignedTo(question.type)) {
                    throw new IllegalArgumentException("Invalid assignment: cannot assign " + computedAnswerType
                            + " to " + question.type + question.getLocation());
                }
            }
        }
    }

    private ReturnType checkBinaryArithmetic(ExpressionBinary expression, String operation) {
        ReturnType leftType = expression.left.accept(this);
        ReturnType rightType = expression.right.accept(this);

        // Check if arithmetic is applied on two number expressions
        if (!leftType.isCompatibleArithmetic(rightType)) {
            throw new IllegalArgumentException("Invalid " + operation + ": non-numeric value in expression "
                    + expression.getLocation());
        }

        // Return strongest of the two number types that the operation is applied to
        return leftType.getStrongestNumber(rightType);
    }

    private ReturnType checkBinaryComparison(ExpressionBinary expression, String operation) {
        ReturnType leftType = expression.left.accept(this);
        ReturnType rightType = expression.right.accept(this);

        if (!leftType.isCompatibleComparison(rightType)) {
            throw new IllegalArgumentException("Invalid " + operation + ": non-numeric value in expression"
                    + expression.getLocation());
        }

        return ReturnType.BOOLEAN;
    }

    private ReturnType checkBinaryBoolean(ExpressionBinary expression, String operation) {
        ReturnType leftType = expression.left.accept(this);
        ReturnType rightType = expression.right.accept(this);

        // Check whether operation can be applied to left and right expression
        boolean selfValid = leftType == ReturnType.BOOLEAN && rightType == ReturnType.BOOLEAN;

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
        ReturnType leftType = expression.left.accept(this);
        ReturnType rightType = expression.right.accept(this);

        if (!leftType.isCompatibleEquality(rightType)) {
            throw new IllegalArgumentException("Invalid equals: cannot check for equality between "
                    + leftType + " and " + rightType + " " + expression.getLocation());
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
        ReturnType expressionType = expression.value.accept(this);

        if (expressionType != ReturnType.BOOLEAN) {
            throw new IllegalArgumentException("Invalid NOT: non-boolean expression " + expression.getLocation());
        }

        return ReturnType.BOOLEAN;
    }

    @Override
    public ReturnType visit(ExpressionUnaryNeg expression) {
        ReturnType expressionType = expression.value.accept(this);

        if (!expressionType.isNumber()) {
            throw new IllegalArgumentException("Invalid negation: non-numeric expression " + expression.getLocation());
        }

        return expressionType;
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
