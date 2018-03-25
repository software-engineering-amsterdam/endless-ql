# Generated from grammar/QLS.g4 by ANTLR 4.7.1
from antlr4 import *
from parser_generator.grammar.QLListener import *
from parser_generator.grammar.QLSParser import QLSParser
from PyQt5 import QtWidgets


# This class defines a complete generic visitor for a parse tree produced by QLSParser.
def visit_qls(tree, question_ids, questions):
    """ Traverse the parsed tree """
    walker = QLSVisitor(question_ids, questions)
    walker.visit(tree)
    return [walker.error_message]
    # warning_message = check_duplicate_question_strings(walker.question_ids, walker.questions)
    # return [walker.question_ids, walker.questions, walker.error_message, warning_message]


class QLSVisitor(ParseTreeVisitor):
    def __init__(self, question_ids, questions):
        self.error_message = None
        self.question_ids = question_ids
        self.questions = questions

    # Visit a parse tree produced by QLSParser#stylesheet.
    def visitStylesheet(self, ctx:QLSParser.StylesheetContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLSParser#page.
    def visitPage(self, ctx:QLSParser.PageContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLSParser#section.
    def visitSection(self, ctx:QLSParser.SectionContext):
        section_frame = QtWidgets.QFrame()
        section_layout = QtWidgets.QVBoxLayout()
        section_frame.setLayout(section_layout)
        print(ctx.default())
        result = self.visitChildren(ctx)
        for question in result:
            pass
        return result


    # Visit a parse tree produced by QLSParser#question.
    def visitQuestion(self, ctx:QLSParser.QuestionContext):
        # print(dir(ctx))
        # print(ctx.getText())
        # question = self.questions[ctx.ID().getText()]
        # print(ctx.widget().getText())
        # print()
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLSParser#widget.
    def visitWidget(self, ctx:QLSParser.WidgetContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLSParser#default.
    def visitDefault(self, ctx:QLSParser.DefaultContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLSParser#type.
    def visitType(self, ctx:QLSParser.TypeContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLSParser#attributes.
    def visitAttributes(self, ctx:QLSParser.AttributesContext):
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