"""
This file contains two widget windows. If this file is run, the first, InputWindow, opens.
In this window QL text can be typed or pasted. When pressing the "Parse" button, this text
is parsed, and a second window, OutputWindow opens. The Outputwindow contains an interactive
 questionnaire, encoded by the input text.
"""
import visitor.visitor as visitorscript
from PyQt5.QtWidgets import *
from PyQt5.QtCore import Qt
from grammar.run_antlr import run_antrl
import sys


class MainWindow(QWidget):
    def __init__(self):
        super(MainWindow, self).__init__()
        # Parses QL input
        self.layout = QVBoxLayout()
        self.layout.setSpacing(10)
        self.setWindowTitle('QL parser')
        self.setGeometry(600, 600, 700, 600)
        self.setLayout(self.layout)
        self.tree = None


        self.row = 0
        self.btn_grp = []
        self.questions = []  # Ordered list of questions
        self.answers = []  # Ordered list of corresponding answers

        self.outputFrame = QFrame()
        # self.outputlayout = QGridLayout()
        # self.outputFrame.setLayout(self.outputlayout)


        # Initialize inputFrame
        self.inputFrame = QFrame()
        self.inputlayout = QGridLayout()
        self.inputFrame.setLayout(self.inputlayout)
        # Creates textbox
        self.inputlayout.addWidget(QLabel("Input your QL text here"))
        self.qlInput = QTextEdit()
        self.inputlayout.addWidget(self.qlInput)

        # Adds parse button
        self.parsebutton = QPushButton('Parse', self)
        self.parsebutton.clicked.connect(self.parse)
        self.parsebutton.resize(self.parsebutton.sizeHint())
        self.inputlayout.addWidget(self.parsebutton)

        # Adds quit button
        self.quitbutton = QPushButton('Quit', self)
        self.quitbutton.clicked.connect(QApplication.instance().quit)
        self.quitbutton.resize(self.quitbutton.sizeHint())
        self.inputlayout.addWidget(self.quitbutton)


        self.splitter = QSplitter(Qt.Horizontal)
        self.splitter.addWidget(self.inputFrame)
        self.splitter.addWidget(self.outputFrame)
        self.layout.addWidget(self.splitter)

    def parse(self):

        # Removes the old outputFrame from splitter, and resets related parameters
        self.outputFrame.setParent(None)
        self.outputFrame.destroy()
        self.row = 0
        self.btn_grp = []
        self.questions = []  # Ordered list of questions
        self.answers = []  # Ordered list of corresponding answers

        # Reinitializes outputframe
        self.outputFrame = QFrame()
        self.outputlayout = QGridLayout()
        self.outputFrame.setLayout(self.outputlayout)

        self.splitter.addWidget(self.outputFrame)

        if self.qlInput.toPlainText():
            self.tree = run_antrl(self.qlInput.toPlainText())
            self.build_gui(self.tree)
            self.add_submit_button()
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
            self.no_tree_message()

    def build_gui(self, tree):
        visitorscript.listen(tree, self)

    def add_question(self, input):
        # Adds questions and answer option

        splitline = input.split('"')  # Filters the actual question from the input string
        question = splitline[1]
        datatype = splitline[2].split(":")[1]  # Filters datatype from the question string

        choices = ['Yes', 'No']  # Default choices; todo: move to appropriate location.

        self.outputlayout.addWidget(QLabel(question))
        self.questions.append(question)
        self.answers.append('undefined')  # Default answer

        if datatype == 'boolean':
            self.btn_grp.append(QButtonGroup())  # Makes sure only one radiobutton can be true per question
            for choicenumber in range(len(choices)):
                radiobutton = QRadioButton(choices[choicenumber])
                radiobutton.answer = choices[choicenumber]
                radiobutton.question = question

                self.outputlayout.addWidget(radiobutton, self.row, choicenumber+1)
                radiobutton.toggled.connect(self.write_answer)

                self.btn_grp[-1].setExclusive(True)
                self.btn_grp[-1].addButton(radiobutton)

        elif datatype == 'money':
            # todo:testing
            textbox = QLineEdit(self)
            textbox.answer = textbox.text()
            textbox.question = question
            textbox.textEdited.connect(self.write_answer)
            textbox.textEdited
            self.outputlayout.addWidget(textbox, self.row, 1)

        self.row += 1

    def write_answer(self):
        # Saves the user's answer to the corresponding question
        sender = self.sender()
        try:
            sender.answer = sender.text()
        except:
            pass
        self.answers[self.questions.index(sender.question)] = sender.answer

    def add_close_button(self):
        # todo: fix bug: clear inputscreen when close is pushed (parse -> close -> parse)
        close_button = QPushButton('Close', self)
        close_button.clicked.connect(self.close)
        close_button.resize(close_button.sizeHint())
        self.outputlayout.addWidget(close_button)
        # self.row +=1

    def add_submit_button(self):
        submit_button = QPushButton('Submit', self)
        submit_button.clicked.connect(self.submit)
        submit_button.resize(submit_button.sizeHint())
        self.outputlayout.addWidget(submit_button,self.row,1)

    def submit(self):
        # Writes answers to txt
        file = open('QL_output.txt', 'w')

        for i in range(len(self.questions)):
            file.write(self.questions[i]+str(self.answers[i])+"\n")
        file.close()


    def no_tree_message(self):
        self.outputlayout.addWidget(QLabel('Invalid input'))

    def testfunc(self):
        print('testingtext')

if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = MainWindow()
    ex.show()
    sys.exit(app.exec_())