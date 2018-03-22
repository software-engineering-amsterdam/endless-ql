from gui.main_window import MainWindow
from PyQt5.QtWidgets import QApplication
from sys import argv
from sys import exit


if __name__ == '__main__':
    app = QApplication(argv)
    ex = MainWindow()
    exit(app.exec_())
