from PyQt5.QtWidgets import QTextEdit
from PyQt5.QtWidgets import QFileDialog
from PyQt5.QtWidgets import QMainWindow
from PyQt5.QtWidgets import QApplication
from PyQt5.QtWidgets import QPushButton
from PyQt5.QtWidgets import QAction
from PyQt5.QtWidgets import QMessageBox

from PyQt5.QtGui import QIcon

from PyQt5.QtCore import pyqtSlot

from screeninfo import get_monitors

import sys

class MainApp(QMainWindow):

    def __init__(self):
        super().__init__()

        # calculate proper window width and position on screen
        self.title = 'QL PyQuest'
        self.width = get_monitors()[0].width / 2
        self.height = get_monitors()[0].height / 2
        self.left = (get_monitors()[0].width - self.width) / 2
        self.top = (get_monitors()[0].height - self.height) / 2

        self.offset = 10
        self.button_size = 40

        # start app
        self.initUI()

    def initUI(self):
        self.setWindowTitle(self.title)
        self.setGeometry(self.left, self.top, self.width, self.height)

        # add option for opening a new file
        open_file = QAction(QIcon('open.png'), 'Open', self)
        open_file.setShortcut('Ctrl+O')
        open_file.setStatusTip('Open new File')
        open_file.triggered.connect(self.showOpenFileDialog)

        # add menu bar with file option and connect with open file function
        menubar = self.menuBar()
        fileMenu = menubar.addMenu('&File')
        fileMenu.addAction(open_file)

        # Create textbox
        self.textbox = QTextEdit(self)
        self.textbox.move(self.offset, self.offset)
        self.textbox.resize(self.width - self.offset*2, self.height - self.offset*2 - self.button_size)

        # Create a button in the window
        self.button = QPushButton('Create Form', self)
        self.button.move(self.offset, self.height - self.button_size)

        # connect button to function on_click
        self.button.clicked.connect(self.on_click)
        self.show()

    @pyqtSlot()
    def on_click(self):
        textboxValue = self.textbox.toPlainText()
        QMessageBox.question(self, 'Form Created!', "You typed: " + textboxValue, QMessageBox.Ok,
                             QMessageBox.Ok)
        self.textbox.setText("")

    # opens a .txt file and fills the text field with its content
    def showOpenFileDialog(self):
        file_name = QFileDialog.getOpenFileName(self, 'Open file', '/home', "QL files (*.ql)")[0]
        if file_name:
            ql_script = open(file_name, 'r').read()
            self.textbox.setText(ql_script)


if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = MainApp()
    sys.exit(app.exec_())