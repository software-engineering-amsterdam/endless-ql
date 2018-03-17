// Generated from /Users/piotrkosytorz/Library/Mobile Documents/com~apple~CloudDocs/Study/SC/endless-ql/piotr/org.uva.sc.piotr.ql/src/grammar/QL.g4 by ANTLR 4.7
package grammar;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link QLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface QLVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link QLParser#form}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForm(QLParser.FormContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(QLParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion(QLParser.QuestionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeDeclarationBoolean}
	 * labeled alternative in {@link QLParser#dataType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDeclarationBoolean(QLParser.TypeDeclarationBooleanContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeDeclarationString}
	 * labeled alternative in {@link QLParser#dataType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDeclarationString(QLParser.TypeDeclarationStringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeDeclarationInteger}
	 * labeled alternative in {@link QLParser#dataType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDeclarationInteger(QLParser.TypeDeclarationIntegerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeDeclarationDecimal}
	 * labeled alternative in {@link QLParser#dataType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDeclarationDecimal(QLParser.TypeDeclarationDecimalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeDeclarationMoney}
	 * labeled alternative in {@link QLParser#dataType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDeclarationMoney(QLParser.TypeDeclarationMoneyContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(QLParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#elseStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseStatement(QLParser.ElseStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpressionParenthesises}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionParenthesises(QLParser.ExpressionParenthesisesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpressionComparisionLessThan}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionComparisionLessThan(QLParser.ExpressionComparisionLessThanContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpressionComparisionNotEqual}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionComparisionNotEqual(QLParser.ExpressionComparisionNotEqualContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpressionComparisionEqual}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionComparisionEqual(QLParser.ExpressionComparisionEqualContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpressionSingleValue}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionSingleValue(QLParser.ExpressionSingleValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpressionArithmeticMultiplication}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionArithmeticMultiplication(QLParser.ExpressionArithmeticMultiplicationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpressionNegation}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionNegation(QLParser.ExpressionNegationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpressionLogicalOr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionLogicalOr(QLParser.ExpressionLogicalOrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpressionComparisionLessEqual}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionComparisionLessEqual(QLParser.ExpressionComparisionLessEqualContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpressionLogicalAnd}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionLogicalAnd(QLParser.ExpressionLogicalAndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpressionArithmeticDivision}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionArithmeticDivision(QLParser.ExpressionArithmeticDivisionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpressionComparisionGreaterEqual}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionComparisionGreaterEqual(QLParser.ExpressionComparisionGreaterEqualContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpressionVariableReference}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionVariableReference(QLParser.ExpressionVariableReferenceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpressionArithmeticMinus}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionArithmeticMinus(QLParser.ExpressionArithmeticMinusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpressionComparisionGreaterThan}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionComparisionGreaterThan(QLParser.ExpressionComparisionGreaterThanContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpressionArithmeticSubtraction}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionArithmeticSubtraction(QLParser.ExpressionArithmeticSubtractionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpressionArithmeticAddition}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionArithmeticAddition(QLParser.ExpressionArithmeticAdditionContext ctx);
}