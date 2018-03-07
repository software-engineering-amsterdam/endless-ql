# Generated from grammar/QL.g4 by ANTLR 4.7.1
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


    # Visit a parse tree produced by QLParser#block.
    def visitBlock(self, ctx:QLParser.BlockContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#statement.
    def visitStatement(self, ctx:QLParser.StatementContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#question.
    def visitQuestion(self, ctx:QLParser.QuestionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#assignment.
    def visitAssignment(self, ctx:QLParser.AssignmentContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#expression.
    def visitExpression(self, ctx:QLParser.ExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#conditional.
    def visitConditional(self, ctx:QLParser.ConditionalContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#if_conditional.
    def visitIf_conditional(self, ctx:QLParser.If_conditionalContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#else_conditional.
    def visitElse_conditional(self, ctx:QLParser.Else_conditionalContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#typeDeclaration.
    def visitTypeDeclaration(self, ctx:QLParser.TypeDeclarationContext):
        return self.visitChildren(ctx)



del QLParser