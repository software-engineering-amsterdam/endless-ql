import ast
import antlr4
from parser_generator.grammar.QLListener import *
from parser_generator.grammar.QLParser import QLParser


def visit(tree, outputWindow):
    ql = QLListener(outputWindow)
    walker = ParseTreeWalker()
    walker.walk(ql, tree)


class QLListener(ParseTreeListener):
    def __init__(self, outputWindow):
        self.outputWindow = outputWindow

    # Enter a parse tree produced by QLParser#form.
    def enterForm(self, ctx: QLParser.FormContext):
        print('Found form')
        print(ctx.getText())
        pass

    # Exit a parse tree produced by QLParser#form.
    def exitForm(self, ctx:QLParser.FormContext):
        pass


    # Enter a parse tree produced by QLParser#block.
    def enterBlock(self, ctx:QLParser.BlockContext):
        pass

    # Exit a parse tree produced by QLParser#block.
    def exitBlock(self, ctx:QLParser.BlockContext):
        pass


    # Enter a parse tree produced by QLParser#statement.
    def enterStatement(self, ctx:QLParser.StatementContext):
        # print(ctx.getText())
        pass

    # Exit a parse tree produced by QLParser#statement.
    def exitStatement(self, ctx:QLParser.StatementContext):
        pass


    # Enter a parse tree produced by QLParser#question.
    def enterQuestion(self, ctx:QLParser.QuestionContext):
        print('Found Question')
        print(ctx.getText())
        self.outputWindow.add_question(ctx.getText())

    # Exit a parse tree produced by QLParser#question.
    def exitQuestion(self, ctx:QLParser.QuestionContext):
        pass


    # Enter a parse tree produced by QLParser#assignment.
    def enterAssignment(self, ctx:QLParser.AssignmentContext):
        pass

    # Exit a parse tree produced by QLParser#assignment.
    def exitAssignment(self, ctx:QLParser.AssignmentContext):
        pass


    # Enter a parse tree produced by QLParser#expression.
    def enterExpression(self, ctx:QLParser.ExpressionContext):
        pass

    # Exit a parse tree produced by QLParser#expression.
    def exitExpression(self, ctx:QLParser.ExpressionContext):
        pass


    # Enter a parse tree produced by QLParser#conditional.
    def enterConditional(self, ctx:QLParser.ConditionalContext):
        pass

    # Exit a parse tree produced by QLParser#conditional.
    def exitConditional(self, ctx:QLParser.ConditionalContext):
        pass


    # Enter a parse tree produced by QLParser#if_conditional.
    def enterIf_conditional(self, ctx:QLParser.If_conditionalContext):
        pass

    # Exit a parse tree produced by QLParser#if_conditional.
    def exitIf_conditional(self, ctx:QLParser.If_conditionalContext):
        pass


    # Enter a parse tree produced by QLParser#else_conditional.
    def enterElse_conditional(self, ctx:QLParser.Else_conditionalContext):
        pass

    # Exit a parse tree produced by QLParser#else_conditional.
    def exitElse_conditional(self, ctx:QLParser.Else_conditionalContext):
        pass


    # Enter a parse tree produced by QLParser#typeDeclaration.
    def enterTypeDeclaration(self, ctx:QLParser.TypeDeclarationContext):
        pass

    # Exit a parse tree produced by QLParser#typeDeclaration.
    def exitTypeDeclaration(self, ctx:QLParser.TypeDeclarationContext):
        pass



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

