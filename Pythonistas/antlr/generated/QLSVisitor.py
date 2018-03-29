# Generated from /Users/Dylan/PycharmProjects/endless-ql/Pythonistas/antlr/grammar/QLS.g4 by ANTLR 4.7.1
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


    # Visit a parse tree produced by QLSParser#default.
    def visitDefault(self, ctx:QLSParser.DefaultContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLSParser#attributes.
    def visitAttributes(self, ctx:QLSParser.AttributesContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLSParser#type.
    def visitType(self, ctx:QLSParser.TypeContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLSParser#widget.
    def visitWidget(self, ctx:QLSParser.WidgetContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLSParser#width.
    def visitWidth(self, ctx:QLSParser.WidthContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLSParser#font.
    def visitFont(self, ctx:QLSParser.FontContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLSParser#fontsize.
    def visitFontsize(self, ctx:QLSParser.FontsizeContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLSParser#color.
    def visitColor(self, ctx:QLSParser.ColorContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLSParser#checkbox.
    def visitCheckbox(self, ctx:QLSParser.CheckboxContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLSParser#radio.
    def visitRadio(self, ctx:QLSParser.RadioContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLSParser#spinbox.
    def visitSpinbox(self, ctx:QLSParser.SpinboxContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLSParser#choices.
    def visitChoices(self, ctx:QLSParser.ChoicesContext):
        return self.visitChildren(ctx)



del QLSParser