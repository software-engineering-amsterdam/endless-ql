// Generated from /Users/piotrkosytorz/Library/Mobile Documents/com~apple~CloudDocs/Study/SC/endless-ql/piotr/org.uva.sc.piotr.qls/src/main/java/qls/grammar/QLS.g4 by ANTLR 4.7
package qls.grammar;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link QLSParser}.
 */
public interface QLSListener extends ParseTreeListener {
    /**
     * Enter a parse tree produced by {@link QLSParser#stylesheet}.
     *
     * @param ctx the parse tree
     */
    void enterStylesheet(QLSParser.StylesheetContext ctx);

    /**
     * Exit a parse tree produced by {@link QLSParser#stylesheet}.
     *
     * @param ctx the parse tree
     */
    void exitStylesheet(QLSParser.StylesheetContext ctx);

    /**
     * Enter a parse tree produced by {@link QLSParser#pageDefinition}.
     *
     * @param ctx the parse tree
     */
    void enterPageDefinition(QLSParser.PageDefinitionContext ctx);

    /**
     * Exit a parse tree produced by {@link QLSParser#pageDefinition}.
     *
     * @param ctx the parse tree
     */
    void exitPageDefinition(QLSParser.PageDefinitionContext ctx);

    /**
     * Enter a parse tree produced by {@link QLSParser#blockElement}.
     *
     * @param ctx the parse tree
     */
    void enterBlockElement(QLSParser.BlockElementContext ctx);

    /**
     * Exit a parse tree produced by {@link QLSParser#blockElement}.
     *
     * @param ctx the parse tree
     */
    void exitBlockElement(QLSParser.BlockElementContext ctx);

    /**
     * Enter a parse tree produced by {@link QLSParser#section}.
     *
     * @param ctx the parse tree
     */
    void enterSection(QLSParser.SectionContext ctx);

    /**
     * Exit a parse tree produced by {@link QLSParser#section}.
     *
     * @param ctx the parse tree
     */
    void exitSection(QLSParser.SectionContext ctx);

    /**
     * Enter a parse tree produced by {@link QLSParser#questionDefinition}.
     *
     * @param ctx the parse tree
     */
    void enterQuestionDefinition(QLSParser.QuestionDefinitionContext ctx);

    /**
     * Exit a parse tree produced by {@link QLSParser#questionDefinition}.
     *
     * @param ctx the parse tree
     */
    void exitQuestionDefinition(QLSParser.QuestionDefinitionContext ctx);

    /**
     * Enter a parse tree produced by {@link QLSParser#defaultDefinition}.
     *
     * @param ctx the parse tree
     */
    void enterDefaultDefinition(QLSParser.DefaultDefinitionContext ctx);

    /**
     * Exit a parse tree produced by {@link QLSParser#defaultDefinition}.
     *
     * @param ctx the parse tree
     */
    void exitDefaultDefinition(QLSParser.DefaultDefinitionContext ctx);

    /**
     * Enter a parse tree produced by {@link QLSParser#typeDefinitionProperty}.
     *
     * @param ctx the parse tree
     */
    void enterTypeDefinitionProperty(QLSParser.TypeDefinitionPropertyContext ctx);

    /**
     * Exit a parse tree produced by {@link QLSParser#typeDefinitionProperty}.
     *
     * @param ctx the parse tree
     */
    void exitTypeDefinitionProperty(QLSParser.TypeDefinitionPropertyContext ctx);

    /**
     * Enter a parse tree produced by {@link QLSParser#dataType}.
     *
     * @param ctx the parse tree
     */
    void enterDataType(QLSParser.DataTypeContext ctx);

    /**
     * Exit a parse tree produced by {@link QLSParser#dataType}.
     *
     * @param ctx the parse tree
     */
    void exitDataType(QLSParser.DataTypeContext ctx);

    /**
     * Enter a parse tree produced by {@link QLSParser#widgetDefinition}.
     *
     * @param ctx the parse tree
     */
    void enterWidgetDefinition(QLSParser.WidgetDefinitionContext ctx);

    /**
     * Exit a parse tree produced by {@link QLSParser#widgetDefinition}.
     *
     * @param ctx the parse tree
     */
    void exitWidgetDefinition(QLSParser.WidgetDefinitionContext ctx);

    /**
     * Enter a parse tree produced by {@link QLSParser#widget}.
     *
     * @param ctx the parse tree
     */
    void enterWidget(QLSParser.WidgetContext ctx);

    /**
     * Exit a parse tree produced by {@link QLSParser#widget}.
     *
     * @param ctx the parse tree
     */
    void exitWidget(QLSParser.WidgetContext ctx);

    /**
     * Enter a parse tree produced by {@link QLSParser#booleanParameters}.
     *
     * @param ctx the parse tree
     */
    void enterBooleanParameters(QLSParser.BooleanParametersContext ctx);

    /**
     * Exit a parse tree produced by {@link QLSParser#booleanParameters}.
     *
     * @param ctx the parse tree
     */
    void exitBooleanParameters(QLSParser.BooleanParametersContext ctx);

    /**
     * Enter a parse tree produced by {@link QLSParser#integerParameters}.
     *
     * @param ctx the parse tree
     */
    void enterIntegerParameters(QLSParser.IntegerParametersContext ctx);

    /**
     * Exit a parse tree produced by {@link QLSParser#integerParameters}.
     *
     * @param ctx the parse tree
     */
    void exitIntegerParameters(QLSParser.IntegerParametersContext ctx);
}