package ast.visitors;

import ast.model.Form;
import ast.model.datatypes.*;
import ast.model.expressions.Expression;
import ast.model.expressions.binary.arithmetics.Addition;
import ast.model.expressions.binary.arithmetics.Division;
import ast.model.expressions.binary.arithmetics.Multiplication;
import ast.model.expressions.binary.arithmetics.Subtraction;
import ast.model.expressions.binary.comparision.*;
import ast.model.expressions.binary.logical.LogicalAnd;
import ast.model.expressions.binary.logical.LogicalOr;
import ast.model.expressions.unary.arithmetics.Minus;
import ast.model.expressions.unary.logical.Negation;
import ast.model.expressions.unary.values.Literal;
import ast.model.expressions.unary.values.VariableReference;
import ast.model.statement.IfStatement;
import ast.model.statement.Question;
import ast.model.statement.Statement;

public class ASTNodeAbstractVisitor implements ASTNodeVisitor {
    @Override
    public void visit(Form form) {
        System.out.println("Visiting form :" + form.getStartLine());

        for (Statement statement : form.getStatementList()) {
            visit(statement);
        }
    }

    @Override
    public void visit(Statement statement) {

        System.out.println("Visiting statement :" + statement.getStartLine());

        if (statement instanceof Question) {
            visit((Question) statement);
        } else if (statement instanceof IfStatement) {
            visit((IfStatement) statement);
        }

    }

    @Override
    public void visit(Question question) {
        System.out.println("Visiting question :" + question.getStartLine());

        visit(question.getVariableType());

        if (question.getAssignedExpression() != null) {
            visit(question.getAssignedExpression());
        }
    }

    @Override
    public void visit(IfStatement ifStatement) {

        System.out.println("Visiting if statement " + ifStatement.getStartLine());

        visit(ifStatement.getCondition());

        for (Statement statement : ifStatement.getStatementList()) {
            visit(statement);
        }
        for (Statement statement : ifStatement.getElseStatementList()) {
            visit(statement);
        }
    }

    @Override
    public void visit(Expression expression) {

        System.out.println("Visiting expression " + expression.getClass().getSimpleName() + " :" + expression.getStartLine());

        if (expression instanceof Negation) {
            visit((Negation) expression);
        } else if (expression instanceof Minus) {
            visit((Minus) expression);
        } else if (expression instanceof Multiplication) {
            visit((Multiplication) expression);
        } else if (expression instanceof Division) {
            visit((Division) expression);
        } else if (expression instanceof Addition) {
            visit((Addition) expression);
        } else if (expression instanceof Subtraction) {
            visit((Subtraction) expression);
        } else if (expression instanceof GreaterThan) {
            visit((GreaterThan) expression);
        } else if (expression instanceof GreaterEqual) {
            visit((GreaterEqual) expression);
        } else if (expression instanceof LessEqual) {
            visit((LessEqual) expression);
        } else if (expression instanceof Equal) {
            visit((Equal) expression);
        } else if (expression instanceof NotEqual) {
            visit((NotEqual) expression);
        } else if (expression instanceof LogicalAnd) {
            visit((LogicalAnd) expression);
        } else if (expression instanceof LogicalOr) {
            visit((LogicalOr) expression);
        } else if (expression instanceof VariableReference) {
            visit((VariableReference) expression);
        } else if (expression instanceof Literal) {
            visit((Literal) expression);
        }
    }

    @Override
    public void visit(Literal literal) {
        System.out.println("Visiting literal " + literal.getClass().getSimpleName() + " :" + literal.getStartLine());
    }

    @Override
    public void visit(VariableReference variableReference) {
        System.out.println("Visiting variable reference " + variableReference.getClass().getSimpleName() + " :" + variableReference.getStartLine());
    }

    @Override
    public void visit(Negation negation) {
        System.out.println("Visiting negation " + negation.getClass().getSimpleName() + " :" + negation.getStartLine());
        visit(negation.getExpression());
    }

    @Override
    public void visit(Minus minus) {
        System.out.println("Visiting minus " + minus.getClass().getSimpleName() + " :" + minus.getStartLine());
        visit(minus.getExpression());
    }

    @Override
    public void visit(Addition addition) {
        System.out.println("Visiting addition " + addition.getClass().getSimpleName() + " :" + addition.getStartLine());
        visit(addition.getLeftSide());
        visit(addition.getRightSide());
    }

