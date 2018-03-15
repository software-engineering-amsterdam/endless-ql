// Generated from C:/dev/uva/endless-ql/Abel_Elias/src/parsing\QL.g4 by ANTLR 4.7
package parsing.gen;
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
	 * Visit a parse tree produced by {@link QLParser#form}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForm(QLParser.FormContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(QLParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#lineInBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLineInBlock(QLParser.LineInBlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code normalQuestion}
	 * labeled alternative in {@link QLParser#question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNormalQuestion(QLParser.NormalQuestionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fixedQuestion}
	 * labeled alternative in {@link QLParser#question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFixedQuestion(QLParser.FixedQuestionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code identifier}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(QLParser.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolExpression(QLParser.BoolExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code numExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumExpression(QLParser.NumExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code string}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(QLParser.StringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolIdentifier}
	 * labeled alternative in {@link QLParser#booleanExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolIdentifier(QLParser.BoolIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notOperation}
	 * labeled alternative in {@link QLParser#booleanExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotOperation(QLParser.NotOperationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code compOperation}
	 * labeled alternative in {@link QLParser#booleanExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompOperation(QLParser.CompOperationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolBraces}
	 * labeled alternative in {@link QLParser#booleanExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolBraces(QLParser.BoolBracesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolValue}
	 * labeled alternative in {@link QLParser#booleanExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolValue(QLParser.BoolValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolOperation}
	 * labeled alternative in {@link QLParser#booleanExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolOperation(QLParser.BoolOperationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intValue}
	 * labeled alternative in {@link QLParser#numberExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntValue(QLParser.IntValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code numIdentifier}
	 * labeled alternative in {@link QLParser#numberExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumIdentifier(QLParser.NumIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by the {@code numBraces}
	 * labeled alternative in {@link QLParser#numberExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumBraces(QLParser.NumBracesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code moneyValue}
	 * labeled alternative in {@link QLParser#numberExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMoneyValue(QLParser.MoneyValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code decValue}
	 * labeled alternative in {@link QLParser#numberExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecValue(QLParser.DecValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code numOperation}
	 * labeled alternative in {@link QLParser#numberExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumOperation(QLParser.NumOperationContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#boolOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolOperator(QLParser.BoolOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#comparisonOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonOperator(QLParser.ComparisonOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#numberOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberOperator(QLParser.NumberOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link QLParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(QLParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code booltype}
	 * labeled alternative in {@link QLParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooltype(QLParser.BooltypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringtype}
	 * labeled alternative in {@link QLParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringtype(QLParser.StringtypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code integertype}
	 * labeled alternative in {@link QLParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegertype(QLParser.IntegertypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code moneytype}
	 * labeled alternative in {@link QLParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMoneytype(QLParser.MoneytypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code datetype}
	 * labeled alternative in {@link QLParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDatetype(QLParser.DatetypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code decimaltype}
	 * labeled alternative in {@link QLParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecimaltype(QLParser.DecimaltypeContext ctx);
}