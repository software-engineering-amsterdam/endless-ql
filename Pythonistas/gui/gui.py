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

        self.inputwindow()
        self.parsebutton()
        self.quitbutton()
        self.setWindowTitle('QL parser')
        self.setGeometry(300, 300, 350, 300)

        self.setLayout(self.layout)

    def inputwindow(self):
        titlelabel = QLabel("Input your QL text here")
        self.layout.addWidget(titlelabel,0,0)

        self.textbox = QTextEdit()
        self.layout.addWidget(self.textbox,1,0,10,5)

    def parsebutton(self):
        parsebtn = QPushButton('Parse', self)
        parsebtn.clicked.connect(self.parse)
        parsebtn.resize(parsebtn.sizeHint())
        self.layout.addWidget(parsebtn, 12, 0)

    def parse(self):
        self.output = OutputWindow()
        tokens = ql_lex(self.textbox.toPlainText())
        result = ql_parser(tokens)
        print(result)
        self.buildGui(result,self.output)

        self.output.quitbutton()
        self.output.submitbutton()

        self.output.show()

    def buildGui(self, node, screen):
        if type(node) is parse.combinators.Result:
            self.buildGui(node.value, screen)
        elif type(node) is ql_ast.ql_ast.FormStatement:
            for child in node.form:
                self.buildGui(child,screen)
            # self.buildGui(node.form, screen)
        elif type(node) is ql_ast.ql_ast.CompoundStatement:
            self.buildGui(node.first, screen)
            self.buildGui(node.second, screen)
        elif type(node) is ql_ast.ql_ast.AssignStatement:
            screen.putQuestion(node.question, node.data_type)

    def quitbutton(self):
        qbtn = QPushButton('Quit', self)
        qbtn.clicked.connect(QApplication.instance().quit)
        qbtn.resize(qbtn.sizeHint())
        self.layout.addWidget(qbtn, 12, 4)


class OutputWindow(QWidget):
    def __init__(self):
        super(OutputWindow,self).__init__()
        self.layout = QGridLayout()
        self.setLayout(self.layout)
        self.row = 0
        self.radioquestions = 0
        self.btn_grp = []
        self.questionnumber = 0
        self.questions = [] # Sorted list of questions
        self.answers = []   # Sorted list of corresponding answers

        self.setWindowTitle('Questionnaire')

    def putQuestion(self,question, datatype='boolean',choices = ['Yes','No']):
        ''' Example function that adds a question and correscponding answer buttons to a gui
        '''
        buttonquestion = QLabel(question.strip('"\''))
        self.layout.addWidget(buttonquestion, self.row, 0)
        self.questions.append(question)
        self.answers.append('undefined')
        self.questionnumber += 1

        if datatype == 'boolean' or datatype == 'multiplechoice': # assuming multiple choice will be required
            self.btn_grp.append(QButtonGroup())
            for choice in range(len(choices)):
                radiobutton = QRadioButton(choices[choice])
                radiobutton.answer = choices[choice]
                radiobutton.question = question

                self.layout.addWidget(radiobutton, self.row, choice+1)
                radiobutton.toggled.connect(self.writeanswer)

                self.btn_grp[self.radioquestions].setExclusive(True)
                self.btn_grp[self.radioquestions].addButton(radiobutton)
            self.radioquestions += 1

        elif datatype == 'Num':
            self.textbox = QLineEdit(self)
            # self.textbox.resize(280, 40)
            self.layout.addWidget(self.textbox, self.row, 1)

        self.row += 1

    def writeanswer(self):
        sender = self.sender()
        self.answers[self.questions.index(sender.question)] = sender.answer


    def quitbutton(self):
        qbtn = QPushButton('Quit', self)
        qbtn.clicked.connect(QApplication.instance().quit)
        qbtn.resize(qbtn.sizeHint())
        self.layout.addWidget(qbtn, self.row,3)
        # self.row +=1

    def submitbutton(self):
        smbtn = QPushButton('Submit', self)
        smbtn.clicked.connect(self.submit)
        smbtn.resize(smbtn.sizeHint())
        self.layout.addWidget(smbtn, self.row,2)

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