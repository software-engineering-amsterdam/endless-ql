// Generated from C:/Users/georg/Documents/GitHub/endless-ql/ForcePush/src/main/resources/antlr\GrammarParser.g4 by ANTLR 4.7
package org.uva.forcepushql.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GrammarParser}.
 */
public interface GrammarParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GrammarParser#compileUnit}.
	 * @param ctx the parse tree
	 */
	void enterCompileUnit(GrammarParser.CompileUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#compileUnit}.
	 * @param ctx the parse tree
	 */
	void exitCompileUnit(GrammarParser.CompileUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#mathUnit}.
	 * @param ctx the parse tree
	 */
	void enterMathUnit(GrammarParser.MathUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#mathUnit}.
	 * @param ctx the parse tree
	 */
	void exitMathUnit(GrammarParser.MathUnitContext ctx);
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
	 * Enter a parse tree produced by {@link GrammarParser#questionTypes}.
	 * @param ctx the parse tree
	 */
	void enterQuestionTypes(GrammarParser.QuestionTypesContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#questionTypes}.
	 * @param ctx the parse tree
	 */
	void exitQuestionTypes(GrammarParser.QuestionTypesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code infixExpression}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterInfixExpression(GrammarParser.InfixExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code infixExpression}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitInfixExpression(GrammarParser.InfixExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code numberExpression}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNumberExpression(GrammarParser.NumberExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code numberExpression}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNumberExpression(GrammarParser.NumberExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenthesisExpression}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParenthesisExpression(GrammarParser.ParenthesisExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenthesisExpression}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParenthesisExpression(GrammarParser.ParenthesisExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code comparisonExpression}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterComparisonExpression(GrammarParser.ComparisonExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code comparisonExpression}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitComparisonExpression(GrammarParser.ComparisonExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryExpression}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpression(GrammarParser.UnaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryExpression}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpression(GrammarParser.UnaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicalExpression}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalExpression(GrammarParser.LogicalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicalExpression}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalExpression(GrammarParser.LogicalExpressionContext ctx);
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
	 * Enter a parse tree produced by {@link GrammarParser#questionAssignValue}.
	 * @param ctx the parse tree
	 */
	void enterQuestionAssignValue(GrammarParser.QuestionAssignValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#questionAssignValue}.
	 * @param ctx the parse tree
	 */
	void exitQuestionAssignValue(GrammarParser.QuestionAssignValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#conditionalIf}.
	 * @param ctx the parse tree
	 */
	void enterConditionalIf(GrammarParser.ConditionalIfContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#conditionalIf}.
	 * @param ctx the parse tree
	 */
	void exitConditionalIf(GrammarParser.ConditionalIfContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#conditionalIfElse}.
	 * @param ctx the parse tree
	 */
	void enterConditionalIfElse(GrammarParser.ConditionalIfElseContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#conditionalIfElse}.
	 * @param ctx the parse tree
	 */
	void exitConditionalIfElse(GrammarParser.ConditionalIfElseContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#conditionalElse}.
	 * @param ctx the parse tree
	 */
	void enterConditionalElse(GrammarParser.ConditionalElseContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#conditionalElse}.
	 * @param ctx the parse tree
	 */
	void exitConditionalElse(GrammarParser.ConditionalElseContext ctx);
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