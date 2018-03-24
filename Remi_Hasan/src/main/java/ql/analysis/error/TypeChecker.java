package ql.analysis.error;

import ql.QLBaseVisitor;
import ql.evaluation.SymbolTable;
import ql.model.Form;
import ql.model.IfBlock;
import ql.model.IfElseBlock;
import ql.model.Question;
import ql.model.expression.ExpressionBinary;
import ql.model.expression.ExpressionIdentifier;
import ql.model.expression.ReturnType;
import ql.model.expression.binary.*;
import ql.model.expression.unary.ExpressionUnaryNeg;
import ql.model.expression.unary.ExpressionUnaryNot;
import ql.model.expression.variable.*;

public class TypeChecker extends QLBaseVisitor<ReturnType> implements IQLErrorAnalysis {

    private SymbolTable symbolTable;

    @Override
    public void analyze(Form form, SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
        form.accept(this);
    }

    private void checkIfCondition(IfBlock ifBlock) {
        ReturnType conditionType = ifBlock.getCondition().accept(this);
        if (conditionType != ReturnType.BOOLEAN) {
            throw new IllegalArgumentException("Invalid if: condition of type " + conditionType + " instead of BOOLEAN "
                    + ifBlock.getLocation());
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
    public ReturnType visit(Question question) {
        // Check type of computed answer is same as the question type
        if (question.isComputed()) {
            ReturnType computedAnswerType = this.visit(question.computedAnswer);

            if (!computedAnswerType.canBeAssignedTo(question.type)) {
                throw new IllegalArgumentException("Invalid assignment: cannot assign " + computedAnswerType
                        + " to " + question.type + question.getLocation());
            }
        }

        return question.type;
    }

    @Override
    public ReturnType visit(IfBlock ifBlock) {
        this.checkIfCondition(ifBlock);

        // Visit if statements using the base visitor
        return super.visit(ifBlock);
    }

    @Override
    public ReturnType visit(IfElseBlock ifElseBlock) {
        this.checkIfCondition(ifElseBlock);

        // Visit if/else statements using the base visitor
        return super.visit(ifElseBlock);
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
