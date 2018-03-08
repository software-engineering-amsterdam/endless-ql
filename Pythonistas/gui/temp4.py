

"""
This file contains two widget windows. If this file is run, the first, InputWindow, opens.
In this window QL text can be typed or pasted. When pressing the "Parse" button, this text
is parsed, and a second window, OutputWindow opens. The Outputwindow contains an interactive
 questionnaire, encoded by the input text.
"""
import visitor.visitor as visitorscript
from PyQt5.QtWidgets import *
from grammar.run_antlr import run_antrl


class InputWindow(QWidget):
    def __init__(self):
        super(InputWindow, self).__init__()
        # Parses QL input
        self.layout = QGridLayout()
        self.layout.setSpacing(10)
        self.setWindowTitle('QL parser')
        self.setGeometry(600, 600, 700, 600)
        self.setLayout(self.layout)
        self.tree = None
        self.outputWindow = OutputWindow()

        # Creates textbox
        self.layout.addWidget(QLabel("Input your QL text here"))
        self.qlInput = QTextEdit()
        self.layout.addWidget(self.qlInput)

        # Adds parse button
        self.parsebutton = QPushButton('Parse', self)
        self.parsebutton.clicked.connect(self.parse)
        self.parsebutton.resize(self.parsebutton.sizeHint())
        self.layout.addWidget(self.parsebutton)

        # Adds quit button
        self.quitbutton = QPushButton('Quit', self)
        self.quitbutton.clicked.connect(QApplication.instance().quit)
        self.quitbutton.resize(self.quitbutton.sizeHint())
        self.layout.addWidget(self.quitbutton)

    def parse(self):
        self.outputWindow = OutputWindow()

        if self.qlInput.toPlainText():
            self.tree = run_antrl(self.qlInput.toPlainText())
            self.build_gui(self.tree)
            self.outputWindow.add_submit_button()
            # if self.tree:
            #     self.build_gui(self.tree)
            # else:
            #     self.outputWindow.no_tree_label()
            # self.outputWindow.add_submit_button()
            # print('below is tree')
            # print(type(self.tree))
            # print((self.tree))
            # print(self.tree.depth())
            # elif self.tree.depth() > 1:
        else:
            self.outputWindow.no_tree_message()

        self.outputWindow.add_quit_button()
        self.outputWindow.show()

    def build_gui(self, tree):
        visitorscript.listen(tree, self.outputWindow)


class OutputWindow(QWidget):
    """A questionnaire window"""
    def __init__(self):
        super(OutputWindow, self).__init__()
        self.layout = QGridLayout()
        self.setLayout(self.layout)
        self.setWindowTitle('Questionnaire')
        self.row = 0
        self.btn_grp = []
        self.questions = []  # Ordered list of questions
        self.answers = []  # Ordered list of corresponding answers

    def add_question(self, question):
        # Adds questions and answer option
        datatype = question  # .split(":", 1)[1]
        question = question  # .split('"')[1]
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
        # todo: fix bug: clear inputscreen when close is pushed (parse -> close -> parse)
        close_button = QPushButton('Close', self)
        close_button.clicked.connect(self.close)
        close_button.resize(close_button.sizeHint())
        self.layout.addWidget(close_button)
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

    def no_tree_message(self):
        self.layout.addWidget(QLabel('Invalid input'))
