// Generated from /home/thijs/Documents/uva/sc/endless-ql/Andras_Thijs/src/AST/QL.g4 by ANTLR 4.7
package AST.gen;
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
	 * Visit a parse tree produced by {@link QLParser#question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion(QLParser.QuestionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(QLParser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(QLParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperator(QLParser.OperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#booloperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooloperator(QLParser.BooloperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#equaloperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualoperator(QLParser.EqualoperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#comparision}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparision(QLParser.ComparisionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#addsub}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddsub(QLParser.AddsubContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#muldiv}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMuldiv(QLParser.MuldivContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(QLParser.FactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(QLParser.TermContext ctx);
}