from PyQt5.QtWidgets import *
import sys

class Window(QWidget):
    def __init__(self):
        super(Window,self).__init__()
        self.setGeometry(300, 300, 300, 200)
        self.setWindowTitle('testing')
        self.layout = QGridLayout()
        self.setLayout(self.layout)
        self.row = 0

        # Add questions and buttons to the window.
        # self.putinbuttons()
        self.putquestion("Cake?")
        self.putquestion("male?")


        qbtn = QPushButton('Quit', self)
        qbtn.clicked.connect(QApplication.instance().quit)
        qbtn.resize(qbtn.sizeHint())
        self.layout.addWidget(qbtn, self.row,2)

    def putquestion(self,question):
        buttonquestion = QLabel(question)
        self.layout.addWidget(buttonquestion, self.row, 0)

        radiobutton1 = QRadioButton("Yes")
        # radiobutton.toggled.connect(self.on_radio_button_toggled)
        self.layout.addWidget(radiobutton1, self.row, 1)

        radiobutton2 = QRadioButton("No")
        radiobutton2.setChecked(True)
        # radiobutton.toggled.connect(self.on_radio_button_toggled)
        self.layout.addWidget(radiobutton2, self.row, 2)

        self.btn_grp = QButtonGroup()
        self.btn_grp.setExclusive(True)
        self.btn_grp.addButton(radiobutton1)
        self.btn_grp.addButton(radiobutton2)

        self.row += 1

    def putinbuttons(self):

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