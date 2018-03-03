package models.ast;

import models.ast.elements.Form;
import grammar.QLBaseVisitor;
import grammar.QLParser;
import models.ast.elements.datatypes.*;
import models.ast.elements.expressions.Expression;
import models.ast.elements.expressions.binary.arithmetics.Addition;
import models.ast.elements.expressions.binary.arithmetics.Division;
import models.ast.elements.expressions.binary.arithmetics.Multiplication;
import models.ast.elements.expressions.binary.arithmetics.Subtraction;
import models.ast.elements.expressions.binary.comparision.*;
import models.ast.elements.expressions.binary.logical.LogicalAnd;
import models.ast.elements.expressions.binary.logical.LogicalOr;
import models.ast.elements.expressions.unary.negation.Negation;
import models.ast.elements.expressions.unary.values.SingleValue;
import models.ast.elements.expressions.unary.values.VariableReference;
import models.ast.elements.statement.IfStatement;
import models.ast.elements.statement.Question;
import models.ast.elements.statement.Statement;


public class ASTBuilder extends QLBaseVisitor {

    @Override
    public Form visitForm(QLParser.FormContext ctx) {

        Form form = new Form(ctx.id.getText());

        for (QLParser.StatementContext StatementContext : ctx.statement()) {
            Statement statement = visitStatement(StatementContext);
            form.addStatement(statement);
        }

        return form;
    }

    @Override
    public Statement visitStatement(QLParser.StatementContext ctx) {

        if (ctx.ifStatement() != null) {
            return visitIfStatement(ctx.ifStatement());
        } else if (ctx.question() != null) {
            return visitQuestion(ctx.question());
        }

        return null;
    }

    @Override
    public Statement visitIfStatement(QLParser.IfStatementContext ctx) {

        IfStatement ifStatement = new IfStatement((Expression) visit(ctx.condition));

        for (QLParser.StatementContext StatementContext : ctx.statement()) {
            Statement statement = visitStatement(StatementContext);
            ifStatement.addStatement(statement);
        }

//        if (ctx.elseStatement() != null) {
//
//        }

        return ifStatement;
    }


    @Override
    public Question visitQuestion(QLParser.QuestionContext ctx) {

        Question question = new Question(
                ctx.label.getText(),
                ctx.variableName.getText(),
                (TypeDeclaration) visit(ctx.dataType())
        );

        if (ctx.expression() != null) {
            question.setAssignedExpression((Expression) visit(ctx.expression()));
        }

        return question;
    }

    // Data type declarations

    @Override
    public TypeDeclarationBoolean visitTypeDeclarationBoolean(QLParser.TypeDeclarationBooleanContext ctx) {
        return new TypeDeclarationBoolean(ctx.getText());
    }

    @Override
    public TypeDeclarationDecimal visitTypeDeclarationDecimal(QLParser.TypeDeclarationDecimalContext ctx) {
        return new TypeDeclarationDecimal(ctx.getText());
    }

    @Override
    public TypeDeclarationInteger visitTypeDeclarationInteger(QLParser.TypeDeclarationIntegerContext ctx) {
        return new TypeDeclarationInteger(ctx.getText());
    }

    @Override
    public TypeDeclarationString visitTypeDeclarationString(QLParser.TypeDeclarationStringContext ctx) {
        return new TypeDeclarationString(ctx.getText());
    }

    // Unary expressions, values and references

    @Override
    public SingleValue visitExpressionSingleValue(QLParser.ExpressionSingleValueContext ctx) {
        return new SingleValue(ctx.value().getText());
    }

    @Override
    public VariableReference visitExpressionVariableReference(QLParser.ExpressionVariableReferenceContext ctx) {
        return new VariableReference(ctx.variableReference.getText());
    }

    @Override
    public Negation visitExpressionNegation(QLParser.ExpressionNegationContext ctx) {
        return new Negation((Expression) visit(ctx.expression()));
    }

    // Arithmetic expressions

    @Override
    public Multiplication visitExpressionArithmeticMultiplication(QLParser.ExpressionArithmeticMultiplicationContext ctx) {
        return new Multiplication((Expression) visit(ctx.lhs), (Expression) visit(ctx.rhs));
    }

    @Override
    public Division visitExpressionArithmeticDivision(QLParser.ExpressionArithmeticDivisionContext ctx) {
        return new Division((Expression) visit(ctx.lhs), (Expression) visit(ctx.rhs));
    }

    @Override
    public Addition visitExpressionArithmeticAddition(QLParser.ExpressionArithmeticAdditionContext ctx) {
        return new Addition((Expression) visit(ctx.lhs), (Expression) visit(ctx.rhs));
    }

    @Override
    public Subtraction visitExpressionArithmeticSubtraction(QLParser.ExpressionArithmeticSubtractionContext ctx) {
        return new Subtraction((Expression) visit(ctx.lhs), (Expression) visit(ctx.rhs));
    }

    // Comparision expressions

    @Override
    public GreaterThan visitExpressionComparisionGreaterThan(QLParser.ExpressionComparisionGreaterThanContext ctx) {
        return new GreaterThan((Expression) visit(ctx.lhs), (Expression) visit(ctx.rhs));
    }

    @Override
    public GreaterEqual visitExpressionComparisionGreaterEqual(QLParser.ExpressionComparisionGreaterEqualContext ctx) {
        return new GreaterEqual((Expression) visit(ctx.lhs), (Expression) visit(ctx.rhs));
    }

    @Override
    public LessThan visitExpressionComparisionLessThan(QLParser.ExpressionComparisionLessThanContext ctx) {
        return new LessThan((Expression) visit(ctx.lhs), (Expression) visit(ctx.rhs));
    }

    @Override
    public LessEqual visitExpressionComparisionLessEqual(QLParser.ExpressionComparisionLessEqualContext ctx) {
        return new LessEqual((Expression) visit(ctx.lhs), (Expression) visit(ctx.rhs));
    }

    @Override
    public Equal visitExpressionComparisionEqual(QLParser.ExpressionComparisionEqualContext ctx) {
        return new Equal((Expression) visit(ctx.lhs), (Expression) visit(ctx.rhs));
    }

    @Override
    public NotEqual visitExpressionComparisionNotEqual(QLParser.ExpressionComparisionNotEqualContext ctx) {
        return new NotEqual((Expression) visit(ctx.lhs), (Expression) visit(ctx.rhs));
    }

    // Binary logical operation

    @Override
    public LogicalAnd visitExpressionLogicalAnd(QLParser.ExpressionLogicalAndContext ctx) {
        return new LogicalAnd((Expression) visit(ctx.lhs), (Expression) visit(ctx.rhs));
    }

    @Override
    public LogicalOr visitExpressionLogicalOr(QLParser.ExpressionLogicalOrContext ctx) {
        return new LogicalOr((Expression) visit(ctx.lhs), (Expression) visit(ctx.rhs));
    }
}
