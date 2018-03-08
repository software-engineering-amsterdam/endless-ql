# Generated from QL.g4 by ANTLR 4.5.3
from antlr4 import *
if __name__ is not None and "." in __name__:
    from .QLParser import QLParser
else:
    from QLParser import QLParser

# This class defines a complete generic visitor for a parse tree produced by QLParser.

class QLVisitor(ParseTreeVisitor):

    # Visit a parse tree produced by QLParser#form.
    def visitForm(self, ctx:QLParser.FormContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#ifStatement.
    def visitIfStatement(self, ctx:QLParser.IfStatementContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#ifElseStatement.
    def visitIfElseStatement(self, ctx:QLParser.IfElseStatementContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#block.
    def visitBlock(self, ctx:QLParser.BlockContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#statement.
    def visitStatement(self, ctx:QLParser.StatementContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#question.
    def visitQuestion(self, ctx:QLParser.QuestionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#booleanType.
    def visitBooleanType(self, ctx:QLParser.BooleanTypeContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#stringType.
    def visitStringType(self, ctx:QLParser.StringTypeContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#integerType.
    def visitIntegerType(self, ctx:QLParser.IntegerTypeContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#dateType.
    def visitDateType(self, ctx:QLParser.DateTypeContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#decimalType.
    def visitDecimalType(self, ctx:QLParser.DecimalTypeContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#moneyType.
    def visitMoneyType(self, ctx:QLParser.MoneyTypeContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#expression.
    def visitExpression(self, ctx:QLParser.ExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#orExpression.
    def visitOrExpression(self, ctx:QLParser.OrExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#andExpression.
    def visitAndExpression(self, ctx:QLParser.AndExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#relExpression.
    def visitRelExpression(self, ctx:QLParser.RelExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#addExpression.
    def visitAddExpression(self, ctx:QLParser.AddExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#addOperator.
    def visitAddOperator(self, ctx:QLParser.AddOperatorContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#mulExpression.
    def visitMulExpression(self, ctx:QLParser.MulExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#mulOperator.
    def visitMulOperator(self, ctx:QLParser.MulOperatorContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#unExpression.
    def visitUnExpression(self, ctx:QLParser.UnExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#moneyLiteral.
    def visitMoneyLiteral(self, ctx:QLParser.MoneyLiteralContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#decimalLiteral.
    def visitDecimalLiteral(self, ctx:QLParser.DecimalLiteralContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#intLiteral.
    def visitIntLiteral(self, ctx:QLParser.IntLiteralContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#stringLiteral.
    def visitStringLiteral(self, ctx:QLParser.StringLiteralContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#boolLiteral.
    def visitBoolLiteral(self, ctx:QLParser.BoolLiteralContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#identifier.
    def visitIdentifier(self, ctx:QLParser.IdentifierContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#money.
    def visitMoney(self, ctx:QLParser.MoneyContext):
        return self.visitChildren(ctx)



del QLParser