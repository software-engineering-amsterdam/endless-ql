package ast;

import ast.model.Form;
import grammar.QLBaseVisitor;
import grammar.QLParser;
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
import ast.model.statements.IfStatement;
import ast.model.statements.Question;
import ast.model.statements.Statement;


public class ASTBuilder extends QLBaseVisitor {

    @Override
    public Form visitForm(QLParser.FormContext ctx) {

        Form form = new Form(ctx.id.getText(), ctx.getStart().getLine(), ctx.getStop().getLine());

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
        } else if (ctx.question() != null) {
            return visitQuestion(ctx.question());
        }

        return null;
    }

    @Override
    public IfStatement visitIfStatement(QLParser.IfStatementContext ctx) {

        Expression ifConditionExpression = (Expression) visit(ctx.condition);

        IfStatement ifStatement = new IfStatement(
                ifConditionExpression,
                ctx.getStart().getLine(),
                ctx.getStop().getLine()
        );

        for (QLParser.StatementContext StatementContext : ctx.statement()) {
            Statement statement = visitStatement(StatementContext);
            ifStatement.addStatement(statement);
        }

        if (ctx.elseStatement() != null) {

            for (QLParser.StatementContext StatementContext : ctx.elseStatement().statement()) {
                Statement statement = visitStatement(StatementContext);
                ifStatement.addElseStatement(statement);
            }

        }

        return ifStatement;
    }

    @Override
    public Question visitQuestion(QLParser.QuestionContext ctx) {

        Question question = new Question(
                ctx.label.getText(),
                ctx.variableName.getText(),
                (TypeDeclaration) visit(ctx.dataType()),
                ctx.getStart().getLine(),
                ctx.getStop().getLine()
        );

        if (ctx.expression() != null) {
            question.setAssignedExpression((Expression) visit(ctx.expression()));
        }

        question.setStartLine(ctx.getStart().getLine());
        question.setEndLine(ctx.getStop().getLine());

        return question;
    }

    // Data type declarations

    @Override
    public TypeDeclarationBoolean visitTypeDeclarationBoolean(QLParser.TypeDeclarationBooleanContext ctx) {
        return new TypeDeclarationBoolean(
                ctx.getText(),
                ctx.getStart().getLine(),
                ctx.getStop().getLine()
        );
    }

    @Override
    public TypeDeclarationDecimal visitTypeDeclarationDecimal(QLParser.TypeDeclarationDecimalContext ctx) {
        return new TypeDeclarationDecimal(
                ctx.getText(),
                ctx.getStart().getLine(),
                ctx.getStop().getLine()
        );
    }

    @Override
    public TypeDeclarationInteger visitTypeDeclarationInteger(QLParser.TypeDeclarationIntegerContext ctx) {
        return new TypeDeclarationInteger(
                ctx.getText(),
                ctx.getStart().getLine(),
                ctx.getStop().getLine()
        );
    }

    @Override
    public TypeDeclarationString visitTypeDeclarationString(QLParser.TypeDeclarationStringContext ctx) {
        return new TypeDeclarationString(
                ctx.getText(),
                ctx.getStart().getLine(),
                ctx.getStop().getLine()
        );
    }

    // Parenthesis

    @Override
    public Expression visitExpressionParenthesises(QLParser.ExpressionParenthesisesContext ctx) {
        return (Expression) this.visit(ctx.expression());
    }

    // Values

    @Override
    public Literal visitExpressionSingleValue(QLParser.ExpressionSingleValueContext ctx) {

        Literal.Type type = Literal.Type.STRING;

        if (ctx.BOOL_FALSE() != null || ctx.BOOL_TRUE() != null)
            type = Literal.Type.BOOLEAN;

        if (ctx.DECIMAL() != null)
            type = Literal.Type.DECIMAL;

        if (ctx.INTEGER() != null)
            type = Literal.Type.INTEGER;

        return new Literal(
                ctx.value.getText(),
                type,
                ctx.getStart().getLine(),
                ctx.getStop().getLine()
        );
    }

    // References

    @Override
    public VariableReference visitExpressionVariableReference(QLParser.ExpressionVariableReferenceContext ctx) {
        return new VariableReference(
                ctx.variableReference.getText(),
                ctx.getStart().getLine(),
                ctx.getStop().getLine()
        );
    }

    @Override
    public Negation visitExpressionNegation(QLParser.ExpressionNegationContext ctx) {
        return new Negation(
                (Expression) visit(ctx.expression()),
                ctx.getStart().getLine(),
                ctx.getStop().getLine()
        );
    }

    // Arithmetic expressions

    @Override
    public Object visitExpressionArithmeticMinus(QLParser.ExpressionArithmeticMinusContext ctx) {
        return new Minus(
                (Expression) visit(ctx.expression()),
                ctx.getStart().getLine(),
                ctx.getStop().getLine()
        );
    }

    @Override
    public Multiplication visitExpressionArithmeticMultiplication(QLParser.ExpressionArithmeticMultiplicationContext ctx) {
        return new Multiplication(
                (Expression) visit(ctx.lhs),
                (Expression) visit(ctx.rhs),
                ctx.getStart().getLine(),
                ctx.getStop().getLine()
        );
    }

    @Override
    public Division visitExpressionArithmeticDivision(QLParser.ExpressionArithmeticDivisionContext ctx) {
        return new Division(
                (Expression) visit(ctx.lhs),
                (Expression) visit(ctx.rhs),
                ctx.getStart().getLine(),
                ctx.getStop().getLine()
        );
    }

    @Override
    public Addition visitExpressionArithmeticAddition(QLParser.ExpressionArithmeticAdditionContext ctx) {
        return new Addition(
                (Expression) visit(ctx.lhs),
                (Expression) visit(ctx.rhs),
                ctx.getStart().getLine(),
                ctx.getStop().getLine()
        );
    }

    @Override
    public Subtraction visitExpressionArithmeticSubtraction(QLParser.ExpressionArithmeticSubtractionContext ctx) {
        return new Subtraction(
                (Expression) visit(ctx.lhs),
                (Expression) visit(ctx.rhs),
                ctx.getStart().getLine(),
                ctx.getStop().getLine()
        );
    }

    // Expressions comparisons

    @Override
    public GreaterThan visitExpressionComparisionGreaterThan(QLParser.ExpressionComparisionGreaterThanContext ctx) {
        return new GreaterThan(
                (Expression) visit(ctx.lhs),
                (Expression) visit(ctx.rhs),
                ctx.getStart().getLine(),
                ctx.getStop().getLine()
        );
    }

    @Override
    public GreaterEqual visitExpressionComparisionGreaterEqual(QLParser.ExpressionComparisionGreaterEqualContext ctx) {
        return new GreaterEqual(
                (Expression) visit(ctx.lhs),
                (Expression) visit(ctx.rhs),
                ctx.getStart().getLine(),
                ctx.getStop().getLine()
        );
    }

    @Override
    public LessThan visitExpressionComparisionLessThan(QLParser.ExpressionComparisionLessThanContext ctx) {
        return new LessThan(
                (Expression) visit(ctx.lhs), (Expression) visit(ctx.rhs),
                ctx.getStart().getLine(),
                ctx.getStop().getLine()
        );
    }

    @Override
    public LessEqual visitExpressionComparisionLessEqual(QLParser.ExpressionComparisionLessEqualContext ctx) {
        return new LessEqual(
                (Expression) visit(ctx.lhs), (Expression) visit(ctx.rhs),
                ctx.getStart().getLine(),
                ctx.getStop().getLine()
        );
    }

    @Override
    public Equal visitExpressionComparisionEqual(QLParser.ExpressionComparisionEqualContext ctx) {
        return new Equal(
                (Expression) visit(ctx.lhs), (Expression) visit(ctx.rhs),
                ctx.getStart().getLine(),
                ctx.getStop().getLine()
        );
    }

    @Override
    public NotEqual visitExpressionComparisionNotEqual(QLParser.ExpressionComparisionNotEqualContext ctx) {
        return new NotEqual(
                (Expression) visit(ctx.lhs), (Expression) visit(ctx.rhs),
                ctx.getStart().getLine(),
                ctx.getStop().getLine()
        );
    }

    // Binary logical operations

    @Override
    public LogicalAnd visitExpressionLogicalAnd(QLParser.ExpressionLogicalAndContext ctx) {
        return new LogicalAnd(
                (Expression) visit(ctx.lhs), (Expression) visit(ctx.rhs),
                ctx.getStart().getLine(),
                ctx.getStop().getLine()
        );
    }

    @Override
    public LogicalOr visitExpressionLogicalOr(QLParser.ExpressionLogicalOrContext ctx) {
        return new LogicalOr(
                (Expression) visit(ctx.lhs), (Expression) visit(ctx.rhs),
                ctx.getStart().getLine(),
                ctx.getStop().getLine()
        );
    }
}
