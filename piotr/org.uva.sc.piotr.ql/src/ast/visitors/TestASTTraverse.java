package ast.visitors;

import ast.model.Form;
import ast.model.declarations.TypeDeclarationBoolean;
import ast.model.declarations.TypeDeclarationDecimal;
import ast.model.declarations.TypeDeclarationInteger;
import ast.model.declarations.TypeDeclarationString;
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

public class TestASTTraverse extends AbstractASTTraverse {

    public TestASTTraverse() {
        System.out.println("TestVisitor created");
    }

    @Override
    public Object visit(Form form) {
        System.out.println("Visiting form :" + form.getMetaInformation().getStartLine());
        return super.visit(form);
    }

    @Override
    public Object visit(Question question) {
        System.out.println("Visiting question :" + question.getMetaInformation().getStartLine());
        return super.visit(question);
    }

    @Override
    public Object visit(IfStatement ifStatement) {
        System.out.println("enter");
        System.out.println("Visiting if statement " + ifStatement.getMetaInformation().getStartLine());
        ifStatement.getCondition().accept(this);
        for (Statement statement : ifStatement.getStatementList()) {
            statement.accept(this);
        }
        for (Statement statement : ifStatement.getElseStatementList()) {
            statement.accept(this);
        }
        System.out.println("exit");

        return null;
    }

    @Override
    public Object visit(Literal literal) {
        System.out.println("Visiting literal " + literal.getClass().getSimpleName() + " :" + literal.getMetaInformation().getStartLine());
        return super.visit(literal);
    }

    @Override
    public Object visit(VariableReference variableReference) {
        System.out.println("Visiting variable reference " + variableReference.getClass().getSimpleName() + " :" + variableReference.getMetaInformation().getStartLine());
        return super.visit(variableReference);
    }

    @Override
    public Object visit(Negation negation) {
        System.out.println("Visiting negation " + negation.getClass().getSimpleName() + " :" + negation.getMetaInformation().getStartLine());
        return super.visit(negation);
    }

    @Override
    public Object visit(Minus minus) {
        System.out.println("Visiting minus " + minus.getClass().getSimpleName() + " :" + minus.getMetaInformation().getStartLine());
        return super.visit(minus);
    }

    @Override
    public Object visit(Addition addition) {
        System.out.println("Visiting addition " + addition.getClass().getSimpleName() + " :" + addition.getMetaInformation().getStartLine());
        return super.visit(addition);
    }

    @Override
    public Object visit(Subtraction subtraction) {
        System.out.println("Visiting subtraction " + subtraction.getClass().getSimpleName() + " :" + subtraction.getMetaInformation().getStartLine());
        return super.visit(subtraction);
    }

    @Override
    public Object visit(Division division) {
        System.out.println("Visiting divistion " + division.getClass().getSimpleName() + " :" + division.getMetaInformation().getStartLine());
        return super.visit(division);
    }

    @Override
    public Object visit(Multiplication multiplication) {
        System.out.println("Visiting multiplication " + multiplication.getClass().getSimpleName() + " :" + multiplication.getMetaInformation().getStartLine());
        return super.visit(multiplication);
    }

    @Override
    public Object visit(Equal equal) {
        System.out.println("Visiting equal " + equal.getClass().getSimpleName() + " :" + equal.getMetaInformation().getStartLine());
        return super.visit(equal);
    }

    @Override
    public Object visit(GreaterEqual greaterEqual) {
        System.out.println("Visiting greaterEqual " + greaterEqual.getClass().getSimpleName() + " :" + greaterEqual.getMetaInformation().getStartLine());
        return super.visit(greaterEqual);
    }

    @Override
    public Object visit(GreaterThan greaterThan) {
        System.out.println("Visiting greaterThan " + greaterThan.getClass().getSimpleName() + " :" + greaterThan.getMetaInformation().getStartLine());
        return super.visit(greaterThan);
    }

    @Override
    public Object visit(LessEqual lessEqual) {
        System.out.println("Visiting lessEqual " + lessEqual.getClass().getSimpleName() + " :" + lessEqual.getMetaInformation().getStartLine());
        return super.visit(lessEqual);
    }

    @Override
    public Object visit(LessThan lessThan) {
        System.out.println("Visiting lessThan " + lessThan.getClass().getSimpleName() + " :" + lessThan.getMetaInformation().getStartLine());
        return super.visit(lessThan);
    }

    @Override
    public Object visit(NotEqual notEqual) {
        System.out.println("Visiting notEqual " + notEqual.getClass().getSimpleName() + " :" + notEqual.getMetaInformation().getStartLine());
        return super.visit(notEqual);
    }

    @Override
    public Object visit(LogicalAnd logicalAnd) {
        System.out.println("Visiting logicalAnd " + logicalAnd.getClass().getSimpleName() + " :" + logicalAnd.getMetaInformation().getStartLine());
        return super.visit(logicalAnd);
    }

    @Override
    public Object visit(LogicalOr logicalOr) {
        System.out.println("Visiting logicalOr " + logicalOr.getClass().getSimpleName() + " :" + logicalOr.getMetaInformation().getStartLine());
        return super.visit(logicalOr);
    }

    @Override
    public Object visit(TypeDeclarationBoolean typeDeclarationBoolean) {
        System.out.println("Visiting typeDeclarationBoolean " + typeDeclarationBoolean.getClass().getSimpleName() + " :" + typeDeclarationBoolean.getMetaInformation().getStartLine());
        return super.visit(typeDeclarationBoolean);
    }

    @Override
    public Object visit(TypeDeclarationDecimal typeDeclarationDecimal) {
        System.out.println("Visiting typeDeclarationDecimal " + typeDeclarationDecimal.getClass().getSimpleName() + " :" + typeDeclarationDecimal.getMetaInformation().getStartLine());
        return super.visit(typeDeclarationDecimal);
    }

    @Override
    public Object visit(TypeDeclarationInteger typeDeclarationInteger) {
        System.out.println("Visiting typeDeclarationInteger " + typeDeclarationInteger.getClass().getSimpleName() + " :" + typeDeclarationInteger.getMetaInformation().getStartLine());
        return super.visit(typeDeclarationInteger);
    }

    @Override
    public Object visit(TypeDeclarationString typeDeclarationString) {
        System.out.println("Visiting typeDeclarationString " + typeDeclarationString.getClass().getSimpleName() + " :" + typeDeclarationString.getMetaInformation().getStartLine());
        return super.visit(typeDeclarationString);
    }
}
