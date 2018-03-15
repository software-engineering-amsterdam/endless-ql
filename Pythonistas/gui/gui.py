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
import sys


from gui.InputFrame import InputFrame
from gui.OutputFrame import OutputFrame


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

        self.inputFrame = InputFrame()
        self.outputFrame = OutputFrame()
        self.splitter = QtWidgets.QSplitter(QtCore.Qt.Horizontal)
        self.splitter.addWidget(self.inputFrame)
        self.splitter.addWidget(self.outputFrame)
        self.layout.addWidget(self.splitter)

        self.inputFrame.createOutputFrame.connect(self.initiate_outputFrame)
        self.inputFrame.createOutputFrame.connect(self.parse)

    def initiate_outputFrame(self):
        # Removes the old outputFrame from splitter, and resets related parameters
        self.outputFrame.setParent(None)
        self.outputFrame.destroy()

        # Reinitializes outputframe
        self.outputFrame = OutputFrame()

        self.splitter.addWidget(self.outputFrame)

    def parse(self,qlText,qlsText):
        if qlText:
            self.tree = run_antlr(qlText)
            listen(self.tree, self.outputFrame)
            # self.build_gui(self.tree)
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
            self.no_tree_message()

    # def build_gui(self, tree):
    #     listen(tree, self.outputFrame)


if __name__ == '__main__':
    app = QtWidgets.QApplication(sys.argv)
    ex = MainWindow()
    ex.show()
    sys.exit(app.exec_())