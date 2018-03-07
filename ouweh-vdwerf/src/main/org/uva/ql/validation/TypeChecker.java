package org.uva.ql.validation;

import org.uva.ql.ast.CalculatedQuestion;
import org.uva.ql.ast.Conditional;
import org.uva.ql.ast.Question;
import org.uva.ql.ast.Statement;
import org.uva.ql.ast.expression.ParameterGroup;
import org.uva.ql.ast.expression.binary.*;
import org.uva.ql.ast.expression.unary.*;
import org.uva.ql.ast.type.*;
import org.uva.ql.visitor.*;

public class TypeChecker implements StatementVisitor<Type, String>, ExpressionVisitor<Type, String>, TypeVisitor<Type, String>  {

    private static final String BOOLEAN = "boolean";
    private SymbolTable symbolTable;

    TypeChecker(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
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

        if (!BOOLEAN.equals(type.toString())) {
            System.out.println("Type is not correct");
        }

        return new BooleanType();
    }

    @Override
    public Type visit(CalculatedQuestion question, String context) {
        Type calculationType = question.getExpression().accept(this, null);

        if (!question.getType().isCompatible(calculationType)) {
            System.out.println("ERROR: Type checker at: " + question);
        }

        return question.getType();
    }

    @Override
    public Type visit(Addition addition, String context) {
        return null;
    }

    @Override
    public Type visit(Division division, String context) {
        return null;
    }

    @Override
    public Type visit(Equal equal, String context) {
        return null;
    }

    @Override
    public Type visit(GreaterThan greaterThan, String context) {
        return null;
    }

    @Override
    public Type visit(GreaterThanEqualTo greaterThanEqualTo, String context) {
        return null;
    }

    @Override
    public Type visit(LessThan lessThan, String context) {
        return null;
    }

    @Override
    public Type visit(LessThanEqualTo lessThanEqualTo, String context) {
        return null;
    }

    @Override
    public Type visit(Multiplication multiplication, String context) {
        return null;
    }

    @Override
    public Type visit(NotEqual notEqual, String context) {
        return null;
    }

    @Override
    public Type visit(Subtraction subtraction, String context) {
        return null;
    }

    @Override
    public Type visit(Or or, String context) {
        return null;
    }

    @Override
    public Type visit(And and, String context) {
        return null;
    }

    @Override
    public Type visit(ParameterGroup parameterGroup, String context) {
        return null;
    }

    @Override
    public Type visit(StringLiteral stringLiteral, String context) {
        return null;
    }

    @Override
    public Type visit(IntegerLiteral integerLiteral, String Context) {
        return null;
    }

    @Override
    public Type visit(Negation negation, String Context) {
        return null;
    }

    @Override
    public Type visit(BooleanLiteral booleanLiteral, String context) {
        return null;
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
}
