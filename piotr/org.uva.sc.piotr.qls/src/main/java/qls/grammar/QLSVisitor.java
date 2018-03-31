// Generated from /Users/piotrkosytorz/Library/Mobile Documents/com~apple~CloudDocs/Study/SC/endless-ql/piotr/org.uva.sc.piotr.qls/src/main/java/qls/grammar/QLS.g4 by ANTLR 4.7
package qls.grammar;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link QLSParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 *            operations with no return type.
 */
public interface QLSVisitor<T> extends ParseTreeVisitor<T> {
    /**
     * Visit a parse tree produced by {@link QLSParser#stylesheet}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitStylesheet(QLSParser.StylesheetContext ctx);

    /**
     * Visit a parse tree produced by {@link QLSParser#pageDefinition}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitPageDefinition(QLSParser.PageDefinitionContext ctx);

    /**
     * Visit a parse tree produced by {@link QLSParser#blockElement}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitBlockElement(QLSParser.BlockElementContext ctx);

    /**
     * Visit a parse tree produced by {@link QLSParser#section}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSection(QLSParser.SectionContext ctx);

    /**
     * Visit a parse tree produced by {@link QLSParser#questionDefinition}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitQuestionDefinition(QLSParser.QuestionDefinitionContext ctx);

    /**
     * Visit a parse tree produced by {@link QLSParser#defaultDefinition}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitDefaultDefinition(QLSParser.DefaultDefinitionContext ctx);

    /**
     * Visit a parse tree produced by {@link QLSParser#typeDefinitionProperty}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitTypeDefinitionProperty(QLSParser.TypeDefinitionPropertyContext ctx);

    /**
     * Visit a parse tree produced by {@link QLSParser#dataType}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitDataType(QLSParser.DataTypeContext ctx);

    /**
     * Visit a parse tree produced by {@link QLSParser#widgetDefinition}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitWidgetDefinition(QLSParser.WidgetDefinitionContext ctx);

    /**
     * Visit a parse tree produced by {@link QLSParser#widget}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitWidget(QLSParser.WidgetContext ctx);

    /**
     * Visit a parse tree produced by {@link QLSParser#booleanParameters}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitBooleanParameters(QLSParser.BooleanParametersContext ctx);

    /**
     * Visit a parse tree produced by {@link QLSParser#integerParameters}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIntegerParameters(QLSParser.IntegerParametersContext ctx);
}