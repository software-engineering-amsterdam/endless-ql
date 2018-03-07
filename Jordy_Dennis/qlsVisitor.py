from LexParser.QLSGrammarParser import QLSGrammarParser
from LexParser.QLSGrammarVisitor import QLSGrammarVisitor
from AST import *
import logging
import sys


class QLSVisitor(QLSGrammarVisitor):
    def __init__(self):
        self.logger = logging.getLogger(__name__)

    # Visit a parse tree produced by QLSGrammarParser#stylesheet.
    def visitStylesheet(self, ctx: QLSGrammarParser.StylesheetContext):
        self.logger.debug("STYLESHEET")
        print("HIER")
        return self.visitChildren(ctx)

    # Visit a parse tree produced by QLSGrammarParser#page.
    def visitPage(self, ctx: QLSGrammarParser.PageContext):
        self.logger.debug("PAGE")
        return self.visitChildren(ctx)

    # Visit a parse tree produced by QLSGrammarParser#section.
    def visitSection(self, ctx: QLSGrammarParser.SectionContext):
        self.logger.debug("SECTION")
        return self.visitChildren(ctx)

    # Visit a parse tree produced by QLSGrammarParser#question.
    def visitQuestion(self, ctx: QLSGrammarParser.QuestionContext):
        self.logger.debug("QUESTION")
        return self.visitChildren(ctx)

    # Visit a parse tree produced by QLSGrammarParser#widget.
    def visitWidget(self, ctx: QLSGrammarParser.WidgetContext):
        return self.visitChildren(ctx)

    # Visit a parse tree produced by QLSGrammarParser#css.
    def visitCss(self, ctx: QLSGrammarParser.CssContext):
        self.logger.debug("CSS")
        return self.visitChildren(ctx)

    # Visit a parse tree produced by QLSGrammarParser#default_style.
    def visitDefault_style(self, ctx: QLSGrammarParser.Default_styleContext):
        self.logger.debug("DEFAULT_STYLE")
        return self.visitChildren(ctx)

    # Visit a parse tree produced by QLSGrammarParser#types.
    def visitTypes(self, ctx: QLSGrammarParser.TypesContext):
        self.logger.debug("TYPES")
        return self.visitChildren(ctx)


del QLSGrammarParser
