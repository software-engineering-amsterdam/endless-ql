// Generated from .\trialfolderparseresult\QL_testprototype.g4 by ANTLR 4.7.1

	package antlr.grammar.trialfolderparseresult;
	import antlr.grammar.*;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link QL_testprototypeParser}.
 */
public interface QL_testprototypeListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link QL_testprototypeParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(QL_testprototypeParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link QL_testprototypeParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(QL_testprototypeParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link QL_testprototypeParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(QL_testprototypeParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link QL_testprototypeParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(QL_testprototypeParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link QL_testprototypeParser#questionType}.
	 * @param ctx the parse tree
	 */
	void enterQuestionType(QL_testprototypeParser.QuestionTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link QL_testprototypeParser#questionType}.
	 * @param ctx the parse tree
	 */
	void exitQuestionType(QL_testprototypeParser.QuestionTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link QL_testprototypeParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(QL_testprototypeParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link QL_testprototypeParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(QL_testprototypeParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link QL_testprototypeParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(QL_testprototypeParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link QL_testprototypeParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(QL_testprototypeParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link QL_testprototypeParser#question}.
	 * @param ctx the parse tree
	 */
	void enterQuestion(QL_testprototypeParser.QuestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QL_testprototypeParser#question}.
	 * @param ctx the parse tree
	 */
	void exitQuestion(QL_testprototypeParser.QuestionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QL_testprototypeParser#ifElseStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfElseStatement(QL_testprototypeParser.IfElseStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link QL_testprototypeParser#ifElseStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfElseStatement(QL_testprototypeParser.IfElseStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link QL_testprototypeParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(QL_testprototypeParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link QL_testprototypeParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(QL_testprototypeParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link QL_testprototypeParser#form}.
	 * @param ctx the parse tree
	 */
	void enterForm(QL_testprototypeParser.FormContext ctx);
	/**
	 * Exit a parse tree produced by {@link QL_testprototypeParser#form}.
	 * @param ctx the parse tree
	 */
	void exitForm(QL_testprototypeParser.FormContext ctx);
}