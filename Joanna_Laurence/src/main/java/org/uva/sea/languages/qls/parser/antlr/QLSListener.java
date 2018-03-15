// Generated from src/main/java/org/uva/sea/languages/qls/parser/antlr/QLS.g by ANTLR 4.7.1

package org.uva.sea.languages.qls.parser.antlr;

import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.uva.sea.languages.qls.parser.antlr.QLSParser.DefaultStyleContext;
import org.uva.sea.languages.qls.parser.antlr.QLSParser.PageContext;
import org.uva.sea.languages.qls.parser.antlr.QLSParser.PagesContext;
import org.uva.sea.languages.qls.parser.antlr.QLSParser.ParameterContext;
import org.uva.sea.languages.qls.parser.antlr.QLSParser.ParametersContext;
import org.uva.sea.languages.qls.parser.antlr.QLSParser.QuestionContext;
import org.uva.sea.languages.qls.parser.antlr.QLSParser.SectionContext;
import org.uva.sea.languages.qls.parser.antlr.QLSParser.SpecificationContext;
import org.uva.sea.languages.qls.parser.antlr.QLSParser.SpecificationsContext;
import org.uva.sea.languages.qls.parser.antlr.QLSParser.StyleSpecificationContext;
import org.uva.sea.languages.qls.parser.antlr.QLSParser.StyleSpecificationsContext;
import org.uva.sea.languages.qls.parser.antlr.QLSParser.StylesheetContext;
import org.uva.sea.languages.qls.parser.antlr.QLSParser.WidgetContext;

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
    void enterStylesheet(StylesheetContext ctx);

    /**
     * Exit a parse tree produced by {@link QLSParser#stylesheet}.
     *
     * @param ctx the parse tree
     */
    void exitStylesheet(StylesheetContext ctx);

    /**
     * Enter a parse tree produced by {@link QLSParser#pages}.
     *
     * @param ctx the parse tree
     */
    void enterPages(PagesContext ctx);

    /**
     * Exit a parse tree produced by {@link QLSParser#pages}.
     *
     * @param ctx the parse tree
     */
    void exitPages(PagesContext ctx);

    /**
     * Enter a parse tree produced by {@link QLSParser#page}.
     *
     * @param ctx the parse tree
     */
    void enterPage(PageContext ctx);

    /**
     * Exit a parse tree produced by {@link QLSParser#page}.
     *
     * @param ctx the parse tree
     */
    void exitPage(PageContext ctx);

    /**
     * Enter a parse tree produced by {@link QLSParser#specifications}.
     *
     * @param ctx the parse tree
     */
    void enterSpecifications(SpecificationsContext ctx);

    /**
     * Exit a parse tree produced by {@link QLSParser#specifications}.
     *
     * @param ctx the parse tree
     */
    void exitSpecifications(SpecificationsContext ctx);

    /**
     * Enter a parse tree produced by {@link QLSParser#specification}.
     *
     * @param ctx the parse tree
     */
    void enterSpecification(SpecificationContext ctx);

    /**
     * Exit a parse tree produced by {@link QLSParser#specification}.
     *
     * @param ctx the parse tree
     */
    void exitSpecification(SpecificationContext ctx);

    /**
     * Enter a parse tree produced by {@link QLSParser#section}.
     *
     * @param ctx the parse tree
     */
    void enterSection(SectionContext ctx);

    /**
     * Exit a parse tree produced by {@link QLSParser#section}.
     *
     * @param ctx the parse tree
     */
    void exitSection(SectionContext ctx);

    /**
     * Enter a parse tree produced by {@link QLSParser#question}.
     *
     * @param ctx the parse tree
     */
    void enterQuestion(QuestionContext ctx);

    /**
     * Exit a parse tree produced by {@link QLSParser#question}.
     *
     * @param ctx the parse tree
     */
    void exitQuestion(QuestionContext ctx);

    /**
     * Enter a parse tree produced by {@link QLSParser#widget}.
     *
     * @param ctx the parse tree
     */
    void enterWidget(WidgetContext ctx);

    /**
     * Exit a parse tree produced by {@link QLSParser#widget}.
     *
     * @param ctx the parse tree
     */
    void exitWidget(WidgetContext ctx);

    /**
     * Enter a parse tree produced by {@link QLSParser#parameters}.
     *
     * @param ctx the parse tree
     */
    void enterParameters(ParametersContext ctx);

    /**
     * Exit a parse tree produced by {@link QLSParser#parameters}.
     *
     * @param ctx the parse tree
     */
    void exitParameters(ParametersContext ctx);

    /**
     * Enter a parse tree produced by {@link QLSParser#parameter}.
     *
     * @param ctx the parse tree
     */
    void enterParameter(ParameterContext ctx);

    /**
     * Exit a parse tree produced by {@link QLSParser#parameter}.
     *
     * @param ctx the parse tree
     */
    void exitParameter(ParameterContext ctx);

    /**
     * Enter a parse tree produced by {@link QLSParser#defaultStyle}.
     *
     * @param ctx the parse tree
     */
    void enterDefaultStyle(DefaultStyleContext ctx);

    /**
     * Exit a parse tree produced by {@link QLSParser#defaultStyle}.
     *
     * @param ctx the parse tree
     */
    void exitDefaultStyle(DefaultStyleContext ctx);

    /**
     * Enter a parse tree produced by {@link QLSParser#styleSpecifications}.
     *
     * @param ctx the parse tree
     */
    void enterStyleSpecifications(StyleSpecificationsContext ctx);

    /**
     * Exit a parse tree produced by {@link QLSParser#styleSpecifications}.
     *
     * @param ctx the parse tree
     */
    void exitStyleSpecifications(StyleSpecificationsContext ctx);

    /**
     * Enter a parse tree produced by {@link QLSParser#styleSpecification}.
     *
     * @param ctx the parse tree
     */
    void enterStyleSpecification(StyleSpecificationContext ctx);

    /**
     * Exit a parse tree produced by {@link QLSParser#styleSpecification}.
     *
     * @param ctx the parse tree
     */
    void exitStyleSpecification(StyleSpecificationContext ctx);
}