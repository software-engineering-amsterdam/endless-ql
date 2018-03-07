# Generated from QLSGrammar.g4 by ANTLR 4.7.1
from antlr4 import *
if __name__ is not None and "." in __name__:
    from .QLSGrammarParser import QLSGrammarParser
else:
    from QLSGrammarParser import QLSGrammarParser

# This class defines a complete generic visitor for a parse tree produced by QLSGrammarParser.

class QLSGrammarVisitor(ParseTreeVisitor):

    # Visit a parse tree produced by QLSGrammarParser#stylesheet.
    def visitStylesheet(self, ctx:QLSGrammarParser.StylesheetContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLSGrammarParser#page.
    def visitPage(self, ctx:QLSGrammarParser.PageContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLSGrammarParser#section.
    def visitSection(self, ctx:QLSGrammarParser.SectionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLSGrammarParser#question.
    def visitQuestion(self, ctx:QLSGrammarParser.QuestionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLSGrammarParser#widget.
    def visitWidget(self, ctx:QLSGrammarParser.WidgetContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLSGrammarParser#css.
    def visitCss(self, ctx:QLSGrammarParser.CssContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLSGrammarParser#default_style.
    def visitDefault_style(self, ctx:QLSGrammarParser.Default_styleContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLSGrammarParser#types.
    def visitTypes(self, ctx:QLSGrammarParser.TypesContext):
        return self.visitChildren(ctx)



del QLSGrammarParser