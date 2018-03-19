// Generated from /home/ajm/Desktop/newEndless/endless-ql/Abel_Elias/src/QLS/parsing/QLS.g4 by ANTLR 4.7
package QLS.parsing;
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
	 * Enter a parse tree produced by the {@code sliderwidget}
	 * labeled alternative in {@link QLSParser#widgetType}.
	 * @param ctx the parse tree
	 */
	void enterSliderwidget(QLSParser.SliderwidgetContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sliderwidget}
	 * labeled alternative in {@link QLSParser#widgetType}.
	 * @param ctx the parse tree
	 */
	void exitSliderwidget(QLSParser.SliderwidgetContext ctx);
	/**
	 * Enter a parse tree produced by the {@code spinboxwidget}
	 * labeled alternative in {@link QLSParser#widgetType}.
	 * @param ctx the parse tree
	 */
	void enterSpinboxwidget(QLSParser.SpinboxwidgetContext ctx);
	/**
	 * Exit a parse tree produced by the {@code spinboxwidget}
	 * labeled alternative in {@link QLSParser#widgetType}.
	 * @param ctx the parse tree
	 */
	void exitSpinboxwidget(QLSParser.SpinboxwidgetContext ctx);
	/**
	 * Enter a parse tree produced by the {@code checkboxwidget}
	 * labeled alternative in {@link QLSParser#widgetType}.
	 * @param ctx the parse tree
	 */
	void enterCheckboxwidget(QLSParser.CheckboxwidgetContext ctx);
	/**
	 * Exit a parse tree produced by the {@code checkboxwidget}
	 * labeled alternative in {@link QLSParser#widgetType}.
	 * @param ctx the parse tree
	 */
	void exitCheckboxwidget(QLSParser.CheckboxwidgetContext ctx);
	/**
	 * Enter a parse tree produced by the {@code textwidget}
	 * labeled alternative in {@link QLSParser#widgetType}.
	 * @param ctx the parse tree
	 */
	void enterTextwidget(QLSParser.TextwidgetContext ctx);
	/**
	 * Exit a parse tree produced by the {@code textwidget}
	 * labeled alternative in {@link QLSParser#widgetType}.
	 * @param ctx the parse tree
	 */
	void exitTextwidget(QLSParser.TextwidgetContext ctx);
	/**
	 * Enter a parse tree produced by the {@code radiowidget}
	 * labeled alternative in {@link QLSParser#widgetType}.
	 * @param ctx the parse tree
	 */
	void enterRadiowidget(QLSParser.RadiowidgetContext ctx);
	/**
	 * Exit a parse tree produced by the {@code radiowidget}
	 * labeled alternative in {@link QLSParser#widgetType}.
	 * @param ctx the parse tree
	 */
	void exitRadiowidget(QLSParser.RadiowidgetContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dropdownwidget}
	 * labeled alternative in {@link QLSParser#widgetType}.
	 * @param ctx the parse tree
	 */
	void enterDropdownwidget(QLSParser.DropdownwidgetContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dropdownwidget}
	 * labeled alternative in {@link QLSParser#widgetType}.
	 * @param ctx the parse tree
	 */
	void exitDropdownwidget(QLSParser.DropdownwidgetContext ctx);
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