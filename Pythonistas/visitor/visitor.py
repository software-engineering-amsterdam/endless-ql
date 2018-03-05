import ast
import antlr4
from processed.antlr.QLListener import *
from processed.antlr.QLParser import QLParser


def visit(tree, ):
    ql = QLListener(tree)
    walker = ParseTreeWalker()
    walker.walk(ql, tree)


class QLListener(ParseTreeListener):
    def __init__(self, output):
        self.output = output

    # Enter a parse tree produced by QLParser#form.
    def enterForm(self, ctx: QLParser.FormContext):
        print('Found form')
        print(ctx.getText())

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
        print(ctx.getText())

    # Exit a parse tree produced by QLParser#statement.
    def exitStatement(self, ctx:QLParser.StatementContext):
        pass


    # Enter a parse tree produced by QLParser#question.
    def enterQuestion(self, ctx:QLParser.QuestionContext):
        print(ctx.getText())

    # Exit a parse tree produced by QLParser#question.
    def exitQuestion(self, ctx:QLParser.QuestionContext):
        pass


    # Enter a parse tree produced by QLParser#assignment.
    def enterAssignment(self, ctx:QLParser.AssignmentContext):
        print(ctx.getText())

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


    # Enter a parse tree produced by QLParser#types.
    def enterTypes(self, ctx:QLParser.TypesContext):
        pass

    # Exit a parse tree produced by QLParser#types.
    def exitTypes(self, ctx:QLParser.TypesContext):
        pass


