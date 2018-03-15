"""
This file contains two widget windows. If this file is run, the first, InputWindow, opens.
In this window QL text can be typed or pasted. When pressing the "Parse" button, this text
is parsed, and a second window, OutputWindow opens. The Outputwindow contains an interactive
 questionnaire, encoded by the input text.
"""
# import visitor.visitor as visitor_script
from visitor.listener import listen
from PyQt5 import QtWidgets
from PyQt5 import QtCore
from grammar.run_antlr import run_antlr
from grammar.data_structure import ParserCarrier
import sys
from gui.input_frame import InputFrame
from gui.output_frame import OutputFrame


class MainWindow(QtWidgets.QWidget):
    def __init__(self):
        super(MainWindow, self).__init__()
        # Parses QL input
        self.layout = QtWidgets.QVBoxLayout()
        self.layout.setSpacing(10)
        self.setLayout(self.layout)
        self.setWindowTitle('QL parser')
        self.setGeometry(600, 600, 1100, 600)
        self.tree = None
        self.parser = None

        # Initiates frames for within the window, and adds them via a splitter widget.
        self.inputFrame = InputFrame()
        self.outputFrame = OutputFrame()

        self.splitter = QtWidgets.QSplitter(QtCore.Qt.Horizontal)
        self.splitter.addWidget(self.inputFrame)
        self.splitter.addWidget(self.outputFrame)
        self.layout.addWidget(self.splitter)

        # When the signal parseIsPressed is given by inputFrame, MainWindow takes necessary actions to parse
        self.inputFrame.parseIsPressed.connect(self.initiate_outputFrame)
        self.inputFrame.parseIsPressed.connect(self.parse)

    def initiate_outputFrame(self):
        # Removes the old outputFrame from the window
        self.outputFrame.setParent(None)
        self.outputFrame.destroy()

        # Reinitializes outputframe
        self.outputFrame = OutputFrame()

        self.splitter.addWidget(self.outputFrame)

    def parse(self, ql_text, qls_text):
        ql_data = ParserCarrier()

        if ql_text:
            ql_data.set_ql_grammar_text(ql_text)
            ql_data.run_antlr_ql()
            listen(ql_data.ql_tree, self.outputFrame)
            self.outputFrame.add_submit_button()
            # if self.tree:
            #     self.build_gui(self.tree)
            # else:
            #     self.outputWindow.no_tree_label()
            # self.outputWindow.add_submit_button()
            # print('below is tree')
            # print(type(self.tree))
            # print((self.tree))
            # print(self.tree.depth())
            # elif self.tree.depth() > 1:
        else:
            self.outputFrame.no_tree_message()

        if qls_text:
            ql_data.set_qls_grammar_text(ql_text)
            ql_data.run_antlr_qls()
            # todo: create listener/visiter for QLS
            # listen(ql_data.qls_tree, self.outputFrame)
            # self.outputFrame.add_submit_button()
        else:
            self.outputFrame.no_tree_message()


if __name__ == '__main__':
    app = QtWidgets.QApplication(sys.argv)
    ex = MainWindow()
    ex.show()
    sys.exit(app.exec_())