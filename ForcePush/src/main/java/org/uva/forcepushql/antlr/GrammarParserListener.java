// Generated from C:/Users/Joana Magalhães/Documents/GitHub/endless-ql/ForcePush/src/main/resources/antlr\GrammarParser.g4 by ANTLR 4.7
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
	 * Enter a parse tree produced by {@link GrammarParser#logical}.
	 * @param ctx the parse tree
	 */
	void enterLogical(GrammarParser.LogicalContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#logical}.
	 * @param ctx the parse tree
	 */
	void exitLogical(GrammarParser.LogicalContext ctx);
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
	 * Enter a parse tree produced by the {@code binaryExpression}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBinaryExpression(GrammarParser.BinaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binaryExpression}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBinaryExpression(GrammarParser.BinaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code valueExpression}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterValueExpression(GrammarParser.ValueExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code valueExpression}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitValueExpression(GrammarParser.ValueExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parensExpression}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParensExpression(GrammarParser.ParensExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parensExpression}
	 * labeled alternative in {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParensExpression(GrammarParser.ParensExpressionContext ctx);
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
	 * Enter a parse tree produced by {@link GrammarParser#questionMultiAns}.
	 * @param ctx the parse tree
	 */
	void enterQuestionMultiAns(GrammarParser.QuestionMultiAnsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#questionMultiAns}.
	 * @param ctx the parse tree
	 */
	void exitQuestionMultiAns(GrammarParser.QuestionMultiAnsContext ctx);
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