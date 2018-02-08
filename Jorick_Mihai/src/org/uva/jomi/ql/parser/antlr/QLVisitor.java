// Generated from QL.g4 by ANTLR 4.7.1

	package org.uva.jomi.ql.parser.antlr;

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
	 * Visit a parse tree produced by {@link QLParser#parse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParse(QLParser.ParseContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#formStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormStmt(QLParser.FormStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#blockStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStmt(QLParser.BlockStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#blockStatements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStatements(QLParser.BlockStatementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#questionStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionStmt(QLParser.QuestionStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#ifStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStmt(QLParser.IfStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IdentifierExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierExpr(QLParser.IdentifierExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code GroupingExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGroupingExpr(QLParser.GroupingExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BinaryExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryExpr(QLParser.BinaryExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrimaryExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExpr(QLParser.PrimaryExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UnaryExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpr(QLParser.UnaryExprContext ctx);
}