// Generated from QLS.g4 by ANTLR 4.7.1

	package org.uva.jomi.qls.parser.antlr;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link QLSParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface QLSVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link QLSParser#parse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParse(QLSParser.ParseContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#stylesheetStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStylesheetStmt(QLSParser.StylesheetStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#pageStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPageStmt(QLSParser.PageStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#blockStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStmt(QLSParser.BlockStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommand(QLSParser.CommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#sectionStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSectionStmt(QLSParser.SectionStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#questionStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionStmt(QLSParser.QuestionStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#defaultWidgetStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefaultWidgetStmt(QLSParser.DefaultWidgetStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#widgetStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWidgetStmt(QLSParser.WidgetStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#widgetType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWidgetType(QLSParser.WidgetTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#widgetRadioStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWidgetRadioStmt(QLSParser.WidgetRadioStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#widgetSpinboxStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWidgetSpinboxStmt(QLSParser.WidgetSpinboxStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#widgetTextStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWidgetTextStmt(QLSParser.WidgetTextStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#widgetYesNoRadiosStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWidgetYesNoRadiosStmt(QLSParser.WidgetYesNoRadiosStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#widgetCheckboxStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWidgetCheckboxStmt(QLSParser.WidgetCheckboxStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#widgetDropdownStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWidgetDropdownStmt(QLSParser.WidgetDropdownStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLSParser#widgetYesNoDropdownStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWidgetYesNoDropdownStmt(QLSParser.WidgetYesNoDropdownStmtContext ctx);
}