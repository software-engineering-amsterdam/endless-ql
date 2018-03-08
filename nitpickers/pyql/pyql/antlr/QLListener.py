# Generated from QL.g4 by ANTLR 4.5.3
from antlr4 import *
if __name__ is not None and "." in __name__:
    from .QLParser import QLParser
else:
    from QLParser import QLParser

# This class defines a complete listener for a parse tree produced by QLParser.
class QLListener(ParseTreeListener):

    # Enter a parse tree produced by QLParser#form.
    def enterForm(self, ctx:QLParser.FormContext):
        pass

    # Exit a parse tree produced by QLParser#form.
    def exitForm(self, ctx:QLParser.FormContext):
        pass


    # Enter a parse tree produced by QLParser#ifStatement.
    def enterIfStatement(self, ctx:QLParser.IfStatementContext):
        pass

    # Exit a parse tree produced by QLParser#ifStatement.
    def exitIfStatement(self, ctx:QLParser.IfStatementContext):
        pass


    # Enter a parse tree produced by QLParser#ifElseStatement.
    def enterIfElseStatement(self, ctx:QLParser.IfElseStatementContext):
        pass

    # Exit a parse tree produced by QLParser#ifElseStatement.
    def exitIfElseStatement(self, ctx:QLParser.IfElseStatementContext):
        pass


    # Enter a parse tree produced by QLParser#block.
    def enterBlock(self, ctx:QLParser.BlockContext):
        pass

    # Exit a parse tree produced by QLParser#block.
    def exitBlock(self, ctx:QLParser.BlockContext):
        pass


    # Enter a parse tree produced by QLParser#statement.
    def enterStatement(self, ctx:QLParser.StatementContext):
        pass

    # Exit a parse tree produced by QLParser#statement.
    def exitStatement(self, ctx:QLParser.StatementContext):
        pass


    # Enter a parse tree produced by QLParser#question.
    def enterQuestion(self, ctx:QLParser.QuestionContext):
        pass

    # Exit a parse tree produced by QLParser#question.
    def exitQuestion(self, ctx:QLParser.QuestionContext):
        pass


    # Enter a parse tree produced by QLParser#booleanType.
    def enterBooleanType(self, ctx:QLParser.BooleanTypeContext):
        pass

    # Exit a parse tree produced by QLParser#booleanType.
    def exitBooleanType(self, ctx:QLParser.BooleanTypeContext):
        pass


    # Enter a parse tree produced by QLParser#stringType.
    def enterStringType(self, ctx:QLParser.StringTypeContext):
        pass

    # Exit a parse tree produced by QLParser#stringType.
    def exitStringType(self, ctx:QLParser.StringTypeContext):
        pass


    # Enter a parse tree produced by QLParser#integerType.
    def enterIntegerType(self, ctx:QLParser.IntegerTypeContext):
        pass

    # Exit a parse tree produced by QLParser#integerType.
    def exitIntegerType(self, ctx:QLParser.IntegerTypeContext):
        pass


    # Enter a parse tree produced by QLParser#dateType.
    def enterDateType(self, ctx:QLParser.DateTypeContext):
        pass

    # Exit a parse tree produced by QLParser#dateType.
    def exitDateType(self, ctx:QLParser.DateTypeContext):
        pass


    # Enter a parse tree produced by QLParser#decimalType.
    def enterDecimalType(self, ctx:QLParser.DecimalTypeContext):
        pass

    # Exit a parse tree produced by QLParser#decimalType.
    def exitDecimalType(self, ctx:QLParser.DecimalTypeContext):
        pass


    # Enter a parse tree produced by QLParser#moneyType.
    def enterMoneyType(self, ctx:QLParser.MoneyTypeContext):
        pass

    # Exit a parse tree produced by QLParser#moneyType.
    def exitMoneyType(self, ctx:QLParser.MoneyTypeContext):
        pass


    # Enter a parse tree produced by QLParser#expression.
    def enterExpression(self, ctx:QLParser.ExpressionContext):
        pass

    # Exit a parse tree produced by QLParser#expression.
    def exitExpression(self, ctx:QLParser.ExpressionContext):
        pass


    # Enter a parse tree produced by QLParser#orExpression.
    def enterOrExpression(self, ctx:QLParser.OrExpressionContext):
        pass

    # Exit a parse tree produced by QLParser#orExpression.
    def exitOrExpression(self, ctx:QLParser.OrExpressionContext):
        pass


    # Enter a parse tree produced by QLParser#andExpression.
    def enterAndExpression(self, ctx:QLParser.AndExpressionContext):
        pass

    # Exit a parse tree produced by QLParser#andExpression.
    def exitAndExpression(self, ctx:QLParser.AndExpressionContext):
        pass


    # Enter a parse tree produced by QLParser#relExpression.
    def enterRelExpression(self, ctx:QLParser.RelExpressionContext):
        pass

    # Exit a parse tree produced by QLParser#relExpression.
    def exitRelExpression(self, ctx:QLParser.RelExpressionContext):
        pass


    # Enter a parse tree produced by QLParser#addExpression.
    def enterAddExpression(self, ctx:QLParser.AddExpressionContext):
        pass

    # Exit a parse tree produced by QLParser#addExpression.
    def exitAddExpression(self, ctx:QLParser.AddExpressionContext):
        pass


    # Enter a parse tree produced by QLParser#addOperator.
    def enterAddOperator(self, ctx:QLParser.AddOperatorContext):
        pass

    # Exit a parse tree produced by QLParser#addOperator.
    def exitAddOperator(self, ctx:QLParser.AddOperatorContext):
        pass


    # Enter a parse tree produced by QLParser#mulExpression.
    def enterMulExpression(self, ctx:QLParser.MulExpressionContext):
        pass

    # Exit a parse tree produced by QLParser#mulExpression.
    def exitMulExpression(self, ctx:QLParser.MulExpressionContext):
        pass


    # Enter a parse tree produced by QLParser#mulOperator.
    def enterMulOperator(self, ctx:QLParser.MulOperatorContext):
        pass

    # Exit a parse tree produced by QLParser#mulOperator.
    def exitMulOperator(self, ctx:QLParser.MulOperatorContext):
        pass


    # Enter a parse tree produced by QLParser#unExpression.
    def enterUnExpression(self, ctx:QLParser.UnExpressionContext):
        pass

    # Exit a parse tree produced by QLParser#unExpression.
    def exitUnExpression(self, ctx:QLParser.UnExpressionContext):
        pass


    # Enter a parse tree produced by QLParser#moneyLiteral.
    def enterMoneyLiteral(self, ctx:QLParser.MoneyLiteralContext):
        pass

    # Exit a parse tree produced by QLParser#moneyLiteral.
    def exitMoneyLiteral(self, ctx:QLParser.MoneyLiteralContext):
        pass


    # Enter a parse tree produced by QLParser#decimalLiteral.
    def enterDecimalLiteral(self, ctx:QLParser.DecimalLiteralContext):
        pass

    # Exit a parse tree produced by QLParser#decimalLiteral.
    def exitDecimalLiteral(self, ctx:QLParser.DecimalLiteralContext):
        pass


    # Enter a parse tree produced by QLParser#intLiteral.
    def enterIntLiteral(self, ctx:QLParser.IntLiteralContext):
        pass

    # Exit a parse tree produced by QLParser#intLiteral.
    def exitIntLiteral(self, ctx:QLParser.IntLiteralContext):
        pass


    # Enter a parse tree produced by QLParser#stringLiteral.
    def enterStringLiteral(self, ctx:QLParser.StringLiteralContext):
        pass

    # Exit a parse tree produced by QLParser#stringLiteral.
    def exitStringLiteral(self, ctx:QLParser.StringLiteralContext):
        pass


    # Enter a parse tree produced by QLParser#boolLiteral.
    def enterBoolLiteral(self, ctx:QLParser.BoolLiteralContext):
        pass

    # Exit a parse tree produced by QLParser#boolLiteral.
    def exitBoolLiteral(self, ctx:QLParser.BoolLiteralContext):
        pass


    # Enter a parse tree produced by QLParser#identifier.
    def enterIdentifier(self, ctx:QLParser.IdentifierContext):
        pass

    # Exit a parse tree produced by QLParser#identifier.
    def exitIdentifier(self, ctx:QLParser.IdentifierContext):
        pass


    # Enter a parse tree produced by QLParser#money.
    def enterMoney(self, ctx:QLParser.MoneyContext):
        pass

    # Exit a parse tree produced by QLParser#money.
    def exitMoney(self, ctx:QLParser.MoneyContext):
        pass


