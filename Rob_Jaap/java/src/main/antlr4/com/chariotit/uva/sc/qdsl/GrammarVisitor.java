// Generated from Grammar.g4 by ANTLR 4.7
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link GrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface GrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link GrammarParser#forms}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForms(GrammarParser.FormsContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#form}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForm(GrammarParser.FormContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#label}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabel(GrammarParser.LabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#elem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElem(GrammarParser.ElemContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#line_elem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLine_elem(GrammarParser.Line_elemContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#block_elem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock_elem(GrammarParser.Block_elemContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#if_block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_block(GrammarParser.If_blockContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(GrammarParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion(GrammarParser.QuestionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(GrammarParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(GrammarParser.ConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#binop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinop(GrammarParser.BinopContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#unop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnop(GrammarParser.UnopContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#type_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_expr(GrammarParser.Type_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(GrammarParser.TypeContext ctx);
}