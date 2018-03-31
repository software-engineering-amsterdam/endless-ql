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
	 * @param ctx the parse tree
	 */
	void enterStylesheet(QLSParser.StylesheetContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#stylesheet}.
	 * @param ctx the parse tree
	 */
	void exitStylesheet(QLSParser.StylesheetContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#stylesheetElement}.
	 * @param ctx the parse tree
	 */
	void enterStylesheetElement(QLSParser.StylesheetElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#stylesheetElement}.
	 * @param ctx the parse tree
	 */
	void exitStylesheetElement(QLSParser.StylesheetElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#pageDefition}.
	 * @param ctx the parse tree
	 */
	void enterPageDefition(QLSParser.PageDefitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#pageDefition}.
	 * @param ctx the parse tree
	 */
	void exitPageDefition(QLSParser.PageDefitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#pageElement}.
	 * @param ctx the parse tree
	 */
	void enterPageElement(QLSParser.PageElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#pageElement}.
	 * @param ctx the parse tree
	 */
	void exitPageElement(QLSParser.PageElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#section}.
	 * @param ctx the parse tree
	 */
	void enterSection(QLSParser.SectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#section}.
	 * @param ctx the parse tree
	 */
	void exitSection(QLSParser.SectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#sectionElement}.
	 * @param ctx the parse tree
	 */
	void enterSectionElement(QLSParser.SectionElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#sectionElement}.
	 * @param ctx the parse tree
	 */
	void exitSectionElement(QLSParser.SectionElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#questionDefinition}.
	 * @param ctx the parse tree
	 */
	void enterQuestionDefinition(QLSParser.QuestionDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#questionDefinition}.
	 * @param ctx the parse tree
	 */
	void exitQuestionDefinition(QLSParser.QuestionDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#defaultTypeDefinition}.
	 * @param ctx the parse tree
	 */
	void enterDefaultTypeDefinition(QLSParser.DefaultTypeDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#defaultTypeDefinition}.
	 * @param ctx the parse tree
	 */
	void exitDefaultTypeDefinition(QLSParser.DefaultTypeDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#dataTypeDefinionBlock}.
	 * @param ctx the parse tree
	 */
	void enterDataTypeDefinionBlock(QLSParser.DataTypeDefinionBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#dataTypeDefinionBlock}.
	 * @param ctx the parse tree
	 */
	void exitDataTypeDefinionBlock(QLSParser.DataTypeDefinionBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#typeDefinitionProperty}.
	 * @param ctx the parse tree
	 */
	void enterTypeDefinitionProperty(QLSParser.TypeDefinitionPropertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#typeDefinitionProperty}.
	 * @param ctx the parse tree
	 */
	void exitTypeDefinitionProperty(QLSParser.TypeDefinitionPropertyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TypeDeclarationBoolean}
	 * labeled alternative in {@link QLSParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterTypeDeclarationBoolean(QLSParser.TypeDeclarationBooleanContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TypeDeclarationBoolean}
	 * labeled alternative in {@link QLSParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitTypeDeclarationBoolean(QLSParser.TypeDeclarationBooleanContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TypeDeclarationString}
	 * labeled alternative in {@link QLSParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterTypeDeclarationString(QLSParser.TypeDeclarationStringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TypeDeclarationString}
	 * labeled alternative in {@link QLSParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitTypeDeclarationString(QLSParser.TypeDeclarationStringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TypeDeclarationInteger}
	 * labeled alternative in {@link QLSParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterTypeDeclarationInteger(QLSParser.TypeDeclarationIntegerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TypeDeclarationInteger}
	 * labeled alternative in {@link QLSParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitTypeDeclarationInteger(QLSParser.TypeDeclarationIntegerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TypeDeclarationDecimal}
	 * labeled alternative in {@link QLSParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterTypeDeclarationDecimal(QLSParser.TypeDeclarationDecimalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TypeDeclarationDecimal}
	 * labeled alternative in {@link QLSParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitTypeDeclarationDecimal(QLSParser.TypeDeclarationDecimalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TypeDeclarationMoney}
	 * labeled alternative in {@link QLSParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterTypeDeclarationMoney(QLSParser.TypeDeclarationMoneyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TypeDeclarationMoney}
	 * labeled alternative in {@link QLSParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitTypeDeclarationMoney(QLSParser.TypeDeclarationMoneyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TypeDeclarationDate}
	 * labeled alternative in {@link QLSParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterTypeDeclarationDate(QLSParser.TypeDeclarationDateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TypeDeclarationDate}
	 * labeled alternative in {@link QLSParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitTypeDeclarationDate(QLSParser.TypeDeclarationDateContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#widgetDefinition}.
	 * @param ctx the parse tree
	 */
	void enterWidgetDefinition(QLSParser.WidgetDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#widgetDefinition}.
	 * @param ctx the parse tree
	 */
	void exitWidgetDefinition(QLSParser.WidgetDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WidgetCheckboxDefinition}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 */
	void enterWidgetCheckboxDefinition(QLSParser.WidgetCheckboxDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WidgetCheckboxDefinition}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 */
	void exitWidgetCheckboxDefinition(QLSParser.WidgetCheckboxDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WidgetDropdownDefinition}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 */
	void enterWidgetDropdownDefinition(QLSParser.WidgetDropdownDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WidgetDropdownDefinition}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 */
	void exitWidgetDropdownDefinition(QLSParser.WidgetDropdownDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WidgetRadioDefinition}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 */
	void enterWidgetRadioDefinition(QLSParser.WidgetRadioDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WidgetRadioDefinition}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 */
	void exitWidgetRadioDefinition(QLSParser.WidgetRadioDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WidgetSpinboxDefinition}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 */
	void enterWidgetSpinboxDefinition(QLSParser.WidgetSpinboxDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WidgetSpinboxDefinition}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 */
	void exitWidgetSpinboxDefinition(QLSParser.WidgetSpinboxDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WidgetSliderDefinition}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 */
	void enterWidgetSliderDefinition(QLSParser.WidgetSliderDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WidgetSliderDefinition}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 */
	void exitWidgetSliderDefinition(QLSParser.WidgetSliderDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WidgetTextDefinition}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 */
	void enterWidgetTextDefinition(QLSParser.WidgetTextDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WidgetTextDefinition}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 */
	void exitWidgetTextDefinition(QLSParser.WidgetTextDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#booleanParameters}.
	 * @param ctx the parse tree
	 */
	void enterBooleanParameters(QLSParser.BooleanParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#booleanParameters}.
	 * @param ctx the parse tree
	 */
	void exitBooleanParameters(QLSParser.BooleanParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#integerParameters}.
	 * @param ctx the parse tree
	 */
	void enterIntegerParameters(QLSParser.IntegerParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#integerParameters}.
	 * @param ctx the parse tree
	 */
	void exitIntegerParameters(QLSParser.IntegerParametersContext ctx);
}