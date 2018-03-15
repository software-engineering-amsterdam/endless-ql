// Generated from src/main/java/org/uva/sea/ql/parser/antlr/QL.g by ANTLR 4.7.1

package org.uva.sea.languages.ql.antlr;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link QLParser}.
 */
public interface QLListener extends ParseTreeListener {
    /**
     * Enter a parse tree produced by {@link QLParser#form}.
     *
     * @param ctx the parse tree
     */
    void enterForm(QLParser.FormContext ctx);

    /**
     * Exit a parse tree produced by {@link QLParser#form}.
     *
     * @param ctx the parse tree
     */
    void exitForm(QLParser.FormContext ctx);

    /**
     * Enter a parse tree produced by {@link QLParser#statements}.
     *
     * @param ctx the parse tree
     */
    void enterStatements(QLParser.StatementsContext ctx);

    /**
     * Exit a parse tree produced by {@link QLParser#statements}.
     *
     * @param ctx the parse tree
     */
    void exitStatements(QLParser.StatementsContext ctx);

    /**
     * Enter a parse tree produced by {@link QLParser#statement}.
     *
     * @param ctx the parse tree
     */
    void enterStatement(QLParser.StatementContext ctx);

    /**
     * Exit a parse tree produced by {@link QLParser#statement}.
     *
     * @param ctx the parse tree
     */
    void exitStatement(QLParser.StatementContext ctx);

    /**
     * Enter a parse tree produced by {@link QLParser#question}.
     *
     * @param ctx the parse tree
     */
    void enterQuestion(QLParser.QuestionContext ctx);

    /**
     * Exit a parse tree produced by {@link QLParser#question}.
     *
     * @param ctx the parse tree
     */
    void exitQuestion(QLParser.QuestionContext ctx);

    /**
     * Enter a parse tree produced by {@link QLParser#label}.
     *
     * @param ctx the parse tree
     */
    void enterLabel(QLParser.LabelContext ctx);

    /**
     * Exit a parse tree produced by {@link QLParser#label}.
     *
     * @param ctx the parse tree
     */
    void exitLabel(QLParser.LabelContext ctx);

    /**
     * Enter a parse tree produced by {@link QLParser#variable}.
     *
     * @param ctx the parse tree
     */
    void enterVariable(QLParser.VariableContext ctx);

    /**
     * Exit a parse tree produced by {@link QLParser#variable}.
     *
     * @param ctx the parse tree
     */
    void exitVariable(QLParser.VariableContext ctx);

    /**
     * Enter a parse tree produced by {@link QLParser#type}.
     *
     * @param ctx the parse tree
     */
    void enterType(QLParser.TypeContext ctx);

    /**
     * Exit a parse tree produced by {@link QLParser#type}.
     *
     * @param ctx the parse tree
     */
    void exitType(QLParser.TypeContext ctx);

    /**
     * Enter a parse tree produced by {@link QLParser#condition}.
     *
     * @param ctx the parse tree
     */
    void enterCondition(QLParser.ConditionContext ctx);

    /**
     * Exit a parse tree produced by {@link QLParser#condition}.
     *
     * @param ctx the parse tree
     */
    void exitCondition(QLParser.ConditionContext ctx);

    /**
     * Enter a parse tree produced by {@link QLParser#block}.
     *
     * @param ctx the parse tree
     */
    void enterBlock(QLParser.BlockContext ctx);

    /**
     * Exit a parse tree produced by {@link QLParser#block}.
     *
     * @param ctx the parse tree
     */
    void exitBlock(QLParser.BlockContext ctx);

    /**
     * Enter a parse tree produced by {@link QLParser#expression}.
     *
     * @param ctx the parse tree
     */
    void enterExpression(QLParser.ExpressionContext ctx);

    /**
     * Exit a parse tree produced by {@link QLParser#expression}.
     *
     * @param ctx the parse tree
     */
    void exitExpression(QLParser.ExpressionContext ctx);

    /**
     * Enter a parse tree produced by {@link QLParser#orExpr}.
     *
     * @param ctx the parse tree
     */
    void enterOrExpr(QLParser.OrExprContext ctx);

    /**
     * Exit a parse tree produced by {@link QLParser#orExpr}.
     *
     * @param ctx the parse tree
     */
    void exitOrExpr(QLParser.OrExprContext ctx);

