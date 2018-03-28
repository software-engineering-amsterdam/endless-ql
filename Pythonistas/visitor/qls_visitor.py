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

        children = self.visitChildren(ctx)
        for child_widget in children:
            section_layout.addWidget(child_widget)

        result = [section_frame]
        return result


    def visitQuestion(self, ctx:QLSParser.QuestionContext):
        if not ctx.ID().getText() in self.questions:
            self.error_message = "Error: undefined reference to QL ID"
            return None  # todo: break out of visit properly
        question = self.questions[ctx.ID().getText()]

        # todo: alter questions based on specifications

        attributes = self.visitChildren(ctx)
        if attributes:
            print(attributes)
            question.set_attributes(attributes)

        result = self.defaultResult()
        return result


    def visitWidget(self, ctx:QLSParser.WidgetContext):
        return self.visitChildren(ctx)


    def visitDefault(self, ctx:QLSParser.DefaultContext):
        result = self.visitChildren(ctx)
        return result


    def visitType(self, ctx:QLSParser.TypeContext):
        return self.visitChildren(ctx)


    def visitAttributes(self, ctx:QLSParser.AttributesContext):
        result = self.visitChildren(ctx)
        final_dict = {}
        for dict in result:
            final_dict.update(dict)
        result = final_dict
        print(final_dict)
        return result


    def visitWidth(self, ctx:QLSParser.WidthContext):
        result = self.visitChildren(ctx)
        if ctx.INT():
            result.extend([{'width':int(ctx.INT().getText())}])
        return result

    def visitFont(self, ctx:QLSParser.FontContext):
        result = self.visitChildren(ctx)
        if ctx.STRING():
            result.extend([{'font':ctx.STRING().getText()}])
        return result

    def visitFontsize(self, ctx:QLSParser.FontsizeContext):
        result = self.visitChildren(ctx)
        if ctx.INT():
            result.extend([{'fontsize':int(ctx.INT().getText())}])
        return result

    def visitColor(self, ctx:QLSParser.ColorContext):
        result = self.visitChildren(ctx)
        if ctx.HEX():
            result.extend([{'color':ctx.HEX().getText()}])
        return result


    def visitCheckbox(self, ctx:QLSParser.CheckboxContext):
        return self.visitChildren(ctx)

    def visitRadio(self, ctx:QLSParser.RadioContext):
        return self.visitChildren(ctx)

    def visitSpinbox(self, ctx:QLSParser.SpinboxContext):
        return self.visitChildren(ctx)

    def visitChoices(self, ctx:QLSParser.ChoicesContext):
        return self.visitChildren(ctx)