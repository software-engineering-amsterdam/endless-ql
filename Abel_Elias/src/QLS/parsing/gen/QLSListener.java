// Generated from /home/ajm/Desktop/newEndless/endless-ql/Abel_Elias/src/QLS/parsing/QLS.g4 by ANTLR 4.7
package QLS.parsing.gen;
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
	 * Enter a parse tree produced by {@link QLSParser#page}.
	 * @param ctx the parse tree
	 */
	void enterPage(QLSParser.PageContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#page}.
	 * @param ctx the parse tree
	 */
	void exitPage(QLSParser.PageContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(QLSParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(QLSParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#lineInBlock}.
	 * @param ctx the parse tree
	 */
	void enterLineInBlock(QLSParser.LineInBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#lineInBlock}.
	 * @param ctx the parse tree
	 */
	void exitLineInBlock(QLSParser.LineInBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#question}.
	 * @param ctx the parse tree
	 */
	void enterQuestion(QLSParser.QuestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#question}.
	 * @param ctx the parse tree
	 */
	void exitQuestion(QLSParser.QuestionContext ctx);
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
	 * Enter a parse tree produced by {@link QLSParser#defaultWidget}.
	 * @param ctx the parse tree
	 */
	void enterDefaultWidget(QLSParser.DefaultWidgetContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#defaultWidget}.
	 * @param ctx the parse tree
	 */
	void exitDefaultWidget(QLSParser.DefaultWidgetContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 */
	void enterWidget(QLSParser.WidgetContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 */
	void exitWidget(QLSParser.WidgetContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#widgetType}.
	 * @param ctx the parse tree
	 */
	void enterWidgetType(QLSParser.WidgetTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#widgetType}.
	 * @param ctx the parse tree
	 */
	void exitWidgetType(QLSParser.WidgetTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#checkboxWidget}.
	 * @param ctx the parse tree
	 */
	void enterCheckboxWidget(QLSParser.CheckboxWidgetContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#checkboxWidget}.
	 * @param ctx the parse tree
	 */
	void exitCheckboxWidget(QLSParser.CheckboxWidgetContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#textWidget}.
	 * @param ctx the parse tree
	 */
	void enterTextWidget(QLSParser.TextWidgetContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#textWidget}.
	 * @param ctx the parse tree
	 */
	void exitTextWidget(QLSParser.TextWidgetContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#radioWidget}.
	 * @param ctx the parse tree
	 */
	void enterRadioWidget(QLSParser.RadioWidgetContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#radioWidget}.
	 * @param ctx the parse tree
	 */
	void exitRadioWidget(QLSParser.RadioWidgetContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#spinboxWidget}.
	 * @param ctx the parse tree
	 */
	void enterSpinboxWidget(QLSParser.SpinboxWidgetContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#spinboxWidget}.
	 * @param ctx the parse tree
	 */
	void exitSpinboxWidget(QLSParser.SpinboxWidgetContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#sliderWidget}.
	 * @param ctx the parse tree
	 */
	void enterSliderWidget(QLSParser.SliderWidgetContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#sliderWidget}.
	 * @param ctx the parse tree
	 */
	void exitSliderWidget(QLSParser.SliderWidgetContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#dropdownWidget}.
	 * @param ctx the parse tree
	 */
	void enterDropdownWidget(QLSParser.DropdownWidgetContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#dropdownWidget}.
	 * @param ctx the parse tree
	 */
	void exitDropdownWidget(QLSParser.DropdownWidgetContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#widgetStyle}.
	 * @param ctx the parse tree
	 */
	void enterWidgetStyle(QLSParser.WidgetStyleContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#widgetStyle}.
	 * @param ctx the parse tree
	 */
	void exitWidgetStyle(QLSParser.WidgetStyleContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#style}.
	 * @param ctx the parse tree
	 */
	void enterStyle(QLSParser.StyleContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#style}.
	 * @param ctx the parse tree
	 */
	void exitStyle(QLSParser.StyleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code booltype}
	 * labeled alternative in {@link QLSParser#type}.
	 * @param ctx the parse tree
	 */
	void enterBooltype(QLSParser.BooltypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code booltype}
	 * labeled alternative in {@link QLSParser#type}.
	 * @param ctx the parse tree
	 */
	void exitBooltype(QLSParser.BooltypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringtype}
	 * labeled alternative in {@link QLSParser#type}.
	 * @param ctx the parse tree
	 */
	void enterStringtype(QLSParser.StringtypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringtype}
	 * labeled alternative in {@link QLSParser#type}.
	 * @param ctx the parse tree
	 */
	void exitStringtype(QLSParser.StringtypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code integertype}
	 * labeled alternative in {@link QLSParser#type}.
	 * @param ctx the parse tree
	 */
	void enterIntegertype(QLSParser.IntegertypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code integertype}
	 * labeled alternative in {@link QLSParser#type}.
	 * @param ctx the parse tree
	 */
	void exitIntegertype(QLSParser.IntegertypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code moneytype}
	 * labeled alternative in {@link QLSParser#type}.
	 * @param ctx the parse tree
	 */
	void enterMoneytype(QLSParser.MoneytypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code moneytype}
	 * labeled alternative in {@link QLSParser#type}.
	 * @param ctx the parse tree
	 */
	void exitMoneytype(QLSParser.MoneytypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code datetype}
	 * labeled alternative in {@link QLSParser#type}.
	 * @param ctx the parse tree
	 */
	void enterDatetype(QLSParser.DatetypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code datetype}
	 * labeled alternative in {@link QLSParser#type}.
	 * @param ctx the parse tree
	 */
	void exitDatetype(QLSParser.DatetypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code decimaltype}
	 * labeled alternative in {@link QLSParser#type}.
	 * @param ctx the parse tree
	 */
	void enterDecimaltype(QLSParser.DecimaltypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code decimaltype}
	 * labeled alternative in {@link QLSParser#type}.
	 * @param ctx the parse tree
	 */
	void exitDecimaltype(QLSParser.DecimaltypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#defaultdef}.
	 * @param ctx the parse tree
	 */
	void enterDefaultdef(QLSParser.DefaultdefContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#defaultdef}.
	 * @param ctx the parse tree
	 */
	void exitDefaultdef(QLSParser.DefaultdefContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#blockdefault}.
	 * @param ctx the parse tree
	 */
	void enterBlockdefault(QLSParser.BlockdefaultContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#blockdefault}.
	 * @param ctx the parse tree
	 */
	void exitBlockdefault(QLSParser.BlockdefaultContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#linedefault}.
	 * @param ctx the parse tree
	 */
	void enterLinedefault(QLSParser.LinedefaultContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#linedefault}.
	 * @param ctx the parse tree
	 */
	void exitLinedefault(QLSParser.LinedefaultContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#widgetProperty}.
	 * @param ctx the parse tree
	 */
	void enterWidgetProperty(QLSParser.WidgetPropertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#widgetProperty}.
	 * @param ctx the parse tree
	 */
	void exitWidgetProperty(QLSParser.WidgetPropertyContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#widthproperty}.
	 * @param ctx the parse tree
	 */
	void enterWidthproperty(QLSParser.WidthpropertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#widthproperty}.
	 * @param ctx the parse tree
	 */
	void exitWidthproperty(QLSParser.WidthpropertyContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#fontproperty}.
	 * @param ctx the parse tree
	 */
	void enterFontproperty(QLSParser.FontpropertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#fontproperty}.
	 * @param ctx the parse tree
	 */
	void exitFontproperty(QLSParser.FontpropertyContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#fontsizeproperty}.
	 * @param ctx the parse tree
	 */
	void enterFontsizeproperty(QLSParser.FontsizepropertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#fontsizeproperty}.
	 * @param ctx the parse tree
	 */
	void exitFontsizeproperty(QLSParser.FontsizepropertyContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#colorproperty}.
	 * @param ctx the parse tree
	 */
	void enterColorproperty(QLSParser.ColorpropertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#colorproperty}.
	 * @param ctx the parse tree
	 */
	void exitColorproperty(QLSParser.ColorpropertyContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(QLSParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(QLSParser.ValueContext ctx);
}