    /**
     * Enter a parse tree produced by {@link QLParser#andExpr}.
     *
     * @param ctx the parse tree
     */
    void enterAndExpr(QLParser.AndExprContext ctx);

    /**
     * Exit a parse tree produced by {@link QLParser#andExpr}.
     *
     * @param ctx the parse tree
     */
    void exitAndExpr(QLParser.AndExprContext ctx);

    /**
     * Enter a parse tree produced by {@link QLParser#relExpr}.
     *
     * @param ctx the parse tree
     */
    void enterRelExpr(QLParser.RelExprContext ctx);

    /**
     * Exit a parse tree produced by {@link QLParser#relExpr}.
     *
     * @param ctx the parse tree
     */
    void exitRelExpr(QLParser.RelExprContext ctx);

    /**
     * Enter a parse tree produced by {@link QLParser#addExpr}.
     *
     * @param ctx the parse tree
     */
    void enterAddExpr(QLParser.AddExprContext ctx);

    /**
     * Exit a parse tree produced by {@link QLParser#addExpr}.
     *
     * @param ctx the parse tree
     */
    void exitAddExpr(QLParser.AddExprContext ctx);

    /**
     * Enter a parse tree produced by {@link QLParser#mulExpr}.
     *
     * @param ctx the parse tree
     */
    void enterMulExpr(QLParser.MulExprContext ctx);

    /**
     * Exit a parse tree produced by {@link QLParser#mulExpr}.
     *
     * @param ctx the parse tree
     */
    void exitMulExpr(QLParser.MulExprContext ctx);

    /**
     * Enter a parse tree produced by {@link QLParser#unExpr}.
     *
     * @param ctx the parse tree
     */
    void enterUnExpr(QLParser.UnExprContext ctx);

    /**
     * Exit a parse tree produced by {@link QLParser#unExpr}.
     *
     * @param ctx the parse tree
     */
    void exitUnExpr(QLParser.UnExprContext ctx);

    /**
     * Enter a parse tree produced by {@link QLParser#primary}.
     *
     * @param ctx the parse tree
     */
    void enterPrimary(QLParser.PrimaryContext ctx);

    /**
     * Exit a parse tree produced by {@link QLParser#primary}.
     *
     * @param ctx the parse tree
     */
    void exitPrimary(QLParser.PrimaryContext ctx);

    /**
     * Enter a parse tree produced by {@link QLParser#bool}.
     *
     * @param ctx the parse tree
     */
    void enterBool(QLParser.BoolContext ctx);

    /**
     * Exit a parse tree produced by {@link QLParser#bool}.
     *
     * @param ctx the parse tree
     */
    void exitBool(QLParser.BoolContext ctx);

    /**
     * Enter a parse tree produced by {@link QLParser#num}.
     *
     * @param ctx the parse tree
     */
    void enterNum(QLParser.NumContext ctx);

    /**
     * Exit a parse tree produced by {@link QLParser#num}.
     *
     * @param ctx the parse tree
     */
    void exitNum(QLParser.NumContext ctx);

    /**
     * Enter a parse tree produced by {@link QLParser#dec}.
     *
     * @param ctx the parse tree
     */
    void enterDec(QLParser.DecContext ctx);

    /**
     * Exit a parse tree produced by {@link QLParser#dec}.
     *
     * @param ctx the parse tree
     */
    void exitDec(QLParser.DecContext ctx);

    /**
     * Enter a parse tree produced by {@link QLParser#str}.
     *
     * @param ctx the parse tree
     */
    void enterStr(QLParser.StrContext ctx);

    /**
     * Exit a parse tree produced by {@link QLParser#str}.
     *
     * @param ctx the parse tree
     */
    void exitStr(QLParser.StrContext ctx);

    /**
     * Enter a parse tree produced by {@link QLParser#money}.
     *
     * @param ctx the parse tree
     */
    void enterMoney(QLParser.MoneyContext ctx);

    /**
     * Exit a parse tree produced by {@link QLParser#money}.
     *
     * @param ctx the parse tree
     */
    void exitMoney(QLParser.MoneyContext ctx);

    /**
     * Enter a parse tree produced by {@link QLParser#date}.
     *
     * @param ctx the parse tree
     */
    void enterDate(QLParser.DateContext ctx);

    /**
     * Exit a parse tree produced by {@link QLParser#date}.
     *
     * @param ctx the parse tree
     */
    void exitDate(QLParser.DateContext ctx);
}