import ast
import antlr4
from parser_generator.grammar.QLListener import *
from parser_generator.grammar.QLParser import QLParser

from PyQt5 import QtWidgets
from PyQt5 import QtCore


def listen(tree, outputWindow):
    print(tree.toStringTree())
    ql = QLListener(outputWindow)
    walker = ParseTreeWalker()
    walker.walk(ql, tree)


class QLListener(ParseTreeListener):
    def __init__(self, outputWindow):
        self.outputWindow = outputWindow

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
        children = ctx.getChildren()
        question = children.__next__().getText()
        questionID = children.__next__().getText()
        children.__next__()
        datatype= children.__next__().getText()

        choices = ['Yes', 'No']  # Default choices; todo: move to appropriate location.

        self.outputWindow.outputlayout.addWidget(QtWidgets.QLabel(question))  # todo: reduce interactions with outputwindow
        self.outputWindow.questions.append(question)
        self.outputWindow.answers.append('undefined')  # Default answer

        if datatype == 'boolean':
            self.outputWindow.btn_grp.append(QtWidgets.QButtonGroup())  # Makes sure only one radiobutton can be true per question
            for choicenumber in range(len(choices)):
                radiobutton = QtWidgets.QRadioButton(choices[choicenumber])
                radiobutton.answer = choices[choicenumber]
                radiobutton.question = question

                self.outputWindow.outputlayout.addWidget(radiobutton, self.outputWindow.row, choicenumber+1)
                radiobutton.toggled.connect(self.outputWindow.write_answer)

                self.outputWindow.btn_grp[-1].setExclusive(True)
                self.outputWindow.btn_grp[-1].addButton(radiobutton)

        elif datatype == 'money':
            # todo:testing
            textbox = QtWidgets.QLineEdit(self)
            textbox.answer = textbox.text()
            textbox.question = question
            textbox.textEdited.connect(self.outputWindow.write_answer)
            self.outputWindow.outputlayout.addWidget(textbox, self.outputWindow.row, 1)

        self.outputWindow.row += 1
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
        pass

    # Exit a parse tree produced by QLParser#if_.
    def exitIf_(self, ctx:QLParser.If_Context):
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