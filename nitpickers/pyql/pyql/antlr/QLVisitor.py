# Generated from QL.g4 by ANTLR 4.7
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


    # Visit a parse tree produced by QLParser#questionType.
    def visitQuestionType(self, ctx:QLParser.QuestionTypeContext):
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


    # Visit a parse tree produced by QLParser#mulExpression.
    def visitMulExpression(self, ctx:QLParser.MulExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#unExpression.
    def visitUnExpression(self, ctx:QLParser.UnExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#literal.
    def visitLiteral(self, ctx:QLParser.LiteralContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#identifier.
    def visitIdentifier(self, ctx:QLParser.IdentifierContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#money.
    def visitMoney(self, ctx:QLParser.MoneyContext):
        return self.visitChildren(ctx)



del QLParser