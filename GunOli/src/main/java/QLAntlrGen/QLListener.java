// Generated from C:/Users/Michael/Desktop/Software Construction/endless-ql/GunOli/src/main/antlr\QL.g4 by ANTLR 4.7
package QLAntlrGen;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link QLParser}.
 */
public interface QLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link QLParser#head}.
	 * @param ctx the parse tree
	 */
	void enterHead(QLParser.HeadContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#head}.
	 * @param ctx the parse tree
	 */
	void exitHead(QLParser.HeadContext ctx);
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
	 * Enter a parse tree produced by {@link QLParser#questionType}.
	 * @param ctx the parse tree
	 */
	void enterQuestionType(QLParser.QuestionTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#questionType}.
	 * @param ctx the parse tree
	 */
	void exitQuestionType(QLParser.QuestionTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpr(QLParser.UnaryExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpr(QLParser.UnaryExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nestedExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNestedExpr(QLParser.NestedExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nestedExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNestedExpr(QLParser.NestedExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binaryExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBinaryExpr(QLParser.BinaryExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binaryExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBinaryExpr(QLParser.BinaryExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code constantExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterConstantExpr(QLParser.ConstantExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code constantExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitConstantExpr(QLParser.ConstantExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#operator}.
	 * @param ctx the parse tree
	 */
	void enterOperator(QLParser.OperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#operator}.
	 * @param ctx the parse tree
	 */
	void exitOperator(QLParser.OperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#unaryOp}.
	 * @param ctx the parse tree
	 */
	void enterUnaryOp(QLParser.UnaryOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#unaryOp}.
	 * @param ctx the parse tree
	 */
	void exitUnaryOp(QLParser.UnaryOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#binaryOp}.
	 * @param ctx the parse tree
	 */
	void enterBinaryOp(QLParser.BinaryOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#binaryOp}.
	 * @param ctx the parse tree
	 */
	void exitBinaryOp(QLParser.BinaryOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code integerConstant}
	 * labeled alternative in {@link QLParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterIntegerConstant(QLParser.IntegerConstantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code integerConstant}
	 * labeled alternative in {@link QLParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitIntegerConstant(QLParser.IntegerConstantContext ctx);
	/**
	 * Enter a parse tree produced by the {@code decimalConstant}
	 * labeled alternative in {@link QLParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterDecimalConstant(QLParser.DecimalConstantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code decimalConstant}
	 * labeled alternative in {@link QLParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitDecimalConstant(QLParser.DecimalConstantContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringConstant}
	 * labeled alternative in {@link QLParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterStringConstant(QLParser.StringConstantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringConstant}
	 * labeled alternative in {@link QLParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitStringConstant(QLParser.StringConstantContext ctx);
	/**
	 * Enter a parse tree produced by the {@code identifierConstant}
	 * labeled alternative in {@link QLParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierConstant(QLParser.IdentifierConstantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code identifierConstant}
	 * labeled alternative in {@link QLParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierConstant(QLParser.IdentifierConstantContext ctx);
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