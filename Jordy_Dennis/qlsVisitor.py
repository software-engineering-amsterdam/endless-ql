from LexParser.QLSGrammarParser import QLSGrammarParser
from LexParser.QLSGrammarVisitor import QLSGrammarVisitor
from AST import *
from QLS import *
import logging
import sys


class QLSVisitor(QLSGrammarVisitor):
    def __init__(self):
        self.logger = logging.getLogger(__name__)

        def __init__(self):
            self.program = {}
            self.QLAst = QLAst()

            self.logger = logging.getLogger(__name__)

    # Visit a parse tree produced by QLSGrammarParser#stylesheet.
    def visitStylesheet(self, ctx: QLSGrammarParser.StylesheetContext):
        self.logger.debug("STYLESHEET")

        stylesheetName = ctx.ID().getText()
        self.stylesheet = Stylesheet(stylesheetName)

        self.visitChildren(ctx)

    # Visit a parse tree produced by QLSGrammarParser#page.
    def visitPage(self, ctx: QLSGrammarParser.PageContext):
        self.logger.debug("PAGE")

        pageName = ctx.ID().getText()
        page = Page(pageName, self.stylesheet.getNumberOfPages() + 1)

        # defaults
        for default in ctx.default_style():
            defaultObject = self.visit(default)
            page.addDefault(defaultObject)

        # sections
        for section in ctx.section():
            sectionObject = self.visit(section)
            page.addSection(sectionObject)

        self.stylesheet.addPage(page)

    # Visit a parse tree produced by QLSGrammarParser#section.
    def visitSection(self, ctx: QLSGrammarParser.SectionContext):
        self.logger.debug("SECTION")

        sectionName = ctx.STRING().getText()
        section = Section(sectionName)

        # sections
        for sections in ctx.section():
            sectionObject = self.visit(sections)
            section.addSection(sectionObject)

        # questions
        for question in ctx.question():
            questionObject = self.visit(question)
            section.addQuestion(questionObject)

        # defaults
        for default in ctx.default_style():
            defaultObject = self.visit(default)
            section.addDefault(defaultObject)

        return section

    # Visit a parse tree produced by QLSGrammarParser#question.
    def visitQuestion(self, ctx: QLSGrammarParser.QuestionContext):
        self.logger.debug("QUESTION")

        questionName = ctx.ID().getText()
        if (ctx.widget()):
            widget = self.visit(ctx.widget())
        else:
            widget = None
        question = Question(questionName, widget)
        return question

    # Visit a parse tree produced by QLSGrammarParser#widget.
    def visitWidget(self, ctx: QLSGrammarParser.WidgetContext):

        if(ctx.CHECKBOX()):
            return CheckBoxWidget()
        elif ctx.RADIO():
            return RadioWidget([ctx.STRING()[0].getText(), ctx.STRING()[1].getText()])
        elif ctx.SPINBOX():
            return SpinBoxWidget(0, 100000)
        elif ctx.WIDTH():
            return WidgetWidth(ctx.INT().getText())
        elif ctx.FONTSIZE():
            return WidgetFontSize(ctx.INT().getText())
        elif ctx.FONT():
            return WidgetFont(ctx.STRING()[0].getText())



    # Visit a parse tree produced by QLSGrammarParser#default_style.
    def visitDefault_style(self, ctx: QLSGrammarParser.Default_styleContext):
        self.logger.debug("DEFAULT_STYLE")

        default = DefaultStyle()

        for widget in ctx.widget():
            widgetObject = self.visit(widget)
            default.addWidget(widgetObject)

        return default

    # Visit a parse tree produced by QLSGrammarParser#types.
    def visitTypes(self, ctx: QLSGrammarParser.TypesContext):
        self.logger.debug("TYPES")
        return ctx.type().getText()


del QLSGrammarParser
