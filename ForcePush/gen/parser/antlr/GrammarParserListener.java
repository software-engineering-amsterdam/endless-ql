// Generated from C:/Users/georg/Documents/GitHub/endless-ql/ForcePush/src/parser/antlr\GrammarParser.g4 by ANTLR 4.7
package parser.antlr;
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
}