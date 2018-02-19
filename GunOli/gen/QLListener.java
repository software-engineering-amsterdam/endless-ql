// Generated from C:/Users/Michael/Desktop/Software Construction/endless-ql/GunOli/src/main/antlr\QL.g4 by ANTLR 4.7
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link QLParser}.
 */
public interface QLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link QLParser#root}.
	 * @param ctx the parse tree
	 */
	void enterRoot(QLParser.RootContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#root}.
	 * @param ctx the parse tree
	 */
	void exitRoot(QLParser.RootContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(QLParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(QLParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(QLParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(QLParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(QLParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(QLParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#question}.
	 * @param ctx the parse tree
	 */
	void enterQuestion(QLParser.QuestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#question}.
	 * @param ctx the parse tree
	 */
	void exitQuestion(QLParser.QuestionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNotExpr(QLParser.NotExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNotExpr(QLParser.NotExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code opExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterOpExpr(QLParser.OpExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code opExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitOpExpr(QLParser.OpExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code negExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNegExpr(QLParser.NegExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code negExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNegExpr(QLParser.NegExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code compExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterCompExpr(QLParser.CompExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code compExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitCompExpr(QLParser.CompExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code andOrExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAndOrExpr(QLParser.AndOrExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code andOrExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAndOrExpr(QLParser.AndOrExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBoolExpr(QLParser.BoolExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBoolExpr(QLParser.BoolExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParenExpr(QLParser.ParenExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParenExpr(QLParser.ParenExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code constExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterConstExpr(QLParser.ConstExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code constExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitConstExpr(QLParser.ConstExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intConstant}
	 * labeled alternative in {@link QLParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterIntConstant(QLParser.IntConstantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intConstant}
	 * labeled alternative in {@link QLParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitIntConstant(QLParser.IntConstantContext ctx);
	/**
	 * Enter a parse tree produced by the {@code floatConstant}
	 * labeled alternative in {@link QLParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterFloatConstant(QLParser.FloatConstantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code floatConstant}
	 * labeled alternative in {@link QLParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitFloatConstant(QLParser.FloatConstantContext ctx);
	/**
	 * Enter a parse tree produced by the {@code strConstant}
	 * labeled alternative in {@link QLParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterStrConstant(QLParser.StrConstantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code strConstant}
	 * labeled alternative in {@link QLParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitStrConstant(QLParser.StrConstantContext ctx);
	/**
	 * Enter a parse tree produced by the {@code idConstant}
	 * labeled alternative in {@link QLParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterIdConstant(QLParser.IdConstantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code idConstant}
	 * labeled alternative in {@link QLParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitIdConstant(QLParser.IdConstantContext ctx);
	/**
	 * Enter a parse tree produced by the {@code moneyConstant}
	 * labeled alternative in {@link QLParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterMoneyConstant(QLParser.MoneyConstantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code moneyConstant}
	 * labeled alternative in {@link QLParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitMoneyConstant(QLParser.MoneyConstantContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dateConstant}
	 * labeled alternative in {@link QLParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterDateConstant(QLParser.DateConstantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dateConstant}
	 * labeled alternative in {@link QLParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitDateConstant(QLParser.DateConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(QLParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(QLParser.TypeContext ctx);
}