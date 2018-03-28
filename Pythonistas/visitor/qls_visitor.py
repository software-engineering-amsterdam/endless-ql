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
        return {}

    def visitChildren(self, node):
        result = self.defaultResult()
        n = node.getChildCount()
        for i in range(n):
            if not self.shouldVisitNextChild(node, result):
                return
            c = node.getChild(i)
            # child.accept() calls the visit%type function from the QLVisitor class; form.accept() returns visitForm()
            child_result = c.accept(self)
            for key in child_result:
                if key in result:
                    result[key].extend(child_result[key])
                else:
                    result[key] = child_result[key]
        return result


    def visitStylesheet(self, ctx:QLSParser.StylesheetContext):
        return self.visitChildren(ctx)


    def visitPage(self, ctx:QLSParser.PageContext):
        result = self.visitChildren(ctx)
        if 'questions' in result:
            question_ids = result['questions']
            if 'defaults' in result:
                default_attributes = result['defaults']

                for id in question_ids:
                    question = self.questions[id]
                    question.set_attributes(default_attributes)

            result = {'questions': question_ids}
        else:
            result = self.defaultResult()
        return result


    def visitSection(self, ctx:QLSParser.SectionContext):
        result = self.visitChildren(ctx)
        if 'questions' in result:
            question_ids = result['questions']
            if 'defaults' in result:
                default_attributes = result['defaults']

                for id in question_ids:
                    question = self.questions[id]
                    question.set_attributes(default_attributes)

            result = {'questions': question_ids}
        else:
            result = self.defaultResult()
        return result


    def visitQuestion(self, ctx:QLSParser.QuestionContext):
        if not ctx.ID().getText() in self.questions:
            self.error_message = "Error: undefined reference to QL ID"
            return {}  # todo: break out of visit properly
        question_id = ctx.ID().getText()
        question = self.questions[ctx.ID().getText()]

        attributes = self.visitChildren(ctx)
        question.set_attributes(attributes['attributes'])

        result = {'questions': [question_id]}
        return result


    def visitWidget(self, ctx:QLSParser.WidgetContext):
        return self.visitChildren(ctx)


    def visitDefault(self, ctx:QLSParser.DefaultContext):
        attributes = self.visitChildren(ctx)
        result = {'defaults': attributes['attributes']} # todo: fix
        return result


    def visitType(self, ctx:QLSParser.TypeContext):
        return self.visitChildren(ctx)


    def visitAttributes(self, ctx:QLSParser.AttributesContext):
        attributes = self.visitChildren(ctx)
        result = {'attributes': attributes}
        return result


    def visitWidth(self, ctx:QLSParser.WidthContext):
        result = self.visitChildren(ctx)
        if ctx.INT():
            result.update({'width':int(ctx.INT().getText())})
        return result

    def visitFont(self, ctx:QLSParser.FontContext):
        result = self.visitChildren(ctx)
        if ctx.STRING():
            result.update({'font':ctx.STRING().getText()})
        return result

    def visitFontsize(self, ctx:QLSParser.FontsizeContext):
        result = self.visitChildren(ctx)
        if ctx.INT():
            result.update({'font_size':int(ctx.INT().getText())})
        return result

    def visitColor(self, ctx:QLSParser.ColorContext):
        result = self.visitChildren(ctx)
        if ctx.HEX():
            result.update({'color':ctx.HEX().getText()})
        return result


    def visitCheckbox(self, ctx:QLSParser.CheckboxContext):
        return self.visitChildren(ctx)

    def visitRadio(self, ctx:QLSParser.RadioContext):
        return self.visitChildren(ctx)

    def visitSpinbox(self, ctx:QLSParser.SpinboxContext):
        return self.visitChildren(ctx)

    def visitChoices(self, ctx:QLSParser.ChoicesContext):
        return self.visitChildren(ctx)