package org.uva.ql.validation;

import com.sun.istack.internal.NotNull;
import org.uva.ql.ast.*;
import org.uva.ql.ast.expression.ParameterGroup;
import org.uva.ql.ast.expression.binary.*;
import org.uva.ql.ast.expression.unary.*;
import org.uva.ql.ast.type.*;
import org.uva.ql.visitor.*;

import java.util.logging.Logger;

public class TypeChecker  extends Checker
        implements StatementVisitor<Type, String>, ExpressionVisitor<Type, String>, TypeVisitor<Type, String>  {

    private SymbolTable symbolTable;
    private Form form;
    private final String ERROR_MESSAGE = "Type checking error at: ";

    TypeChecker(Form form, SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
        this.form = form;
    }

    @Override
    public void runCheck() {
        for (Statement statement : form.getStatements()) {
            statement.accept(this, null);
        }
    }

    @NotNull
    private Type validateBooleanExpression(BinaryOperation operation) {
        Type left = operation.getLeft().accept(this, null);
        Type right = operation.getRight().accept(this, null);

        if (!new BooleanType().isCompatible(left) || !new BooleanType().isCompatible(right)) {
            logger.severe(ERROR_MESSAGE + operation);
        }

        return new BooleanType();
    }

    private Type validateType(BinaryOperation operation) {
        Type left = operation.getLeft().accept(this, null);
        Type right = operation.getRight().accept(this, null);

        if (!left.isCompatible(right)) {
            logger.severe(ERROR_MESSAGE + operation);
        }

        return left;
    }

    @Override
    public Type visit(Parameter parameter, String Context) {
        Type type = symbolTable.getTypeByName(parameter.getID());
        assert type != null;
        return type;
    }

    @Override
    public Type visit(Conditional conditional, String context) {
        Type type = conditional.getCondition().accept(this, null);

        for (Statement statement : conditional.getIfSide()) {
            statement.accept(this, null);
        }

        for (Statement statement : conditional.getElseSide()) {
            statement.accept(this, null);
        }

        if (!new BooleanType().isCompatible(type)) {
            logger.severe(ERROR_MESSAGE + conditional);
        }

        return new BooleanType();
    }

    @Override
    public Type visit(CalculatedQuestion question, String context) {
        Type calculationType = question.getExpression().accept(this, null);

        if (!question.getType().isCompatible(calculationType)) {
            logger.severe(ERROR_MESSAGE + question);
        }

        return question.getType();
    }

    @Override
    public Type visit(Addition addition, String context) {
        return validateType(addition);
    }

    @Override
    public Type visit(Division division, String context) {
        return validateType(division);
    }

    @Override
    public Type visit(Multiplication multiplication, String context) {
        return validateType(multiplication);
    }

    @Override
    public Type visit(Subtraction subtraction, String context) {
        return validateType(subtraction);
    }

    @Override
    public Type visit(Equal equal, String context) {
        validateType(equal);
        return new BooleanType();
    }

    @Override
    public Type visit(GreaterThan greaterThan, String context) {
        validateType(greaterThan);
        return new BooleanType();
    }

    @Override
    public Type visit(GreaterThanEqualTo greaterThanEqualTo, String context) {
        validateType(greaterThanEqualTo);
        return new BooleanType();
    }

    @Override
    public Type visit(LessThan lessThan, String context) {
        validateType(lessThan);
        return new BooleanType();
    }

    @Override
    public Type visit(LessThanEqualTo lessThanEqualTo, String context) {
        validateType(lessThanEqualTo);
        return new BooleanType();
    }

    @Override
    public Type visit(NotEqual notEqual, String context) {
        validateType(notEqual);
        return new BooleanType();
    }


    @Override
    public Type visit(Or or, String context) {
        return validateBooleanExpression(or);
    }

    @Override
    public Type visit(And and, String context) {
        return validateBooleanExpression(and);
    }

    @Override
    public Type visit(ParameterGroup parameterGroup, String context) {
        return parameterGroup.getExpression().accept(this, null);
    }

    @Override
    public Type visit(Negation negation, String Context) {
        Type type = negation.getExpression().accept(this, null);

        if (!new BooleanType().isCompatible(type)) {
            System.out.println(ERROR_MESSAGE + negation);
        }

        return type;
    }

    @Override
    public Type visit(Question question, String context) {
        return null;
    }

    @Override
    public Type visit(BooleanType booleanType, String context) {
        return booleanType;
    }

    @Override
    public Type visit(IntegerType integerType, String context) {
        return integerType;
    }

    @Override
    public Type visit(MoneyType moneyType, String context) {
        return moneyType;
    }

    @Override
    public Type visit(StringType stringType, String context) {
        return stringType;
    }

    @Override
    public Type visit(StringLiteral stringLiteral, String context) {
        return new StringType();
    }

    @Override
    public Type visit(IntegerLiteral integerLiteral, String Context) {
        return new IntegerType();
    }

    @Override
    public Type visit(BooleanLiteral booleanLiteral, String context) {
        return new BooleanType();
    }
}
