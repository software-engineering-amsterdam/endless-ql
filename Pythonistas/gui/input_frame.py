from PyQt5 import QtWidgets
from PyQt5 import QtCore
from PyQt5 import QtGui
import sys

class InputFrame(QtWidgets.QFrame):
    parse_is_pressed = QtCore.pyqtSignal(str,str)

    def __init__(self):
        super(InputFrame, self).__init__()
        self.input_layout = QtWidgets.QGridLayout()
        self.setLayout(self.input_layout)
        self.resize(800,600)
        self.setMinimumWidth(500)

        # Creates textbox for QL input, at specified positions
        self.input_layout.addWidget(QtWidgets.QLabel("Input your QL text here"),0,0)
        self.ql_input = QtWidgets.QTextEdit()
        self.input_layout.addWidget(self.ql_input,1,0,9,5)

        # Creates textbox for QLS input, at specified positions
        self.input_layout.addWidget(QtWidgets.QLabel("Input your QLS text here"),10,0)
        self.qls_input = QtWidgets.QTextEdit()
        self.input_layout.addWidget(self.qls_input,11,0,9,5)

        # Adds parse button, at specified positions
        self.parse_button = QtWidgets.QPushButton('Parse', self)
        self.parse_button.clicked.connect(self.on_parse)
        self.parse_button.resize(self.parse_button.sizeHint())
        self.input_layout.addWidget(self.parse_button,20,3)

        # Adds quit button, at specified positions
        self.quit_button = QtWidgets.QPushButton('Quit', self)
        self.quit_button.clicked.connect(self.close)
        self.quit_button.clicked.connect(QtWidgets.QApplication.instance().quit)  # todo: check for redundancy
        self.quit_button.resize(self.quit_button.sizeHint())
        self.input_layout.addWidget(self.quit_button,20,4)

    def on_parse(self):
        # Sends a signal to MainWindow, together with the text from the input windows.
        # The signal triggers the parsing of the sent text.
        ql_text = self.ql_input.toPlainText()
        qls_text = self.qls_input.toPlainText()
        self.parse_is_pressed.emit(ql_text,qls_text)

if __name__ == '__main__':
    app = QtWidgets.QApplication(sys.argv)
    input_frame = InputFrame()
    input_frame.show()
    sys.exit(app.exec_())