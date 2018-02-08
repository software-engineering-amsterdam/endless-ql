// Generated from /home/jouker/Downloads/endless-ql/JS/qlparser/src/main/java/nl/uva/js/qlparser/antlr/QL.g4 by ANTLR 4.7
package nl.uva.js.qlparser.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link QLParser}.
 */
public interface QLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link QLParser#datatype}.
	 * @param ctx the parse tree
	 */
	void enterDatatype(QLParser.DatatypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#datatype}.
	 * @param ctx the parse tree
	 */
	void exitDatatype(QLParser.DatatypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#values}.
	 * @param ctx the parse tree
	 */
	void enterValues(QLParser.ValuesContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#values}.
	 * @param ctx the parse tree
	 */
	void exitValues(QLParser.ValuesContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#boolOp}.
	 * @param ctx the parse tree
	 */
	void enterBoolOp(QLParser.BoolOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#boolOp}.
	 * @param ctx the parse tree
	 */
	void exitBoolOp(QLParser.BoolOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#compOp}.
	 * @param ctx the parse tree
	 */
	void enterCompOp(QLParser.CompOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#compOp}.
	 * @param ctx the parse tree
	 */
	void exitCompOp(QLParser.CompOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#arithOp}.
	 * @param ctx the parse tree
	 */
	void enterArithOp(QLParser.ArithOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#arithOp}.
	 * @param ctx the parse tree
	 */
	void exitArithOp(QLParser.ArithOpContext ctx);
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
	 * Enter a parse tree produced by {@link QLParser#formBlock}.
	 * @param ctx the parse tree
	 */
	void enterFormBlock(QLParser.FormBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#formBlock}.
	 * @param ctx the parse tree
	 */
	void exitFormBlock(QLParser.FormBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#formExpression}.
	 * @param ctx the parse tree
	 */
	void enterFormExpression(QLParser.FormExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#formExpression}.
	 * @param ctx the parse tree
	 */
	void exitFormExpression(QLParser.FormExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(QLParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(QLParser.ExpressionContext ctx);
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
}