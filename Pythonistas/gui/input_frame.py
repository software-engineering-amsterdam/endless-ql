"""
This file contains InputFrame, a frame for the MainWindow in gui.py. In InputFrame there is a ql_input textbox, in which
Questionnaire Language (QL) text can be pasted, which can then be parsed into an interactive questionnaire by pressing
the "Parse" button. Further, this frame contains a "Quit" button, which closes the MainWindow.
"""
import sys
from PyQt5 import QtWidgets, QtCore, QtGui


class InputFrame(QtWidgets.QFrame):
    parse_is_pressed = QtCore.pyqtSignal(str, str)

    def __init__(self):
        super(InputFrame, self).__init__()
        self.input_layout = QtWidgets.QGridLayout()
        self.setLayout(self.input_layout)
        self.resize(800, 600)
        self.setMinimumWidth(500)

        # Creates textbox for QL input
        self.input_layout.addWidget(QtWidgets.QLabel("Input your QL text here"), 0, 0)
        self.ql_input = QtWidgets.QTextEdit()
        self.input_layout.addWidget(self.ql_input, 1, 0, 9, 5)

        # Creates textbox for QLS input
        self.input_layout.addWidget(QtWidgets.QLabel("Input your QLS text here"), 10, 0)
        self.qls_input = QtWidgets.QTextEdit()
        self.input_layout.addWidget(self.qls_input, 11, 0, 9, 5)

        # Adds parse button
        self.parse_button = QtWidgets.QPushButton('Parse', self)
        self.parse_button.clicked.connect(self.on_parse)
        self.input_layout.addWidget(self.parse_button, 20, 3)

        # Adds quit button
        self.quit_button = QtWidgets.QPushButton('Quit', self)
        self.quit_button.clicked.connect(QtWidgets.QApplication.instance().quit)
        self.input_layout.addWidget(self.quit_button, 20, 4)

    def on_parse(self):
        # Emit input to parsing
        ql_text = self.ql_input.toPlainText()
        qls_text = self.qls_input.toPlainText()
        self.parse_is_pressed.emit(ql_text, qls_text)
