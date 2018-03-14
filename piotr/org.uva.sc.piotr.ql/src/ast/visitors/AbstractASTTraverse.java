package ast.visitors;

import ast.model.Form;
import ast.model.declarations.*;
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
        for (Statement statement : form.getStatementList()) {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public T visit(Question question) {
        question.getVariableType().accept(this);
        if (question.getAssignedExpression() != null) {
            question.getAssignedExpression().accept(this);
        }
        return null;
    }

    @Override
    public T visit(IfStatement ifStatement) {
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
        return null;
    }

    @Override
    public T visit(VariableReference variableReference) {
        return null;
    }

    @Override
    public T visit(Negation negation) {
        negation.getExpression().accept(this);
        return null;
    }

    @Override
    public T visit(Minus minus) {
        minus.getExpression().accept(this);
        return null;
    }

    @Override
    public T visit(Addition addition) {
        addition.getLeftSide().accept(this);
        addition.getRightSide().accept(this);
        return null;
    }

    @Override
    public T visit(Subtraction subtraction) {
        subtraction.getLeftSide().accept(this);
        subtraction.getRightSide().accept(this);
        return null;
    }

    @Override
    public T visit(Division division) {
        division.getLeftSide().accept(this);
        division.getRightSide().accept(this);
        return null;
    }

    @Override
    public T visit(Multiplication multiplication) {
        multiplication.getLeftSide().accept(this);
        multiplication.getRightSide().accept(this);
        return null;
    }

    @Override
    public T visit(Equal equal) {
        equal.getLeftSide().accept(this);
        equal.getRightSide().accept(this);
        return null;
    }

    @Override
    public T visit(GreaterEqual greaterEqual) {
        greaterEqual.getLeftSide().accept(this);
        greaterEqual.getRightSide().accept(this);
        return null;
    }

    @Override
    public T visit(GreaterThan greaterThan) {
        greaterThan.getLeftSide().accept(this);
        greaterThan.getRightSide().accept(this);
        return null;
    }

    @Override
    public T visit(LessEqual lessEqual) {
        lessEqual.getLeftSide().accept(this);
        lessEqual.getRightSide().accept(this);
        return null;
    }

    @Override
    public T visit(LessThan lessThan) {
        lessThan.getLeftSide().accept(this);
        lessThan.getRightSide().accept(this);
        return null;
    }

    @Override
    public T visit(NotEqual notEqual) {
        notEqual.getLeftSide().accept(this);
        notEqual.getRightSide().accept(this);
        return null;
    }

    @Override
    public T visit(LogicalAnd logicalAnd) {
        logicalAnd.getLeftSide().accept(this);
        logicalAnd.getRightSide().accept(this);
        return null;
    }

    @Override
    public T visit(LogicalOr logicalOr) {
        logicalOr.getLeftSide().accept(this);
        logicalOr.getRightSide().accept(this);
        return null;
    }

    @Override
    public T visit(TypeDeclarationBoolean typeDeclarationBoolean) {
        return null;
    }

    @Override
    public T visit(TypeDeclarationDecimal typeDeclarationDecimal) {
        return null;
    }

    @Override
    public T visit(TypeDeclarationInteger typeDeclarationInteger) {
        return null;
    }

    @Override
    public T visit(TypeDeclarationString typeDeclarationString) {
        return null;
    }

    @Override
    public T visit(TypeDeclarationMoney typeDeclarationMoney) {
        return null;
    }
}
