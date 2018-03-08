package ast.visitors;

import ast.model.Form;
import ast.model.datatypes.TypeDeclarationBoolean;
import ast.model.datatypes.TypeDeclarationDecimal;
import ast.model.datatypes.TypeDeclarationInteger;
import ast.model.datatypes.TypeDeclarationString;
import ast.model.expressions.binary.arithmetics.Addition;
import ast.model.expressions.binary.arithmetics.Division;
import ast.model.expressions.binary.arithmetics.Multiplication;
import ast.model.expressions.binary.arithmetics.Subtraction;
import ast.model.expressions.binary.comparision.*;
import ast.model.expressions.binary.logical.LogicalAnd;
import ast.model.expressions.binary.logical.LogicalOr;
import ast.model.expressions.unary.arithmetics.Minus;
import ast.model.expressions.unary.logical.Negation;
import ast.model.expressions.values.Literal;
import ast.model.expressions.values.VariableReference;
import ast.model.statements.IfStatement;
import ast.model.statements.Question;
import ast.model.statements.Statement;

public abstract class AbstractASTTraverse<T> implements ASTNodeVisitor<T> {
    @Override
    public T visit(Form form) {
        System.out.println("Visiting form :" + form.getMetaInformation().getStartLine());

        for (Statement statement : form.getStatementList()) {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public T visit(Question question) {
        System.out.println("Visiting question :" + question.getMetaInformation().getStartLine());

        question.getVariableType().accept(this);

        if (question.getAssignedExpression() != null) {
            question.getAssignedExpression().accept(this);
        }
        return null;
    }

    @Override
    public T visit(IfStatement ifStatement) {

        System.out.println("Visiting if statement " + ifStatement.getMetaInformation().getStartLine());

        ifStatement.getCondition().accept(this);

        for (Statement statement : ifStatement.getStatementList()) {
            statement.accept(this);
        }
        for (Statement statement : ifStatement.getElseStatementList()) {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public T visit(Literal literal) {
        System.out.println("Visiting literal " + literal.getClass().getSimpleName() + " :" + literal.getMetaInformation().getStartLine());
        return null;
    }

    @Override
    public T visit(VariableReference variableReference) {
        System.out.println("Visiting variable reference " + variableReference.getClass().getSimpleName() + " :" + variableReference.getMetaInformation().getStartLine());
        return null;
    }

    @Override
    public T visit(Negation negation) {
        System.out.println("Visiting negation " + negation.getClass().getSimpleName() + " :" + negation.getMetaInformation().getStartLine());
        negation.getExpression().accept(this);
        return null;
    }

    @Override
    public T visit(Minus minus) {
        System.out.println("Visiting minus " + minus.getClass().getSimpleName() + " :" + minus.getMetaInformation().getStartLine());
        minus.getExpression().accept(this);
        return null;
    }

    @Override
    public T visit(Addition addition) {
        System.out.println("Visiting addition " + addition.getClass().getSimpleName() + " :" + addition.getMetaInformation().getStartLine());
        addition.getLeftSide().accept(this);
        addition.getRightSide().accept(this);
        return null;
    }

    @Override
    public T visit(Subtraction subtraction) {
        System.out.println("Visiting subtraction " + subtraction.getClass().getSimpleName() + " :" + subtraction.getMetaInformation().getStartLine());
        subtraction.getLeftSide().accept(this);
        subtraction.getRightSide().accept(this);
        return null;
    }

    @Override
    public T visit(Division division) {
        System.out.println("Visiting divistion " + division.getClass().getSimpleName() + " :" + division.getMetaInformation().getStartLine());
        division.getLeftSide().accept(this);
        division.getRightSide().accept(this);
        return null;
    }

    @Override
    public T visit(Multiplication multiplication) {
        System.out.println("Visiting multiplication " + multiplication.getClass().getSimpleName() + " :" + multiplication.getMetaInformation().getStartLine());
        multiplication.getLeftSide().accept(this);
        multiplication.getRightSide().accept(this);
        return null;
    }

    @Override
    public T visit(Equal equal) {
        System.out.println("Visiting equal " + equal.getClass().getSimpleName() + " :" + equal.getMetaInformation().getStartLine());
        equal.getLeftSide().accept(this);
        equal.getRightSide().accept(this);
        return null;
    }

    @Override
    public T visit(GreaterEqual greaterEqual) {
        System.out.println("Visiting greaterEqual " + greaterEqual.getClass().getSimpleName() + " :" + greaterEqual.getMetaInformation().getStartLine());
        greaterEqual.getLeftSide().accept(this);
        greaterEqual.getRightSide().accept(this);
        return null;
    }

    @Override
    public T visit(GreaterThan greaterThan) {
        System.out.println("Visiting greaterThan " + greaterThan.getClass().getSimpleName() + " :" + greaterThan.getMetaInformation().getStartLine());
        greaterThan.getLeftSide().accept(this);
        greaterThan.getRightSide().accept(this);
        return null;
    }

    @Override
    public T visit(LessEqual lessEqual) {
        System.out.println("Visiting lessEqual " + lessEqual.getClass().getSimpleName() + " :" + lessEqual.getMetaInformation().getStartLine());
        lessEqual.getLeftSide().accept(this);
        lessEqual.getRightSide().accept(this);
        return null;
    }

    @Override
    public T visit(LessThan lessThan) {
        System.out.println("Visiting lessThan " + lessThan.getClass().getSimpleName() + " :" + lessThan.getMetaInformation().getStartLine());
        lessThan.getLeftSide().accept(this);
        lessThan.getRightSide().accept(this);
        return null;
    }

    @Override
    public T visit(NotEqual notEqual) {
        System.out.println("Visiting notEqual " + notEqual.getClass().getSimpleName() + " :" + notEqual.getMetaInformation().getStartLine());
        notEqual.getLeftSide().accept(this);
        notEqual.getRightSide().accept(this);
        return null;
    }

    @Override
    public T visit(LogicalAnd logicalAnd) {
        System.out.println("Visiting logicalAnd " + logicalAnd.getClass().getSimpleName() + " :" + logicalAnd.getMetaInformation().getStartLine());
        logicalAnd.getLeftSide().accept(this);
        logicalAnd.getRightSide().accept(this);
        return null;
    }

    @Override
    public T visit(LogicalOr logicalOr) {
        System.out.println("Visiting logicalOr " + logicalOr.getClass().getSimpleName() + " :" + logicalOr.getMetaInformation().getStartLine());
        logicalOr.getLeftSide().accept(this);
        logicalOr.getRightSide().accept(this);
        return null;
    }

    @Override
    public T visit(TypeDeclarationBoolean typeDeclarationBoolean) {
        System.out.println("Visiting typeDeclarationBoolean " + typeDeclarationBoolean.getClass().getSimpleName() + " :" + typeDeclarationBoolean.getMetaInformation().getStartLine());
        return null;
    }

    @Override
    public T visit(TypeDeclarationDecimal typeDeclarationDecimal) {
        System.out.println("Visiting typeDeclarationDecimal " + typeDeclarationDecimal.getClass().getSimpleName() + " :" + typeDeclarationDecimal.getMetaInformation().getStartLine());
        return null;
    }

    @Override
    public T visit(TypeDeclarationInteger typeDeclarationInteger) {
        System.out.println("Visiting typeDeclarationInteger " + typeDeclarationInteger.getClass().getSimpleName() + " :" + typeDeclarationInteger.getMetaInformation().getStartLine());
        return null;
    }

    @Override
    public T visit(TypeDeclarationString typeDeclarationString) {
        System.out.println("Visiting typeDeclarationString " + typeDeclarationString.getClass().getSimpleName() + " :" + typeDeclarationString.getMetaInformation().getStartLine());
        return null;
    }
}
