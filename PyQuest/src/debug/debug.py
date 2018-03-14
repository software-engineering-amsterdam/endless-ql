from termcolor import colored
from PyQt5.QtWidgets import QMessageBox


def error(line, message):
    tag = colored('[error]', 'red')
    print('{} [line:{}] {}'.format(tag, line, message))
    exit()


def warning(line, message):
    tag = colored('[warning]', 'yellow')
    print('{} [line:{}] {}'.format(tag, line, message))


def pyqt5_error(line, message):
    QMessageBox.critical(QMessageBox(), 'Warning', '[line:{}] {}'.format(line, message),
                         QMessageBox.Close, QMessageBox.Escape)


def pyqt5_warning(line, message):
    QMessageBox.warning(QMessageBox(), 'Warning', '[line:{}] {}'.format(line, message),
                        QMessageBox.Close, QMessageBox.Escape)
