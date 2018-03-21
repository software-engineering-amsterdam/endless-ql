from termcolor import colored
from PyQt5.QtWidgets import QMessageBox


class Debug:
    def __init__(self, terminal=True, pyqt5=True):
        self.__terminal = terminal
        self.__pyqt5 = pyqt5

    @property
    def terminal(self):
        return self.__terminal

    @property
    def pyqt5(self):
        return self.__pyqt5

    def error(self, lines, message):
        separated_lines = ' , '.join([str(line) for line in lines])
        if self.pyqt5:
            QMessageBox.critical(QMessageBox(), 'Error', '[line(s): {}] {}'.format(lines, message),
                                 QMessageBox.Close, QMessageBox.Escape)
        if self.terminal:
            tag = colored('[error]', 'red')
            print('{} [line(s): {}] {}'.format(tag, separated_lines, message))
            exit()

    def warning(self, lines, message):
        separated_lines = ' , '.join([str(line) for line in lines])
        if self.pyqt5:
            QMessageBox.critical(QMessageBox(), 'Warning', '[line(s): {}] {}'.format(lines, message),
                                 QMessageBox.Close, QMessageBox.Escape)
        if self.terminal:
            tag = colored('[warning]', 'yellow')
            print('{} [line(s): {}] {}'.format(tag, separated_lines, message))
