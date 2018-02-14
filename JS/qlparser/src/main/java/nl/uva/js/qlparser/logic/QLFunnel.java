package nl.uva.js.qlparser.logic;

import nl.uva.js.qlparser.antlr.QLParser;
import nl.uva.js.qlparser.antlr.QLVisitor;
import nl.uva.js.qlparser.models.Form;
import nl.uva.js.qlparser.models.dataexpressions.*;
import nl.uva.js.qlparser.models.enums.*;
import nl.uva.js.qlparser.models.formexpressions.FormExpression;
import nl.uva.js.qlparser.models.formexpressions.IfBlock;
import nl.uva.js.qlparser.models.formexpressions.Question;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

class QLFunnel implements QLVisitor {

    @Override
    public DataType visitDatatype(QLParser.DatatypeContext ctx) {
        return DataType.valueOf(ctx.getText().toUpperCase());
    }

    @Override
    public Boolean visitBoolval(QLParser.BoolvalContext ctx) {
        return ctx.getText().equals("true");
    }

    @Override
    public Value visitValue(QLParser.ValueContext ctx) {
        DataType type;
        Object value;

        if (ctx.boolval() != null) {
            type = DataType.BOOLEAN;
            value = visitBoolval(ctx.boolval());
        } else {
            type = Arrays.stream(DataType.values())
                    .filter(dt -> dt.toString().startsWith(getTokenType(ctx).substring(0, 3)))
                    .findFirst()
                    .orElse(null);

            value = type.getValueOf().apply(ctx.getText());
        }

        return Value.builder()
                .dataType(type)
                .value(value)
                .build();
    }

    @Override
    public Operator visitBoolOp(QLParser.BoolOpContext ctx) {
        return BoolOp.valueOf(this.getTokenType(ctx));
    }

    @Override
    public Operator visitCompOp(QLParser.CompOpContext ctx) {
        return CompOp.valueOf(this.getTokenType(ctx));
    }

    @Override
    public Operator visitArithOp(QLParser.ArithOpContext ctx) {
        return ArithOp.valueOf(this.getTokenType(ctx));
    }

    @Override
    public Operator visitOper(QLParser.OperContext ctx) {
        if (ctx.boolOp() != null) return visitBoolOp(ctx.boolOp());
        else if (ctx.compOp() != null) return visitCompOp(ctx.compOp());
        else return visitArithOp(ctx.arithOp());
    }

    @Override
    public Form visitForm(QLParser.FormContext ctx) {
        return Form.builder()
                .name(ctx.NAME().getText())
                .formExpressions(visitFormBlock(ctx.formBlock()))
                .build();
    }

    @Override
    public LinkedList<FormExpression> visitFormBlock(QLParser.FormBlockContext ctx) {
        return ctx.formExpression()
                .stream()
                .map(this::visitFormExpression)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public FormExpression visitFormExpression(QLParser.FormExpressionContext ctx) {
        if (ctx.question() != null)
            return visitQuestion(ctx.question());

        else
            return IfBlock.builder()
                    .condition(visitExpression(ctx.expression()))
                    .expressions(visitFormBlock(ctx.formBlock()))
                    .build();
    }

    @Override
    public DataExpression visitExpression(QLParser.ExpressionContext ctx) {
        if (ctx.NAME() != null)
            return Variable.builder()
                    .name(ctx.NAME().getText())
                    .build();

        else if (ctx.value() != null)
            return visitValue(ctx.value());

        else if (ctx.NOT() != null)
            return Negation.builder()
                    .expression(visitExpression(ctx.expression(0)))
                    .build();

        else if (ctx.oper() != null)
            return Combinator.builder()
                    .left(visitExpression(ctx.expression(0)))
                    .operator(visitOper(ctx.oper()))
                    .right(visitExpression(ctx.expression(1)))
                    .build();

        else return visitExpression(ctx.expression(0));
    }

    @Override
    public Question visitQuestion(QLParser.QuestionContext ctx) {
        return Question.builder()
                .name(ctx.NAME().getText())
                .question(ctx.STRVAL().getText())
                .dataType(visitDatatype(ctx.datatype()))
                .value((ctx.expression() == null)? null : visitExpression(ctx.expression()))
                .build();
    }

    private String getTokenType(ParserRuleContext ctx) {
        return QLParser.VOCABULARY.getSymbolicName(
                ((TerminalNode) ctx.children.get(0)).getSymbol().getType()
        );
    }


    @Override
    public Object visit(ParseTree tree) {
        return null;
    }

    @Override
    public Object visitChildren(RuleNode node) {
        return null;
    }

    @Override
    public Object visitTerminal(TerminalNode node) {
        return null;
    }

    @Override
    public Object visitErrorNode(ErrorNode node) {
        return null;
    }
}
