// Generated from C:/dev/uva/endless-ql/Abel_Elias/src/QL/parsing\QL.g4 by ANTLR 4.7
package QL.parsing.gen;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link QLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 *            operations with no return type.
 */
public interface QLVisitor<T> extends ParseTreeVisitor<T> {
    /**
     * Visit a parse tree produced by {@link QLParser#form}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitForm(QLParser.FormContext ctx);

    /**
     * Visit a parse tree produced by {@link QLParser#block}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitBlock(QLParser.BlockContext ctx);

    /**
     * Visit a parse tree produced by {@link QLParser#lineInBlock}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitLineInBlock(QLParser.LineInBlockContext ctx);

    /**
     * Visit a parse tree produced by the {@code normalQuestion}
     * labeled alternative in {@link QLParser#question}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitNormalQuestion(QLParser.NormalQuestionContext ctx);

    /**
     * Visit a parse tree produced by the {@code fixedQuestion}
     * labeled alternative in {@link QLParser#question}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitFixedQuestion(QLParser.FixedQuestionContext ctx);

    /**
     * Visit a parse tree produced by the {@code identifier}
     * labeled alternative in {@link QLParser#expression}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIdentifier(QLParser.IdentifierContext ctx);

    /**
     * Visit a parse tree produced by the {@code eqExpression}
     * labeled alternative in {@link QLParser#expression}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitEqExpression(QLParser.EqExpressionContext ctx);

    /**
     * Visit a parse tree produced by the {@code boolExpression}
     * labeled alternative in {@link QLParser#expression}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitBoolExpression(QLParser.BoolExpressionContext ctx);

    /**
     * Visit a parse tree produced by the {@code compExpression}
     * labeled alternative in {@link QLParser#expression}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitCompExpression(QLParser.CompExpressionContext ctx);

    /**
     * Visit a parse tree produced by the {@code numExpression}
     * labeled alternative in {@link QLParser#expression}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitNumExpression(QLParser.NumExpressionContext ctx);

    /**
     * Visit a parse tree produced by the {@code braceExpression}
     * labeled alternative in {@link QLParser#expression}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitBraceExpression(QLParser.BraceExpressionContext ctx);

    /**
     * Visit a parse tree produced by the {@code notExpression}
     * labeled alternative in {@link QLParser#expression}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitNotExpression(QLParser.NotExpressionContext ctx);

    /**
     * Visit a parse tree produced by the {@code boolValue}
     * labeled alternative in {@link QLParser#expression}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitBoolValue(QLParser.BoolValueContext ctx);

    /**
     * Visit a parse tree produced by the {@code strValue}
     * labeled alternative in {@link QLParser#expression}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitStrValue(QLParser.StrValueContext ctx);

    /**
     * Visit a parse tree produced by the {@code numValue}
     * labeled alternative in {@link QLParser#expression}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitNumValue(QLParser.NumValueContext ctx);

    /**
     * Visit a parse tree produced by {@link QLParser#boolOperator}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitBoolOperator(QLParser.BoolOperatorContext ctx);

    /**
     * Visit a parse tree produced by {@link QLParser#comparisonOperator}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitComparisonOperator(QLParser.ComparisonOperatorContext ctx);

    /**
     * Visit a parse tree produced by {@link QLParser#equalsOperator}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitEqualsOperator(QLParser.EqualsOperatorContext ctx);

    /**
     * Visit a parse tree produced by {@link QLParser#numberOperator}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitNumberOperator(QLParser.NumberOperatorContext ctx);

    /**
     * Visit a parse tree produced by {@link QLParser#ifStatement}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIfStatement(QLParser.IfStatementContext ctx);

    /**
     * Visit a parse tree produced by the {@code booltype}
     * labeled alternative in {@link QLParser#type}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitBooltype(QLParser.BooltypeContext ctx);

    /**
     * Visit a parse tree produced by the {@code stringtype}
     * labeled alternative in {@link QLParser#type}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitStringtype(QLParser.StringtypeContext ctx);

    /**
     * Visit a parse tree produced by the {@code integertype}
     * labeled alternative in {@link QLParser#type}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIntegertype(QLParser.IntegertypeContext ctx);

    /**
     * Visit a parse tree produced by the {@code moneytype}
     * labeled alternative in {@link QLParser#type}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitMoneytype(QLParser.MoneytypeContext ctx);

    /**
     * Visit a parse tree produced by the {@code datetype}
     * labeled alternative in {@link QLParser#type}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitDatetype(QLParser.DatetypeContext ctx);

    /**
     * Visit a parse tree produced by the {@code decimaltype}
     * labeled alternative in {@link QLParser#type}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitDecimaltype(QLParser.DecimaltypeContext ctx);
}