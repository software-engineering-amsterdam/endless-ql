from PyQt5.QtWidgets import *
import sys
import parse
import ast

class Window(QWidget):
    def __init__(self):
        super(Window,self).__init__()
        self.layout = QGridLayout()
        self.setLayout(self.layout)
        self.row = 0
        self.radioquestions = 0
        self.btn_grp = []


    def buildExample(self):
        ''' Add specific questions and buttons to the window, and sets several other window parameters'''
        # self.putinbuttons()
        self.putQuestion("Cake?")
        self.putQuestion("male?")
        self.setGeometry(300, 300, 300, 200)
        self.setWindowTitle('testing')

        qbtn = QPushButton('Quit', self)
        qbtn.clicked.connect(QApplication.instance().quit)
        qbtn.resize(qbtn.sizeHint())
        self.layout.addWidget(qbtn, self.row,2)

    def putQuestion(self,question, datatype='boolean',choices = ['Yes','No']):
        ''' Example function that adds a question and radiobuttons to a gui
        '''
        buttonquestion = QLabel(question)
        self.layout.addWidget(buttonquestion, self.row, 0)

        if datatype == 'boolean':
            self.btn_grp.append(QButtonGroup())
            radiobutton = []
            for choice in range(len(choices)):
                radiobutton.append(QRadioButton(choices[choice]))
                # radiobutton.toggled.connect(self.on_radio_button_toggled)
                self.layout.addWidget(radiobutton[choice], self.row, choice+1)
                self.btn_grp[self.radioquestions].setExclusive(True)
                self.btn_grp[self.radioquestions].addButton(radiobutton[choice])
            self.radioquestions += 1

        elif datatype == 'Num':
            self.textbox = QLineEdit(self)
            self.textbox.resize(280, 40)
            self.layout.addWidget(self.textbox, self.row, 1)


        self.row += 1

    def putButtons(self):
        ''' Example function that adds buttons to a gui
        '''
        sometext = QLabel("Some text")
        self.layout.addWidget(sometext,0,0)

        sometext = QLabel("cake or pie")
        self.layout.addWidget(sometext,1,0)

        radiobutton1 = QRadioButton("Brazil")
        radiobutton1.setChecked(True)
        radiobutton1.country = "Brazil"
        # radiobutton.toggled.connect(self.on_radio_button_toggled)
        self.layout.addWidget(radiobutton1, 0, 1)

        radiobutton2 = QRadioButton("Argentina")
        radiobutton2.country = "Argentina"
        # radiobutton.toggled.connect(self.on_radio_button_toggled)
        self.layout.addWidget(radiobutton2, 0, 2)

        radiobutton3 = QRadioButton("Ecuador")
        radiobutton3.country = "Ecuador"
        # radiobutton.toggled.connect(self.on_radio_button_toggled)
        self.layout.addWidget(radiobutton3, 0, 3)

        self.btn_grp = QButtonGroup()
        self.btn_grp.setExclusive(True)
        self.btn_grp.addButton(radiobutton1)
        self.btn_grp.addButton(radiobutton2)
        self.btn_grp.addButton(radiobutton3)

        pie = QRadioButton("pie")
        pie.setChecked(True)
        pie.country = "Brazil"
        # radiobutton.toggled.connect(self.on_radio_button_toggled)
        self.layout.addWidget(pie, 1, 1)

        cake = QRadioButton("cake")
        cake.country = "Argentina"
        # radiobutton.toggled.connect(self.on_radio_button_toggled)
        self.layout.addWidget(cake, 1, 2)

        self.btn_grp = QButtonGroup()
        self.btn_grp.setExclusive(True)
        self.btn_grp.addButton(pie)
        self.btn_grp.addButton(cake)

        qbtn = QPushButton('Quit', self)
        qbtn.clicked.connect(QApplication.instance().quit)
        qbtn.resize(qbtn.sizeHint())
        self.layout.addWidget(qbtn, 2,3)


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