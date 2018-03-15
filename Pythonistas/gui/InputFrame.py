from PyQt5 import QtWidgets
from PyQt5 import QtCore


class InputFrame(QtWidgets.QFrame):
    parseIsPressed = QtCore.pyqtSignal(str,str)

    def __init__(self):
        super(InputFrame, self).__init__()
        self.inputlayout = QtWidgets.QGridLayout()
        self.setLayout(self.inputlayout)
        self.resize(10000,100)

        # Creates textbox for QL input
        self.inputlayout.addWidget(QtWidgets.QLabel("Input your QL text here"))
        self.qlInput = QtWidgets.QTextEdit()
        self.inputlayout.addWidget(self.qlInput)

        # Creates textbox for QLS input
        self.inputlayout.addWidget(QtWidgets.QLabel("Input your QLS text here"))
        self.qlsInput = QtWidgets.QTextEdit()
        self.inputlayout.addWidget(self.qlsInput)

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
        # Sends a signal to MainWindow, together with the text from the input windows.
        # The signal triggers the parsing of the sent text.
        qlText = self.qlInput.toPlainText()
        qlsText = self.qlsInput.toPlainText()
        self.parseIsPressed.emit(qlText,qlsText)
