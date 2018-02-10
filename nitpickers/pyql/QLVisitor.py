# Generated from QL.g by ANTLR 4.7
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

    # Visit a parse tree produced by QLParser#conditional_block.
    def visitConditional_block(self, ctx:QLParser.Conditional_blockContext):
        return self.visitChildren(ctx)

    # Visit a parse tree produced by QLParser#expression.
    def visitExpression(self, ctx:QLParser.ExpressionContext):
        return self.visitChildren(ctx)

    # Visit a parse tree produced by QLParser#statement.
    def visitStatement(self, ctx:QLParser.StatementContext):
        return self.visitChildren(ctx)

    # Visit a parse tree produced by QLParser#quest.
    def visitQuest(self, ctx:QLParser.QuestContext):
        return self.visitChildren(ctx)

    # Visit a parse tree produced by QLParser#typ.
    def visitTyp(self, ctx:QLParser.TypContext):
        return self.visitChildren(ctx)

del QLParser