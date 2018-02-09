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
	 * Visit a parse tree produced by {@link QLParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommand(QLParser.CommandContext ctx);
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
	 * Visit a parse tree produced by the {@code AndExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpr(QLParser.AndExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IntegerExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerExpr(QLParser.IntegerExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AdditionExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditionExpr(QLParser.AdditionExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EqualityExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualityExpr(QLParser.EqualityExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ComparisonExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonExpr(QLParser.ComparisonExprContext ctx);
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
	 * Visit a parse tree produced by the {@code LabelExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabelExpr(QLParser.LabelExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MultiplicationExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicationExpr(QLParser.MultiplicationExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UnaryExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpr(QLParser.UnaryExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BooleanExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanExpr(QLParser.BooleanExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code OrExpr}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrExpr(QLParser.OrExprContext ctx);
}