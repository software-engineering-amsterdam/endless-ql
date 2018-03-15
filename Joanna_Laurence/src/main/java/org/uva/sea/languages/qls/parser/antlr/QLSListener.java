// Generated from src/main/java/org/uva/sea/qls/parser/antlr/QLS.g by ANTLR 4.7.1

package org.uva.sea.languages.qls.parser.antlr;

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
     * Enter a parse tree produced by {@link QLSParser#pages}.
     *
     * @param ctx the parse tree
     */
    void enterPages(QLSParser.PagesContext ctx);

    /**
     * Exit a parse tree produced by {@link QLSParser#pages}.
     *
     * @param ctx the parse tree
     */
    void exitPages(QLSParser.PagesContext ctx);

    /**
     * Enter a parse tree produced by {@link QLSParser#page}.
     *
     * @param ctx the parse tree
     */
    void enterPage(QLSParser.PageContext ctx);

    /**
     * Exit a parse tree produced by {@link QLSParser#page}.
     *
     * @param ctx the parse tree
     */
    void exitPage(QLSParser.PageContext ctx);

    /**
     * Enter a parse tree produced by {@link QLSParser#specifications}.
     *
     * @param ctx the parse tree
     */
    void enterSpecifications(QLSParser.SpecificationsContext ctx);

    /**
     * Exit a parse tree produced by {@link QLSParser#specifications}.
     *
     * @param ctx the parse tree
     */
    void exitSpecifications(QLSParser.SpecificationsContext ctx);

    /**
     * Enter a parse tree produced by {@link QLSParser#specification}.
     *
     * @param ctx the parse tree
     */
    void enterSpecification(QLSParser.SpecificationContext ctx);

    /**
     * Exit a parse tree produced by {@link QLSParser#specification}.
     *
     * @param ctx the parse tree
     */
    void exitSpecification(QLSParser.SpecificationContext ctx);

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
     * Enter a parse tree produced by {@link QLSParser#question}.
     *
     * @param ctx the parse tree
     */
    void enterQuestion(QLSParser.QuestionContext ctx);

    /**
     * Exit a parse tree produced by {@link QLSParser#question}.
     *
     * @param ctx the parse tree
     */
    void exitQuestion(QLSParser.QuestionContext ctx);

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
     * Enter a parse tree produced by {@link QLSParser#parameters}.
     *
     * @param ctx the parse tree
     */
    void enterParameters(QLSParser.ParametersContext ctx);

    /**
     * Exit a parse tree produced by {@link QLSParser#parameters}.
     *
     * @param ctx the parse tree
     */
    void exitParameters(QLSParser.ParametersContext ctx);

    /**
     * Enter a parse tree produced by {@link QLSParser#parameter}.
     *
     * @param ctx the parse tree
     */
    void enterParameter(QLSParser.ParameterContext ctx);

    /**
     * Exit a parse tree produced by {@link QLSParser#parameter}.
     *
     * @param ctx the parse tree
     */
    void exitParameter(QLSParser.ParameterContext ctx);

    /**
     * Enter a parse tree produced by {@link QLSParser#defaultStyle}.
     *
     * @param ctx the parse tree
     */
    void enterDefaultStyle(QLSParser.DefaultStyleContext ctx);

    /**
     * Exit a parse tree produced by {@link QLSParser#defaultStyle}.
     *
     * @param ctx the parse tree
     */
    void exitDefaultStyle(QLSParser.DefaultStyleContext ctx);

    /**
     * Enter a parse tree produced by {@link QLSParser#styleSpecifications}.
     *
     * @param ctx the parse tree
     */
    void enterStyleSpecifications(QLSParser.StyleSpecificationsContext ctx);

    /**
     * Exit a parse tree produced by {@link QLSParser#styleSpecifications}.
     *
     * @param ctx the parse tree
     */
    void exitStyleSpecifications(QLSParser.StyleSpecificationsContext ctx);

    /**
     * Enter a parse tree produced by {@link QLSParser#styleSpecification}.
     *
     * @param ctx the parse tree
     */
    void enterStyleSpecification(QLSParser.StyleSpecificationContext ctx);

    /**
     * Exit a parse tree produced by {@link QLSParser#styleSpecification}.
     *
     * @param ctx the parse tree
     */
    void exitStyleSpecification(QLSParser.StyleSpecificationContext ctx);

    /**
     * Enter a parse tree produced by {@link QLSParser#string}.
     *
     * @param ctx the parse tree
     */
    void enterString(QLSParser.StringContext ctx);

    /**
     * Exit a parse tree produced by {@link QLSParser#string}.
     *
     * @param ctx the parse tree
     */
    void exitString(QLSParser.StringContext ctx);
}