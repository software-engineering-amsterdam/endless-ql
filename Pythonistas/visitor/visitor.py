import ast
import antlr4
from parser_generator.grammar.QLListener import *
from parser_generator.grammar.QLParser import QLParser

from PyQt5 import QtWidgets
from PyQt5 import QtCore
from gui import question_classes

def visit(tree):
    walker = QLVisitor()
    walker.visit(tree)
    warning_message = check_duplicate_question_strings(walker.questionIDs, walker.questions)
    return [walker.questionIDs, walker.questions, walker.error_message, warning_message]


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


class QLVisitor(ParseTreeVisitor):
    def __init__(self):
        self.error_message = None
        self.questionIDs = [] # Ordered list of question IDs.
        self.questions = {}  # Dictionary with question objects as values, IDs as keys

    # def visitChildren(self, node):
    #     result = self.defaultResult()
    #     n = node.getChildCount()
    #     for i in range(n):
    #         if not self.shouldVisitNextChild(node, result):
    #             return
    #
    #         c = node.getChild(i)
    #         # child.accept() calls the visit%type function from the QLVisitor class; form.accept() returns visitForm()
    #         childResult = c.accept(self)
    #         result = self.aggregateResult(result, childResult)
    #
    #     return result

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
        # todo: give node as input to Question?
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
        # Gets the question IDs of the if argument and the question contained in the if, then links them so that the
        # contained question becomes invisible when the argument becomes False.

        # Picks out the ID of the question that is the argument of the if
        conditionalID = ctx.expression().getText()

        # If the ID of the question that is the argument of the if does not exist, throws an error
        if conditionalID not in self.questionIDs:
            self.error_message = "Error: if argument is undefined: {}".format(conditionalID)
            return
        elif self.questions[conditionalID].get_data_type() != 'boolean':
            self.error_message = "Error: if argument is not boolean: {}".format(conditionalID)
            return

        conditional_question = self.questions[conditionalID]

        result = self.visitChildren(ctx)

        questions_in_if = ctx.block()

        for statement in questions_in_if.stmt():
            ifquestionID = statement.question().ID().getText()

            question_in_if = self.questions[ifquestionID]  # todo: use return to get answer
            conditional_question.add_if_question(question_in_if)

        return result


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
