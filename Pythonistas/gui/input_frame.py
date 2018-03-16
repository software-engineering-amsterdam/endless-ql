from PyQt5 import QtWidgets
from PyQt5 import QtCore
from PyQt5 import QtGui
import sys

class InputFrame(QtWidgets.QFrame):
    parseIsPressed = QtCore.pyqtSignal(str,str)

    def __init__(self):
        super(InputFrame, self).__init__()
        self.inputlayout = QtWidgets.QGridLayout()
        self.setLayout(self.inputlayout)
        self.resize(800,600)

        # Creates textbox for QL input, at specified positions
        self.inputlayout.addWidget(QtWidgets.QLabel("Input your QL text here"),0,0)
        self.qlInput = QtWidgets.QTextEdit()
        self.inputlayout.addWidget(self.qlInput,1,0,9,5)

        # Creates textbox for QLS input, at specified positions
        self.inputlayout.addWidget(QtWidgets.QLabel("Input your QLS text here"),10,0)
        self.qlsInput = QtWidgets.QTextEdit()
        self.inputlayout.addWidget(self.qlsInput,11,0,9,5)

        # Adds parse button, at specified positions
        self.parsebutton = QtWidgets.QPushButton('Parse', self)
        self.parsebutton.clicked.connect(self.on_parse)
        self.parsebutton.resize(self.parsebutton.sizeHint())
        self.inputlayout.addWidget(self.parsebutton,20,3)

        # Adds quit button, at specified positions
        self.quitbutton = QtWidgets.QPushButton('Quit', self)
        self.quitbutton.clicked.connect(self.close)
        self.quitbutton.clicked.connect(QtWidgets.QApplication.instance().quit)  # todo: check for redundancy
        self.quitbutton.resize(self.quitbutton.sizeHint())
        self.inputlayout.addWidget(self.quitbutton,20,4)

    def on_parse(self):
        # Sends a signal to MainWindow, together with the text from the input windows.
        # The signal triggers the parsing of the sent text.
        qlText = self.qlInput.toPlainText()
        qlsText = self.qlsInput.toPlainText()
        self.parseIsPressed.emit(qlText,qlsText)

if __name__ == '__main__':
    app = QtWidgets.QApplication(sys.argv)
    inputFrame = InputFrame()
    inputFrame.show()
    sys.exit(app.exec_())