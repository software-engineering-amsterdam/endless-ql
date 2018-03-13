// Generated from C:/Users/Michael/Desktop/Software Construction/endless-ql/GunOli/src/main/antlr\QLS.g4 by ANTLR 4.7
package QLSAntlrGen;
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
	 * Enter a parse tree produced by the {@code widthWidget}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 */
	void enterWidthWidget(QLSParser.WidthWidgetContext ctx);
	/**
	 * Exit a parse tree produced by the {@code widthWidget}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 */
	void exitWidthWidget(QLSParser.WidthWidgetContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fontWidget}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 */
	void enterFontWidget(QLSParser.FontWidgetContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fontWidget}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 */
	void exitFontWidget(QLSParser.FontWidgetContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fontSizeWidget}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 */
	void enterFontSizeWidget(QLSParser.FontSizeWidgetContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fontSizeWidget}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 */
	void exitFontSizeWidget(QLSParser.FontSizeWidgetContext ctx);
	/**
	 * Enter a parse tree produced by the {@code colorWidget}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 */
	void enterColorWidget(QLSParser.ColorWidgetContext ctx);
	/**
	 * Exit a parse tree produced by the {@code colorWidget}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 */
	void exitColorWidget(QLSParser.ColorWidgetContext ctx);
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