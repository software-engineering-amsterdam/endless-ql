# Generated from QL.g4 by ANTLR 4.7
from antlr4 import *
from QLast import *

# This class defines a complete generic visitor for a parse tree produced by QLParser.

class QLVisitor(ParseTreeVisitor):

    # Visit a parse tree produced by QLParser#form.
    def visitForm(self, ctx):
        form_id = ctx.form_id().getText()
        node = formNode(form_id)
        print(node)
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#block.
    def visitBlock(self, ctx):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#statement.
    def visitStatement(self, ctx):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#assignment.
    def visitAssignment(self, ctx):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#question.
    def visitQuestion(self, ctx):
        question = ctx.STR().getText()
        var = ctx.var().getText()
        vartype = ctx.vartype().getText()
        node = questionNode(question, var, vartype)
        print(node)
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#conditional.
    def visitConditional(self, ctx):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#expression.
    def visitExpression(self, ctx):
        # Todo: Make distinction between expressions
        # expression = ctx.expression()
        # if (expression)
            # print(ctx.expression().getText())
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#form_id.
    def visitForm_id(self, ctx):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#var.
    def visitVar(self, ctx):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#vartype.
    def visitVartype(self, ctx):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#if_cond.
    def visitIf_cond(self, ctx):
        expression = ctx.expression().getText()
        node = ifNode(expression)
        print(node)
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#elif_cond.
    def visitElif_cond(self, ctx):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#else_cond.
    def visitElse_cond(self, ctx):
        return self.visitChildren(ctx)
