package ast.visitors;

import ast.model.Form;
import ast.model.datatypes.*;
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

public class ASTNodeAbstractVisitor implements ASTNodeVisitor {
    @Override
    public void visit(Form form) {
        System.out.println("Visiting form :" + form.getMetaInformation().getStartLine());

        for (Statement statement : form.getStatementList()) {
            statement.accept(this);
        }
    }

    @Override
    public void visit(Question question) {
        System.out.println("Visiting question :" + question.getMetaInformation().getStartLine());

        question.getVariableType().accept(this);

        if (question.getAssignedExpression() != null) {
            question.getAssignedExpression().accept(this);
        }
    }

    @Override
    public void visit(IfStatement ifStatement) {

        System.out.println("Visiting if statement " + ifStatement.getMetaInformation().getStartLine());

        ifStatement.getCondition().accept(this);

        for (Statement statement : ifStatement.getStatementList()) {
            statement.accept(this);
        }
        for (Statement statement : ifStatement.getElseStatementList()) {
            statement.accept(this);
        }
    }

    @Override
    public void visit(Literal literal) {
        System.out.println("Visiting literal " + literal.getClass().getSimpleName() + " :" + literal.getMetaInformation().getStartLine());
    }

    @Override
    public void visit(VariableReference variableReference) {
        System.out.println("Visiting variable reference " + variableReference.getClass().getSimpleName() + " :" + variableReference.getMetaInformation().getStartLine());
    }

    @Override
    public void visit(Negation negation) {
        System.out.println("Visiting negation " + negation.getClass().getSimpleName() + " :" + negation.getMetaInformation().getStartLine());
        negation.getExpression().accept(this);
    }

    @Override
    public void visit(Minus minus) {
        System.out.println("Visiting minus " + minus.getClass().getSimpleName() + " :" + minus.getMetaInformation().getStartLine());
        minus.getExpression().accept(this);
    }

    @Override
    public void visit(Addition addition) {
        System.out.println("Visiting addition " + addition.getClass().getSimpleName() + " :" + addition.getMetaInformation().getStartLine());
        addition.getLeftSide().accept(this);
        addition.getRightSide().accept(this);
    }

    @Override
    public void visit(Subtraction subtraction) {
        System.out.println("Visiting subtraction " + subtraction.getClass().getSimpleName() + " :" + subtraction.getMetaInformation().getStartLine());
        subtraction.getLeftSide().accept(this);
        subtraction.getRightSide().accept(this);
    }

    @Override
    public void visit(Division division) {
        System.out.println("Visiting divistion " + division.getClass().getSimpleName() + " :" + division.getMetaInformation().getStartLine());
        division.getLeftSide().accept(this);
        division.getRightSide().accept(this);
    }

    @Override
    public void visit(Multiplication multiplication) {
        System.out.println("Visiting multiplication " + multiplication.getClass().getSimpleName() + " :" + multiplication.getMetaInformation().getStartLine());
        multiplication.getLeftSide().accept(this);
        multiplication.getRightSide().accept(this);
    }

    @Override
    public void visit(Equal equal) {
        System.out.println("Visiting equal " + equal.getClass().getSimpleName() + " :" + equal.getMetaInformation().getStartLine());
        equal.getLeftSide().accept(this);
        equal.getRightSide().accept(this);
    }

    @Override
    public void visit(GreaterEqual greaterEqual) {
        System.out.println("Visiting greaterEqual " + greaterEqual.getClass().getSimpleName() + " :" + greaterEqual.getMetaInformation().getStartLine());
        greaterEqual.getLeftSide().accept(this);
        greaterEqual.getRightSide().accept(this);
    }

    @Override
    public void visit(GreaterThan greaterThan) {
        System.out.println("Visiting greaterThan " + greaterThan.getClass().getSimpleName() + " :" + greaterThan.getMetaInformation().getStartLine());
        greaterThan.getLeftSide().accept(this);
        greaterThan.getRightSide().accept(this);
    }

    @Override
    public void visit(LessEqual lessEqual) {
        System.out.println("Visiting lessEqual " + lessEqual.getClass().getSimpleName() + " :" + lessEqual.getMetaInformation().getStartLine());
        lessEqual.getLeftSide().accept(this);
        lessEqual.getRightSide().accept(this);
    }

    @Override
    public void visit(LessThan lessThan) {
        System.out.println("Visiting lessThan " + lessThan.getClass().getSimpleName() + " :" + lessThan.getMetaInformation().getStartLine());
        lessThan.getLeftSide().accept(this);
        lessThan.getRightSide().accept(this);
    }

    @Override
    public void visit(NotEqual notEqual) {
        System.out.println("Visiting notEqual " + notEqual.getClass().getSimpleName() + " :" + notEqual.getMetaInformation().getStartLine());
        notEqual.getLeftSide().accept(this);
        notEqual.getRightSide().accept(this);
    }

    @Override
    public void visit(LogicalAnd logicalAnd) {
        System.out.println("Visiting logicalAnd " + logicalAnd.getClass().getSimpleName() + " :" + logicalAnd.getMetaInformation().getStartLine());
        logicalAnd.getLeftSide().accept(this);
        logicalAnd.getRightSide().accept(this);
    }

    @Override
    public void visit(LogicalOr logicalOr) {
        System.out.println("Visiting logicalOr " + logicalOr.getClass().getSimpleName() + " :" + logicalOr.getMetaInformation().getStartLine());
        logicalOr.getLeftSide().accept(this);
        logicalOr.getRightSide().accept(this);
    }

    @Override
    public void visit(TypeDeclarationBoolean typeDeclarationBoolean) {
        System.out.println("Visiting typeDeclarationBoolean " + typeDeclarationBoolean.getClass().getSimpleName() + " :" + typeDeclarationBoolean.getMetaInformation().getStartLine());
    }

    @Override
    public void visit(TypeDeclarationDecimal typeDeclarationDecimal) {
        System.out.println("Visiting typeDeclarationDecimal " + typeDeclarationDecimal.getClass().getSimpleName() + " :" + typeDeclarationDecimal.getMetaInformation().getStartLine());
    }

    @Override
    public void visit(TypeDeclarationInteger typeDeclarationInteger) {
        System.out.println("Visiting typeDeclarationInteger " + typeDeclarationInteger.getClass().getSimpleName() + " :" + typeDeclarationInteger.getMetaInformation().getStartLine());
    }

    @Override
    public void visit(TypeDeclarationString typeDeclarationString) {
        System.out.println("Visiting typeDeclarationString " + typeDeclarationString.getClass().getSimpleName() + " :" + typeDeclarationString.getMetaInformation().getStartLine());
    }
}
