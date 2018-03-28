import ast
import antlr4
from parser_generator.grammar.QLListener import *
from parser_generator.grammar.QLParser import QLParser

from PyQt5 import QtWidgets
from PyQt5 import QtCore
from gui import question_classes


def visit(tree):
    """ Traverse the parsed tree """
    walker = QLVisitor()
    walker.visit(tree)
    warning_message = check_duplicate_question_strings(walker.question_ids, walker.questions)
    return [walker.question_ids, walker.questions, walker.error_message, warning_message]


def check_duplicate_question_strings(question_ids, questions):
    """ returns warning if duplicate question strings """
    question_list = []
    warning_string = None

    for question_id in question_ids:
        question = questions[question_id]
        question_list.append(question.question_string)

    duplicates = set([duplicate for duplicate in question_list if question_list.count(duplicate) > 1])
    if len(duplicates) > 0:
        warning_string = "Warning: duplicate questions:{}".format(str(duplicates)[1:-1])
    return warning_string


class QLVisitor(ParseTreeVisitor):
    def __init__(self):
        self.error_message = None
        self.question_ids = []  # Ordered list of question IDs.
        self.questions = {}  # {question_id: question object}

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
            # result = self.aggregateResult(result, childResult)
            result.extend(child_result)

        return result

    def visitForm(self, ctx: QLParser.FormContext):
        return self.visitChildren(ctx)

    def visitBlock(self, ctx: QLParser.BlockContext):
        return self.visitChildren(ctx)

    def visitStmt(self, ctx: QLParser.StmtContext):
        return self.visitChildren(ctx)

    def visitQuestion(self, ctx: QLParser.QuestionContext):
        # Gets necessary information from the node
        # todo: give node as input to Question?
        question_string = ctx.STRING().getText()
        question_id = ctx.ID().getText()
        data_type = ctx.type().getText()

        if question_id in self.question_ids:
            self.error_message = "Error: duplicate question IDs: {}".format(question_id)
            return

        # todo: remove instanceof
        if data_type == 'boolean':
            question_object = question_classes.BooleanQuestion(question_id, question_string)

        elif data_type == 'money':
            question_object = question_classes.MoneyQuestion(question_id, question_string)

        else:
            self.error_message = "Error: unknown data_type: {}".format(data_type)
            return

        self.question_ids.append(question_id)
        self.questions[question_id] = question_object

        return self.visitChildren(ctx)

    def visitDeclaration(self, ctx: QLParser.DeclarationContext):
        result = self.visitChildren(ctx)
        declared_value = QtWidgets.QLabel(str(result.pop()))
        self.questions[ctx.parentCtx.ID().getText()].text_input_box = declared_value
        return result

    def visitExpression(self, ctx: QLParser.ExpressionContext):
        return self.visitChildren(ctx)

    def visitIf_(self, ctx: QLParser.If_Context):
        # Gets the question IDs of the if argument and the question contained in the if, then links them so that the
        # contained question becomes invisible when the argument becomes False.

        # Picks out the ID of the question that is the argument of the if
        conditional_id = ctx.expression().getText()

        if conditional_id not in self.question_ids:
            self.error_message = "Error: if argument is undefined: {}".format(conditional_id)
            return
        elif self.questions[conditional_id].get_data_type() != 'boolean':
            self.error_message = "Error: if argument is not boolean: {}".format(conditional_id)
            return

        conditional_question = self.questions[conditional_id]

        result = self.visitChildren(ctx)

        questions_in_if = ctx.block()

        for statement in questions_in_if.stmt():
            if_question_id = statement.question().ID().getText()

            question_in_if = self.questions[if_question_id]  # todo: use return to get answer
            conditional_question.add_if_question(question_in_if)

        return result

    def visitType(self, ctx: QLParser.TypeContext):
        return self.visitChildren(ctx)

    def visitValue(self, ctx: QLParser.ValueContext):
        result = self.visitChildren(ctx)
        if result:
            return result
        else:
            return [ctx.getText()]

    def visitCompute(self, ctx: QLParser.ComputeContext):
        return self.visitChildren(ctx)

    @staticmethod
    def visitArithmetic_(ctx: QLParser.Arithmetic_Context):
        result = eval(ctx.INT()[0].getText() + ctx.ARITHMETIC_OP().getText() + ctx.INT()[1].getText())
        return [result]

    def visitBoolean_(self, ctx: QLParser.Boolean_Context):
        return self.visitChildren(ctx)
