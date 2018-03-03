package models.ast;

import models.ast.elements.Form;
import grammar.QLBaseVisitor;
import grammar.QLParser;
import models.ast.elements.datatypes.*;
import models.ast.elements.expressions.Expression;
import models.ast.elements.statement.Condition;
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

        Condition condition = new Condition(ctx);

        for (QLParser.StatementContext StatementContext : ctx.statement()) {
            Statement statement = visitStatement(StatementContext);
            condition.addStatement(statement);
        }

//        if (ctx.elseStatement() != null) {
//
//        }

        return condition;
    }


    @Override
    public Question visitQuestion(QLParser.QuestionContext ctx) {

        Question question = new Question(
                ctx.label.getText(),
                ctx.variableName.getText(),
                (DataType) visit(ctx.dataType())
        );

        if (ctx.expression() != null) {
            question.setAssignedExpression((Expression) visit(ctx.expression()));
        }

        return question;
    }

    @Override
    public Object visitTypeDeclarationBoolean(QLParser.TypeDeclarationBooleanContext ctx) {
        return super.visitTypeDeclarationBoolean(ctx);
    }

    @Override
    public Object visitTypeDeclarationDecimal(QLParser.TypeDeclarationDecimalContext ctx) {
        return super.visitTypeDeclarationDecimal(ctx);
    }

    @Override
    public Object visitTypeDeclarationInteger(QLParser.TypeDeclarationIntegerContext ctx) {
        return super.visitTypeDeclarationInteger(ctx);
    }

    @Override
    public Object visitTypeDeclarationString(QLParser.TypeDeclarationStringContext ctx) {
        return super.visitTypeDeclarationString(ctx);
    }

//    @Override
//    public Expression visitExpression(QLParser.ExpressionContext ctx) {
//        super.visitExpression(ctx);
//        return new Expression();
//    }
}
