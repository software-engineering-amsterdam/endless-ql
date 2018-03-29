package ql.analysis.error;

import ql.evaluation.SymbolTable;
import ql.model.Form;
import ql.model.expression.Identifier;
import ql.model.expression.ReturnType;
import ql.model.expression.binary.*;
import ql.model.expression.constant.*;
import ql.model.expression.unary.NegationExpression;
import ql.model.expression.unary.NotExpression;
import ql.model.statement.IfBlock;
import ql.model.statement.IfElseBlock;
import ql.model.statement.Question;
import ql.visitor.QLVisitor;

public class TypeChecker extends QLVisitor<ReturnType> implements IQLErrorAnalysis {

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

    private ReturnType checkBinaryArithmetic(BinaryExpression expression, String operation) {
        ReturnType leftType = expression.getLeft().accept(this);
        ReturnType rightType = expression.getRight().accept(this);

        // Check if arithmetic is applied on two number expressions
        if (!leftType.isCompatibleArithmetic(rightType)) {
            throw new IllegalArgumentException("Invalid " + operation + ": non-numeric value in expression "
                    + expression.getLocation());
        }

        // Return strongest of the two number types that the operation is applied to
        return leftType.getStrongestNumber(rightType);
    }

    private ReturnType checkBinaryComparison(BinaryExpression expression, String operation) {
        ReturnType leftType = expression.getLeft().accept(this);
        ReturnType rightType = expression.getRight().accept(this);

        if (!leftType.isCompatibleComparison(rightType)) {
            throw new IllegalArgumentException("Invalid " + operation + ": non-numeric value in expression"
                    + expression.getLocation());
        }

        return ReturnType.BOOLEAN;
    }

    private ReturnType checkBinaryBoolean(BinaryExpression expression, String operation) {
        ReturnType leftType = expression.getLeft().accept(this);
        ReturnType rightType = expression.getRight().accept(this);

        // Check whether operation can be applied to left and right expression
        boolean selfValid = leftType == ReturnType.BOOLEAN && rightType == ReturnType.BOOLEAN;

        if (!selfValid) {
            throw new IllegalArgumentException("Invalid " + operation + ": non-boolean value in expression"
                    + expression.getLocation());
        }

        return ReturnType.BOOLEAN;
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
    public ReturnType visit(Question question) {
        // Check type of computed answer is same as the question type
        if (question.isComputed()) {
            ReturnType computedAnswerType = this.visit(question.getComputedAnswer());

            if (!computedAnswerType.canBeAssignedTo(question.getType())) {
                throw new IllegalArgumentException("Invalid assignment: cannot assign " + computedAnswerType
                        + " to " + question.getType() + question.getLocation());
            }
        }

        return question.getType();
    }

    @Override
    public ReturnType visit(DivisionExpression expression) {
        return checkBinaryArithmetic(expression, "division");
    }

    @Override
    public ReturnType visit(MultiplicationExpression expression) {
        return checkBinaryArithmetic(expression, "multiplication");
    }

    @Override
    public ReturnType visit(SubtractionExpression expression) {
        return checkBinaryArithmetic(expression, "subtraction");
    }

    @Override
    public ReturnType visit(SumExpression expression) {
        return checkBinaryArithmetic(expression, "addition");
    }

    @Override
    public ReturnType visit(EqualExpression expression) {
        ReturnType leftType = expression.getLeft().accept(this);
        ReturnType rightType = expression.getRight().accept(this);

        if (!leftType.isCompatibleEquality(rightType)) {
            throw new IllegalArgumentException("Invalid equals: cannot check for equality between "
                    + leftType + " and " + rightType + " " + expression.getLocation());
        }

        return ReturnType.BOOLEAN;
    }

    @Override
    public ReturnType visit(GreaterEqualExpression expression) {
        return checkBinaryComparison(expression, "GE");
    }

    @Override
    public ReturnType visit(GreaterThanExpression expression) {
        return checkBinaryComparison(expression, "GT");
    }

    @Override
    public ReturnType visit(LessEqualExpression expression) {
        return checkBinaryComparison(expression, "LE");
    }

    @Override
    public ReturnType visit(LessThanExpression expression) {
        return checkBinaryComparison(expression, "LT");
    }

    @Override
    public ReturnType visit(AndExpression expression) {
        return checkBinaryBoolean(expression, "AND");
    }

    @Override
    public ReturnType visit(OrExpression expression) {
        return checkBinaryBoolean(expression, "OR");
    }

    @Override
    public ReturnType visit(NotExpression expression) {
        ReturnType expressionType = expression.getOperand().accept(this);

        if (expressionType != ReturnType.BOOLEAN) {
            throw new IllegalArgumentException("Invalid NOT: non-boolean expression " + expression.getLocation());
        }

        return ReturnType.BOOLEAN;
    }

    @Override
    public ReturnType visit(NegationExpression expression) {
        ReturnType expressionType = expression.getOperand().accept(this);

        if (!expressionType.isNumber()) {
            throw new IllegalArgumentException("Invalid negation: non-numeric expression " + expression.getLocation());
        }

        return expressionType;
    }

    @Override
    public ReturnType visit(BooleanConstant constant) {
        return ReturnType.BOOLEAN;
    }

    @Override
    public ReturnType visit(DateConstant constant) {
        return ReturnType.DATE;
    }

    @Override
    public ReturnType visit(IntegerConstant constant) {
        return ReturnType.INTEGER;
    }

    @Override
    public ReturnType visit(DecimalConstant constant) {
        return ReturnType.DECIMAL;
    }

    @Override
    public ReturnType visit(MoneyConstant constant) {
        return ReturnType.MONEY;
    }

    @Override
    public ReturnType visit(StringConstant constant) {
        return ReturnType.STRING;
    }

    @Override
    public ReturnType visit(UndefinedConstant constant) {
        return constant.getReturnType();
    }

    @Override
    public ReturnType visit(Identifier identifier) {
        return this.symbolTable.getExpression(identifier.getIdentifier()).accept(this);
    }
}
