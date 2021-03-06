"""
    Visits the parse tree created by Antlr and create the QLS ast
"""


from Grammar.QLSGrammarParser import QLSGrammarParser
from Grammar.QLSGrammarVisitor import QLSGrammarVisitor
from Visitors.qlVisitor import mapStringToType
from QL import *
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
        sectionName = sectionName.replace("\"", "")
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
        self.isQuestion = True
        question = None
        questionName = ctx.ID().getText()

        # In case there is no default styling, just get the widget, and set your type
        if (ctx.widget()):
            widget = self.visit(ctx.widget())
            question = Question(questionName, widget, widget.getWidget(), ctx.start.line)

        # In case their is a default style, set your type according to the default style
        elif (ctx.default_style()):
            default = self.visit(ctx.default_style())
            question = Question(questionName, default.getWidget(), default.getWidgetType(), ctx.start.line, default)

        self.isQuestion = False
        return question

    # Visit a parse tree produced by QLSGrammarParser#widget.
    def visitWidget(self, ctx: QLSGrammarParser.WidgetContext):
        # Actual widget types: BOOL
        if (ctx.CHECKBOX()):
            return CheckBoxWidget()
        elif ctx.RADIO():
            return RadioWidget(ctx.STRING()[0].getText().replace("\"", ""), ctx.STRING()[1].getText().replace("\"", ""))
        elif ctx.DROPDOWN():
            return DropdownWidget()

        # Actual widget types: TEXT/INT
        elif ctx.TEXT():
            return TextWidget()
        elif ctx.SLIDER():
            return SliderWidget(ctx.INT()[0], ctx.INT()[1])
        elif ctx.SPINBOX():
            return SpinboxWidget(ctx.INT()[0], ctx.INT()[1])

        # Styling classes
        elif ctx.WIDTH():
            return StyleWidth(ctx.INT()[0].getText())
        elif ctx.FONTSIZE():
            return StyleFontSize(ctx.INT()[0].getText())
        elif ctx.FONT():
            font = ctx.STRING()[0].getText()
            font = font.replace("\"", "")
            return StyleFont(font)
        elif ctx.COLOR():
            return StyleColor(ctx.HEXCOLOR().getText())

    # Visit a parse tree produced by QLSGrammarParser#default_style.
    def visitDefault_style(self, ctx: QLSGrammarParser.Default_styleContext):
        self.logger.debug("DEFAULT_STYLE")
        defaultType = self.visit(ctx.types())
        default = DefaultStyle(defaultType, ctx.start.line)
        hasWidget = False

        # Add all attributes to the default style object, and already check if there are no
        # double declarations, we also check if a question has a widget in the default style
        for widget in ctx.widget():
            widgetObject = self.visit(widget)
            if widgetObject.getAttributeType() == 'widget':
                if hasWidget:
                    errorstring = "Double declaration of widget in default style near line " + str(ctx.start.line)
                    throwError(errorstring)
                else:
                    hasWidget = True
                default.setWidgetType(widgetObject.getWidget())
            default.addAttribute(widgetObject)

        if not hasWidget and self.isQuestion:
            errorstring = "Default style missing widget declaration near line " + str(ctx.start.line)
            throwError(errorstring)

        return default

    # Visit a parse tree produced by QLSGrammarParser#types.
    def visitTypes(self, ctx: QLSGrammarParser.TypesContext):
        self.logger.debug("TYPES")
        return mapStringToType(ctx.getText())


del QLSGrammarParser


# Throw an exception without printing the python stacktrace
def throwError(text):
    print("QLS Interpreter error:")
    print(text)
    sys.exit(1)
