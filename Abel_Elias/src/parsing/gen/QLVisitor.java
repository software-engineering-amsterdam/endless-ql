// Generated from C:/dev/uva/endless-ql/Abel_Elias/src/parsing\QL.g4 by ANTLR 4.7
package parsing.gen;
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
	 * Visit a parse tree produced by {@link QLParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(QLParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion(QLParser.QuestionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(QLParser.StatementContext ctx);
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
	 * Visit a parse tree produced by {@link QLParser#boolOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolOperator(QLParser.BoolOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#comparisonOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonOperator(QLParser.ComparisonOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#numberOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberOperator(QLParser.NumberOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(QLParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(QLParser.TypeContext ctx);
}