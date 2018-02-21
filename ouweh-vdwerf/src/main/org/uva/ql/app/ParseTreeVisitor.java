package main.org.uva.ql.app;

import generated.org.uva.ql.parser.QLBaseVisitor;
import generated.org.uva.ql.parser.QLParser;
import main.org.uva.ql.ast.*;
import main.org.uva.ql.ast.expression.Expression;
import main.org.uva.ql.ast.expression.ParameterGroup;
import main.org.uva.ql.ast.expression.binary.*;
import main.org.uva.ql.ast.expression.unary.BooleanLiteral;
import main.org.uva.ql.ast.expression.unary.IntegerLiteral;
import main.org.uva.ql.ast.expression.unary.Parameter;
import main.org.uva.ql.ast.expression.unary.StringLiteral;
import main.org.uva.ql.ast.type.*;

import java.util.*;

public class ParseTreeVisitor extends QLBaseVisitor {

    @Override
    public Form visitForm(QLParser.FormContext ctx) {
        String formId = ctx.id.getText();
        List<Statement> statements = new ArrayList<>();

        for(QLParser.StatementContext statementContext : ctx.statement()){
            statements.add((Statement) visit(statementContext));
        }
        return new Form(formId, statements);
    }

    @Override
    public TreeNode visitQuestion(QLParser.QuestionContext ctx) {
        String questionName = ctx.ID().getText();
        String questionContent = ctx.text.getText();
        Type questionType = (Type)visit(ctx.type());

        if(ctx.calculatedValue() != null){
            Expression calculation = (Expression) visit(ctx.calculatedValue());
            return new CalculatedQuestion(questionName, questionContent, questionType, calculation);
        } else {
            return new Question(questionName, questionContent, questionType);
        }
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
    public TreeNode visitAddSub(QLParser.AddSubContext ctx) {
        Expression left = (Expression) visit(ctx.left);
        Expression right = (Expression) visit(ctx.right);
        String operation = ctx.op.getText();

        if (operation.equals("+")) {
            return new Addition(left, right);
        } else {
            return new Subtraction(left, right);
        }
    }

    @Override
    public TreeNode visitComparation(QLParser.ComparationContext ctx){
        String operation = ctx.op.getText();

        switch (operation){
            case ">":
                return new GreaterThan((Expression) visit(ctx.left), (Expression) visit(ctx.right));
            case "<":
                return new LessThan((Expression) visit(ctx.left), (Expression) visit(ctx.right));
            case "<=":
                return new LessThanEqualTo((Expression) visit(ctx.left), (Expression) visit(ctx.right));
            case ">=":
                return new GreaterThanEqualTo((Expression) visit(ctx.left), (Expression) visit(ctx.right));
            case "!=":
                return new NotEqual((Expression) visit(ctx.left), (Expression) visit(ctx.right));
            default:
                return new Equal((Expression) visit(ctx.left), (Expression) visit(ctx.right));
        }
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

    @Override
    public TreeNode visitParameter(QLParser.ParameterContext ctx) {
        return new Parameter(ctx.getText());
    }

    @Override
    public TreeNode visitStringLiteral(QLParser.StringLiteralContext ctx) {
        return new StringLiteral(ctx.getText());
    }

    @Override
    public TreeNode visitBooleanLiteral(QLParser.BooleanLiteralContext ctx) {
        return new BooleanLiteral(ctx.getText());
    }

    @Override
    public TreeNode visitIntegerLiteral(QLParser.IntegerLiteralContext ctx) {
        return new IntegerLiteral(ctx.getText());
    }

    @Override
    public TreeNode visitLogicalOr(QLParser.LogicalOrContext ctx) {
        return new Or((Expression) visit(ctx.left), (Expression) visit(ctx.right));
    }

    @Override
    public TreeNode visitLogicalAnd(QLParser.LogicalAndContext ctx) {
        return new And((Expression) visit(ctx.left), (Expression) visit(ctx.right));
    }

    @Override
    public TreeNode visitCalculatedValue(QLParser.CalculatedValueContext ctx) {
        return (Expression)visit(ctx.expression());
    }

    @Override
    public TreeNode visitParameterGroup(QLParser.ParameterGroupContext ctx) {
        return new ParameterGroup((Expression) visit(ctx.expression()));
    }
}
