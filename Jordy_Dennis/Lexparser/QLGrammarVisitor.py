# Generated from QLGrammar.g4 by ANTLR 4.7.1
from antlr4 import *
if __name__ is not None and "." in __name__:
    from .QLGrammarParser import QLGrammarParser
else:
    from QLGrammarParser import QLGrammarParser

# This class defines a complete generic visitor for a parse tree produced by QLGrammarParser.

class QLGrammarVisitor(ParseTreeVisitor):

    # Visit a parse tree produced by QLGrammarParser#form.
    def visitForm(self, ctx:QLGrammarParser.FormContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLGrammarParser#block.
    def visitBlock(self, ctx:QLGrammarParser.BlockContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLGrammarParser#statement.
    def visitStatement(self, ctx:QLGrammarParser.StatementContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLGrammarParser#question.
    def visitQuestion(self, ctx:QLGrammarParser.QuestionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLGrammarParser#assignment.
    def visitAssignment(self, ctx:QLGrammarParser.AssignmentContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLGrammarParser#expression.
    def visitExpression(self, ctx:QLGrammarParser.ExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLGrammarParser#literal.
    def visitLiteral(self, ctx:QLGrammarParser.LiteralContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLGrammarParser#varnode.
    def visitVarnode(self, ctx:QLGrammarParser.VarnodeContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLGrammarParser#unaryexp.
    def visitUnaryexp(self, ctx:QLGrammarParser.UnaryexpContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLGrammarParser#conditional.
    def visitConditional(self, ctx:QLGrammarParser.ConditionalContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLGrammarParser#if_conditional.
    def visitIf_conditional(self, ctx:QLGrammarParser.If_conditionalContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLGrammarParser#elif_conditional.
    def visitElif_conditional(self, ctx:QLGrammarParser.Elif_conditionalContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLGrammarParser#else_conditional.
    def visitElse_conditional(self, ctx:QLGrammarParser.Else_conditionalContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLGrammarParser#types.
    def visitTypes(self, ctx:QLGrammarParser.TypesContext):
        return self.visitChildren(ctx)



del QLGrammarParser