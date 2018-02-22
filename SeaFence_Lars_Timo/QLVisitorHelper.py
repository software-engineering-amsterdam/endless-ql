from antlr_files_2.QLVisitor import QLVisitor
from antlr4 import *
from QLast import *

# This class defines a complete generic visitor for a parse tree produced by QLParser.

class QLVisitorHelper(QLVisitor):

    # Visit a parse tree produced by QLParser#form.
    def visitForm(self, ctx):
        form_id = ctx.form_id().getText()
        form = FormNode(form_id)
        block = ctx.block()
        statements = block.statement()
        for statement in statements:
            node = self.visit(statement)
            if (statement.conditional() and node != None):
                form.statements.extend(node)
            elif (node != None):
                form.statements.append(node)

        print(form)
        return


    # Visit a parse tree produced by QLParser#assignment.
    def visitAssignment(self, ctx):
        name = ctx.STR().getText()
        var = ctx.var().getText()
        vartype = ctx.vartype().getText()
        expression = self.visit(ctx.expression())
        node = AssignmentNode(name, var, vartype, expression)
        # print(node)
        return node
        # return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#question.
    def visitQuestion(self, ctx):
        question = ctx.STR().getText()
        var = ctx.var().getText()
        vartype = ctx.vartype().getText()
        node = QuestionNode(question, var, vartype)
        # print(node)
        return node
        # return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#conditional.
    def visitConditional(self, ctx):
        conditionals = []

        if_condition_node = self.visit(ctx.if_cond())
        conditionals.append(if_condition_node)

        if (ctx.elif_cond()):
            for condition in ctx.elif_cond():
                elif_condition_node = self.visit(condition)
                conditionals.append(elif_condition_node)

        if (ctx.else_cond()):
            else_condition_node = self.visit(ctx.else_cond())
            conditionals.append(else_condition_node)

        return conditionals


    # Visit a parse tree produced by QLParser#expression.
    def visitExpression(self, ctx):

        if (ctx.left and ctx.right):
            if (ctx.COMPARER()):
                binop = ctx.COMPARER().getText()
            elif (ctx.DIVMULOPERATOR()):
                binop = ctx.DIVMULOPERATOR().getText()
            elif (ctx.ADDSUBOPERATOR()):
                binop = ctx.ADDSUBOPERATOR().getText()
            elif (ctx.AND()):
                binop = ctx.AND().getText()
            elif (ctx.OR()):
                binop = ctx.OR().getText()
            left_node = self.visit(ctx.left)
            right_node = self.visit(ctx.right)
            return BinOpNode(left_node, binop, right_node)

        elif (ctx.var()):
            var = ctx.var().getText()
            # print var
            return UnOpNode(var)

        return


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
        expression = self.visit(ctx.expression())
        if_node = IfNode(expression)
        block = ctx.block()
        statements = block.statement()
        for statement in statements:
            node = self.visit(statement)
            if (node != None):
                # print(node)
                if_node.statements.append(node)
        # print(node)
        return if_node


    # Visit a parse tree produced by QLParser#elif_cond.
    def visitElif_cond(self, ctx):
        expression = self.visit(ctx.expression())
        elif_node = ElifNode(expression)
        block = ctx.block()
        statements = block.statement()
        for statement in statements:
            node = self.visit(statement)
            if (node != None):
                # print(node)
                elif_node.statements.append(node)
        return elif_node


    # Visit a parse tree produced by QLParser#else_cond.
    def visitElse_cond(self, ctx):
        else_node = ElseNode()
        block = ctx.block()
        statements = block.statement()
        for statement in statements:
            node = self.visit(statement)
            if (node != None):
                # print(node)
                else_node.statements.append(node)
        # print(node)
        return else_node