package ql;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import ql.antlr.QLLexer;
import ql.antlr.QLParser;
import ql.antlr.QLVisitor;
import ql.ast.Form;
import ql.ast.Node;
import ql.ast.expressions.Expression;
import ql.ast.expressions.GroupExpression;
import ql.ast.expressions.Identifier;
import ql.ast.expressions.binary.*;
import ql.ast.expressions.literals.BooleanLiteral;
import ql.ast.expressions.literals.IntegerLiteral;
import ql.ast.expressions.literals.StringLiteral;
import ql.ast.statements.*;
import ql.types.*;
import ql.types.Boolean;
import ql.types.Integer;
import ql.types.String;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class ASTBuilder extends AbstractParseTreeVisitor<Node> implements QLVisitor<Node> {

    public Form build(InputStream inputStream) throws IOException {
        return visitForm(initQLParser(inputStream).form());
    }

    private QLParser initQLParser(InputStream inputStream) throws IOException {
        return new QLParser(new CommonTokenStream(new QLLexer(new ANTLRInputStream(inputStream))));
    }

    @Override
    public Form visitForm(QLParser.FormContext ctx) {
        return new Form(ctx.start.getLine(),
                (Identifier) visit(ctx.id()),
                statements(ctx.statement())
        );
    }

    private List<Statement> statements(List<QLParser.StatementContext> ctx) {
        List<Statement> statements = new ArrayList<>();
        for (QLParser.StatementContext statementContext: ctx) {
            statements.add((Statement) visit(statementContext));
        }
        return statements;
    }

    @Override
    public Node visitQuestion(QLParser.QuestionContext ctx) {
        return new Question(ctx.start.getLine(),
                (StringLiteral) visit(ctx.description()),
                (Identifier) visit(ctx.id()),
                (Type) visit(ctx.type())
        );
    }

    @Override
    public Node visitCalculableQuestion(QLParser.CalculableQuestionContext ctx) {
        return new CalculableQuestion(ctx.start.getLine(),
                (StringLiteral) visit(ctx.description()),
                (Identifier) visit(ctx.id()),
                (Type) visit(ctx.type()),
                (Expression) visit(ctx.expression())
        );
    }

    @Override
    public Node visitIfThen(QLParser.IfThenContext ctx) {
        return new IfThen(ctx.start.getLine(),
                (Expression) visit(ctx.expression()),
                statements(ctx.statement())
        );
    }

    @Override
    public Node visitIfThenElse(QLParser.IfThenElseContext ctx) {
        return new IfThenElse(ctx.start.getLine(),
                (Expression) visit(ctx.expression()),
                statements(ctx.statement()),
                statements(ctx.statement())
        );
    }

    @Override
    public Node visitDescription(QLParser.DescriptionContext ctx) {
        return new StringLiteral(ctx.start.getLine(),
                trimQuotes(ctx.StringLiteral().getText()));
    }

    @Override
    public Node visitId(QLParser.IdContext ctx) {
        return new Identifier(ctx.start.getLine(),
                ctx.ID().getText()
        );
    }

    @Override
    public Node visitBooleanType(QLParser.BooleanTypeContext ctx) {
        return new Boolean(ctx.start.getLine(),
                ctx.getText()
        );
    }

    @Override
    public Node visitIntegerType(QLParser.IntegerTypeContext ctx) {
        return new Integer(ctx.start.getLine(),
                ctx.getText()
        );
    }

    @Override
    public Node visitStringType(QLParser.StringTypeContext ctx) {
        return new String(ctx.start.getLine(),
                ctx.getText()
        );
    }

    @Override
    public Node visitMoneyType(QLParser.MoneyTypeContext ctx) {
        return new Money(ctx.start.getLine(),
                ctx.getText()
        );
    }

    @Override
    public Identifier visitIdentifier(QLParser.IdentifierContext ctx) {
        return new Identifier(ctx.start.getLine(),
                ctx.ID().getText()
        );
    }

    @Override
    public Node visitSubtraction(QLParser.SubtractionContext ctx) {
        return new Subtraction(ctx.start.getLine(),
                (Expression) visit(ctx.left),
                (Expression) visit(ctx.right)
        );
    }

    @Override
    public Node visitNotEqual(QLParser.NotEqualContext ctx) {
        return new NotEqual(ctx.start.getLine(),
                (Expression) visit(ctx.left),
                (Expression) visit(ctx.right)
        );
    }

    @Override
    public Node visitLogicalAnd(QLParser.LogicalAndContext ctx) {
        return new LogicalAnd(ctx.start.getLine(),
                (Expression) visit(ctx.left),
                (Expression) visit(ctx.right)
        );
    }

    @Override
    public Node visitGreaterThanOrEqual(QLParser.GreaterThanOrEqualContext ctx) {
        return new GreaterThanOrEqual(ctx.start.getLine(),
                (Expression) visit(ctx.left),
                (Expression) visit(ctx.right)
        );
    }

    @Override
    public Node visitGroupExpression(QLParser.GroupExpressionContext ctx) {
        return new GroupExpression(ctx.start.getLine(),
                (Expression) visit(ctx.expression())
        );
    }

    @Override
    public Node visitDivision(QLParser.DivisionContext ctx) {
        return new Division(ctx.start.getLine(),
                (Expression) visit(ctx.left),
                (Expression) visit(ctx.right)
        );
    }

    @Override
    public Node visitEqual(QLParser.EqualContext ctx) {
        return new Equal(ctx.start.getLine(),
                (Expression) visit(ctx.left),
                (Expression) visit(ctx.right)
        );
    }

    @Override
    public Node visitNotExpr(QLParser.NotExprContext ctx) {
        return null;
    }

    @Override
    public Node visitUnaryMinusExpr(QLParser.UnaryMinusExprContext ctx) {
        return null;
    }

    @Override
    public Node visitStringLiteral(QLParser.StringLiteralContext ctx) {
        return new StringLiteral(ctx.start.getLine(),
                ctx.StringLiteral().getText()
        );
    }

    @Override
    public Node visitLessThan(QLParser.LessThanContext ctx) {
        return new LessThan(ctx.start.getLine(),
                (Expression) visit(ctx.left),
                (Expression) visit(ctx.right)
        );
    }

    @Override
    public Node visitLessThanOrEqual(QLParser.LessThanOrEqualContext ctx) {
        return new LessThanOrEqual(ctx.start.getLine(),
                (Expression) visit(ctx.left),
                (Expression) visit(ctx.right)
        );
    }

    @Override
    public Node visitIntegerLiteral(QLParser.IntegerLiteralContext ctx) {
        return new IntegerLiteral(ctx.start.getLine(),
                java.lang.Integer.parseInt(ctx.IntegerLiteral().getText())
        );
    }

    @Override
    public Node visitUnaryPlusExpr(QLParser.UnaryPlusExprContext ctx) {
        return null;
    }

    @Override
    public Node visitMultiplication(QLParser.MultiplicationContext ctx) {
        return new Multiplication(ctx.start.getLine(),
                (Expression) visit(ctx.left),
                (Expression) visit(ctx.right)
        );
    }

    @Override
    public Node visitLogicalOr(QLParser.LogicalOrContext ctx) {
        return new LogicalOr(ctx.start.getLine(),
                (Expression) visit(ctx.left),
                (Expression) visit(ctx.right)
        );
    }

    @Override
    public Node visitBooleanLiteral(QLParser.BooleanLiteralContext ctx) {
        return new BooleanLiteral(ctx.start.getLine(),
                java.lang.Boolean.parseBoolean(ctx.BooleanLiteral().getText())
        );
    }

    @Override
    public Node visitAddition(QLParser.AdditionContext ctx) {
        return new Addition(ctx.start.getLine(),
                (Expression) visit(ctx.left),
                (Expression) visit(ctx.right)
        );
    }

    @Override
    public Node visitGreaterThan(QLParser.GreaterThanContext ctx) {
        return new GreaterThan(ctx.start.getLine(),
                (Expression) visit(ctx.left),
                (Expression) visit(ctx.right)
        );
    }

    private java.lang.String trimQuotes(java.lang.String arg) {
        return arg.replaceAll("^\"|\"$", "");
    }
}
