// Generated from QLS.g4 by ANTLR 4.7.1

package antlr.generated;

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
	 * Visit a parse tree produced by {@link QLSParser#section}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSection(QLSParser.SectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#segment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSegment(QLSParser.SegmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#defaultStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefaultStatement(QLSParser.DefaultStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion(QLSParser.QuestionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWidget(QLSParser.WidgetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code radio}
	 * labeled alternative in {@link QLSParser#widgetType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRadio(QLSParser.RadioContext ctx);
	/**
	 * Visit a parse tree produced by the {@code checkbox}
	 * labeled alternative in {@link QLSParser#widgetType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCheckbox(QLSParser.CheckboxContext ctx);
	/**
	 * Visit a parse tree produced by the {@code dropdown}
	 * labeled alternative in {@link QLSParser#widgetType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropdown(QLSParser.DropdownContext ctx);
	/**
	 * Visit a parse tree produced by the {@code slider}
	 * labeled alternative in {@link QLSParser#widgetType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSlider(QLSParser.SliderContext ctx);
	/**
	 * Visit a parse tree produced by the {@code text}
	 * labeled alternative in {@link QLSParser#widgetType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitText(QLSParser.TextContext ctx);
	/**
	 * Visit a parse tree produced by the {@code booleanType}
	 * labeled alternative in {@link QLSParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanType(QLSParser.BooleanTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code integerType}
	 * labeled alternative in {@link QLSParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerType(QLSParser.IntegerTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code moneyType}
	 * labeled alternative in {@link QLSParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMoneyType(QLSParser.MoneyTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringType}
	 * labeled alternative in {@link QLSParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringType(QLSParser.StringTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#style}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStyle(QLSParser.StyleContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#styleProperty}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStyleProperty(QLSParser.StylePropertyContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(QLSParser.ValueContext ctx);
}