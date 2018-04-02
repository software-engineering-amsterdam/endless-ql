// Generated from C:/dev/uva/endless-ql/Abel_Elias/src/QLS/parsing\QLS.g4 by ANTLR 4.7
package QLS.parsing.gen;

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
     * Visit a parse tree produced by {@link QLSParser#page}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitPage(QLSParser.PageContext ctx);

    /**
     * Visit a parse tree produced by {@link QLSParser#section}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSection(QLSParser.SectionContext ctx);

    /**
     * Visit a parse tree produced by {@link QLSParser#element}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitElement(QLSParser.ElementContext ctx);

    /**
     * Visit a parse tree produced by {@link QLSParser#question}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitQuestion(QLSParser.QuestionContext ctx);

    /**
     * Visit a parse tree produced by {@link QLSParser#defaultWidget}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitDefaultWidget(QLSParser.DefaultWidgetContext ctx);

    /**
     * Visit a parse tree produced by {@link QLSParser#widget}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitWidget(QLSParser.WidgetContext ctx);

    /**
     * Visit a parse tree produced by {@link QLSParser#widgetType}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitWidgetType(QLSParser.WidgetTypeContext ctx);

    /**
     * Visit a parse tree produced by {@link QLSParser#checkboxWidget}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitCheckboxWidget(QLSParser.CheckboxWidgetContext ctx);

    /**
     * Visit a parse tree produced by {@link QLSParser#textWidget}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitTextWidget(QLSParser.TextWidgetContext ctx);

    /**
     * Visit a parse tree produced by {@link QLSParser#radioWidget}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitRadioWidget(QLSParser.RadioWidgetContext ctx);

    /**
     * Visit a parse tree produced by {@link QLSParser#spinboxWidget}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSpinboxWidget(QLSParser.SpinboxWidgetContext ctx);

    /**
     * Visit a parse tree produced by {@link QLSParser#sliderWidget}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSliderWidget(QLSParser.SliderWidgetContext ctx);

    /**
     * Visit a parse tree produced by {@link QLSParser#dropdownWidget}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitDropdownWidget(QLSParser.DropdownWidgetContext ctx);

    /**
     * Visit a parse tree produced by {@link QLSParser#style}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitStyle(QLSParser.StyleContext ctx);

    /**
     * Visit a parse tree produced by {@link QLSParser#argList}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitArgList(QLSParser.ArgListContext ctx);

    /**
     * Visit a parse tree produced by the {@code booltype}
     * labeled alternative in {@link QLSParser#type}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitBooltype(QLSParser.BooltypeContext ctx);

    /**
     * Visit a parse tree produced by the {@code stringtype}
     * labeled alternative in {@link QLSParser#type}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitStringtype(QLSParser.StringtypeContext ctx);

    /**
     * Visit a parse tree produced by the {@code integertype}
     * labeled alternative in {@link QLSParser#type}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIntegertype(QLSParser.IntegertypeContext ctx);

    /**
     * Visit a parse tree produced by the {@code moneytype}
     * labeled alternative in {@link QLSParser#type}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitMoneytype(QLSParser.MoneytypeContext ctx);

    /**
     * Visit a parse tree produced by the {@code datetype}
     * labeled alternative in {@link QLSParser#type}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitDatetype(QLSParser.DatetypeContext ctx);

    /**
     * Visit a parse tree produced by the {@code decimaltype}
     * labeled alternative in {@link QLSParser#type}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitDecimaltype(QLSParser.DecimaltypeContext ctx);

    /**
     * Visit a parse tree produced by {@link QLSParser#defaultdef}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitDefaultdef(QLSParser.DefaultdefContext ctx);

    /**
     * Visit a parse tree produced by {@link QLSParser#blockdefault}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitBlockdefault(QLSParser.BlockdefaultContext ctx);

    /**
     * Visit a parse tree produced by {@link QLSParser#linedefault}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitLinedefault(QLSParser.LinedefaultContext ctx);

    /**
     * Visit a parse tree produced by {@link QLSParser#widgetProperty}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitWidgetProperty(QLSParser.WidgetPropertyContext ctx);

    /**
     * Visit a parse tree produced by {@link QLSParser#widthproperty}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitWidthproperty(QLSParser.WidthpropertyContext ctx);

    /**
     * Visit a parse tree produced by {@link QLSParser#fontproperty}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitFontproperty(QLSParser.FontpropertyContext ctx);

    /**
     * Visit a parse tree produced by {@link QLSParser#fontsizeproperty}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitFontsizeproperty(QLSParser.FontsizepropertyContext ctx);

    /**
     * Visit a parse tree produced by {@link QLSParser#colorproperty}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitColorproperty(QLSParser.ColorpropertyContext ctx);

    /**
     * Visit a parse tree produced by {@link QLSParser#value}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitValue(QLSParser.ValueContext ctx);
}