    @Override
    public void visit(Subtraction subtraction) {
        System.out.println("Visiting subtraction " + subtraction.getClass().getSimpleName() + " :" + subtraction.getStartLine());
        visit(subtraction.getLeftSide());
        visit(subtraction.getRightSide());
    }

    @Override
    public void visit(Division division) {
        System.out.println("Visiting divistion " + division.getClass().getSimpleName() + " :" + division.getStartLine());
        visit(division.getLeftSide());
        visit(division.getRightSide());
    }

    @Override
    public void visit(Multiplication multiplication) {
        System.out.println("Visiting multiplication " + multiplication.getClass().getSimpleName() + " :" + multiplication.getStartLine());
        visit(multiplication.getLeftSide());
        visit(multiplication.getRightSide());
    }

    @Override
    public void visit(Equal equal) {
        System.out.println("Visiting equal " + equal.getClass().getSimpleName() + " :" + equal.getStartLine());
        visit(equal.getLeftSide());
        visit(equal.getRightSide());
    }

    @Override
    public void visit(GreaterEqual greaterEqual) {
        System.out.println("Visiting greaterEqual " + greaterEqual.getClass().getSimpleName() + " :" + greaterEqual.getStartLine());
        visit(greaterEqual.getLeftSide());
        visit(greaterEqual.getRightSide());
    }

    @Override
    public void visit(GreaterThan greaterThan) {
        System.out.println("Visiting greaterThan " + greaterThan.getClass().getSimpleName() + " :" + greaterThan.getStartLine());
        visit(greaterThan.getLeftSide());
        visit(greaterThan.getRightSide());
    }

    @Override
    public void visit(LessEqual lessEqual) {
        System.out.println("Visiting lessEqual " + lessEqual.getClass().getSimpleName() + " :" + lessEqual.getStartLine());
        visit(lessEqual.getLeftSide());
        visit(lessEqual.getRightSide());
    }

    @Override
    public void visit(LessThan lessThan) {
        System.out.println("Visiting lessThan " + lessThan.getClass().getSimpleName() + " :" + lessThan.getStartLine());
        visit(lessThan.getLeftSide());
        visit(lessThan.getRightSide());
    }

    @Override
    public void visit(NotEqual notEqual) {
        System.out.println("Visiting notEqual " + notEqual.getClass().getSimpleName() + " :" + notEqual.getStartLine());
        visit(notEqual.getLeftSide());
        visit(notEqual.getRightSide());
    }

    @Override
    public void visit(LogicalAnd logicalAnd) {
        System.out.println("Visiting logicalAnd " + logicalAnd.getClass().getSimpleName() + " :" + logicalAnd.getStartLine());
        visit(logicalAnd.getLeftSide());
        visit(logicalAnd.getRightSide());
    }

    @Override
    public void visit(LogicalOr logicalOr) {
        System.out.println("Visiting logicalOr " + logicalOr.getClass().getSimpleName() + " :" + logicalOr.getStartLine());
        visit(logicalOr.getLeftSide());
        visit(logicalOr.getRightSide());
    }

    @Override
    public void visit(TypeDeclaration typeDeclaration) {
        System.out.println("Visiting typeDeclaration " + typeDeclaration.getClass().getSimpleName() + " :" + typeDeclaration.getStartLine());
    }

    @Override
    public void visit(TypeDeclarationBoolean typeDeclarationBoolean) {
        System.out.println("Visiting typeDeclarationBoolean " + typeDeclarationBoolean.getClass().getSimpleName() + " :" + typeDeclarationBoolean.getStartLine());
    }

    @Override
    public void visit(TypeDeclarationDecimal typeDeclarationDecimal) {
        System.out.println("Visiting typeDeclarationDecimal " + typeDeclarationDecimal.getClass().getSimpleName() + " :" + typeDeclarationDecimal.getStartLine());
    }

    @Override
    public void visit(TypeDeclarationInteger typeDeclarationInteger) {
        System.out.println("Visiting typeDeclarationInteger " + typeDeclarationInteger.getClass().getSimpleName() + " :" + typeDeclarationInteger.getStartLine());
    }

    @Override
    public void visit(TypeDeclarationString typeDeclarationString) {
        System.out.println("Visiting typeDeclarationString " + typeDeclarationString.getClass().getSimpleName() + " :" + typeDeclarationString.getStartLine());
    }
}
