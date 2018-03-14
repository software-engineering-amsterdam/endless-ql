import ast
import antlr4
from parser_generator.grammar.QLListener import *
from parser_generator.grammar.QLParser import QLParser


def visit(tree, outputWindow):
    # print(tree.toStringTree())
    ql = QLVisitor(outputWindow)
    walker = ParseTreeVisitor()
    walker.visit(tree)


class QLVisitor(ParseTreeVisitor):
    def __init__(self, outputWindow):
        self.outputWindow = outputWindow

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
        self.outputWindow.add_question(ctx.getText())
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


    # Visit a parse tree produced by QLParser#arithmetic.
    def visitArithmetic_(self, ctx:QLParser.Arithmetic_Context):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#boolean.
    def visitBoolean_(self, ctx:QLParser.Boolean_Context):
        return self.visitChildren(ctx)