// Generated from C:/dev/uva/endless-ql/Abel_Elias/src/parsing\QL.g4 by ANTLR 4.7
package parsing.gen;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link QLParser}.
 */
public interface QLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link QLParser#form}.
	 * @param ctx the parse tree
	 */
	void enterForm(QLParser.FormContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#form}.
	 * @param ctx the parse tree
	 */
	void exitForm(QLParser.FormContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(QLParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(QLParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#lineInBlock}.
	 * @param ctx the parse tree
	 */
	void enterLineInBlock(QLParser.LineInBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#lineInBlock}.
	 * @param ctx the parse tree
	 */
	void exitLineInBlock(QLParser.LineInBlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code normalQuestion}
	 * labeled alternative in {@link QLParser#question}.
	 * @param ctx the parse tree
	 */
	void enterNormalQuestion(QLParser.NormalQuestionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code normalQuestion}
	 * labeled alternative in {@link QLParser#question}.
	 * @param ctx the parse tree
	 */
	void exitNormalQuestion(QLParser.NormalQuestionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fixedQuestion}
	 * labeled alternative in {@link QLParser#question}.
	 * @param ctx the parse tree
	 */
	void enterFixedQuestion(QLParser.FixedQuestionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fixedQuestion}
	 * labeled alternative in {@link QLParser#question}.
	 * @param ctx the parse tree
	 */
	void exitFixedQuestion(QLParser.FixedQuestionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code identifier}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(QLParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by the {@code identifier}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(QLParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBoolExpression(QLParser.BoolExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBoolExpression(QLParser.BoolExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code numExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNumExpression(QLParser.NumExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code numExpression}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNumExpression(QLParser.NumExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code string}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterString(QLParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code string}
	 * labeled alternative in {@link QLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitString(QLParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolIdentifier}
	 * labeled alternative in {@link QLParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void enterBoolIdentifier(QLParser.BoolIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolIdentifier}
	 * labeled alternative in {@link QLParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void exitBoolIdentifier(QLParser.BoolIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notOperation}
	 * labeled alternative in {@link QLParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void enterNotOperation(QLParser.NotOperationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notOperation}
	 * labeled alternative in {@link QLParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void exitNotOperation(QLParser.NotOperationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code compOperation}
	 * labeled alternative in {@link QLParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void enterCompOperation(QLParser.CompOperationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code compOperation}
	 * labeled alternative in {@link QLParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void exitCompOperation(QLParser.CompOperationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolBraces}
	 * labeled alternative in {@link QLParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void enterBoolBraces(QLParser.BoolBracesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolBraces}
	 * labeled alternative in {@link QLParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void exitBoolBraces(QLParser.BoolBracesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolValue}
	 * labeled alternative in {@link QLParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void enterBoolValue(QLParser.BoolValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolValue}
	 * labeled alternative in {@link QLParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void exitBoolValue(QLParser.BoolValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolOperation}
	 * labeled alternative in {@link QLParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void enterBoolOperation(QLParser.BoolOperationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolOperation}
	 * labeled alternative in {@link QLParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void exitBoolOperation(QLParser.BoolOperationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intValue}
	 * labeled alternative in {@link QLParser#numberExpression}.
	 * @param ctx the parse tree
	 */
	void enterIntValue(QLParser.IntValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intValue}
	 * labeled alternative in {@link QLParser#numberExpression}.
	 * @param ctx the parse tree
	 */
	void exitIntValue(QLParser.IntValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code numIdentifier}
	 * labeled alternative in {@link QLParser#numberExpression}.
	 * @param ctx the parse tree
	 */
	void enterNumIdentifier(QLParser.NumIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by the {@code numIdentifier}
	 * labeled alternative in {@link QLParser#numberExpression}.
	 * @param ctx the parse tree
	 */
	void exitNumIdentifier(QLParser.NumIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by the {@code numBraces}
	 * labeled alternative in {@link QLParser#numberExpression}.
	 * @param ctx the parse tree
	 */
	void enterNumBraces(QLParser.NumBracesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code numBraces}
	 * labeled alternative in {@link QLParser#numberExpression}.
	 * @param ctx the parse tree
	 */
	void exitNumBraces(QLParser.NumBracesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code moneyValue}
	 * labeled alternative in {@link QLParser#numberExpression}.
	 * @param ctx the parse tree
	 */
	void enterMoneyValue(QLParser.MoneyValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code moneyValue}
	 * labeled alternative in {@link QLParser#numberExpression}.
	 * @param ctx the parse tree
	 */
	void exitMoneyValue(QLParser.MoneyValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code decValue}
	 * labeled alternative in {@link QLParser#numberExpression}.
	 * @param ctx the parse tree
	 */
	void enterDecValue(QLParser.DecValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code decValue}
	 * labeled alternative in {@link QLParser#numberExpression}.
	 * @param ctx the parse tree
	 */
	void exitDecValue(QLParser.DecValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code numOperation}
	 * labeled alternative in {@link QLParser#numberExpression}.
	 * @param ctx the parse tree
	 */
	void enterNumOperation(QLParser.NumOperationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code numOperation}
	 * labeled alternative in {@link QLParser#numberExpression}.
	 * @param ctx the parse tree
	 */
	void exitNumOperation(QLParser.NumOperationContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#boolOperator}.
	 * @param ctx the parse tree
	 */
	void enterBoolOperator(QLParser.BoolOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#boolOperator}.
	 * @param ctx the parse tree
	 */
	void exitBoolOperator(QLParser.BoolOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void enterComparisonOperator(QLParser.ComparisonOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void exitComparisonOperator(QLParser.ComparisonOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#numberOperator}.
	 * @param ctx the parse tree
	 */
	void enterNumberOperator(QLParser.NumberOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#numberOperator}.
	 * @param ctx the parse tree
	 */
	void exitNumberOperator(QLParser.NumberOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link QLParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(QLParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link QLParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(QLParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code booltype}
	 * labeled alternative in {@link QLParser#type}.
	 * @param ctx the parse tree
	 */
	void enterBooltype(QLParser.BooltypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code booltype}
	 * labeled alternative in {@link QLParser#type}.
	 * @param ctx the parse tree
	 */
	void exitBooltype(QLParser.BooltypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringtype}
	 * labeled alternative in {@link QLParser#type}.
	 * @param ctx the parse tree
	 */
	void enterStringtype(QLParser.StringtypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringtype}
	 * labeled alternative in {@link QLParser#type}.
	 * @param ctx the parse tree
	 */
	void exitStringtype(QLParser.StringtypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code integertype}
	 * labeled alternative in {@link QLParser#type}.
	 * @param ctx the parse tree
	 */
	void enterIntegertype(QLParser.IntegertypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code integertype}
	 * labeled alternative in {@link QLParser#type}.
	 * @param ctx the parse tree
	 */
	void exitIntegertype(QLParser.IntegertypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code moneytype}
	 * labeled alternative in {@link QLParser#type}.
	 * @param ctx the parse tree
	 */
	void enterMoneytype(QLParser.MoneytypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code moneytype}
	 * labeled alternative in {@link QLParser#type}.
	 * @param ctx the parse tree
	 */
	void exitMoneytype(QLParser.MoneytypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code datetype}
	 * labeled alternative in {@link QLParser#type}.
	 * @param ctx the parse tree
	 */
	void enterDatetype(QLParser.DatetypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code datetype}
	 * labeled alternative in {@link QLParser#type}.
	 * @param ctx the parse tree
	 */
	void exitDatetype(QLParser.DatetypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code decimaltype}
	 * labeled alternative in {@link QLParser#type}.
	 * @param ctx the parse tree
	 */
	void enterDecimaltype(QLParser.DecimaltypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code decimaltype}
	 * labeled alternative in {@link QLParser#type}.
	 * @param ctx the parse tree
	 */
	void exitDecimaltype(QLParser.DecimaltypeContext ctx);
}