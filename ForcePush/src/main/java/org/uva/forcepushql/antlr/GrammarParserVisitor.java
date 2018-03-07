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
	 * Visit a parse tree produced by {@link GrammarParser#compileUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompileUnit(GrammarParser.CompileUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#mathUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMathUnit(GrammarParser.MathUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(GrammarParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(GrammarParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#questionTypes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionTypes(GrammarParser.QuestionTypesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code infixExpression}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInfixExpression(GrammarParser.InfixExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code numberExpression}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberExpression(GrammarParser.NumberExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenthesisExpression}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesisExpression(GrammarParser.ParenthesisExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code comparisonExpression}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonExpression(GrammarParser.ComparisonExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unaryExpression}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpression(GrammarParser.UnaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code logicalExpression}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalExpression(GrammarParser.LogicalExpressionContext ctx);
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