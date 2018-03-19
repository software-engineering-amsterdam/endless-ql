package ql;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTree;
import ql.antlr.QLBaseVisitor;
import ql.antlr.QLLexer;
import ql.antlr.QLParser;
import ql.antlr.QLVisitor;
import ql.ast.Form;
import ql.ast.Node;
import ql.ast.expressions.literals.Identifier;
import ql.ast.expressions.literals.StringLiteral;
import ql.ast.statements.Question;
import ql.ast.statements.Statement;
import ql.types.Boolean;
import ql.types.String;
import ql.types.Type;

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
        List<Statement> statements = new ArrayList<Statement>();
        for (QLParser.StatementContext statementContext: ctx.statement()) {
            statements.add((Statement) visit(statementContext));
        }
        return new Form(ctx.start.getLine(),
                (Identifier) visit(ctx.Identifier()),
                statements
        );
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
    public Node visitIfThen(QLParser.IfThenContext ctx) {
        return null;
    }

    @Override
    public Node visitDescription(QLParser.DescriptionContext ctx) {
        return new StringLiteral(ctx.start.getLine(),
                trimQuotes(ctx.StringLiteral().getText()));
    }

    @Override
    public Node visitId(QLParser.IdContext ctx) {
        return new Identifier(ctx.start.getLine(),
                ctx.Identifier().getText()
        );
    }

    @Override
    public Node visitBooleanType(QLParser.BooleanTypeContext ctx) {
        return new Boolean(ctx.start.getLine());
    }

    @Override
    public Node visitIntegerType(QLParser.IntegerTypeContext ctx) {
        return null;
    }

    @Override
    public Node visitStringType(QLParser.StringTypeContext ctx) {
        return new String(ctx.start.getLine());
    }

    @Override
    public Node visitMoneyType(QLParser.MoneyTypeContext ctx) {
        return null;
    }

    @Override
    public Identifier visitIdentifier(QLParser.IdentifierContext ctx) {
        return new Identifier(ctx.start.getLine(),
                ctx.Identifier().getText()
        );
    }

    @Override
    public Node visitOrExpr(QLParser.OrExprContext ctx) {
        return null;
    }

    @Override
    public Node visitAdditiveExpr(QLParser.AdditiveExprContext ctx) {
        return null;
    }

    @Override
    public Node visitRelationalExpr(QLParser.RelationalExprContext ctx) {
        return null;
    }

    @Override
    public Node visitGroupedExpression(QLParser.GroupedExpressionContext ctx) {
        return null;
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
                ctx.StringLiteral().getText());
    }

    @Override
    public Node visitMultiplicationExpr(QLParser.MultiplicationExprContext ctx) {
        return null;
    }

    @Override
    public Node visitIntegerLiteral(QLParser.IntegerLiteralContext ctx) {
        return null;
    }

    @Override
    public Node visitUnaryPlusExpr(QLParser.UnaryPlusExprContext ctx) {
        return null;
    }

    @Override
    public Node visitBooleanLiteral(QLParser.BooleanLiteralContext ctx) {
        return null;
    }

    @Override
    public Node visitEqualityExpr(QLParser.EqualityExprContext ctx) {
        return null;
    }

    @Override
    public Node visitAndExpr(QLParser.AndExprContext ctx) {
        return null;
    }

    private java.lang.String trimQuotes(java.lang.String arg) {
        return arg.replaceAll("^\"|\"$", "");
    }
}
