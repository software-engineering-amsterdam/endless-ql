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
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;

class QLVisitor extends QLBaseVisitor {
    private final Map<String, Variable> variableRegistry = new HashMap<>();

    @Override
    public DataType visitDataType(QLParser.DataTypeContext ctx) {
        return DataType.valueOf(ctx.getText().toUpperCase());
    }

    @Override
    public Value visitValue(QLParser.ValueContext ctx) {
        String typeName = (ctx.BOOLVAL != null)? "BOOL" : getTokenType(((TerminalNode) ctx.getChild(0)).getSymbol());

        DataType type = Arrays.stream(DataType.values())
//                Match datatype of the value with any of the defined datatypes.
//                Should antlr decide to make up value types that are not available as enum (which it should not,
//                as the value types are defined in the grammar), the Java Optional class with throw an exception.
                .filter(dataType -> dataType.toString().startsWith(typeName.substring(0, 3)))
                .findFirst()
                .get();

        return Value.builder()
                .dataType(type)
                .value(type.getValueOf().apply(ctx.getText()))
                .build();
    }

    private Combinator buildCombinator(ParserRuleContext ctx, Operator operator) {
        return Combinator.builder()
                .left(ctx.getChild(0).<DataExpression>accept(this))
                .operator(operator)
                .right(ctx.getChild(2).<DataExpression>accept(this))
                .build();
    }

    @Override
    public Combinator visitBoolExpression(QLParser.BoolExpressionContext ctx) {
        return buildCombinator(ctx, BoolOp.valueOf(this.getTokenType(ctx.op)));
    }

    @Override
    public Combinator visitCompExpression(QLParser.CompExpressionContext ctx) {
        return buildCombinator(ctx, CompOp.valueOf(this.getTokenType(ctx.op)));
    }

    @Override
    public Combinator visitArithExpression(QLParser.ArithExpressionContext ctx) {
        return buildCombinator(ctx, ArithOp.valueOf(this.getTokenType(ctx.op)));
    }

    @Override
    public Form visitForm(QLParser.FormContext ctx) {
        return Form.builder()
                .name(ctx.NAME().getText())
                .formExpressions(ctx.formBlock().<LinkedList<FormExpression>>accept(this))
                .build();
    }

    @Override
    public FormExpression visitFormExpression(QLParser.FormExpressionContext ctx) {
        return (FormExpression) super.visitFormExpression(ctx);
    }

    @Override
    public LinkedList<FormExpression> visitFormBlock(QLParser.FormBlockContext ctx) {
        return ctx.formExpression()
                .stream()
                .map(this::visitFormExpression)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public Negation visitNegation(QLParser.NegationContext ctx) {
        return Negation.builder()
                .expression(ctx.<DataExpression>accept(this))
                .build();
    }

    @Override
    public Variable visitVariable(QLParser.VariableContext ctx) {
        String varName = ctx.NAME().getText();

        if (!variableRegistry.containsKey(varName))
            throw new VariableNotFoundException(varName,
                    ctx.NAME().getSymbol().getLine(),
                    ctx.NAME().getSymbol().getCharPositionInLine());

        return variableRegistry.get(varName);
    }

    @Override
    public Question visitQuestion(QLParser.QuestionContext ctx) {
        String varName = ctx.NAME().getText();

        if (variableRegistry.containsKey(varName))
            throw new VariableAlreadyExistsException(varName,
                ctx.NAME().getSymbol().getLine(),
                ctx.NAME().getSymbol().getCharPositionInLine());

        Variable variable = Variable.builder()
                .dataType(ctx.dataType().<DataType>accept(this))
                .name(varName)
//                As the value is optional, only apply the visitExpression function if there is something to visit.
                .value(NonNullRun.function(ctx.expression(), e -> e.<DataExpression>accept(this)))
                .build();

        Question question = Question.builder()
                .question((String) DataType.STRING.getValueOf().apply(ctx.STRVAL().getText()))
                .dataType(variable.getDataType())
                .variable(variable)
                .build();

        variableRegistry.put(varName, variable);

        return question;
    }

    @Override
    public IfBlock visitIfBlock(QLParser.IfBlockContext ctx) {
        return IfBlock.builder()
                .condition(ctx.expression().<DataExpression>accept(this))
                .expressions(ctx.formBlock().<LinkedList<FormExpression>>accept(this))
                .build();
    }

    @Override
    public DataExpression visitParentheses(QLParser.ParenthesesContext ctx) {
        return ctx.expression().<DataExpression>accept(this);
    }

    /*
     * Get the name of the token that was parsed.
     * This is particularly interesting for operators, as they can immediately be parsed to the corresponding enum.
     */
    private String getTokenType(Token token) {
        return QLParser.VOCABULARY.getSymbolicName(
                token.getType()
        );
    }
}
