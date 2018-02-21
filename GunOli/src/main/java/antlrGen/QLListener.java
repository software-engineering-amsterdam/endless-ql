// Generated from C:/Users/Michael/Desktop/Software Construction/endless-ql/GunOli/src/main/antlr\QL.g4 by ANTLR 4.7
package antlrGen;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link QLParser}.
 */
public interface QLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link QLParser#form}.
	 * @param ctx the parse tree
	 */
	void enterForm(QLParser.FormContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#form}.
	 * @param ctx the parse tree
	 */
	void exitForm(QLParser.FormContext ctx);
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
	 * Enter a parse tree produced by {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(QLParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(QLParser.ExpressionContext ctx);
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
	 * Enter a parse tree produced by {@link QLParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(QLParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(QLParser.ConstantContext ctx);
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