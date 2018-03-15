# Generated from grammar/QLS.g4 by ANTLR 4.7.1
from antlr4 import *
if __name__ is not None and "." in __name__:
    from .QLSParser import QLSParser
else:
    from QLSParser import QLSParser

# This class defines a complete generic visitor for a parse tree produced by QLSParser.

class QLSVisitor(ParseTreeVisitor):

    # Visit a parse tree produced by QLSParser#stylesheet.
    def visitStylesheet(self, ctx:QLSParser.StylesheetContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLSParser#page.
    def visitPage(self, ctx:QLSParser.PageContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLSParser#section.
    def visitSection(self, ctx:QLSParser.SectionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLSParser#question.
    def visitQuestion(self, ctx:QLSParser.QuestionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLSParser#widget.
    def visitWidget(self, ctx:QLSParser.WidgetContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLSParser#checkbox.
    def visitCheckbox(self, ctx:QLSParser.CheckboxContext):
        return self.visitChildren(ctx)



del QLSParser