// Generated from /home/jouker/Downloads/endless-ql/JS/qlparser/src/main/java/nl/uva/js/qlparser/antlr/QL.g4 by ANTLR 4.7
package nl.uva.js.qlparser.antlr;
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
	 * Visit a parse tree produced by {@link QLParser#datatype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDatatype(QLParser.DatatypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#values}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValues(QLParser.ValuesContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#boolOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolOp(QLParser.BoolOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#compOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompOp(QLParser.CompOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#arithOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithOp(QLParser.ArithOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#form}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForm(QLParser.FormContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#formBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormBlock(QLParser.FormBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#formExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormExpression(QLParser.FormExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(QLParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion(QLParser.QuestionContext ctx);
}