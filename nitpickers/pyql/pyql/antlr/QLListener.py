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


    # Enter a parse tree produced by QLParser#conditional_block.
    def enterConditional_block(self, ctx:QLParser.Conditional_blockContext):
        pass

    # Exit a parse tree produced by QLParser#conditional_block.
    def exitConditional_block(self, ctx:QLParser.Conditional_blockContext):
        pass


    # Enter a parse tree produced by QLParser#block.
    def enterBlock(self, ctx:QLParser.BlockContext):
        pass

    # Exit a parse tree produced by QLParser#block.
    def exitBlock(self, ctx:QLParser.BlockContext):
        pass


    # Enter a parse tree produced by QLParser#question.
    def enterQuestion(self, ctx:QLParser.QuestionContext):
        pass

    # Exit a parse tree produced by QLParser#question.
    def exitQuestion(self, ctx:QLParser.QuestionContext):
        pass


    # Enter a parse tree produced by QLParser#question_type.
    def enterQuestion_type(self, ctx:QLParser.Question_typeContext):
        pass

    # Exit a parse tree produced by QLParser#question_type.
    def exitQuestion_type(self, ctx:QLParser.Question_typeContext):
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


    # Enter a parse tree produced by QLParser#mulExpression.
    def enterMulExpression(self, ctx:QLParser.MulExpressionContext):
        pass

    # Exit a parse tree produced by QLParser#mulExpression.
    def exitMulExpression(self, ctx:QLParser.MulExpressionContext):
        pass


    # Enter a parse tree produced by QLParser#unExpression.
    def enterUnExpression(self, ctx:QLParser.UnExpressionContext):
        pass

    # Exit a parse tree produced by QLParser#unExpression.
    def exitUnExpression(self, ctx:QLParser.UnExpressionContext):
        pass


    # Enter a parse tree produced by QLParser#literal.
    def enterLiteral(self, ctx:QLParser.LiteralContext):
        pass

    # Exit a parse tree produced by QLParser#literal.
    def exitLiteral(self, ctx:QLParser.LiteralContext):
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


