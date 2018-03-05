"""
This file contains two widget windows. If this file is run, the first, InputWindow, opens.
In this window QL text can be typed or pasted. When pressing the "Parse" button, this text
is parsed, and a second window, OutputWindow opens. The Outputwindow contains an interactive
 questionnaire, encoded by the input text.
"""
import sys
from visitor.visitor import *
from PyQt5.QtWidgets import *


class InputWindow(QWidget):
    def __init__(self, tree):
        super(InputWindow, self).__init__()
        self.output = OutputWindow()
        self.layout = QGridLayout()
        self.layout.setSpacing(10)
        self.tree = tree

        # Creates textbox
        titlelabel = QLabel("Input your QL text here")
        self.layout.addWidget(titlelabel)
        self.qlInput = QTextEdit()
        self.layout.addWidget(self.qlInput)

        # Adds parse button
        self.parsebutton = QPushButton('Parse', self)
        self.parsebutton.clicked.connect(self.parse)
        self.parsebutton.resize(self.parsebutton.sizeHint())
        self.layout.addWidget(self.parsebutton)

        # Adds close button
        self.quitbutton = QPushButton('Quit', self)
        self.quitbutton.clicked.connect(QApplication.instance().quit)
        self.quitbutton.resize(self.quitbutton.sizeHint())
        self.layout.addWidget(self.quitbutton)

        # General window
        self.setWindowTitle('QL parser')
        self.setGeometry(600, 600, 700, 600)
        self.setLayout(self.layout)

    def parse(self):
        # Parses QL input
        self.build_gui(self.tree, self.output)
        self.output.add_quit_button()
        self.output.add_submit_button()
        self.output.show()

    def build_gui(self, tree, screen):
        # todo: move this another py file (cleanup general)
        def visit(tree, ):
            ql = QLListener(tree)
            walker = ParseTreeWalker()
            walker.walk(ql, tree)

        class QLListener(ParseTreeListener):
            def __init__(self, output):
                self.output = output

            # Enter a parse tree produced by QLParser#form.
            def enterForm(self, ctx: QLParser.FormContext):
                print('Found form')
                print(ctx.getText())

            # Exit a parse tree produced by QLParser#form.
            def exitForm(self, ctx: QLParser.FormContext):
                pass

            # Enter a parse tree produced by QLParser#block.
            def enterBlock(self, ctx: QLParser.BlockContext):
                pass

            # Exit a parse tree produced by QLParser#block.
            def exitBlock(self, ctx: QLParser.BlockContext):
                pass

            # Enter a parse tree produced by QLParser#statement.
            def enterStatement(self, ctx: QLParser.StatementContext):
                print(ctx.getText())

            # Exit a parse tree produced by QLParser#statement.
            def exitStatement(self, ctx: QLParser.StatementContext):
                pass

            # Enter a parse tree produced by QLParser#question.
            def enterQuestion(self, ctx: QLParser.QuestionContext):
                screen.add_question(ctx.getText())

            # Exit a parse tree produced by QLParser#question.
            def exitQuestion(self, ctx: QLParser.QuestionContext):
                pass

            # Enter a parse tree produced by QLParser#assignment.
            def enterAssignment(self, ctx: QLParser.AssignmentContext):
                print(ctx.getText())

            # Exit a parse tree produced by QLParser#assignment.
            def exitAssignment(self, ctx: QLParser.AssignmentContext):
                pass

            # Enter a parse tree produced by QLParser#expression.
            def enterExpression(self, ctx: QLParser.ExpressionContext):
                pass

            # Exit a parse tree produced by QLParser#expression.
            def exitExpression(self, ctx: QLParser.ExpressionContext):
                pass

            # Enter a parse tree produced by QLParser#conditional.
            def enterConditional(self, ctx: QLParser.ConditionalContext):
                pass

            # Exit a parse tree produced by QLParser#conditional.
            def exitConditional(self, ctx: QLParser.ConditionalContext):
                pass

            # Enter a parse tree produced by QLParser#if_conditional.
            def enterIf_conditional(self, ctx: QLParser.If_conditionalContext):
                print('Found if')
                print(ctx.getText())

            # Exit a parse tree produced by QLParser#if_conditional.
            def exitIf_conditional(self, ctx: QLParser.If_conditionalContext):
                pass

            # Enter a parse tree produced by QLParser#else_conditional.
            def enterElse_conditional(self, ctx: QLParser.Else_conditionalContext):
                pass

            # Exit a parse tree produced by QLParser#else_conditional.
            def exitElse_conditional(self, ctx: QLParser.Else_conditionalContext):
                pass

            # Enter a parse tree produced by QLParser#types.
            def enterTypes(self, ctx: QLParser.TypesContext):
                pass

            # Exit a parse tree produced by QLParser#types.
            def exitTypes(self, ctx: QLParser.TypesContext):
                pass

        visit(tree)


class OutputWindow(QWidget):
    """A questionnaire window"""
    def __init__(self):
        super(OutputWindow, self).__init__()
        self.layout = QGridLayout()
        self.setLayout(self.layout)
        self.row = 0
        self.btn_grp = []
        self.questions = []  # Ordered list of questions
        self.answers = []  # Ordered list of corresponding answers

        self.setWindowTitle('Questionnaire')

    def add_question(self, question):
        # Adds questions and answer option
        datatype = question.split(":", 1)[1]
        question = question.split('"')[1]
        choices = ['Yes', 'No']

        self.layout.addWidget(QLabel(question))
        self.questions.append(question)
        self.answers.append('undefined')  # Default answer

        if datatype == 'boolean':
            self.btn_grp.append(QButtonGroup())  # Makes sure only one radiobutton can be true per question
            for choicenumber in range(len(choices)):
                radiobutton = QRadioButton(choices[choicenumber])
                radiobutton.answer = choices[choicenumber]
                radiobutton.question = question

                self.layout.addWidget(radiobutton, self.row, choicenumber+1)
                radiobutton.toggled.connect(self.write_answer)

                self.btn_grp[-1].setExclusive(True)
                self.btn_grp[-1].addButton(radiobutton)

        elif datatype == 'Num':
            # todo:testing

            textbox = QLineEdit(self)
            textbox.answer = textbox.text
            textbox.question = question
            textbox.textEdited(self.write_answer)
            self.layout.addWidget(textbox, self.row, 1)

        self.row += 1

    def write_answer(self):
        sender = self.sender()
        self.answers[self.questions.index(sender.question)] = sender.answer

    def add_quit_button(self):
        quit_button = QPushButton('Quit', self)
        quit_button.clicked.connect(self.close)
        quit_button.resize(quit_button.sizeHint())
        self.layout.addWidget(quit_button)
        # self.row +=1

    def add_submit_button(self):
        submit_button = QPushButton('Submit', self)
        submit_button.clicked.connect(self.submit)
        submit_button.resize(submit_button.sizeHint())
        self.layout.addWidget(submit_button)

    def submit(self):
        # Writes answers to txt
        file = open('QL_output.txt', 'w')

        for i in range(len(self.questions)):
            file.write(self.questions[i]+str(self.answers[i])+"\n")
        file.close()
