from PyQt5.QtWidgets import *
import sys
import parse
import ql_ast

from parse.ql_parser import *

'''
This file contains two widget windows. If this file is run, the first, InputWindow, opens.
In this window QL text can be typed or pasted. When pressing the "Parse" button, this text
is parsed, and a second window, OutputWindow opens. The Outputwindow contains an interactive
 questionnaire, encoded by the input text.
'''


class InputWindow(QWidget):
    def __init__(self):
        super(InputWindow,self).__init__()
        self.layout = QGridLayout()
        self.layout.setSpacing(10)

        # Creates a textbox for input of QL text.
        # This QL text can later be parsed into a questionnaire.
        titlelabel = QLabel("Input your QL text here")
        self.layout.addWidget(titlelabel,0,0)
        self.qlInput = QTextEdit()
        self.layout.addWidget(self.qlInput,1,0,10,5)

        # Adds a button to the window, which when pressed generates a questionnaire
        # GUI from QL text that is entered in the input textbox.
        parsebutton = QPushButton('Parse', self)
        parsebutton.clicked.connect(self.parse)
        parsebutton.resize(parsebutton.sizeHint())
        self.layout.addWidget(parsebutton, 12, 0)

        # Adds a button which, when clicked, closes the window
        quitbutton = QPushButton('Quit', self)
        quitbutton.clicked.connect(QApplication.instance().quit)
        quitbutton.resize(quitbutton.sizeHint())
        self.layout.addWidget(quitbutton, 12, 4)

        self.setWindowTitle('QL parser')
        self.setGeometry(300, 300, 350, 300)
        self.setLayout(self.layout)

    def parse(self):
        # Parses QL that is entered in the input textbox, and generates a questionnaire GUI
        self.output = OutputWindow()
        tokens = ql_lex(self.qlInput.toPlainText())
        print(tokens)
        result = ql_parser(tokens)
        print(result)
        self.buildGui(result,self.output)

        self.output.addQuitButton()
        self.output.addSubmitButton()

        self.output.show()

    def buildGui(self, node, screen):
        if type(node) is parse.combinators.Result:
            self.buildGui(node.value, screen)
        elif type(node) is ql_ast.ql_ast.FormStatement:

            # for child in node.form:
            #     self.buildGui(child,screen)

            self.buildGui(node.form, screen)

        elif type(node) is ql_ast.ql_ast.CompoundStatement:
            self.buildGui(node.first, screen)
            self.buildGui(node.second, screen)
        elif type(node) is ql_ast.ql_ast.AssignStatement:
            screen.addQuestion(node.question, node.data_type)


class OutputWindow(QWidget):
    def __init__(self):
        super(OutputWindow,self).__init__()
        self.layout = QGridLayout()
        self.setLayout(self.layout)
        self.row = 0
        self.btn_grp = []
        self.questions = [] # Ordered list of questions
        self.answers = []   # Ordered list of corresponding answers

        self.setWindowTitle('Questionnaire')

    def addQuestion(self,question, datatype='boolean',choices = ['Yes','No']):
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

    def writeAnswer(self):
        # Writes the new answer to a question to the list of answers.
        sender = self.sender()
        self.answers[self.questions.index(sender.question)] = sender.answer


    def addQuitButton(self):
        qbtn = QPushButton('Quit', self)
        qbtn.clicked.connect(self.close)
        # qbtn.clicked.connect(QApplication.instance().quit)
        qbtn.resize(qbtn.sizeHint())
        self.layout.addWidget(qbtn, self.row,2)
        # self.row +=1

    def addSubmitButton(self):
        smbtn = QPushButton('Submit', self)
        smbtn.clicked.connect(self.submit)
        smbtn.resize(smbtn.sizeHint())
        self.layout.addWidget(smbtn, self.row,1)

    def submit(self):
        file = open( 'output.txt', 'w')

        for i in range(len(self.questions)):
            file.write(self.questions[i]+str(self.answers[i])+"\n")
        file.close()


if __name__ == '__main__':
    app = QApplication(sys.argv)

    screen = InputWindow()
    screen.show()

    sys.exit(app.exec_())