import ast
import antlr4
from parser_generator.grammar.QLListener import *
from parser_generator.grammar.QLParser import QLParser

from PyQt5 import QtWidgets
from PyQt5 import QtCore
from gui import question_classes


def listen(tree, outputFrame):
    # print(tree.toStringTree())
    ql = QLListener(outputFrame)
    walker = ParseTreeWalker()
    walker.walk(ql, tree)


class QLListener(ParseTreeListener):
    def __init__(self, outputFrame):
        self.outputFrame = outputFrame
        self.inIf = False

    # Enter a parse tree produced by QLParser#form.
    def enterForm(self, ctx:QLParser.FormContext):
        # print(ctx.getText())
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
        # Filters necessary information from the node
        children = ctx.getChildren()
        question = children.__next__().getText()
        questionID = children.__next__().getText()
        children.__next__()
        datatype = children.__next__().getText()

        # questionObject = Question(questionID, question, datatype, 'undefined')
        # self.outputFrame.questionDict[questionID] = questionObject
        # self.outputFrame.quesionIDs.append(questionID)

        # todo: reduce interactions with outputwindow
        # todo: move following code to dedicated functions, for each datatype, and maybe even qls version

        # self.outputFrame.frameLayout.addWidget(QtWidgets.QLabel(question),self.outputFrame.row,0)
        # self.outputFrame.questions.append(question)
        # self.outputFrame.answers.append('undefined')  # Default answer

        # if datatype == 'boolean':
        #     choices = ['Yes', 'No']  # Default choices; todo: move to appropriate location.
        #     self.outputFrame.btn_grp.append(QtWidgets.QButtonGroup())  # Makes sure only one radiobutton can be true per question
        #     for choicenumber in range(len(choices)):
        #         radiobutton = QtWidgets.QRadioButton(choices[choicenumber])
        #         radiobutton.answer = choices[choicenumber]
        #         radiobutton.question = question
        #
        #         self.outputFrame.frameLayout.addWidget(radiobutton, self.outputFrame.row, choicenumber+1)
        #         radiobutton.toggled.connect(self.outputFrame.write_answer)
        #
        #         self.outputFrame.btn_grp[-1].setExclusive(True)
        #         self.outputFrame.btn_grp[-1].addButton(radiobutton)
        #
        # elif datatype == 'money':
        #     # todo:testing
        #     textbox = QtWidgets.QLineEdit()
        #     textbox.answer = textbox.text()
        #     textbox.question = question
        #     textbox.textEdited.connect(self.outputFrame.write_answer)
        #     self.outputFrame.frameLayout.addWidget(textbox, self.outputFrame.row, 1)

        if datatype == 'boolean':
            questionObject = question_classes.BooleanQuestion(questionID, question, datatype)
            choices = ['Yes','No']

            truebutton = QtWidgets.QRadioButton(choices[0])
            truebutton.toggled.connect(questionObject.set_answer_true)
            questionObject.set_truebutton(truebutton)

            falsebutton = QtWidgets.QRadioButton(choices[1])
            falsebutton.toggled.connect(questionObject.set_answer_false)
            questionObject.set_falsebutton(falsebutton)

        elif datatype == 'money':
            questionObject = question_classes.MoneyQuestion(questionID, question, datatype)
            textbox = QtWidgets.QLineEdit()
            textbox.textEdited.connect(questionObject.set_answer_text)
            questionObject.set_text_input(textbox)

        self.outputFrame.quesionIDs.append(questionID)
        self.outputFrame.questions.append(questionObject)
        self.outputFrame.add_question(questionObject.create_frame())
        self.outputFrame.row += 1

        # print(self.outputFrame.row)
        # print(self.outputFrame.row)

        # print(ctx.getText())
        # self.outputWindow.add_question(ctx.getText())

    # Exit a parse tree produced by QLParser#question.
    def exitQuestion(self, ctx:QLParser.QuestionContext):
        pass


    # Enter a parse tree produced by QLParser#declaration.
    def enterDeclaration(self, ctx:QLParser.DeclarationContext):
        pass

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
        # self.ifFrame = QtWidgets.QFrame()
        # self.frameLayout = QtWidgets.QGridLayout()
        # self.ifFrame.setLayout(self.frameLayout)
        # self.ifFrame.row = 0
        # self.parentFrame = self.ifFrame
        #
        # self.outputFrame.frameLayout.addWidget(self.ifFrame, self.outputFrame.row,0)
        # self.outputFrame.row += 1

        # print(ctx.getText())
        children = ctx.getChildren()
        children.__next__()
        children.__next__()
        conditionalquestion = children.__next__().getText()
        self.inIf = True
        # print(children.__next__().getText())
        # for child in children:
        #     print(child)
        #     print(child.getText())
        #     print()
            # print(dir)
            # for grandchild in child:
            #     print(grandchild)
        # question = children.__next__().getText()
        # pass

    # Exit a parse tree produced by QLParser#if_.
    def exitIf_(self, ctx:QLParser.If_Context):
        self.inIf = False
        # self.parentFrame = self.outputFrame
        pass


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