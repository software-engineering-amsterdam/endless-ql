// Generated from /Users/piotrkosytorz/Library/Mobile Documents/com~apple~CloudDocs/Study/SC/endless-ql/piotr/org.uva.sc.piotr.ql/src/grammar/QL.g4 by ANTLR 4.7
package grammar;
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
	 * Enter a parse tree produced by the {@code TypeDeclarationBoolean}
	 * labeled alternative in {@link QLParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterTypeDeclarationBoolean(QLParser.TypeDeclarationBooleanContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TypeDeclarationBoolean}
	 * labeled alternative in {@link QLParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitTypeDeclarationBoolean(QLParser.TypeDeclarationBooleanContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TypeDeclarationString}
	 * labeled alternative in {@link QLParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterTypeDeclarationString(QLParser.TypeDeclarationStringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TypeDeclarationString}
	 * labeled alternative in {@link QLParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitTypeDeclarationString(QLParser.TypeDeclarationStringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TypeDeclarationInteger}
	 * labeled alternative in {@link QLParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterTypeDeclarationInteger(QLParser.TypeDeclarationIntegerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TypeDeclarationInteger}
	 * labeled alternative in {@link QLParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitTypeDeclarationInteger(QLParser.TypeDeclarationIntegerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TypeDeclarationDecimal}
	 * labeled alternative in {@link QLParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterTypeDeclarationDecimal(QLParser.TypeDeclarationDecimalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TypeDeclarationDecimal}
	 * labeled alternative in {@link QLParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitTypeDeclarationDecimal(QLParser.TypeDeclarationDecimalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TypeDeclarationMoney}
	 * labeled alternative in {@link QLParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterTypeDeclarationMoney(QLParser.TypeDeclarationMoneyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TypeDeclarationMoney}
	 * labeled alternative in {@link QLParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitTypeDeclarationMoney(QLParser.TypeDeclarationMoneyContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(QLParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(QLParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#elseStatement}.
	 * @param ctx the parse tree
	 */
	void enterElseStatement(QLParser.ElseStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#elseStatement}.
	 * @param ctx the parse tree
	 */
	void exitElseStatement(QLParser.ElseStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpressionParenthesises}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionParenthesises(QLParser.ExpressionParenthesisesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpressionParenthesises}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionParenthesises(QLParser.ExpressionParenthesisesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpressionComparisionLessThan}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionComparisionLessThan(QLParser.ExpressionComparisionLessThanContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpressionComparisionLessThan}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionComparisionLessThan(QLParser.ExpressionComparisionLessThanContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpressionComparisionNotEqual}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionComparisionNotEqual(QLParser.ExpressionComparisionNotEqualContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpressionComparisionNotEqual}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionComparisionNotEqual(QLParser.ExpressionComparisionNotEqualContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpressionComparisionEqual}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionComparisionEqual(QLParser.ExpressionComparisionEqualContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpressionComparisionEqual}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionComparisionEqual(QLParser.ExpressionComparisionEqualContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpressionSingleValue}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionSingleValue(QLParser.ExpressionSingleValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpressionSingleValue}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionSingleValue(QLParser.ExpressionSingleValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpressionArithmeticMultiplication}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionArithmeticMultiplication(QLParser.ExpressionArithmeticMultiplicationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpressionArithmeticMultiplication}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionArithmeticMultiplication(QLParser.ExpressionArithmeticMultiplicationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpressionNegation}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionNegation(QLParser.ExpressionNegationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpressionNegation}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionNegation(QLParser.ExpressionNegationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpressionLogicalOr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionLogicalOr(QLParser.ExpressionLogicalOrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpressionLogicalOr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionLogicalOr(QLParser.ExpressionLogicalOrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpressionComparisionLessEqual}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionComparisionLessEqual(QLParser.ExpressionComparisionLessEqualContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpressionComparisionLessEqual}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionComparisionLessEqual(QLParser.ExpressionComparisionLessEqualContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpressionLogicalAnd}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionLogicalAnd(QLParser.ExpressionLogicalAndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpressionLogicalAnd}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionLogicalAnd(QLParser.ExpressionLogicalAndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpressionArithmeticDivision}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionArithmeticDivision(QLParser.ExpressionArithmeticDivisionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpressionArithmeticDivision}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionArithmeticDivision(QLParser.ExpressionArithmeticDivisionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpressionComparisionGreaterEqual}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionComparisionGreaterEqual(QLParser.ExpressionComparisionGreaterEqualContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpressionComparisionGreaterEqual}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionComparisionGreaterEqual(QLParser.ExpressionComparisionGreaterEqualContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpressionVariableReference}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionVariableReference(QLParser.ExpressionVariableReferenceContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpressionVariableReference}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionVariableReference(QLParser.ExpressionVariableReferenceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpressionArithmeticMinus}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionArithmeticMinus(QLParser.ExpressionArithmeticMinusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpressionArithmeticMinus}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionArithmeticMinus(QLParser.ExpressionArithmeticMinusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpressionComparisionGreaterThan}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionComparisionGreaterThan(QLParser.ExpressionComparisionGreaterThanContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpressionComparisionGreaterThan}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionComparisionGreaterThan(QLParser.ExpressionComparisionGreaterThanContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpressionArithmeticSubtraction}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionArithmeticSubtraction(QLParser.ExpressionArithmeticSubtractionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpressionArithmeticSubtraction}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionArithmeticSubtraction(QLParser.ExpressionArithmeticSubtractionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpressionArithmeticAddition}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionArithmeticAddition(QLParser.ExpressionArithmeticAdditionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpressionArithmeticAddition}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionArithmeticAddition(QLParser.ExpressionArithmeticAdditionContext ctx);
}