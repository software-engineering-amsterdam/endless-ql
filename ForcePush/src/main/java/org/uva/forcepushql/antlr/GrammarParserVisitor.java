// Generated from C:/Users/georg/Documents/GitHub/endless-ql/ForcePush/src/main/resources/antlr\GrammarParser.g4 by ANTLR 4.7
package org.uva.forcepushql.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link GrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface GrammarParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link GrammarParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(GrammarParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#decisions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecisions(GrammarParser.DecisionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperator(GrammarParser.OperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(GrammarParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#arithmetic}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmetic(GrammarParser.ArithmeticContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#comparison}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparison(GrammarParser.ComparisonContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(GrammarParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#questionFormat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionFormat(GrammarParser.QuestionFormatContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#conditional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditional(GrammarParser.ConditionalContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#formStructure}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormStructure(GrammarParser.FormStructureContext ctx);
}