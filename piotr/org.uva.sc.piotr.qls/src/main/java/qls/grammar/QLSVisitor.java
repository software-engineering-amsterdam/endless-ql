// Generated from /Users/piotrkosytorz/Library/Mobile Documents/com~apple~CloudDocs/Study/SC/endless-ql/piotr/org.uva.sc.piotr.qls/src/main/java/qls/grammar/QLS.g4 by ANTLR 4.7
package qls.grammar;
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
	 * Visit a parse tree produced by {@link QLSParser#stylesheetElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStylesheetElement(QLSParser.StylesheetElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#pageDefition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPageDefition(QLSParser.PageDefitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#pageElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPageElement(QLSParser.PageElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#section}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSection(QLSParser.SectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#sectionElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSectionElement(QLSParser.SectionElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#questionDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionDefinition(QLSParser.QuestionDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#defaultTypeDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefaultTypeDefinition(QLSParser.DefaultTypeDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#dataTypeDefinionBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDataTypeDefinionBlock(QLSParser.DataTypeDefinionBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#typeDefinitionProperty}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDefinitionProperty(QLSParser.TypeDefinitionPropertyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeDeclarationBoolean}
	 * labeled alternative in {@link QLSParser#dataType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDeclarationBoolean(QLSParser.TypeDeclarationBooleanContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeDeclarationString}
	 * labeled alternative in {@link QLSParser#dataType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDeclarationString(QLSParser.TypeDeclarationStringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeDeclarationInteger}
	 * labeled alternative in {@link QLSParser#dataType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDeclarationInteger(QLSParser.TypeDeclarationIntegerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeDeclarationDecimal}
	 * labeled alternative in {@link QLSParser#dataType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDeclarationDecimal(QLSParser.TypeDeclarationDecimalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeDeclarationMoney}
	 * labeled alternative in {@link QLSParser#dataType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDeclarationMoney(QLSParser.TypeDeclarationMoneyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeDeclarationDate}
	 * labeled alternative in {@link QLSParser#dataType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDeclarationDate(QLSParser.TypeDeclarationDateContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#widgetDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWidgetDefinition(QLSParser.WidgetDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code WidgetCheckboxDefinition}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWidgetCheckboxDefinition(QLSParser.WidgetCheckboxDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code WidgetDropdownDefinition}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWidgetDropdownDefinition(QLSParser.WidgetDropdownDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code WidgetRadioDefinition}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWidgetRadioDefinition(QLSParser.WidgetRadioDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code WidgetSpinboxDefinition}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWidgetSpinboxDefinition(QLSParser.WidgetSpinboxDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code WidgetSliderDefinition}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWidgetSliderDefinition(QLSParser.WidgetSliderDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code WidgetTextDefinition}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWidgetTextDefinition(QLSParser.WidgetTextDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#booleanParameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanParameters(QLSParser.BooleanParametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#integerParameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerParameters(QLSParser.IntegerParametersContext ctx);
}