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
        self.layout = QGridLayout()
        self.layout.setSpacing(10)
        self.tree = tree

        # Creates a textbox for input of QL text.
        # This QL text can later be parsed into a questionnaire.
        titlelabel = QLabel("Input your QL text here")
        self.layout.addWidget(titlelabel,0,0)
        self.qlInput = QTextEdit()
        self.layout.addWidget(self.qlInput,1,0,10,5)

        # Adds a button to the window, which when pressed generates a questionnaire
        # GUI from QL text that is entered in the input textbox.
        self.parsebutton = QPushButton('Parse', self)
        self.parsebutton.clicked.connect(self.parse)
        self.parsebutton.resize(self.parsebutton.sizeHint())
        self.layout.addWidget(self.parsebutton, 12, 0)

        # Adds a button which, when clicked, closes the window
        self.quitbutton = QPushButton('Quit', self)
        self.quitbutton.clicked.connect(QApplication.instance().quit)
        self.quitbutton.resize(self.quitbutton.sizeHint())
        self.layout.addWidget(self.quitbutton, 12, 4)

        self.setWindowTitle('QL parser')
        self.setGeometry(300, 300, 350, 300)
        self.setLayout(self.layout)

    def parse(self):
        # Parses QL that is entered in the input textbox, and generates a questionnaire GUI
        self.output = OutputWindow()
        self.buildGui(self.tree, self.output)

        self.output.add_quit_button()
        self.output.add_submit_button()

        self.output.show()

    def buildGui(self, tree, screen):

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
                screen.add_question(ctx.getText(), None)

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
                pass

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
        self.questions = [] # Ordered list of questions
        self.answers = []   # Ordered list of corresponding answers

        self.setWindowTitle('Questionnaire')

    def add_question(self,question, datatype='boolean',choices = ['Yes','No']):
        # Adds questions and corresponding buttons or textboxes to the questionnaire window,
        # as instructed by QL text.

        self.layout.addWidget(QLabel(question.strip('"\'')), self.row, 0)
        self.questions.append(question)
        self.answers.append('undefined') # Default answer

        if datatype == 'boolean':
            self.btn_grp.append(QButtonGroup()) # Makes sure only one radiobutton can be true per question
            for choicenumber in range(len(choices)):
                radiobutton = QRadioButton(choices[choicenumber])
                radiobutton.answer = choices[choicenumber]
                radiobutton.question = question

                self.layout.addWidget(radiobutton, self.row, choicenumber+1)
                radiobutton.toggled.connect(self.writeAnswer)

                self.btn_grp[-1].setExclusive(True)
                self.btn_grp[-1].addButton(radiobutton)

        elif datatype == 'Num':
            # Needs testing

            textbox = QLineEdit(self)
            textbox.answer = textbox.text # does this work? is the answer updated when it needs to?
            textbox.question = question
            # self.textbox.resize(280, 40)
            textbox.textEdited(self.writeAnswer)
            self.layout.addWidget(textbox, self.row, 1)

        self.row += 1

    def write_answer(self):
        # Writes the new answer to a question to the list of answers.
        sender = self.sender()
        self.answers[self.questions.index(sender.question)] = sender.answer


    def add_quit_button(self):
        quitbutton = QPushButton('Quit', self)
        quitbutton.clicked.connect(self.close)
        # qbtn.clicked.connect(QApplication.instance().quit)
        quitbutton.resize(quitbutton.sizeHint())
        self.layout.addWidget(quitbutton, self.row,2)
        # self.row +=1

    def add_submit_button(self):
        self.submitbutton = QPushButton('Submit', self)
        self.submitbutton.clicked.connect(self.submit)
        self.submitbutton.resize(self.submitbutton.sizeHint())
        self.layout.addWidget(self.submitbutton, self.row,1)

    def submit(self):
        # Writes the entries from the answers list to a text file, together with the questions
        file = open( 'output.txt', 'w')

        for i in range(len(self.questions)):
            file.write(self.questions[i]+str(self.answers[i])+"\n")
        file.close()
