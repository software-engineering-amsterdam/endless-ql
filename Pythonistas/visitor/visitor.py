import ast
import antlr4
from parser_generator.grammar.QLListener import *
from parser_generator.grammar.QLParser import QLParser

from PyQt5 import QtWidgets
from PyQt5 import QtCore
from gui import question_classes

def visit(tree):
    ql = QLVisitor()
    walker = ParseTreeVisitor()
    walker.visit(tree)
    warning_message = check_duplicate_question_strings(ql.questionIDs, ql.questions)
    return [ql.questionIDs, ql.questions, ql.error_message, warning_message]



def check_duplicate_question_strings(questionIDs, questions):
    question_list = []
    warning_string = None
    # Compiles a list of all question strings
    for ID in questionIDs:
        question = questions[ID]
        question_list.append(question.question)

    duplicates = set([duplicate for duplicate in question_list if question_list.count(duplicate) > 1])
    if len(duplicates) > 0:
        warning_string = "Warning: duplicate questions:{}".format(str(duplicates)[1:-1])
    return warning_string


# todo: move to appropriate place
class MyTreeVisitor(ParseTreeVisitor):
    # Class overwrite, to be edited for our own use
    def visitChildren(self, node):
        result = self.defaultResult()
        n = node.getChildCount()
        for i in range(n):
            if not self.shouldVisitNextChild(node, result):
                return

            c = node.getChild(i)
            childResult = c.accept(self)
            result = self.aggregateResult(result, childResult)

        return result


class QLVisitor(ParseTreeVisitor):
    def __init__(self):
        self.error_message = None
        self.questionIDs = [] # Ordered list of question IDs.
        self.questions = {}  # Dictionary with question objects as values, IDs as keys

    # Visit a parse tree produced by QLParser#form.
    def visitForm(self, ctx:QLParser.FormContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#block.
    def visitBlock(self, ctx:QLParser.BlockContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#stmt.
    def visitStmt(self, ctx:QLParser.StmtContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#question.
    def visitQuestion(self, ctx:QLParser.QuestionContext):
        # Gets necessary information from the node
        question = ctx.STRING().getText()
        questionID = ctx.ID().getText()
        data_type = ctx.type().getText()

        if questionID in self.questionIDs:
            self.error_message = "Error: duplicate question IDs: {}".format(questionID)
            return

        # todo: remove instanceof
        if data_type == 'boolean':
            question_object = question_classes.BooleanQuestion(questionID, question)
            # todo: make flexible
            choices = ['Yes','No']

            # todo: move to question_classes
            truebutton = QtWidgets.QRadioButton(choices[0])
            truebutton.pressed.connect(question_object.set_answer_true)
            question_object.set_truebutton(truebutton)

            falsebutton = QtWidgets.QRadioButton(choices[1])
            falsebutton.pressed.connect(question_object.set_answer_false)
            question_object.set_falsebutton(falsebutton)

        elif data_type == 'money':
            question_object = question_classes.MoneyQuestion(questionID, question)

        else:
            self.error_message = "Error: unknown data_type: {}".format(data_type)
            return

        self.questionIDs.append(questionID)
        self.questions[questionID] = question_object
        # self.outputWindow.add_question(ctx.getText())
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#declaration.
    def visitDeclaration(self, ctx:QLParser.DeclarationContext):
        declared_value = QtWidgets.QLabel(ctx.value().getText())
        self.questions[ctx.parentCtx.ID().getText()].text_input_box = declared_value
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#expression.
    def visitExpression(self, ctx:QLParser.ExpressionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#if_.
    def visitIf_(self, ctx:QLParser.If_Context):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#type.
    def visitType(self, ctx:QLParser.TypeContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#value.
    def visitValue(self, ctx:QLParser.ValueContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#compute.
    def visitCompute(self, ctx:QLParser.ComputeContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#arithmetic.
    def visitArithmetic_(self, ctx:QLParser.Arithmetic_Context):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by QLParser#boolean.
    def visitBoolean_(self, ctx:QLParser.Boolean_Context):
        return self.visitChildren(ctx)

