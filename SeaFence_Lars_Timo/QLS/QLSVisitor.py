# Generated from QLS.g4 by ANTLR 4.7
from antlr4 import *

# This class defines a complete generic visitor for a parse tree produced by QLSParser.

class QLSVisitor(ParseTreeVisitor):

    # Visit a parse tree produced by QLSParser#stylesheet.
    def visitStylesheet(self, ctx):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLSParser#page.
    def visitPage(self, ctx):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLSParser#section.
    def visitSection(self, ctx):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLSParser#question.
    def visitQuestion(self, ctx):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLSParser#default_style.
    def visitDefault_style(self, ctx):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLSParser#default_options.
    def visitDefault_options(self, ctx):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLSParser#stylesheet_id.
    def visitStylesheet_id(self, ctx):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLSParser#page_id.
    def visitPage_id(self, ctx):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLSParser#section_id.
    def visitSection_id(self, ctx):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLSParser#widget.
    def visitWidget(self, ctx):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLSParser#widget_type.
    def visitWidget_type(self, ctx):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLSParser#width.
    def visitWidth(self, ctx):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLSParser#font.
    def visitFont(self, ctx):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLSParser#fontsize.
    def visitFontsize(self, ctx):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLSParser#color.
    def visitColor(self, ctx):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLSParser#var.
    def visitVar(self, ctx):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLSParser#vartype.
    def visitVartype(self, ctx):
        return self.visitChildren(ctx)


