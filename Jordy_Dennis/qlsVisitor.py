from LexParser.QLSGrammarParser import QLSGrammarParser
from LexParser.QLSGrammarVisitor import QLSGrammarVisitor
from qlVisitor import mapStringToType, getLiteralValue
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
        page = Page(pageName)

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
        question = Question(questionName, widget, ctx.start.line)
        return question

    # Visit a parse tree produced by QLSGrammarParser#widget.
    def visitWidget(self, ctx: QLSGrammarParser.WidgetContext):
<<<<<<< HEAD
        # Actual widget types: BOOL
=======

>>>>>>> f72660c77cd153702e369fb13784099e68383615
        if(ctx.CHECKBOX()):
            return CheckBoxWidget()
        elif ctx.RADIO():
            return RadioWidget(ctx.STRING()[0].getText(), ctx.STRING()[1].getText())
        elif ctx.DROPDOWN():
            return DropdownWidget()

        # Actual widget types: TEXT/INT
        elif ctx.TEXT():
            return TextWidget()
        elif ctx.SLIDER():
            return SliderWidget(0, 10)
        elif ctx.SPINBOX():
            return SpinboxWidget(0, 10)
        
        # Styling classes
        elif ctx.WIDTH():
            return StyleWidth(ctx.INT().getText())
        elif ctx.FONTSIZE():
            return StyleFontSize(ctx.INT().getText())
        elif ctx.FONT():
<<<<<<< HEAD
            return StyleFont(ctx.STRING()[0].getText())
        elif ctx.COLOR():
            return StyleColor(ctx.HEXCOLOR().getText())
=======
            return WidgetFont(ctx.STRING()[0].getText())
>>>>>>> f72660c77cd153702e369fb13784099e68383615



    # Visit a parse tree produced by QLSGrammarParser#default_style.
    def visitDefault_style(self, ctx: QLSGrammarParser.Default_styleContext):
        self.logger.debug("DEFAULT_STYLE")
        defaultType = self.visit(ctx.types())
        default = DefaultStyle(defaultType, ctx.start.line)
        for widget in ctx.widget():
            widgetObject = self.visit(widget)
            default.addAttribute(widgetObject)

        return default

    # Visit a parse tree produced by QLSGrammarParser#types.
    def visitTypes(self, ctx: QLSGrammarParser.TypesContext):
        self.logger.debug("TYPES")
        return mapStringToType(ctx.getText())


del QLSGrammarParser
