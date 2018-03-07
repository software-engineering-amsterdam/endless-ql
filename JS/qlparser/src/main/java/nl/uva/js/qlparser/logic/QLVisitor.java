package nl.uva.js.qlparser.logic;

import nl.uva.js.qlparser.antlr.QLBaseVisitor;
import nl.uva.js.qlparser.antlr.QLParser;
import nl.uva.js.qlparser.exceptions.VariableAlreadyExistsException;
import nl.uva.js.qlparser.exceptions.VariableNotFoundException;
import nl.uva.js.qlparser.helpers.NonNullRun;
import nl.uva.js.qlparser.models.enums.*;
import nl.uva.js.qlparser.models.expressions.Form;
import nl.uva.js.qlparser.models.expressions.data.*;
import nl.uva.js.qlparser.models.expressions.form.FormExpression;
import nl.uva.js.qlparser.models.expressions.form.IfBlock;
import nl.uva.js.qlparser.models.expressions.form.Question;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;

class QLVisitor extends QLBaseVisitor {
    private Map<String, Variable> variableRegistry = new HashMap<>();

    @Override
    public DataType visitDatatype(QLParser.DatatypeContext ctx) {
        return DataType.valueOf(ctx.getText().toUpperCase());
    }

    @Override
    public Boolean visitBoolval(QLParser.BoolvalContext ctx) {
        return Boolean.parseBoolean(ctx.getText());
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
//                    Match datatype of the value with any of the defined datatypes.
//                    Should antlr decide to make up value types that are not available as enum (which it should not,
//                    as the value types are defined in the grammar), the Java Optional class with throw an exception.
                    .filter(dataType -> dataType.toString().startsWith(getTokenType(ctx).substring(0, 3)))
                    .findFirst()
                    .get();

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
    public Operator visitOp(QLParser.OpContext ctx) {
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
        if (ctx.NAME() != null) {
            String varName = ctx.NAME().getText();

            if (!variableRegistry.containsKey(varName))
                throw new VariableNotFoundException(varName,
                        ctx.NAME().getSymbol().getLine(),
                        ctx.NAME().getSymbol().getCharPositionInLine());

            return variableRegistry.get(varName);

        } else if (ctx.NOT() != null)
            return Negation.builder()
                    .expression(visitExpression(ctx.expression(0)))
                    .build();

        else if (ctx.value() != null)
            return visitValue(ctx.value());

        else if (ctx.op() != null)
            return Combinator.builder()
                    .left(visitExpression(ctx.expression(0)))
                    .operator(visitOp(ctx.op()))
                    .right(visitExpression(ctx.expression(1)))
                    .build();

//        The only possibility left here is an expression between parentheses. These do not matter for the models,
//        so just return the embedded expression
        else return visitExpression(ctx.expression(0));
    }

    @Override
    public Question visitQuestion(QLParser.QuestionContext ctx) {
        String varName = ctx.NAME().getText();

        if (variableRegistry.containsKey(varName))
            throw new VariableAlreadyExistsException(varName,
                ctx.NAME().getSymbol().getLine(),
                ctx.NAME().getSymbol().getCharPositionInLine());

        Variable variable = Variable.builder()
                .dataType(visitDatatype(ctx.datatype()))
                .name(varName)
//                As the value is optional, only apply the visitExpression function if there is something to visit.
                .value(NonNullRun.function(ctx.expression(), this::visitExpression))
                .build();

        Question question = Question.builder()
                .name(varName)
                .question((String) DataType.STRING.getValueOf().apply(ctx.STRVAL().getText()))
                .dataType(variable.getDataType())
                .variable(variable)
                .build();

        variableRegistry.put(varName, variable);

        return question;
    }

    /*
     * Get the name of the token that was parsed.
     * This is particularly interesting for operators, as they can immediately be parsed to the corresponding enum.
     */
    private String getTokenType(ParserRuleContext ctx) {
        return QLParser.VOCABULARY.getSymbolicName(
                ((TerminalNode) ctx.children.get(0)).getSymbol().getType()
        );
    }
}
