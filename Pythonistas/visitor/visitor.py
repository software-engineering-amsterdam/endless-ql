import ast
import antlr4
from parser_generator.grammar.QLListener import *
from parser_generator.grammar.QLParser import QLParser


def listen(tree, outputWindow):
    ql = QLListener(outputWindow)
    walker = ParseTreeWalker()
    walker.walk(ql, tree)


def visit(tree, outputWindow):
    print(tree.toStringTree())
    ql = QLVisitor(outputWindow)
    walker = ParseTreeVisitor()
    walker.visit(tree)


class QLListener(ParseTreeListener):
    def __init__(self, outputWindow):
        self.outputWindow = outputWindow

    # Enter a parse tree produced by QLParser#form.
    def enterForm(self, ctx:QLParser.FormContext):
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


    # Enter a parse tree produced by QLParser#question.
    def enterQuestion(self, ctx:QLParser.QuestionContext):
        print('Found Question')
        print(ctx.getText())
        self.outputWindow.add_question(ctx.getText())

    # Exit a parse tree produced by QLParser#question.
    def exitQuestion(self, ctx:QLParser.QuestionContext):
        pass

    # Enter a parse tree produced by QLParser#stmt.
    def enterStmt(self, ctx: QLParser.StmtContext):
        pass

    # Exit a parse tree produced by QLParser#stmt.
    def exitStmt(self, ctx: QLParser.StmtContext):
        pass

    # Enter a parse tree produced by QLParser#declaration.
    def enterDeclaration(self, ctx: QLParser.DeclarationContext):
        print('Found Declaration')
        print(ctx.getText())

    # Exit a parse tree produced by QLParser#declaration.
    def exitDeclaration(self, ctx: QLParser.DeclarationContext):
        pass

    # Enter a parse tree produced by QLParser#type_declaration.
    def enterType_declaration(self, ctx: QLParser.Type_declarationContext):
        print('Found Type_declaration')
        print(ctx.getText())

    # Exit a parse tree produced by QLParser#type_declaration.
    def exitType_declaration(self, ctx: QLParser.Type_declarationContext):
        pass

    # Enter a parse tree produced by QLParser#if_conditional.
    def enterIf_conditional(self, ctx:QLParser.If_conditionalContext):
        print('Found if')
        print(ctx.getText())

    # Exit a parse tree produced by QLParser#if_conditional.
    def exitIf_conditional(self, ctx:QLParser.If_conditionalContext):
        pass

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
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#if_conditional.
    def visitIf_conditional(self, ctx:QLParser.If_conditionalContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#type_declaration.
    def visitType_declaration(self, ctx:QLParser.Type_declarationContext):
        return self.visitChildren(ctx)