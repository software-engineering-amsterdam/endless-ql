// Generated from C:/Users/Joana Magalhães/Documents/GitHub/endless-ql/ForcePush/src/main/resources/antlr\GrammarParser.g4 by ANTLR 4.7
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
	 * Visit a parse tree produced by {@link GrammarParser#logical}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogical(GrammarParser.LogicalContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#arithmetic}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmetic(GrammarParser.ArithmeticContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(GrammarParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#comparison}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparison(GrammarParser.ComparisonContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#questionTypes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionTypes(GrammarParser.QuestionTypesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binaryExpression}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryExpression(GrammarParser.BinaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code valueExpression}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValueExpression(GrammarParser.ValueExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parensExpression}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParensExpression(GrammarParser.ParensExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#questionFormat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionFormat(GrammarParser.QuestionFormatContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#questionAssignValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionAssignValue(GrammarParser.QuestionAssignValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#conditionalIf}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionalIf(GrammarParser.ConditionalIfContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#conditionalIfElse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionalIfElse(GrammarParser.ConditionalIfElseContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#conditionalElse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionalElse(GrammarParser.ConditionalElseContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#questionMultiAns}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionMultiAns(GrammarParser.QuestionMultiAnsContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#formStructure}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormStructure(GrammarParser.FormStructureContext ctx);
}