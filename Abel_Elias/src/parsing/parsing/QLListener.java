// Generated from C:/dev/uva/endless-ql/Abel_Elias/src/parsing\QL.g4 by ANTLR 4.7
package parsing;
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
	 * Enter a parse tree produced by {@link QLParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(QLParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(QLParser.BlockContext ctx);
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
	 * Enter a parse tree produced by {@link QLParser#operator}.
	 * @param ctx the parse tree
	 */
	void enterOperator(QLParser.OperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#operator}.
	 * @param ctx the parse tree
	 */
	void exitOperator(QLParser.OperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#boolOperator}.
	 * @param ctx the parse tree
	 */
	void enterBoolOperator(QLParser.BoolOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#boolOperator}.
	 * @param ctx the parse tree
	 */
	void exitBoolOperator(QLParser.BoolOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void enterComparisonOperator(QLParser.ComparisonOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void exitComparisonOperator(QLParser.ComparisonOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#numberOperator}.
	 * @param ctx the parse tree
	 */
	void enterNumberOperator(QLParser.NumberOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#numberOperator}.
	 * @param ctx the parse tree
	 */
	void exitNumberOperator(QLParser.NumberOperatorContext ctx);
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
	 * Enter a parse tree produced by {@link QLParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(QLParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(QLParser.TypeContext ctx);
}