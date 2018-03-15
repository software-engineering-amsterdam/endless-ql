"""
This file contains two widget windows. If this file is run, the first, InputWindow, opens.
In this window QL text can be typed or pasted. When pressing the "Parse" button, this text
is parsed, and a second window, OutputWindow opens. The Outputwindow contains an interactive
 questionnaire, encoded by the input text.
"""
import visitor.visitor as visitor_script
from visitor.listener import listen
from PyQt5 import QtWidgets
from PyQt5 import QtCore
from grammar.run_antlr import run_antlr
import sys


class MainWindow(QtWidgets.QWidget):
    def __init__(self):
        super(MainWindow, self).__init__()
        # Parses QL input
        self.layout = QtWidgets.QVBoxLayout()
        self.layout.setSpacing(10)
        self.setLayout(self.layout)
        self.setWindowTitle('QL parser')
        self.setGeometry(600, 600, 1100, 600)
        self.tree = None

        self.inputFrame = InputFrame()
        self.outputFrame = OutputFrame()
        self.splitter = QtWidgets.QSplitter(QtCore.Qt.Horizontal)
        self.splitter.addWidget(self.inputFrame)
        self.splitter.addWidget(self.outputFrame)
        self.layout.addWidget(self.splitter)

        self.inputFrame.createOutputFrame.connect(self.initiate_outputFrame)
        self.inputFrame.createOutputFrame.connect(self.parse)

    def initiate_outputFrame(self):
        # Removes the old outputFrame from splitter, and resets related parameters
        self.outputFrame.setParent(None)
        self.outputFrame.destroy()

        # Reinitializes outputframe
        self.outputFrame = OutputFrame()

        self.splitter.addWidget(self.outputFrame)

    def parse(self,qlText):
        if qlText:
            self.tree = run_antlr(qlText)
            listen(self.tree, self.outputFrame)
            # self.build_gui(self.tree)
            self.outputFrame.add_submit_button()
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

    # def build_gui(self, tree):
    #     listen(tree, self.outputFrame)


class InputFrame(QtWidgets.QFrame):
    createOutputFrame = QtCore.pyqtSignal(str)

    def __init__(self):
        super(InputFrame, self).__init__()

        # Initialize inputFrame
        self.inputlayout = QtWidgets.QGridLayout()
        self.setLayout(self.inputlayout)
        self.resize(10000,100)
        # Creates textbox
        self.inputlayout.addWidget(QtWidgets.QLabel("Input your QL text here"))
        self.qlInput = QtWidgets.QTextEdit()
        self.inputlayout.addWidget(self.qlInput)

        # Adds parse button
        self.parsebutton = QtWidgets.QPushButton('Parse', self)
        self.parsebutton.clicked.connect(self.on_parse)
        self.parsebutton.resize(self.parsebutton.sizeHint())
        self.inputlayout.addWidget(self.parsebutton)

        # Adds quit button
        self.quitbutton = QtWidgets.QPushButton('Quit', self)
        self.quitbutton.clicked.connect(QtWidgets.QApplication.instance().quit)
        self.quitbutton.resize(self.quitbutton.sizeHint())
        self.inputlayout.addWidget(self.quitbutton)

    def on_parse(self):
        qlText = self.qlInput.toPlainText()
        self.createOutputFrame.emit(qlText)


class OutputFrame(QtWidgets.QFrame):
    def __init__(self):
        super(OutputFrame, self).__init__()
        self.outputlayout = QtWidgets.QGridLayout()
        self.setLayout(self.outputlayout)
        self.row = 0
        self.btn_grp = []
        self.questions = []  # Ordered list of questions
        self.answers = []  # Ordered list of corresponding answers

    # def add_question(self, completequestion):  # todo: split question and its datatype in ast rather than here
    #     # Adds questions and answer option
    #
    #     splitquestion = completequestion.split('"')  # Filters the actual question from the input string
    #     question = splitquestion[1]
    #     datatype = splitquestion[2].split(":")[1]  # Filters datatype from the question string
    #
    #     choices = ['Yes', 'No']  # Default choices; todo: move to appropriate location.
    #
    #     self.outputlayout.addWidget(QtWidgets.QLabel(question))
    #     self.questions.append(question)
    #     self.answers.append('undefined')  # Default answer
    #
    #     if datatype == 'boolean':
    #         self.btn_grp.append(QtWidgets.QButtonGroup())  # Makes sure only one radiobutton can be true per question
    #         for choicenumber in range(len(choices)):
    #             radiobutton = QtWidgets.QRadioButton(choices[choicenumber])
    #             radiobutton.answer = choices[choicenumber]
    #             radiobutton.question = question
    #
    #             self.outputlayout.addWidget(radiobutton, self.row, choicenumber+1)
    #             radiobutton.toggled.connect(self.write_answer)
    #
    #             self.btn_grp[-1].setExclusive(True)
    #             self.btn_grp[-1].addButton(radiobutton)
    #
    #     elif datatype == 'money':
    #         # todo:testing
    #         textbox = QtWidgets.QLineEdit(self)
    #         textbox.answer = textbox.text()
    #         textbox.question = question
    #         textbox.textEdited.connect(self.write_answer)
    #         self.outputlayout.addWidget(textbox, self.row, 1)
    #
    #     self.row += 1

    def write_answer(self):
        # Saves the user's answer to the corresponding question
        sender = self.sender()
        try:
            sender.answer = sender.text()
        except:
            pass
        self.answers[self.questions.index(sender.question)] = sender.answer

    def add_submit_button(self):
        self.submit_button = QtWidgets.QPushButton('Submit', self)
        self.submit_button.clicked.connect(self.submit)
        self.submit_button.resize(self.submit_button.sizeHint())
        self.outputlayout.addWidget(self.submit_button,self.row,1)

    def submit(self):
        # Writes answers to txt
        file = open('QL_output.txt', 'w')

        for i in range(len(self.questions)):
            file.write(self.questions[i]+str(self.answers[i])+"\n")
        file.close()

    def no_tree_message(self):
        self.outputlayout.addWidget(QtWidgets.QLabel('Invalid input'))


if __name__ == '__main__':
    app = QtWidgets.QApplication(sys.argv)
    ex = MainWindow()
    ex.show()
    sys.exit(app.exec_())