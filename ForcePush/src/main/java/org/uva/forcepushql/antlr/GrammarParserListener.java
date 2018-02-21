// Generated from C:/Users/georg/Documents/GitHub/endless-ql/ForcePush/src/main/resources/antlr\GrammarParser.g4 by ANTLR 4.7
package antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GrammarParser}.
 */
public interface GrammarParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GrammarParser#a}.
	 * @param ctx the parse tree
	 */
	void enterA(GrammarParser.AContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#a}.
	 * @param ctx the parse tree
	 */
	void exitA(GrammarParser.AContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#b}.
	 * @param ctx the parse tree
	 */
	void enterB(GrammarParser.BContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#b}.
	 * @param ctx the parse tree
	 */
	void exitB(GrammarParser.BContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#r}.
	 * @param ctx the parse tree
	 */
	void enterR(GrammarParser.RContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#r}.
	 * @param ctx the parse tree
	 */
	void exitR(GrammarParser.RContext ctx);
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
	 * Enter a parse tree produced by {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(GrammarParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(GrammarParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#question}.
	 * @param ctx the parse tree
	 */
	void enterQuestion(GrammarParser.QuestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#question}.
	 * @param ctx the parse tree
	 */
	void exitQuestion(GrammarParser.QuestionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#conditional}.
	 * @param ctx the parse tree
	 */
	void enterConditional(GrammarParser.ConditionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#conditional}.
	 * @param ctx the parse tree
	 */
	void exitConditional(GrammarParser.ConditionalContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#form}.
	 * @param ctx the parse tree
	 */
	void enterForm(GrammarParser.FormContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#form}.
	 * @param ctx the parse tree
	 */
	void exitForm(GrammarParser.FormContext ctx);
}