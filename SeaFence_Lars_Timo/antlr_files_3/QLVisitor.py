# Generated from QL.g4 by ANTLR 4.7.1
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


    # Visit a parse tree produced by QLParser#assignment.
    def visitAssignment(self, ctx:QLParser.AssignmentContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#question.
    def visitQuestion(self, ctx:QLParser.QuestionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#conditional.
    def visitConditional(self, ctx:QLParser.ConditionalContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#expression.
    def visitExpression(self, ctx:QLParser.ExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#form_id.
    def visitForm_id(self, ctx:QLParser.Form_idContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#var.
    def visitVar(self, ctx:QLParser.VarContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#vartype.
    def visitVartype(self, ctx:QLParser.VartypeContext):
        return self.visitChildren(ctx)



del QLParser