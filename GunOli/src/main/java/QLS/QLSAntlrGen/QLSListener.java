// Generated from /Users/molivari/temp/endless-ql/GunOli/src/main/antlr/QLS.g4 by ANTLR 4.7
package QLS.QLSAntlrGen;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link QLSParser}.
 */
public interface QLSListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link QLSParser#head}.
	 * @param ctx the parse tree
	 */
	void enterHead(QLSParser.HeadContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#head}.
	 * @param ctx the parse tree
	 */
	void exitHead(QLSParser.HeadContext ctx);
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
	 * Enter a parse tree produced by {@link QLSParser#defaultSec}.
	 * @param ctx the parse tree
	 */
	void enterDefaultSec(QLSParser.DefaultSecContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#defaultSec}.
	 * @param ctx the parse tree
	 */
	void exitDefaultSec(QLSParser.DefaultSecContext ctx);
	/**
	 * Enter a parse tree produced by the {@code radioWidget}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 */
	void enterRadioWidget(QLSParser.RadioWidgetContext ctx);
	/**
	 * Exit a parse tree produced by the {@code radioWidget}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 */
	void exitRadioWidget(QLSParser.RadioWidgetContext ctx);
	/**
	 * Enter a parse tree produced by the {@code checkWidget}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 */
	void enterCheckWidget(QLSParser.CheckWidgetContext ctx);
	/**
	 * Exit a parse tree produced by the {@code checkWidget}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 */
	void exitCheckWidget(QLSParser.CheckWidgetContext ctx);
	/**
	 * Enter a parse tree produced by the {@code spinWidget}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 */
	void enterSpinWidget(QLSParser.SpinWidgetContext ctx);
	/**
	 * Exit a parse tree produced by the {@code spinWidget}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 */
	void exitSpinWidget(QLSParser.SpinWidgetContext ctx);
	/**
	 * Enter a parse tree produced by the {@code widthStyle}
	 * labeled alternative in {@link QLSParser#style}.
	 * @param ctx the parse tree
	 */
	void enterWidthStyle(QLSParser.WidthStyleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code widthStyle}
	 * labeled alternative in {@link QLSParser#style}.
	 * @param ctx the parse tree
	 */
	void exitWidthStyle(QLSParser.WidthStyleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fontStyle}
	 * labeled alternative in {@link QLSParser#style}.
	 * @param ctx the parse tree
	 */
	void enterFontStyle(QLSParser.FontStyleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fontStyle}
	 * labeled alternative in {@link QLSParser#style}.
	 * @param ctx the parse tree
	 */
	void exitFontStyle(QLSParser.FontStyleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fontSizeStyle}
	 * labeled alternative in {@link QLSParser#style}.
	 * @param ctx the parse tree
	 */
	void enterFontSizeStyle(QLSParser.FontSizeStyleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fontSizeStyle}
	 * labeled alternative in {@link QLSParser#style}.
	 * @param ctx the parse tree
	 */
	void exitFontSizeStyle(QLSParser.FontSizeStyleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code colorStyle}
	 * labeled alternative in {@link QLSParser#style}.
	 * @param ctx the parse tree
	 */
	void enterColorStyle(QLSParser.ColorStyleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code colorStyle}
	 * labeled alternative in {@link QLSParser#style}.
	 * @param ctx the parse tree
	 */
	void exitColorStyle(QLSParser.ColorStyleContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLSParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(QLSParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLSParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(QLSParser.TypeContext ctx);
}