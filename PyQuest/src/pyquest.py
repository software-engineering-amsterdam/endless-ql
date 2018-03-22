from gui.app import MainApp
from PyQt5.QtWidgets import QApplication
from sys import argv
from sys import exit


if __name__ == '__main__':
    app = QApplication(argv)
    ex = MainApp()
    exit(app.exec_())
