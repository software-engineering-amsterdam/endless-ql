// Generated from /Users/molivari/temp/endless-ql/GunOli/src/main/antlr/QLS.g4 by ANTLR 4.7
package QLS.QLSAntlrGen;
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
	 * Visit a parse tree produced by {@link QLSParser#head}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHead(QLSParser.HeadContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(QLSParser.BlockContext ctx);
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
	 * Visit a parse tree produced by {@link QLSParser#question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion(QLSParser.QuestionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#defaultSec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefaultSec(QLSParser.DefaultSecContext ctx);
	/**
	 * Visit a parse tree produced by the {@code radioWidget}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRadioWidget(QLSParser.RadioWidgetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code checkWidget}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCheckWidget(QLSParser.CheckWidgetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code spinWidget}
	 * labeled alternative in {@link QLSParser#widget}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpinWidget(QLSParser.SpinWidgetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code widthStyle}
	 * labeled alternative in {@link QLSParser#style}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWidthStyle(QLSParser.WidthStyleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fontStyle}
	 * labeled alternative in {@link QLSParser#style}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFontStyle(QLSParser.FontStyleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fontSizeStyle}
	 * labeled alternative in {@link QLSParser#style}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFontSizeStyle(QLSParser.FontSizeStyleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code colorStyle}
	 * labeled alternative in {@link QLSParser#style}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColorStyle(QLSParser.ColorStyleContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(QLSParser.TypeContext ctx);
}