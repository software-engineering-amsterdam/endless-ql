from PyQt5.QtWidgets import QMainWindow
from PyQt5.QtWidgets import QTextEdit
from PyQt5.QtWidgets import QAction
from PyQt5.QtWidgets import QFileDialog
from PyQt5.QtWidgets import QApplication

from PyQt5.QtGui import QIcon

import sys

class MainApp(QMainWindow):

    def __init__(self):
        super().__init__()
        self.initUI()

    def initUI(self):
        # add text field as main widget
        self.textEdit = QTextEdit()
        self.setCentralWidget(self.textEdit)

        # add option for opening a new file
        openFile = QAction(QIcon('open.png'), 'Open', self)
        openFile.setShortcut('Ctrl+O')
        openFile.setStatusTip('Open new File')
        openFile.triggered.connect(self.showOpenFileDialog)

        # add menu bar with file option and connect with open file function
        menubar = self.menuBar()
        fileMenu = menubar.addMenu('&File')
        fileMenu.addAction(openFile)

        # set title and dimensions of window, and display it
        self.setGeometry(300, 300, 350, 300)
        self.setWindowTitle('PyQuest QL')
        self.show()

    # opens a .txt file and fills the text field with its content
    def showOpenFileDialog(self):
        file_name = QFileDialog.getOpenFileName(self, 'Open file', '/home', "Text files (*.txt)")[0]
        if file_name:
            ql_script = open(file_name, 'r').read()
            self.textEdit.setText(ql_script)

if __name__ == '__main__':
    app = QApplication(sys.argv)
    app_window = MainApp()
    sys.exit(app.exec_())

