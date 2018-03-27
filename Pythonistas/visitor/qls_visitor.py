# Generated from grammar/QLS.g4 by ANTLR 4.7.1
from antlr4 import *
from parser_generator.grammar.QLListener import *
from parser_generator.grammar.QLSParser import QLSParser
from PyQt5 import QtWidgets


# This class defines a complete generic visitor for a parse tree produced by QLSParser.
def visit_qls(tree, question_ids, questions):
    """ Traverse the parsed tree """
    walker = QLSVisitor(question_ids, questions)
    page_frames = walker.visit(tree)
    return [walker.error_message, page_frames]
    # warning_message = check_duplicate_question_strings(walker.question_ids, walker.questions)
    # return [walker.question_ids, walker.questions, walker.error_message, warning_message]


class QLSVisitor(ParseTreeVisitor):
    def __init__(self, question_ids, questions):
        self.error_message = None
        self.question_ids = question_ids
        self.questions = questions

    def defaultResult(self):
        return []

    def visitChildren(self, node):
        result = self.defaultResult()
        n = node.getChildCount()
        for i in range(n):
            if not self.shouldVisitNextChild(node, result):
                return
            c = node.getChild(i)
            # child.accept() calls the visit%type function from the QLVisitor class; form.accept() returns visitForm()
            child_result = c.accept(self)
            result.extend(child_result)
        return result


    def visitStylesheet(self, ctx:QLSParser.StylesheetContext):
        return self.visitChildren(ctx)


    def visitPage(self, ctx:QLSParser.PageContext):
        page_frame = QtWidgets.QFrame()
        page_layout = QtWidgets.QVBoxLayout()
        page_frame.setLayout(page_layout)
        result = self.visitChildren(ctx)

        for section_widget in result:
            page_layout.addWidget(section_widget)

        result.extend([page_frame])
        return result


    def visitSection(self, ctx:QLSParser.SectionContext):
        section_frame = QtWidgets.QFrame()
        section_layout = QtWidgets.QVBoxLayout()
        section_frame.setLayout(section_layout)
        if ctx.default():
            print(ctx.default().getText())

        result = self.visitChildren(ctx)
        for child_widget in result:
            section_layout.addWidget(child_widget)

        result.extend([section_frame])
        return result


    def visitQuestion(self, ctx:QLSParser.QuestionContext):
        if not ctx.ID().getText() in self.questions:
            self.error_message = "Error: undefined reference to QL ID"
            return None
        question = self.questions[ctx.ID().getText()]

        # todo: alter questions based on specifications

        question_frame = question.create_frame()
        result = self.visitChildren(ctx)
        result.extend([question_frame])
        return result


    def visitWidget(self, ctx:QLSParser.WidgetContext):
        return self.visitChildren(ctx)


    def visitDefault(self, ctx:QLSParser.DefaultContext):
        print(dir(ctx))
        print(ctx.getText())
        if ctx.attributes():
            print(ctx.attributes().getText())
        return self.visitChildren(ctx)


    def visitType(self, ctx:QLSParser.TypeContext):
        return self.visitChildren(ctx)


    def visitAttributes(self, ctx:QLSParser.AttributesContext):
        return self.visitChildren(ctx)


    def visitWidth(self, ctx:QLSParser.WidthContext):
        return self.visitChildren(ctx)


    def visitFont(self, ctx:QLSParser.FontContext):
        return self.visitChildren(ctx)


    def visitFontsize(self, ctx:QLSParser.FontsizeContext):
        return self.visitChildren(ctx)


    def visitColor(self, ctx:QLSParser.ColorContext):
        return self.visitChildren(ctx)


    def visitCheckbox(self, ctx:QLSParser.CheckboxContext):
        return self.visitChildren(ctx)


    def visitRadio(self, ctx:QLSParser.RadioContext):
        return self.visitChildren(ctx)


    def visitSpinbox(self, ctx:QLSParser.SpinboxContext):
        return self.visitChildren(ctx)


    def visitChoices(self, ctx:QLSParser.ChoicesContext):
        return self.visitChildren(ctx)