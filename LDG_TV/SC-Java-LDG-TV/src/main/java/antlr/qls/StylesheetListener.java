// Generated from C:/Users/laure/Documents/Git-repositories/software-construction/endless-ql/LDG_TV/SC-Java-LDG-TV/src/main/java/antlr/qls\Stylesheet.g4 by ANTLR 4.7
package antlr.qls;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link StylesheetParser}.
 */
public interface StylesheetListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link StylesheetParser#stylesheetBuilder}.
	 * @param ctx the parse tree
	 */
	void enterStylesheetBuilder(StylesheetParser.StylesheetBuilderContext ctx);
	/**
	 * Exit a parse tree produced by {@link StylesheetParser#stylesheetBuilder}.
	 * @param ctx the parse tree
	 */
	void exitStylesheetBuilder(StylesheetParser.StylesheetBuilderContext ctx);
	/**
	 * Enter a parse tree produced by {@link StylesheetParser#stylesheetData}.
	 * @param ctx the parse tree
	 */
	void enterStylesheetData(StylesheetParser.StylesheetDataContext ctx);
	/**
	 * Exit a parse tree produced by {@link StylesheetParser#stylesheetData}.
	 * @param ctx the parse tree
	 */
	void exitStylesheetData(StylesheetParser.StylesheetDataContext ctx);
	/**
	 * Enter a parse tree produced by {@link StylesheetParser#pageNodeStructure}.
	 * @param ctx the parse tree
	 */
	void enterPageNodeStructure(StylesheetParser.PageNodeStructureContext ctx);
	/**
	 * Exit a parse tree produced by {@link StylesheetParser#pageNodeStructure}.
	 * @param ctx the parse tree
	 */
	void exitPageNodeStructure(StylesheetParser.PageNodeStructureContext ctx);
	/**
	 * Enter a parse tree produced by {@link StylesheetParser#sectionNodeStructure}.
	 * @param ctx the parse tree
	 */
	void enterSectionNodeStructure(StylesheetParser.SectionNodeStructureContext ctx);
	/**
	 * Exit a parse tree produced by {@link StylesheetParser#sectionNodeStructure}.
	 * @param ctx the parse tree
	 */
	void exitSectionNodeStructure(StylesheetParser.SectionNodeStructureContext ctx);
	/**
	 * Enter a parse tree produced by {@link StylesheetParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(StylesheetParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link StylesheetParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(StylesheetParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link StylesheetParser#label}.
	 * @param ctx the parse tree
	 */
	void enterLabel(StylesheetParser.LabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link StylesheetParser#label}.
	 * @param ctx the parse tree
	 */
	void exitLabel(StylesheetParser.LabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link StylesheetParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(StylesheetParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link StylesheetParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(StylesheetParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link StylesheetParser#questionStructure}.
	 * @param ctx the parse tree
	 */
	void enterQuestionStructure(StylesheetParser.QuestionStructureContext ctx);
	/**
	 * Exit a parse tree produced by {@link StylesheetParser#questionStructure}.
	 * @param ctx the parse tree
	 */
	void exitQuestionStructure(StylesheetParser.QuestionStructureContext ctx);
	/**
	 * Enter a parse tree produced by {@link StylesheetParser#uiElement}.
	 * @param ctx the parse tree
	 */
	void enterUiElement(StylesheetParser.UiElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link StylesheetParser#uiElement}.
	 * @param ctx the parse tree
	 */
	void exitUiElement(StylesheetParser.UiElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link StylesheetParser#uiIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterUiIdentifier(StylesheetParser.UiIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link StylesheetParser#uiIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitUiIdentifier(StylesheetParser.UiIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link StylesheetParser#uiType}.
	 * @param ctx the parse tree
	 */
	void enterUiType(StylesheetParser.UiTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link StylesheetParser#uiType}.
	 * @param ctx the parse tree
	 */
	void exitUiType(StylesheetParser.UiTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link StylesheetParser#checkbox}.
	 * @param ctx the parse tree
	 */
	void enterCheckbox(StylesheetParser.CheckboxContext ctx);
	/**
	 * Exit a parse tree produced by {@link StylesheetParser#checkbox}.
	 * @param ctx the parse tree
	 */
	void exitCheckbox(StylesheetParser.CheckboxContext ctx);
	/**
	 * Enter a parse tree produced by {@link StylesheetParser#radio}.
	 * @param ctx the parse tree
	 */
	void enterRadio(StylesheetParser.RadioContext ctx);
	/**
	 * Exit a parse tree produced by {@link StylesheetParser#radio}.
	 * @param ctx the parse tree
	 */
	void exitRadio(StylesheetParser.RadioContext ctx);
	/**
	 * Enter a parse tree produced by {@link StylesheetParser#radioOptions}.
	 * @param ctx the parse tree
	 */
	void enterRadioOptions(StylesheetParser.RadioOptionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link StylesheetParser#radioOptions}.
	 * @param ctx the parse tree
	 */
	void exitRadioOptions(StylesheetParser.RadioOptionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link StylesheetParser#spinbox}.
	 * @param ctx the parse tree
	 */
	void enterSpinbox(StylesheetParser.SpinboxContext ctx);
	/**
	 * Exit a parse tree produced by {@link StylesheetParser#spinbox}.
	 * @param ctx the parse tree
	 */
	void exitSpinbox(StylesheetParser.SpinboxContext ctx);
	/**
	 * Enter a parse tree produced by {@link StylesheetParser#money}.
	 * @param ctx the parse tree
	 */
	void enterMoney(StylesheetParser.MoneyContext ctx);
	/**
	 * Exit a parse tree produced by {@link StylesheetParser#money}.
	 * @param ctx the parse tree
	 */
	void exitMoney(StylesheetParser.MoneyContext ctx);
	/**
	 * Enter a parse tree produced by {@link StylesheetParser#option}.
	 * @param ctx the parse tree
	 */
	void enterOption(StylesheetParser.OptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link StylesheetParser#option}.
	 * @param ctx the parse tree
	 */
	void exitOption(StylesheetParser.OptionContext ctx);
}