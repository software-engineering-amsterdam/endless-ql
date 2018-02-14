package main.org.uva.ql.app;

import generated.org.uva.ql.parser.QLBaseVisitor;
import generated.org.uva.ql.parser.QLParser;
import main.org.uva.ql.ast.*;
import main.org.uva.ql.ast.type.*;

import java.util.*;

public class ParseTreeVisitor extends QLBaseVisitor {

    @Override
    public Form visitForm(QLParser.FormContext ctx) {
        System.out.println(String.format("Visit Form: %s", ctx.id.getText()));
        List<Statement> statements = new ArrayList<>();

        for(QLParser.StatementContext statementContext : ctx.statement()){
            statements.add((Statement) visit(statementContext));
        }

        return new Form(statements);
    }

    @Override
    public TreeNode visitQuestion(QLParser.QuestionContext ctx) {
        System.out.println(String.format("Visit Question: %s", ctx.text.getText()));
        return new Question(ctx.text.getText(), (Type)visit(ctx.type()));
    }

    @Override
    public TreeNode visitIfStatement(QLParser.IfStatementContext ctx) {
        List<Statement> body = new ArrayList<>();
        for (QLParser.StatementContext item : ctx.statement()) {
            body.add((Statement) visit(item));
        }

        return new Conditional((Expression) visit(ctx.expression()), body);
    }

    @Override
    public TreeNode visitIfElseStatement(QLParser.IfElseStatementContext ctx) {
        List<Statement> ifBody = new ArrayList<>();
        for (QLParser.StatementContext item : ctx.ifCase) {
            ifBody.add((Statement) visit(item));
        }

        List<Statement> elseBody = new ArrayList<>();
        for (QLParser.StatementContext item : ctx.elseCase) {
            elseBody.add((Statement) visit(item));
        }

        return new Conditional((Expression) visit(ctx.expression()), ifBody, elseBody);
    }

    @Override
    public TreeNode visitIntegerType(QLParser.IntegerTypeContext ctx) {
        return new IntegerType();
    }

    @Override
    public TreeNode visitBooleanType(QLParser.BooleanTypeContext ctx) {
        return new BooleanType();
    }

    @Override
    public TreeNode visitStringType(QLParser.StringTypeContext ctx) {
        return new StringType();
    }
}
