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


    # Visit a parse tree produced by QLParser#stmt.
    def visitStmt(self, ctx:QLParser.StmtContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#question.
    def visitQuestion(self, ctx:QLParser.QuestionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#declaration.
    def visitDeclaration(self, ctx:QLParser.DeclarationContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#expression.
    def visitExpression(self, ctx:QLParser.ExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#if_.
    def visitIf_(self, ctx:QLParser.If_Context):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#type.
    def visitType(self, ctx:QLParser.TypeContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#value.
    def visitValue(self, ctx:QLParser.ValueContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#compute.
    def visitCompute(self, ctx:QLParser.ComputeContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#arithmetic_.
    def visitArithmetic_(self, ctx:QLParser.Arithmetic_Context):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#boolean_.
    def visitBoolean_(self, ctx:QLParser.Boolean_Context):
        return self.visitChildren(ctx)



del QLParser