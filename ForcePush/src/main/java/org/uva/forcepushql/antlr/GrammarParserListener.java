// Generated from C:/Users/georg/Documents/GitHub/endless-ql/ForcePush/src/main/resources/antlr\GrammarParser.g4 by ANTLR 4.7
package org.uva.forcepushql.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GrammarParser}.
 */
public interface GrammarParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GrammarParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(GrammarParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(GrammarParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#decisions}.
	 * @param ctx the parse tree
	 */
	void enterDecisions(GrammarParser.DecisionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#decisions}.
	 * @param ctx the parse tree
	 */
	void exitDecisions(GrammarParser.DecisionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#operator}.
	 * @param ctx the parse tree
	 */
	void enterOperator(GrammarParser.OperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#operator}.
	 * @param ctx the parse tree
	 */
	void exitOperator(GrammarParser.OperatorContext ctx);
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
	/**
	 * Enter a parse tree produced by {@link GrammarParser#arithmetic}.
	 * @param ctx the parse tree
	 */
	void enterArithmetic(GrammarParser.ArithmeticContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#arithmetic}.
	 * @param ctx the parse tree
	 */
	void exitArithmetic(GrammarParser.ArithmeticContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#comparison}.
	 * @param ctx the parse tree
	 */
	void enterComparison(GrammarParser.ComparisonContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#comparison}.
	 * @param ctx the parse tree
	 */
	void exitComparison(GrammarParser.ComparisonContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(GrammarParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(GrammarParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#questionFormat}.
	 * @param ctx the parse tree
	 */
	void enterQuestionFormat(GrammarParser.QuestionFormatContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#questionFormat}.
	 * @param ctx the parse tree
	 */
	void exitQuestionFormat(GrammarParser.QuestionFormatContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#conditional}.
	 * @param ctx the parse tree
	 */
	void enterConditional(GrammarParser.ConditionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#conditional}.
	 * @param ctx the parse tree
	 */
	void exitConditional(GrammarParser.ConditionalContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#formStructure}.
	 * @param ctx the parse tree
	 */
	void enterFormStructure(GrammarParser.FormStructureContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#formStructure}.
	 * @param ctx the parse tree
	 */
	void exitFormStructure(GrammarParser.FormStructureContext ctx);
}