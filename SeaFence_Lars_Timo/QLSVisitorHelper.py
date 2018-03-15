from QLS.QLSVisitor import QLSVisitor
from antlr4 import *
from QLSast import *

# This class defines a complete generic visitor for a parse tree produced by QLSParser.

class QLSVisitorHelper(QLSVisitor):

    # Visit a parse tree produced by QLSParser#stylesheet.
    def visitStylesheet(self, ctx):
        stylesheet_id = ctx.stylesheet_id().getText()
        stylesheet = StylesheetNode(stylesheet_id)

        for page in ctx.page():
            page_node = self.visit(page)

        return stylesheet


    # Visit a parse tree produced by QLSParser#page.
    def visitPage(self, ctx):
        page_node = PageNode(ctx.page_id().getText())

        for section in ctx.section():
            section_node = self.visit(section)

        return page_node

    # Visit a parse tree produced by QLSParser#section.
    def visitSection(self, ctx):
        section_node = SectionNode(ctx.section_id().getText())

        if ctx.section():
            for section in ctx.section():
                child_section_node = self.visit(section)

        if ctx.question():
            for question in ctx.question():
                question_node = self.visit(question)

        return section_node
        

    # Visit a parse tree produced by QLSParser#question.
    def visitQuestion(self, ctx):
        var = ctx.var().getText()

        question_node = QuestionNode(var)

        if ctx.widget():
            widget_node = self.visit(ctx.widget())
            question_node.widget = widget_node

        return question_node


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
        widget = ctx.widget_type().getText()
        widget_node = WidgetNode(widget)
        return widget_node


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


