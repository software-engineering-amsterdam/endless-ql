from PyQt5.QtWidgets import *
import sys
import parse
import ast
import os

class Window(QWidget):
    def __init__(self):
        super(Window,self).__init__()
        self.layout = QGridLayout()
        self.setLayout(self.layout)
        self.row = 0
        self.radioquestions = 0
        self.btn_grp = []
        self.questionnumber = 0
        self.questions = [] # Sorted list of questions
        self.answers = []   # Sorted list of corresponding answers

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

        # if datatype == 'boolean':
        #     self.btn_grp.append(QButtonGroup())
        #     radiobuttons = []
        #     for choice in range(len(choices)):
        #         radiobuttons.append(QRadioButton(choices[choice]))
        #         radiobuttons[-1].answer = choices[choice]
        #         radiobuttons[-1].question = question
        #         print(choices[choice])
        #         self.layout.addWidget(radiobuttons[choice], self.row, choice+1)
        #         radiobuttons[choice].toggled.connect(self.writeanswer)
        #
        #
        #         self.btn_grp[self.radioquestions].setExclusive(True)
        #         self.btn_grp[self.radioquestions].addButton(radiobuttons[choice])
        #         self.radiobuttons += 1
        #     self.radioquestions += 1

        elif datatype == 'Num':
            self.textbox = QLineEdit(self)
            self.textbox.resize(280, 40)
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
        self.row +=1

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

    # def submit(self):
    #     file = open( 'output.txt', 'w')
    #
    #     for i in range(self.questionnumber-1):
    #         file.write(str(self.database[i][0])+str(self.database[i][1])+"\n")
    #     file.close()

    # def on_radio_button_toggled(self):
    #     radiobutton = self.sender()
    #
    #     if radiobutton.isChecked():
    #         print("Selected country is %s" % (radiobutton.country))

if __name__ == '__main__':

    app = QApplication(sys.argv)

    screen = Window()
    screen.show()

    sys.exit(app.exec_())

def buildWidget(ast):
    app = QApplication(sys.argv)

    screen = Window()

    findTypes(ast,screen)

    # screen.putQuestion("hi")
    screen.submitbutton()
    screen.quitbutton()
    screen.show()

    sys.exit(app.exec())

def findTypes(node,screen):
    if type(node) is parse.combinators.Result:
        findTypes(node.value,screen)
    elif type(node) is ast.ql_ast.FormStatement:
        findTypes(node.form,screen)
    elif type(node) is ast.ql_ast.CompoundStatement:
        findTypes(node.first,screen)
        findTypes(node.second,screen)
    elif type(node) is ast.ql_ast.AssignStatement:
        screen.putQuestion(node.question, node.data_type)