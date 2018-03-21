'''
Traverses Questionnaire Language (QL) Abstract Syntax Trees (ASTs). For every node in the tree, an appropriate action
is taken: when a question is encountered, a question object is instanced, with the question string, ID and data type
attached; an if node links the question of its argument to the question it contains, in order to hide the contained
question when the argument becomes False; declarations overwrite questions' editable textboxes with a static label
containing the value given in the QL AST.
'''
import ast
import antlr4
from parser_generator.grammar.QLListener import *
from parser_generator.grammar.QLParser import QLParser

from PyQt5 import QtWidgets
from PyQt5 import QtCore
from gui import question_classes


def listen(tree):
    # print(tree.toStringTree())
    ql = QLListener()
    walker = ParseTreeWalker()
    walker.walk(ql, tree)
    return [ql.questionIDs, ql.questions, ql.error_message]


class QLListener(ParseTreeListener):
    def __init__(self):
        self.error_message = None
        self.questionIDs = [] # Ordered list of question IDs.
        self.questions = {}  # Ordered list of question objects

    # Enter a parse tree produced by QLParser#form.
    def enterForm(self, ctx:QLParser.FormContext):
        pass

    # Exit a parse tree produced by QLParser#form.
    def exitForm(self, ctx:QLParser.FormContext):
        pass


    # Enter a parse tree produced by QLParser#block.
    def enterBlock(self, ctx:QLParser.BlockContext):
        pass

    # Exit a parse tree produced by QLParser#block.
    def exitBlock(self, ctx:QLParser.BlockContext):
        pass


    # Enter a parse tree produced by QLParser#stmt.
    def enterStmt(self, ctx:QLParser.StmtContext):
        pass

    # Exit a parse tree produced by QLParser#stmt.
    def exitStmt(self, ctx:QLParser.StmtContext):
        pass


    # Enter a parse tree produced by QLParser#question.
    def enterQuestion(self, ctx:QLParser.QuestionContext):
        # print(dir(ctx))
        # print(dir(ctx.declaration())) # todo: how to communicate proper between parents and children?
        # if len(ctx.declaration()) > 0:
        #     print(ctx.declaration()[0].getText())
        #     print(dir(ctx.declaration()[0]))
        #     print(ctx.declaration()[0].EMPTY)

        # Gets necessary information from the node
        question = ctx.STRING().getText()
        questionID = ctx.ID().getText()
        data_type = ctx.type().getText()

        if questionID in self.questionIDs:
            self.error_message = "Error: duplicate question IDs: {}".format(questionID)
            return

        if data_type == 'boolean':
            question_object = question_classes.BooleanQuestion(questionID, question)
            choices = ['Yes','No']  # todo: make flexible

            truebutton = QtWidgets.QRadioButton(choices[0])  # todo: move to question_classes
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

    # Exit a parse tree produced by QLParser#question.
    def exitQuestion(self, ctx:QLParser.QuestionContext):
        pass


    # Enter a parse tree produced by QLParser#declaration.
    def enterDeclaration(self, ctx:QLParser.DeclarationContext):
        # print(dir(ctx))
        # print(ctx.value().getText())
        # print(ctx.parentCtx.getText())
        # print((ctx.parentCtx.ID().getText()))
        self.questions[ctx.parentCtx.ID().getText()].text_input_box = QtWidgets.QLabel(ctx.value().getText())

    # Exit a parse tree produced by QLParser#declaration.
    def exitDeclaration(self, ctx:QLParser.DeclarationContext):
        pass


    # Enter a parse tree produced by QLParser#expression.
    def enterExpression(self, ctx:QLParser.ExpressionContext):
        pass

    # Exit a parse tree produced by QLParser#expression.
    def exitExpression(self, ctx:QLParser.ExpressionContext):
        pass


    # Enter a parse tree produced by QLParser#if_.
    def enterIf_(self, ctx:QLParser.If_Context):
        pass

    # Exit a parse tree produced by QLParser#if_.
    def exitIf_(self, ctx:QLParser.If_Context):
        # todo: cleanup, remove the need for __next__s
        # Gets the question IDs of the if argument and the question contained in the if, then links them so that the
        # contained question becomes invisible when the argument becomes False.
        children = ctx.getChildren()

        # Picks out the ID of the question that is the argument of the if
        children.__next__()
        children.__next__()
        conditionalID = children.__next__().getText()

        # If the ID of the question that is the argument of the if does not exist, throws an error
        if conditionalID not in self.questionIDs:
            self.error_message = "Error: if argument is undefined: {}".format(conditionalID)
            return
        elif self.questions[conditionalID].get_datatype() != 'boolean':
            self.error_message = "Error: if argument is not boolean: {}".format(conditionalID)
            return

        children.__next__()
        if_question = children.__next__()  # picks out the question within the if

        ifquestion_children = if_question.getChildren()
        ifquestion_children.__next__()
        for ifchild in ifquestion_children:  # If there's multiple questions within the if, all are picked out
            if ifchild.getText() == '}':
                break
            grandchildren = ifchild.getChildren()
            grandchild = grandchildren.__next__()

            ggrandchildren = grandchild.getChildren()
            ggrandchildren.__next__()
            ifquestionID = ggrandchildren.__next__().getText()  # Specifically: picks out the IDs of the questions

            conditional_question = self.questions[conditionalID]
            ifquestion = self.questions[ifquestionID]
            conditional_question.add_if_question(ifquestion)


    # Enter a parse tree produced by QLParser#type.
    def enterType(self, ctx:QLParser.TypeContext):
        pass

    # Exit a parse tree produced by QLParser#type.
    def exitType(self, ctx:QLParser.TypeContext):
        pass


    # Enter a parse tree produced by QLParser#value.
    def enterValue(self, ctx:QLParser.ValueContext):
        pass

    # Exit a parse tree produced by QLParser#value.
    def exitValue(self, ctx:QLParser.ValueContext):
        pass


    # Enter a parse tree produced by QLParser#compute.
    def enterCompute(self, ctx:QLParser.ComputeContext):
        pass

    # Exit a parse tree produced by QLParser#compute.
    def exitCompute(self, ctx:QLParser.ComputeContext):
        pass


    # Enter a parse tree produced by QLParser#arithmetic.
    def enterArithmetic_(self, ctx:QLParser.Arithmetic_Context):
        pass

    # Exit a parse tree produced by QLParser#arithmetic.
    def exitArithmetic_(self, ctx:QLParser.Arithmetic_Context):
        pass


    # Enter a parse tree produced by QLParser#boolean.
    def enterBoolean_(self, ctx:QLParser.Boolean_Context):
        pass

    # Exit a parse tree produced by QLParser#boolean.
    def exitBoolean_(self, ctx:QLParser.Boolean_Context):
        pass