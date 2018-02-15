// Generated from C:/Users/georg/Documents/GitHub/endless-ql/ForcePush/src/parser/antlr\Grammar.g4 by ANTLR 4.7
package parser.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link GrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface GrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link GrammarParser#a}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitA(GrammarParser.AContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#b}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitB(GrammarParser.BContext ctx);
}