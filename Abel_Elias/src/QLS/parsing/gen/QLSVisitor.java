// Generated from /home/ajm/Desktop/newEndless/endless-ql/Abel_Elias/src/QLS/parsing/QLS.g4 by ANTLR 4.7
package QLS.parsing;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link QLSParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface QLSVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link QLSParser#stylesheet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStylesheet(QLSParser.StylesheetContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#page}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPage(QLSParser.PageContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(QLSParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#lineInBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLineInBlock(QLSParser.LineInBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion(QLSParser.QuestionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#section}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSection(QLSParser.SectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#defaultWidget}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefaultWidget(QLSParser.DefaultWidgetContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWidget(QLSParser.WidgetContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#widgetStyle}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWidgetStyle(QLSParser.WidgetStyleContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#style}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStyle(QLSParser.StyleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code booltype}
	 * labeled alternative in {@link QLSParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooltype(QLSParser.BooltypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringtype}
	 * labeled alternative in {@link QLSParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringtype(QLSParser.StringtypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code integertype}
	 * labeled alternative in {@link QLSParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegertype(QLSParser.IntegertypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code moneytype}
	 * labeled alternative in {@link QLSParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMoneytype(QLSParser.MoneytypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code datetype}
	 * labeled alternative in {@link QLSParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDatetype(QLSParser.DatetypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code decimaltype}
	 * labeled alternative in {@link QLSParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecimaltype(QLSParser.DecimaltypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sliderwidget}
	 * labeled alternative in {@link QLSParser#widgetType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSliderwidget(QLSParser.SliderwidgetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code spinboxwidget}
	 * labeled alternative in {@link QLSParser#widgetType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpinboxwidget(QLSParser.SpinboxwidgetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code checkboxwidget}
	 * labeled alternative in {@link QLSParser#widgetType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCheckboxwidget(QLSParser.CheckboxwidgetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code textwidget}
	 * labeled alternative in {@link QLSParser#widgetType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTextwidget(QLSParser.TextwidgetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code radiowidget}
	 * labeled alternative in {@link QLSParser#widgetType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRadiowidget(QLSParser.RadiowidgetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code dropdownwidget}
	 * labeled alternative in {@link QLSParser#widgetType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropdownwidget(QLSParser.DropdownwidgetContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(QLSParser.ValueContext ctx);
}