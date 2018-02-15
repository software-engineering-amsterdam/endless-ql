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
        self.radiobuttons = 0
        self.radiobuttonlist = []
        self.database = {}

    def putQuestion(self,question, datatype='boolean',choices = ['Yes','No']):
        ''' Example function that adds a question and radiobuttons to a gui
        '''
        buttonquestion = QLabel(question.strip('"\''))
        self.layout.addWidget(buttonquestion, self.row, 0)
        # self.database[self.questionnumber] = [question, 'undefined']
        self.database[question] = 'undefined'
        self.questionnumber += 1
        if datatype == 'boolean':
            self.btn_grp.append(QButtonGroup())
            radiobutton = []
            for choice in range(len(choices)):
                radiobutton.append(QRadioButton(choices[choice]))
                # radiobutton.toggled.connect(self.on_radio_button_toggled)
                self.layout.addWidget(radiobutton[choice], self.row, choice+1)
                radiobutton[choice].toggled.connect(lambda: self.writeanswer(question,choices[choice]))
                # if self.radiobuttonlist[self.radiobuttons].isChecked:
                #     self.database[self.questionnumber] = [question, choices[choice]]
                #     print(choices[choice])

                self.btn_grp[self.radioquestions].setExclusive(True)
                self.btn_grp[self.radioquestions].addButton(radiobutton[choice])
                self.radiobuttons += 1
            self.radioquestions += 1

            # for choice in range(len(choices)):
            #     self.radiobuttonlist.append(QRadioButton(choices[choice]))
            #     # radiobutton.toggled.connect(self.on_radio_button_toggled)
            #     self.layout.addWidget(self.radiobuttonlist[-1], self.row, choice+1)
            #     self.radiobuttonlist[self.radiobuttons].toggled.connect(self.writeanswer,)
            #     # if self.radiobuttonlist[self.radiobuttons].isChecked:
            #     #     self.database[self.questionnumber] = [question, choices[choice]]
            #     #     print(choices[choice])
            #
            #     self.btn_grp[self.radioquestions].setExclusive(True)
            #     self.btn_grp[self.radioquestions].addButton(self.radiobuttonlist[-1])
            #     self.radiobuttons += 1
            # self.radioquestions += 1

        elif datatype == 'Num':
            self.textbox = QLineEdit(self)
            self.textbox.resize(280, 40)
            self.layout.addWidget(self.textbox, self.row, 1)

        self.row += 1

    def writeanswer(self,question,answer):
        self.database[question] = answer

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

        for key in self.database.keys():
            file.write(key+str(self.database[key])+"\n")
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