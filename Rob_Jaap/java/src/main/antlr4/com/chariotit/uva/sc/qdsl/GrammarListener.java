// Generated from Grammar.g4 by ANTLR 4.7
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GrammarParser}.
 */
public interface GrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GrammarParser#forms}.
	 * @param ctx the parse tree
	 */
	void enterForms(GrammarParser.FormsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#forms}.
	 * @param ctx the parse tree
	 */
	void exitForms(GrammarParser.FormsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#form}.
	 * @param ctx the parse tree
	 */
	void enterForm(GrammarParser.FormContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#form}.
	 * @param ctx the parse tree
	 */
	void exitForm(GrammarParser.FormContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#label}.
	 * @param ctx the parse tree
	 */
	void enterLabel(GrammarParser.LabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#label}.
	 * @param ctx the parse tree
	 */
	void exitLabel(GrammarParser.LabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#elem}.
	 * @param ctx the parse tree
	 */
	void enterElem(GrammarParser.ElemContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#elem}.
	 * @param ctx the parse tree
	 */
	void exitElem(GrammarParser.ElemContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#line_elem}.
	 * @param ctx the parse tree
	 */
	void enterLine_elem(GrammarParser.Line_elemContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#line_elem}.
	 * @param ctx the parse tree
	 */
	void exitLine_elem(GrammarParser.Line_elemContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#block_elem}.
	 * @param ctx the parse tree
	 */
	void enterBlock_elem(GrammarParser.Block_elemContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#block_elem}.
	 * @param ctx the parse tree
	 */
	void exitBlock_elem(GrammarParser.Block_elemContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#if_block}.
	 * @param ctx the parse tree
	 */
	void enterIf_block(GrammarParser.If_blockContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#if_block}.
	 * @param ctx the parse tree
	 */
	void exitIf_block(GrammarParser.If_blockContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(GrammarParser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(GrammarParser.StmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#question}.
	 * @param ctx the parse tree
	 */
	void enterQuestion(GrammarParser.QuestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#question}.
	 * @param ctx the parse tree
	 */
	void exitQuestion(GrammarParser.QuestionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(GrammarParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(GrammarParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(GrammarParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(GrammarParser.ConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#binop}.
	 * @param ctx the parse tree
	 */
	void enterBinop(GrammarParser.BinopContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#binop}.
	 * @param ctx the parse tree
	 */
	void exitBinop(GrammarParser.BinopContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#unop}.
	 * @param ctx the parse tree
	 */
	void enterUnop(GrammarParser.UnopContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#unop}.
	 * @param ctx the parse tree
	 */
	void exitUnop(GrammarParser.UnopContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#type_expr}.
	 * @param ctx the parse tree
	 */
	void enterType_expr(GrammarParser.Type_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#type_expr}.
	 * @param ctx the parse tree
	 */
	void exitType_expr(GrammarParser.Type_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(GrammarParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(GrammarParser.TypeContext ctx);
}